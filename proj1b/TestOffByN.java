import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    @Test
    public void testOffByN() {
        CharacterComparator offByN = new OffByN(5);

        assertEquals(true, offByN.equalChars('a', 'b'));
        assertEquals(true, offByN.equalChars('a', 'f'));
        assertEquals(false, offByN.equalChars('z', 'r'));
        assertEquals(false, offByN.equalChars('a', 'a'));
        assertEquals(true, offByN.equalChars('%', '&'));

    }
}
