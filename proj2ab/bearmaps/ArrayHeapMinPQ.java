package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class Node {
        T item;
        double priority;

        Node() {
            item = null;
            priority = -1;
        }

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
        
        T getItem() { return item; }
        
        double getPriority() { return priority; }
        
        void setPriority(double priority) { this.priority = priority; }
    }

    private ArrayList<Node> keys;
    private HashMap<T, Integer> keyIndexMap;
    private int size;

    public ArrayHeapMinPQ() {
        keys = new ArrayList<>();
        keys.add(new Node());
        keyIndexMap = new HashMap<>();
        size = 0;
    }

    private int parent(int k) {
        return k / 2;
    }

    private int leftChild(int k) {
        return 2 * k;
    }

    private int rightChild(int k) {
        return 2 * k + 1;
    }

    private void swap(int k, int l) {
        Node kN = keys.get(k);
        Node lN = keys.get(l);
        keys.set(k, lN);
        keys.set(l, kN);
        keyIndexMap.replace(kN.getItem(), l);
        keyIndexMap.replace(lN.getItem(), k);
    }

    private void swim(int k) {
        if (k > 1) {
            if (keys.get(parent(k)).getPriority() > keys.get(k).getPriority()) {
                swap(k, parent(k));
                swim(parent(k));
            }
        }
    }

    private int highChild(int k) {
        if (leftChild(k) >= keys.size()) {
            return -1;
        }
        if (leftChild(k) == keys.size() - 1) {
            return leftChild(k);
        }
        return keys.get(leftChild(k)).getPriority() > keys.get(rightChild(k)).getPriority()
                ? rightChild(k) : leftChild(k);
    }

    private void sink(int k) {
        int nextChild = highChild(k);
        if (nextChild != -1) {
            if (keys.get(k).getPriority() > keys.get(nextChild).getPriority()) {
                swap(k, nextChild);
                sink(nextChild);
            }
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        keys.add(new Node(item, priority));
        keyIndexMap.put(item, keys.size() - 1);
        swim(keys.size() - 1);
        size++;
    }

    @Override
    public boolean contains(T item) {
        return keyIndexMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return keys.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T t = keys.get(1).getItem();
        swap(1, keys.size() - 1);
        keys.remove(keys.size() - 1);
        keyIndexMap.remove(t);
        sink(1);
        size--;
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (size == 0 || !contains(item)) {
            throw new IllegalArgumentException();
        }
        int index = keyIndexMap.get(item);
        double oldPriority = keys.get(index).getPriority();
        keys.get(index).setPriority(priority);
        if (priority < oldPriority) {
            swim(index);
        } else {
            sink(index);
        }
    }
}
