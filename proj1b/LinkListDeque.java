public class LinkListDeque<T> implements Deque<T> {

    private class LLD {
        public T item;
        public LLD prev;
        public LLD next;

        public LLD(T it, LLD p, LLD n) {
            item = it;
            prev = p;
            next = n;
        }
    }

    private LLD sentinel;
    private int size;

    /** Create an empty linked list deque */
    public LinkListDeque() {
        //sentinel = new LLD(null, sentinel, sentinel);   not worked
        sentinel = new LLD(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque */
    @Override
    public void addFirst(T item) {
        sentinel.next = new LLD(item, sentinel, sentinel.next);
        if (size == 0) {
            sentinel.prev = sentinel.next;
            size += 1;
            return;
        }
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque */
    @Override
    public void addLast(T item) {
        sentinel.prev.next = new LLD(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /**Returns true if deque is empty, false else */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**Removes and returns the item at the front of the deque.
     * if no, returns null
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T t = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return t;
    }

    /**Removes and returns the item at the back of the deque.
     * if no, returns null
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T t = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return t;
    }

    /**Gets the item at the given index */
    @Override
    public T get(int index) {
        LLD l = sentinel;
        for (int i = 0; i <= index; i += 1) {
            l = l.next;
        }
        return l.item;
    }
    /* Recursion */
    /* get the ist item of list starting at L */
    private T getRecursive(LLD l, int index) {
        if (index == 0) {
            return l.item;
        }
        return getRecursive(l.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /** Print deque from first to last, separated by a space */
    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println("Empty list!");
        }
        LLD l = sentinel.next;
        while (l != sentinel) {
            System.out.print(l.item + " ");
            l = l.next;
        }
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }
}
