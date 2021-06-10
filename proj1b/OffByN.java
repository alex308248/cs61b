public class OffByN implements CharacterComparator {
    private int n = 0;
    public OffByN(int i) {
        if (i >= 0) {
            n = i;
        }
    }
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        if (b - a == n || b - a == -n) {
            return true;
        }
        return false;

    }
}
