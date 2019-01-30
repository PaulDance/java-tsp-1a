package hashmap;


public class HashMap<K, V> implements Map<K, V> {
	private Map<K, V>[] maps;
	
	@SuppressWarnings("unchecked")
	public HashMap() {
		this.maps = new Map[10];
		
		for (int i = 0; i < this.maps.length; i++) {
			this.maps[i] = new ListMap<K, V>(null, null);
		}
	}
	
	public boolean put(K key, V value) {
		return this.getList(key).put(key, value);
	}

	public V get(K key) {
		return this.getList(key).get(key);
	}

	public boolean remove(K key) {
		return this.getList(key).remove(key);
	}

	public void display() {
		for (int i = 0; i < this.maps.length; i++) {
			System.out.print("[" + i + "]  ");
			this.maps[i].display();
			System.out.println();
		}
	}
	
	private Map<K, V> getList(K key) {
		int h = key.hashCode();
		int r = h - this.maps.length * (h / this.maps.length);
		
		if (r < 0) {
			return this.maps[r + this.maps.length];
		}
		else {
			return this.maps[r];
		}
	}
}
