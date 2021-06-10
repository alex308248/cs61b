import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void randomTest1() {
        StudentArrayDeque<Integer> sDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> aDeque = new ArrayDequeSolution<>();

        String message = "";
        message += "isEmpty()\n";
        assertEquals(message, aDeque.isEmpty(), sDeque.isEmpty());

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sDeque.addLast(i);
                aDeque.addLast(i);
                message += "addLast(" + i + ")\n";
            } else {
                sDeque.addFirst(i);
                aDeque.addFirst(i);
                message += "addFirst(" + i + ")\n";
            }

            message += "size()\n";
            assertEquals(message, aDeque.size(), sDeque.size());
        }

        message += "isEmpty()\n";
        assertEquals(message, aDeque.isEmpty(), sDeque.isEmpty());

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer expected, actual;
            if (numberBetweenZeroAndOne < 0.5) {
                actual = sDeque.removeLast();
                expected = aDeque.removeLast();
                message += "removeLast()\n";
                assertEquals(message, expected, actual);
            } else {
                actual = sDeque.removeFirst();
                expected = aDeque.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, expected, actual);
            }
            message += "size()\n";
            assertEquals(message, aDeque.size(), sDeque.size());
        }
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeGold.class);
    }
}
