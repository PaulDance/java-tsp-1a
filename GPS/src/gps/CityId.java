package gps;


public class CityId {
    private final String name;

    public CityId(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof CityId && this.name.equals(((CityId) obj).name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
