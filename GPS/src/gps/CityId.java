package gps;


public class CityId {
	private String name;
	
	public CityId(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof CityId && this.name.equals(((CityId) obj).name);
	}
	
	public int hashCode() {
		return this.name.hashCode();
	}
}
