import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expected = new ArrayDequeSolution<>();
        String mes = "";

        for (int i = 0; i < 100; i++) {
            int zeroAndOne = StdRandom.uniform(2);
            int numBetweenZeroAndThree = StdRandom.uniform(4);
            int numBetweenZeroAndTen = StdRandom.uniform(10);
            if (zeroAndOne == 0) {
                expected.addFirst(numBetweenZeroAndTen);
                actual.addFirst(numBetweenZeroAndTen);
                mes = mes + "\naddFirst(" + numBetweenZeroAndTen + ")";
            } else {
                expected.addLast(numBetweenZeroAndTen);
                actual.addLast(numBetweenZeroAndTen);
                mes = mes + "\naddLast(" + numBetweenZeroAndTen + ")";
            }
            switch (numBetweenZeroAndThree) {
                case 1:
                    mes = mes + "\nremoveFirst()";
                    assertEquals(mes, expected.removeFirst(), actual.removeFirst());
                case 2:
                    mes = mes + "\nremoveLast()";
                    assertEquals(mes, expected.removeLast(), actual.removeLast());
            }
        }
    }
}
