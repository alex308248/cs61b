public class disc01_3{
    public static int fib(int n) {
        int[] pair = {0, 1} ;
        if(n < 2)
            return pair[n];
        for(int i = 2; i < n; i++) {
            int tem = pair[0] + pair[1];
            pair[0] = pair[1];
            pair[1] = tem;
        }
        return pair[1];
    }
    public static void main(String[] args) {
        System.out.println(fib(9));
    }
}