/** ArrayDeque
 */

public class ArrayDeque<T>  {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create an empty array deque with size of 8 */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
    /** Resize method */
    /* Copy all the items to index 0 in new array t, then reset nextFirst and nextLast. */
    private void resize(int capacity) {
        T[] t = (T[]) new Object[capacity];

        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            t[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = t;
    }

    /** Add the first item of ArrayDeque */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length << 1);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Add the last item of ArrayDeque */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length << 1);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Returns true if deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return size */
    public int size() {
        return size;
    }

    private void checkUsage() {
        if ((double) size / items.length < 0.25) {
            resize(items.length >> 1);
        }
    }

    /** Remove and return the item at the front of the deque.
     * If no, return null */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        T it = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        if (size != 0) {
            checkUsage();
        }
        return it;
    }

    /** Remove and return the item at the back of the deque.
     * If no, return null */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        T it = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        if (size != 0) {
            checkUsage();
        }
        return it;
    }

    /** Get the item at the given index */
    public T get(int index) {
        return items[(nextFirst + index + 1) % items.length];
    }

    /** Print the items in the deque from first to last,
     * separated by a space */
    public void printDeque() {
        int cur;
        if (size == 0) {
            System.out.println("Empty array!");
        }
        for (int i = 0; i < size; i += 1) {
            cur = (nextFirst + 1 + i) % items.length;
            System.out.print(items[cur] + " ");
        }
        System.out.println();
    }
}
