import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    int size = 0;
    BSTNode root;

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    private V get(K key, BSTNode n) {
        if (n == null)
            return null;
        if (key.compareTo(n.key) == 0)
            return n.value;
        else if (key.compareTo(n.key) < 0)
            return get(key, n.left);
        else
            return get(key, n.right);
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    private BSTNode put(K key, V value, BSTNode n) {
        if (n == null)
            return new BSTNode(key, value);
        if (key.compareTo(n.key) < 0)
            n.left = put(key, value, n.left);
        else if (key.compareTo(n.key) > 0)
            n.right = put(key, value, n.right);
        return n;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
        }
        else
            put(key, value, root);
        size += 1;
    }

    /* Print BSTMap in order of increasing key. */
    private void printInOrder(BSTNode n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.println("key: " + n.key + " value: " + n.value);
        printInOrder(n.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> BMap = new BSTMap<>();
        BMap.put(2,10);
        BMap.put(1,13);
        BMap.put(6,23);
        BMap.put(8,33);
        BMap.put(5,43);
        BMap.put(7,53);
        BMap.put(4,53);
        BMap.put(3,53);
        BMap.printInOrder();
    }
}
