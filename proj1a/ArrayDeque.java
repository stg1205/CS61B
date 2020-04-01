/** ArrayDeque
 * It should have a minusOne function...
 * It would be better using 2-bit...but I don't know how.
 */

public class ArrayDeque<T> {
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

    private int addOne(int index) {
        return (index + 1) % items.length;
    }
    /** Resize method */
    /* Copy all the items to index 4 in new array t, then reset nextFirst and nextLast. */
    private void resize(int capacity) {
        T[] t = (T[]) new Object[capacity];

        if (nextFirst == minusOne(nextLast) || nextFirst > nextLast) {
            System.arraycopy(items, 0, t, 0, nextLast);
            int backLen = items.length - addOne(nextFirst);
            System.arraycopy(items, addOne(nextFirst), t, capacity - backLen, backLen);
            nextFirst = capacity - backLen - 1;
        } else {
            System.arraycopy(items, addOne(nextFirst), t, 0, size);
            nextFirst = capacity - 1;
            nextLast = size;
        }
        items = t;
    }

    /** Add the first item of ArrayDeque */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Add the last item of ArrayDeque */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
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
            resize(items.length / 2);
        }
    }

    /** Remove and return the item at the front of the deque.
     * If no, return null */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = addOne(nextFirst);
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

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(0);
        a.removeLast();
        a.addFirst(2);
        a.removeFirst();
        a.addLast(4);
        a.get(0);
        a.addLast(6);
        a.removeLast();
        a.addLast(8);
        a.addLast(9);
        a.get(0);
        a.addLast(11);
        a.addLast(12);
        a.addLast(13);
        a.get(3);
    }
}
