package shortespath;

/**
 * Defines a set structure containing Location objects.
 * 
 * @author Paul Mabileau
 */
public class LocationSet {
    /**
     * Uses an extensible array as the source of memory.
     */
    Location[] locations;
    /**
     * To simulate the list's length.
     */
    int numLocations;

    public LocationSet() {
        this.locations = new Location[4];
        this.numLocations = 0;
    }

    /**
     * Returns true if and only if the set does not contain any element, i.e.
     * non null objects.
     */
    public boolean isEmpty() {
        for (int i = 0; i < this.numLocations; i++) {
            if (this.locations[i] != null) {
                return false;
            }
        }
        return this.numLocations == 0;
    }

    /**
     * Adds the given <location> at the end of the array, and manages the
     * extensibility if required.
     */
    public void add(final Location location) {
        if (this.numLocations == this.locations.length) {
            final Location[] tmp = new Location[2 * this.numLocations];

            for (int i = 0; i < this.numLocations; i++) {
                tmp[i] = this.locations[i];
            }

            this.locations = tmp;
        }

        this.locations[this.numLocations] = location;
        this.numLocations += 1;
    }

    /**
     * Adds multiple locations.
     */
    public void add(final Location... locations) {
        for (final Location location: locations) {
            this.add(location);
        }
    }

    /**
     * Debug display.
     */
    public void display() {
        System.out.println("locations.length: " + this.locations.length);
        System.out.println("numLocations: " + this.numLocations);
        System.out.println("locations:");

        for (int i = 0; i < this.numLocations; i++) {
            this.locations[i].display();
        }
    }

    /**
     * Searches for the element having the minimum distance, removes it from the
     * array and updates <numLocations>.
     */
    public Location removeMin() {
        if (this.numLocations != 0) {
            Location minLoc = this.locations[0];
            double minDis = minLoc.getDistance();
            int iMin = 0;

            for (int i = 1; i < this.numLocations; i++) {
                if (this.locations[i].getDistance() < minDis) {
                    minLoc = this.locations[i];
                    minDis = minLoc.getDistance();
                    iMin = i;
                }
            }

            for (int i = iMin; i < this.numLocations - 1; i++) {
                this.locations[i] = this.locations[i + 1];
            }

            this.numLocations -= 1;
            return minLoc;
        } else {
            return null;
        }
    }

    /**
     * Empties out completely the array, but preserves the same RAM space.
     */
    public void clear() {
        for (int i = 0; i < this.numLocations; i++) {
            this.locations[i] = null;
        }
        this.numLocations = 0;
    }

    /**
     * Overwrites <this>'s elements by <newSet>'s. Enables LocationSet copying.
     */
    public void become(final LocationSet newSet) {
        this.numLocations = newSet.numLocations;
        this.locations = new Location[newSet.locations.length];

        for (int i = 0; i < newSet.numLocations; i++) {
            this.locations[i] = newSet.locations[i];
        }
    }
}
