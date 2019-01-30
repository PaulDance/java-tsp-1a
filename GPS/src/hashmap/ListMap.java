package hashmap;


public class ListMap<K, V> implements Map<K, V> {
	private K key;
	private V value;
	private ListMap<K, V> next, prev;
	
	public ListMap(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = this;
		this.prev = this;
	}
	
	public ListMap(K key, V value, ListMap<K, V> next, ListMap<K, V> prev) {
		this.key = key;
		this.value = value;
		this.next = next;
		this.prev = prev;
	}
	
	public String toString() {
		return "(" + this.key.toString() + ", " + this.value.toString() + ")";
	}
	
	public void display() {
		System.out.println("List: ");
		ListMap<K, V> currentNode = this.next;
		
		while (currentNode != this) {
			System.out.println("\t" + currentNode.toString());
			currentNode = currentNode.next;
		}
	}
	
	private void append(K key, V value) {
		ListMap<K, V> newNode = new ListMap<K, V>(key, value);
		newNode.next = this.next;
		newNode.prev = this;
		this.next.prev = newNode;
		this.next = newNode;
	}
	
	private ListMap<K, V> lookupNode(K key) {
		ListMap<K, V> currentNode = this.next;
		
		while (currentNode != this) {
			if (currentNode.key.equals(key)) {
				return currentNode;
			}
			currentNode = currentNode.next;
		}
		
		return null;
	}
	
	public V get(K key) {
		ListMap<K, V> keyNode = this.lookupNode(key);
		
		if (keyNode == null) {
			return null;
		}
		else {
			return keyNode.value;
		}	
	}
	
	public boolean remove(K key) {
		ListMap<K, V> keyNode = this.lookupNode(key);
		
		if (keyNode == null) {
			return false;
		}
		else {
			keyNode.prev.next = keyNode.next;
			keyNode.next.prev = keyNode.prev;
			keyNode.prev = null;
			keyNode.next = null;
			return true;
		}
	}
	
	public boolean put(K key, V value) {
		ListMap<K, V> keyNode = this.lookupNode(key);
		
		if (keyNode == null) {
			this.append(key, value);
			return true;
		}
		else {
			keyNode.value = value;
			return false;
		}
	}
}
