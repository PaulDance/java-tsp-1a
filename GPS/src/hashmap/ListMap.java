package hashmap;


public class ListMap<K, V> implements Map<K, V> {
    private final K key;
    private V value;
    private ListMap<K, V> next, prev;

    public ListMap(final K key, final V value) {
        this.key = key;
        this.value = value;
        this.next = this;
        this.prev = this;
    }

    public ListMap(final K key, final V value, final ListMap<K, V> next, final ListMap<K, V> prev) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "(" + this.key.toString() + ", " + this.value.toString() + ")";
    }

    @Override
    public void display() {
        System.out.println("List: ");
        ListMap<K, V> currentNode = this.next;

        while (currentNode != this) {
            System.out.println("\t" + currentNode.toString());
            currentNode = currentNode.next;
        }
    }

    private void append(final K key, final V value) {
        final ListMap<K, V> newNode = new ListMap<K, V>(key, value);
        newNode.next = this.next;
        newNode.prev = this;
        this.next.prev = newNode;
        this.next = newNode;
    }

    private ListMap<K, V> lookupNode(final K key) {
        ListMap<K, V> currentNode = this.next;

        while (currentNode != this) {
            if (currentNode.key.equals(key)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    @Override
    public V get(final K key) {
        final ListMap<K, V> keyNode = this.lookupNode(key);

        if (keyNode == null) {
            return null;
        } else {
            return keyNode.value;
        }
    }

    @Override
    public boolean remove(final K key) {
        final ListMap<K, V> keyNode = this.lookupNode(key);

        if (keyNode == null) {
            return false;
        } else {
            keyNode.prev.next = keyNode.next;
            keyNode.next.prev = keyNode.prev;
            keyNode.prev = null;
            keyNode.next = null;
            return true;
        }
    }

    @Override
    public boolean put(final K key, final V value) {
        final ListMap<K, V> keyNode = this.lookupNode(key);

        if (keyNode == null) {
            this.append(key, value);
            return true;
        } else {
            keyNode.value = value;
            return false;
        }
    }
}
