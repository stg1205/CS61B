public class SpeedTestArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        int i = 0;
        while (i < 1000000) {
            L.addLast(i);
            i = i + 1;
        }
        System.out.println(L.get(999999));
    }
}
