import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    @Test
    public void testOffByN() {
        CharacterComparator offByN = new OffByN(-5);
        offByN = new OffByN(5);
        assertEquals(true, offByN.equalChars('a', 'f'));
        assertEquals(true, offByN.equalChars('f', 'a'));
        assertEquals(false, offByN.equalChars('f', 'h'));
        assertEquals(false, offByN.equalChars('A', 'f'));
        assertEquals(false, offByN.equalChars('%', '&'));

    }
}
