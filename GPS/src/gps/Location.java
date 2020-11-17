package gps;


public class Location {
    private final double latitude, longitude;

    public Location(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.latitude + ", " + this.longitude;
    }
}
