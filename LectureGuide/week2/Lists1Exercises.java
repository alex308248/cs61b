public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        IntList Q = new IntList(0,null);
        IntList R = Q;
        while(L != null) {
            Q.first = L.first + x;
            if(L.rest != null) {
                Q.rest = new IntList(0,null);
            }
            L = L.rest; 
            Q = Q.rest;
        }
        return R;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList Q = L;
        while(Q != null) {
            Q.first = Q.first + x;
            Q = Q.rest;
        }
        return L;
    }

    public static void printList(IntList L) {
        if(L == null) {
            System.out.println();
            return;
        }
        System.out.print(L.first + " ");
        printList(L.rest);
    }
    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        printList(L);
        System.out.println(L.size());
        System.out.println(L.iterativeSize());
        System.out.println(L.get(0));

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        
        IntList Q = incrList(L, 4);
        printList(Q);
        printList(L);
        IntList R = dincrList(L, 3);        
        printList(R);
        printList(L);
    }
}