

import java.util.*;


public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private final double loadFactor;
    private int threshold = 12;
    private Entry[] table;

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private K getKey() {return key;}
        private V getValue() {return value;}

        private V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    private void validate(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
    }
    private void validate(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
    }
    private void validate(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException();
        }
    }
    private void validate(int initialSize, double loadFactor) {
        if (initialSize <= 0 || loadFactor <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public MyHashMap() {
        table = new Entry[INITIAL_CAPACITY];
        this.loadFactor = LOAD_FACTOR;
        size = 0;
    }
    public MyHashMap(int initialSize) {
        validate(initialSize);
        table = new Entry[initialSize];
        this.loadFactor = LOAD_FACTOR;
        size = 0;
    }
    public MyHashMap(int initialSize, double loadFactor) {
        validate(initialSize, loadFactor);
        table = new Entry[initialSize];
        this.loadFactor = loadFactor;
        threshold = (int) (table.length * loadFactor);
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        validate(key);
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        validate(key);
        int hashCode = hash(key, table.length);
        int index = Math.floorMod(hashCode, table.length);
        if (table[index] != null) {
            Entry<K, V> b = table[index];
            while (b != null) {
                if (b.getKey().equals(key))
                    return b.getValue();
                b = b.next;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Entry<K, V>[] newTable = new Entry[capacity];
        for (Entry<K, V> bin: table) {
            while (bin != null) {
                Entry<K, V> next = bin.next;
                int index = hash(bin.getKey(), capacity);
                bin.next = newTable[index]; //单链表头插入
                newTable[index] = bin;
                bin = next;
            }
        }
        table = newTable;
        threshold = (int) (newTable.length * loadFactor);
    }

    private int hash(K key, int length) {
        validate(key);
        // Cited from https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
        return (key.hashCode() & 0x7fffffff) % length;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int hashCode = hash(key, table.length);
        put(hashCode, key, value);
    }

    private void put(int hashCode, K key, V value) {
        validate(key, value);

        int index = Math.floorMod(hashCode, table.length);
        if (table[index] == null) {
            table[index] = new Entry(key, value, null);
        }
        else {
            Entry<K, V> bin = table[index];
            Entry<K, V> last = bin;
            while (bin != null) {
                if (bin.getKey().equals(key)) {
                    bin.setValue(value);
                    return;
                }
                last = bin;
                bin = bin.next;
            }
            last.next = new Entry(key, value, null);
        }
        size++;
        if (size >= threshold) {
            resize(2 * table.length);
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for (Entry<K, V> b : table) {
            if (b != null) {
                while (b != null) {
                    allKeys.add(b.getKey());
                    b = b.next;
                }
            }
        }
        return allKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> m = new MyHashMap<>();
        m.put("one", 1);
        m.put("two", 2);
        m.put("three", 3);
        for (String x: m.keySet()) {
            System.out.println(m.get(x));
        }
    }

}
