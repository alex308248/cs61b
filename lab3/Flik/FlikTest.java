import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

    @Test
    public void testfilk() {
        int a = 0;
        int b = 1453243;

        assertEquals(Flik.isSameNumber(a, b), 0);

        int a1 = 2147483647;
        int b1 = 2147483647;

        assertEquals(Flik.isSameNumber(a1, b1), 1);

        int a2 = -2147483648;
        int b2 = -2147483648;

        assertEquals(Flik.isSameNumber(a2, b2), 1);
    }
}