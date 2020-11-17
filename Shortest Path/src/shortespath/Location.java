package shortespath;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Defines a vertex representing a city using a name and geodesic coordinates.
 * 
 * @author Paul Mabileau
 */
public class Location {
    /**
     * This structure can be used to implement graphs by calling setNeighbors.
     */
    private final String name;
    private final double latitude, longitude;
    private double distance;
    /**
     * The attribute <neighbors> is used to make the graph edges.
     */
    private Location[] neighbors;
    /**
     * <distance> and <from> are specifically meant for the path search.
     */
    private Location from;

    public Location(final String name, final double latitude, final double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = Double.POSITIVE_INFINITY;
        this.from = null;
    }

    public Location(final String name, final double latitude, final double longitude,
            final Location... neighbors) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.neighbors = neighbors;
        this.distance = Double.POSITIVE_INFINITY;
        this.from = null;
    }

    /**
     * Standard display method.
     */
    public void display() {
        System.out.println(this.name + ": (" + this.latitude + ", " + this.longitude + ")");
    }

    /**
     * Display used only in the path search.
     */
    private void distanceDisplay() {
        System.out.println("\t" + this.name + " at " + this.distance + " km");
    }

    /**
     * Computes the length of the 3D arc going from Location <this> to <city> on
     * the Earth' surface.
     */
    public double distanceTo(final Location city) {
        final double lat1 = Math.PI * this.latitude / 180, lat2 = Math.PI * city.latitude / 180,
                long1 = Math.PI * this.longitude / 180, long2 = Math.PI * city.longitude / 180;
        return 6378 * (Math.PI / 2 - Math.asin(Math.sin(lat2) * Math.sin(lat1)
                + Math.cos(long2 - long1) * Math.cos(lat2) * Math.cos(lat1)));
    }

    /**
     * Sets <this> current vertex' neighboring Locations to the given
     * <neighbors> Location array.
     */
    public void setNeighbors(final Location... neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Getter for path distance from starting point.
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Uses Dijkstra's graph path search algorithm to compute the shortest path
     * from <this> to <city>.
     */
    public LinkedList<Location> pathTo(final Location city) {
        final LocationSet toVisit = new LocationSet(), newToVisit = new LocationSet();
        // <toVisit> is a LocationSet containing the locations that have to be
        // visited during the current step.
        toVisit.add(this);
        // <newToVisit> is used to receive the next locations needing to be
        // visited and give them to <toVisit> after a step.
        newToVisit.add(this);
        // Distance from start to start: 0.
        this.distance = 0;

        // While there are still some locations to visit,
        while (!toVisit.isEmpty()) {
            // we visit them one by one,
            for (int i = 0; i < toVisit.numLocations; i++) {
                // and if the current location has neighbors,
                if (toVisit.locations[i].neighbors != null) {
                    // we go through them also;
                    for (int j = 0; j < toVisit.locations[i].neighbors.length; j++) {
                        // if the accumulated distance to the current neighbor
                        // is smaller than previously,
                        if (toVisit.locations[i] != city
                                && toVisit.locations[i].distance + toVisit.locations[i].distanceTo(
                                        toVisit.locations[i].neighbors[j]) < toVisit.locations[i].neighbors[j].distance) {
                            // we update its parameters: the distance is
                            // corrected, the path now goes through it, and it
                            // needs to be visited during next step.
                            toVisit.locations[i].neighbors[j].distance = toVisit.locations[i].distance
                                    + toVisit.locations[i]
                                            .distanceTo(toVisit.locations[i].neighbors[j]);
                            toVisit.locations[i].neighbors[j].from = toVisit.locations[i];
                            newToVisit.add(toVisit.locations[i].neighbors[j]);
                        }
                    }
                }
            }

            // After each of every available location's neighbors has been
            // processed, the next round of locations is loaded.
            toVisit.become(newToVisit);
            newToVisit.clear();
        }

        // The following part is to extract the path from the links built step
        // by step.
        Location exploreLoc = city;
        final LinkedList<Location> shortestPath = new LinkedList<Location>();

        while (exploreLoc != null) {
            shortestPath.addFirst(exploreLoc);
            exploreLoc = exploreLoc.from;
        }

        return shortestPath;
    }

    /**
     * Displays the shortest path nicely.
     */
    public void printPathTo(final Location city) {
        final Iterator<Location> pathIterator = this.pathTo(city).iterator();
        System.out.println("The path from " + this.name + " to " + city.name + " is:");

        while (pathIterator.hasNext()) {
            pathIterator.next().distanceDisplay();
        }

        this.resetPathFrom();
    }

    private void resetPathFrom() {
        this.distance = Double.POSITIVE_INFINITY;
        this.from = null;

        for (final Location neighbor: this.neighbors) {
            if (neighbor.distance != Double.POSITIVE_INFINITY || neighbor.from != null) {
                neighbor.resetPathFrom();
            }
        }
    }
}
