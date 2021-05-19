public class disc01_32{
    public static int fib(int n, int k, int f0, int f1) {
        for(int i = 2; i < n; i++) {
            k = f0 + f1;
            f0 = f1;
            f1 = k;
        }
        return k;
    }
    public static void main(String[] args) {
        int k = 0;
        System.out.println(fib(9, k, 0, 1));
    }
}