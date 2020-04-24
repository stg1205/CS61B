package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int minNum = oomages.size() / 50;
        int maxNum = (int) (oomages.size() / 2.5);
        int[] count = new int[M];
        for (Oomage o: oomages) {
            o.printParams();
            int index = (o.hashCode() & 0x7FFFFFFF) % M;
            if (++count[index] > maxNum) return false;
        }
        for (int a: count) {if (a < minNum) return false;}
        return true;
    }
}
