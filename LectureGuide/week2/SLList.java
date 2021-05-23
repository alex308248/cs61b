public class SLList {
    /*Use private for classes that we will not get into it from other classes
      Use static for classes as the word "Never Look Outside" */
    private static class IntNode {
        public int item;
        public IntNode next;   
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    } 
    
    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }
    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }
    public void addLast(int x) {
        size += 1;
        IntNode tem = sentinel;
        while(tem.next != null) {
            tem = tem.next;
        }
        tem.next = new IntNode(x, null);
    }

    public int size() {
        return size;
    }
    
    public int getFirst() {
        return sentinel.next.item;
    }
    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addLast(5);
        L.addLast(1);
        System.out.println(L.size());
    }
}