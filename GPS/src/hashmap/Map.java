package hashmap;

public interface Map<K, V> {
    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
    void display();
}
