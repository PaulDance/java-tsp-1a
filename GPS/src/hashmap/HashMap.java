package hashmap;


public class HashMap<K, V> implements Map<K, V> {
    private final Map<K, V>[] maps;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.maps = new Map[10];

        for (int i = 0; i < this.maps.length; i++) {
            this.maps[i] = new ListMap<K, V>(null, null);
        }
    }

    @Override
    public boolean put(final K key, final V value) {
        return this.getList(key).put(key, value);
    }

    @Override
    public V get(final K key) {
        return this.getList(key).get(key);
    }

    @Override
    public boolean remove(final K key) {
        return this.getList(key).remove(key);
    }

    @Override
    public void display() {
        for (int i = 0; i < this.maps.length; i++) {
            System.out.print("[" + i + "]  ");
            this.maps[i].display();
            System.out.println();
        }
    }

    private Map<K, V> getList(final K key) {
        final int h = key.hashCode();
        final int r = h - this.maps.length * (h / this.maps.length);

        if (r < 0) {
            return this.maps[r + this.maps.length];
        } else {
            return this.maps[r];
        }
    }
}
