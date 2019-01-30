package gps;


public class Location {
	private double latitude, longitude;
	
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String toString() {
		return this.latitude + ", " + this.longitude;
	}
}
