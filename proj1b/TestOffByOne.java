import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testequalChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(true, offByOne.equalChars('e', 'f'));
        assertEquals(false, offByOne.equalChars('z', 'r'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(true, offByOne.equalChars('%', '&'));
        assertEquals(true, offByOne.equalChars('A', 'B'));
        assertEquals(false, offByOne.equalChars('A', 'b'));
    }
    // Your tests go here.
}
