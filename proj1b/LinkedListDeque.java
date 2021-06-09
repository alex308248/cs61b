public class LinkedListDeque<T> {
    private class Node {
        private Node left;
        private T val;
        private Node right;

        Node(Node L, T i, Node R) {
            left = L;
            val = i;
            right = R;
        }

    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        size += 1;
        if (sentinel.right == null) {
            Node a = new Node(null, item, null);
            sentinel.right = a;
            a.right = a;
            a.left = a;
            return;
        }
        Node originfirst = sentinel.right;
        Node a = new Node(originfirst.left, item, originfirst);
        sentinel.right = a;
        originfirst.left.right = a;
        originfirst.left = a;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        size += 1;
        if (sentinel.right == null) {
            Node a = new Node(null, item, null);
            sentinel.right = a;
            a.left = a;
            a.right = a;
            return;
        } else if (sentinel.right.right == sentinel.right) {
            Node originlast = sentinel.right.left;
            Node a = new Node(originlast, item, sentinel.right);
            originlast.right = a;
            originlast.left = a;
            return;
        }
        Node originlast = sentinel.right.left;
        Node a = new Node(originlast, item, sentinel.right);
        sentinel.right.left = a;
        originlast.right = a;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        Node a = sentinel.right;
        for (int i = 0; i < size; i += 1) {
            System.out.print(a.val + " ");
            a = a.right;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        Node a = sentinel.right;
        if (a == null) {
            return null;
        } else if (a.right == a) {
            sentinel.right = null;
            size = 0;
            return a.val;
        } else {
            size -= 1;
            sentinel.right = a.right;
            a.right.left = a.left;
            a.left.right = a.right;
            return a.val;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        Node a = sentinel.right;
        if (a == null) {
            return null;
        } else if (a.right == a) {
            sentinel.right = null;
            size = 0;
            return a.val;
        } else {
            size -= 1;
            Node out = a.left;
            a.left = out.left;
            out.left.right = a;
            return out.val;
        }
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        Node a = sentinel.right;
        if (index > size) {
            return null;
        }
        for (int i = 0; i <= index; i += 1) {
            if (index == i) {
                return a.val;
            }
            a = a.right;
        }
        return null;
    }

    private T recursive(int index, Node a) {
        if (index == 0) {
            return a.val;
        }
        return recursive(index - 1, a.right);
    }

    public T getRecursive(int index) {
        return recursive(index, sentinel.right);
    }
}
