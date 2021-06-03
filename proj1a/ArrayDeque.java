public class ArrayDeque <T>{
    public int size;
    public T[] items;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque() {
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        items[nextFirst] = item;
        nextFirst -= 1;
        size = 1;
    }

    private  void resize(int capacity, int first_or_last) {
        T[] a = (T[]) new Object[capacity];

        // add first
        if (first_or_last == 1) {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst, a, a.length - items.length + nextFirst, items.length - nextFirst);
            nextFirst = a.length - items.length + nextFirst - 1;
            items = a;
        }
        // add last
        else if (first_or_last == 0) {
            System.arraycopy(items, 0, a, 0, nextLast + 1);
            if (nextFirst + 1 < items.length ) {
                System.arraycopy(items, nextFirst + 1, a, a.length - items.length + nextFirst + 1, items.length - nextFirst - 1);
            }
            nextFirst = a.length - items.length + nextFirst;
            nextLast = nextLast + 1;
            items = a;
        }
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        if (size == items.length) {
            resize(size*2,1);
        }
        else if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        else {
            nextFirst -= 1;
        }
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        if (size == items.length) {
            resize(size*2, 0);
        }
        else if (nextLast == items.length - 1) {
            nextLast = 0;
        }
        else {
            nextLast += 1;
        }
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        if (nextLast <= nextFirst) {
            for (int i = nextFirst + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
        else {
            for (int i = nextFirst + 1; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        size -= 1;
        if(nextFirst == items.length - 1) {
            nextFirst = 0;
            return items[0];
        }
        else {
            nextFirst += 1;
            return items[nextFirst];
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        size -= 1;
        if (nextLast == 0) {
            nextLast = items.length - 1;
            return items[items.length - 1];
        }
        else {
            nextLast -= 1;
            return items[nextLast];
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (nextLast <= nextFirst) {
            for (int i = 0; i < size; i += 1) {
                if (index == 0) {
                    return items[i];
                }
                index -= 1;
            }
            for (int i = 0; i < nextLast; i += 1) {
                if (index == 0) {
                    return items[i];
                }
                index -= 1;
            }
            return null;
        }
        else {
            for (int i = nextFirst + 1; i < nextLast; i += 1) {
                if (index == 0) {
                    return items[i];
                }
                index -= 1;
            }
            return null;
        }
    }
}