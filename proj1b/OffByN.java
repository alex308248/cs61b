public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int i) {
        n = i;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int a = x;
        int b = y;
        if(b-a >0 && b-a <= n || a-b > 0 && a-b <= n) {
            return true;
        } else {
            return false;
        }

    }
}
