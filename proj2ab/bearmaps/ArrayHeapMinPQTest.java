package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayHeapMinPQTest {

    NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
    ArrayHeapMinPQ<Integer> heapMinPQ = new ArrayHeapMinPQ<>();

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 2);
        minHeap.add(6, 3);
        assertEquals(6, minHeap.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 2);
        minHeap.add(6, 3);
        assertTrue(minHeap.contains(2));
        assertFalse(minHeap.contains(0));
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0);
        minHeap.add(6, 3);
        minHeap.changePriority(5, 5);
        assertEquals(1, (int) minHeap.getSmallest());
        minHeap.changePriority(1, 6);
        assertEquals(2, (int) minHeap.getSmallest());
    }

    @Test
    public void finalTest() {
        int SIZE = 10, PRSIZE = 1000; // make sure all the priorities are unique.
        int[] nums = new int[SIZE], priority = new int[PRSIZE], priority2 = new int[PRSIZE];

        // generate numbers from 0 to SIZE/PRSIZE, and randomly change the position to generate unique numbers.
        for (int i = 0; i < SIZE; i++) {
            nums[i] = i;
        }
        for (int i = 0; i < PRSIZE; i++) {
            priority[i] = i;
            priority2[i] = i;
        }
        for (int i = 0; i < SIZE; i++) {
            int first = StdRandom.uniform(SIZE);
            int second = StdRandom.uniform(SIZE);
            int temp = nums[first];
            nums[first] = nums[second];
            nums[second] = temp;
        }
        for (int i = 0; i < PRSIZE; i++) {
            int first = StdRandom.uniform(PRSIZE);
            int second = StdRandom.uniform(PRSIZE);
            int temp = priority[first];
            priority[first] = priority[second];
            priority[second] = temp;
        }
        for (int i = 0; i < PRSIZE; i++) {
            int first = StdRandom.uniform(PRSIZE);
            int second = StdRandom.uniform(PRSIZE);
            int temp = priority2[first];
            priority2[first] = priority2[second];
            priority2[second] = temp;
        }

        for (int i = 0; i < SIZE; i++) {
            naivePQ.add(nums[i], priority[i]);
            heapMinPQ.add(nums[i], priority[i]);
        }
        for (int i = 0; i < SIZE; i++) {
            assertEquals(naivePQ.getSmallest(), heapMinPQ.getSmallest());
            naivePQ.changePriority(naivePQ.getSmallest(), priority2[i]);
            heapMinPQ.changePriority(heapMinPQ.getSmallest(), priority2[i]);
            assertEquals(naivePQ.removeSmallest(), heapMinPQ.removeSmallest());
        }
    }
}
