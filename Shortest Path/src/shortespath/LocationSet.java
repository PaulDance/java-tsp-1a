package shortespath;


public class LocationSet {												// Defines a set structure containing Location objects.
	Location[] locations;										// Uses an extensible array as the source of memory.
	int numLocations;											// To simulate the list's length.
	
	public LocationSet() {
		this.locations = new Location[4];
		this.numLocations = 0;
	}
	
	public boolean isEmpty() {									// Returns true if and only if the set does not contain any element, i.e. non null objects.
		for (int i = 0; i < this.numLocations; i++) {			// Used in path search.
			if (this.locations[i] != null) {
				return false;
			}
		}
		return this.numLocations == 0;
	}
	
	public void add(Location location) {						// Adds the given <location> at the end of the array,
		if (this.numLocations == this.locations.length) {		// and manages the extensibility if required.
			Location[] tmp = new Location[2 * this.numLocations];
			
			for (int i = 0; i < this.numLocations; i++) {
				tmp[i] = this.locations[i];
			}
			
			this.locations = tmp;
		}
		
		this.locations[this.numLocations] = location;
		this.numLocations += 1;
	}
	
	public void add(Location... locations) {					// Adds multiple locations.
		for (int i = 0; i < locations.length; i++) {
			this.add(locations[i]);
		}
	}
	
	public void display() {										// Debug display.
		System.out.println("locations.length: " + this.locations.length);
		System.out.println("numLocations: " + this.numLocations);
		System.out.println("locations:");
		
		for (int i = 0; i < this.numLocations; i++) {
			this.locations[i].display();
		}
	}
	
	public Location removeMin() {								// Searches for the element having the minimum distance, removes it from the array and updates <numLocations>.
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
		}
		else {
			return null;
		}
	}
	
	public void clear() {										// Empties out completely the array, but preserves the same RAM space.
		for (int i = 0; i < this.numLocations; i++) {
			this.locations[i] = null;
		}
		this.numLocations = 0;
	}
	
	public void become(LocationSet newSet) {					// Overwrites <this>'s elements by <newSet>'s. Enables LocationSet copying.
		this.numLocations = newSet.numLocations;
		this.locations = new Location[newSet.locations.length];
		
		for (int i = 0; i < newSet.numLocations; i++) {
			this.locations[i] = newSet.locations[i];
		}
	}
}
