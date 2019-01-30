package shortespath;
import java.util.Iterator;
import java.util.LinkedList;


public class Location {									// Defines a vertex representing a city using a name and geodesic coordinates.
	private String name;							// This structure can be used to implement graphs by calling setNeighbors.
	private double latitude, longitude, distance;
	private Location[] neighbors;					// The attribute <neighbors> is used to make the graph edges.
	private Location from;							// <distance> and <from> are specifically meant for the path search.
	
	public Location(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = Double.POSITIVE_INFINITY;
		this.from = null;
	}
	
	public Location(String name, double latitude, double longitude, Location... neighbors) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.neighbors = neighbors;
		this.distance = Double.POSITIVE_INFINITY;
		this.from = null;
	}
	
	public void display() {								// Standard display method.
		System.out.println(this.name + ": (" + this.latitude + ", " + this.longitude + ")");
	}
	
	private void distanceDisplay() {				// Display used only in the path search.
		System.out.println("\t" + this.name + " at " + this.distance + " km");
	}
	
	public double distanceTo(Location city) {				// Computes the length of the 3D arc going from Location <this> to <city> on the Earth' surface.
		double lat1 = (Math.PI * this.latitude) / 180, lat2 = (Math.PI * city.latitude) / 180, long1 = (Math.PI * this.longitude) / 180, long2 = (Math.PI * city.longitude) / 180;
		return 6378 * (Math.PI / 2 - Math.asin(Math.sin(lat2) * Math.sin(lat1) + Math.cos(long2 - long1) * Math.cos(lat2) * Math.cos(lat1)));
	}
	
	public void setNeighbors(Location... neighbors) {		// Sets <this> current vertex' neighboring Locations to the given <neighbors> Location array.
		this.neighbors = neighbors;
	}
	
	public double getDistance() {							// Getter for path distance from starting point.
		return this.distance;
	}
	
	
	public LinkedList<Location> pathTo(Location city) {	// Uses Dijkstra's graph path search algorithm to compute the shortest path from <this> to <city>.
		LocationSet toVisit = new LocationSet(), newToVisit = new LocationSet();
		toVisit.add(this);							// <toVisit> is a LocationSet containing the locations that have to be visited during the current step.
		newToVisit.add(this);						// <newToVisit> is used to receive the next locations needing to be visited and give them to <toVisit> after a step.
		this.distance = 0;							// Distance from start to start: 0.
		
		while (!toVisit.isEmpty()) {														// While there are still some locations to visit,
			for (int i = 0; i < toVisit.numLocations; i++) {								// we visit them one by one,
				if (toVisit.locations[i].neighbors != null) {								// and if the current location has neighbors,
					for (int j = 0; j < toVisit.locations[i].neighbors.length; j++) {		// we go through them also;
						if (toVisit.locations[i] != city									// if the accumulated distance to the current neighbor is smaller than previously,
								&& toVisit.locations[i].distance + toVisit.locations[i].distanceTo(toVisit.locations[i].neighbors[j]) < toVisit.locations[i].neighbors[j].distance) {
							toVisit.locations[i].neighbors[j].distance = toVisit.locations[i].distance + toVisit.locations[i].distanceTo(toVisit.locations[i].neighbors[j]);
							toVisit.locations[i].neighbors[j].from = toVisit.locations[i];	// we update its parameters: the distance is corrected, the path now goes through it,
							newToVisit.add(toVisit.locations[i].neighbors[j]);				// and it needs to be visited during next step.
						}
					}
				}
			}
			toVisit.become(newToVisit);				// After each of every available location's neighbors has been processed,
			newToVisit.clear();						// the next round of locations is loaded.
		}
		
		Location exploreLoc = city;					// The following part is to extract the path from the links built step by step.
		LinkedList<Location> shortestPath = new LinkedList<Location>();
		
		while (exploreLoc != null) {
			shortestPath.addFirst(exploreLoc);
			exploreLoc = exploreLoc.from;
		}
		
		return shortestPath;
	}
	
	public void printPathTo(Location city) {				// Displays the shortest path nicely. 
		Iterator<Location> pathIterator = this.pathTo(city).iterator();
		System.out.println("The path from " + this.name + " to " + city.name + " is:");
		
		while (pathIterator.hasNext()) {
			pathIterator.next().distanceDisplay();
		}
		
		this.resetPathFrom();
	}
	
	private void resetPathFrom() {
		this.distance = Double.POSITIVE_INFINITY;
		this.from = null;
		
		for (int i = 0; i < this.neighbors.length; i++) {
			if (this.neighbors[i].distance != Double.POSITIVE_INFINITY || this.neighbors[i].from != null) {
				this.neighbors[i].resetPathFrom();
			}
		}
	}
}
