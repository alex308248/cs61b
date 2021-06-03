public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
        size = 0;
    }

    private  void resize(int capacity, int firstorlast) {
        T[] a = (T[]) new Object[capacity];

        if (firstorlast == 1) { // add first
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst, a,
                    a.length - items.length + nextFirst, items.length - nextFirst);
            nextFirst = a.length - items.length + nextFirst - 1;
            items = a;
        } else if (firstorlast == 0) { // add last
            System.arraycopy(items, 0, a, 0, nextLast + 1);
            if (nextFirst + 1 < items.length) {
                System.arraycopy(items, nextFirst + 1, a,
                        a.length - items.length + nextFirst + 1, items.length - nextFirst - 1);
            }
            nextFirst = a.length - items.length + nextFirst;
            nextLast = nextLast + 1;
            items = a;
        } else if (firstorlast == 3) { // remove
            if (nextFirst > nextLast) {
                if (nextFirst + 1 < items.length) {
                    System.arraycopy(items, nextFirst + 1, a, 0, items.length - 1 - nextFirst);
                }
                System.arraycopy(items, 0, a, items.length - 1 - nextFirst, nextLast);
            } else {
                System.arraycopy(items, nextFirst + 1, a, 0, size);
            }
            items = a;
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        if (size == items.length) {
            resize(size * 2, 1);
        } else if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        if (size == items.length) {
            resize(size * 2, 0);
        } else if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
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
        if (nextLast <= nextFirst) {
            for (int i = nextFirst + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = nextFirst + 1; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        T ret;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
            ret = items[0];
        } else {
            nextFirst += 1;
            ret = items[nextFirst];
        }

        if (size < items.length / 4) {
            resize(items.length / 2, 3);
        }
        return ret;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        T ret;
        if (nextLast == 0) {
            nextLast = items.length - 1;
            ret = items[items.length - 1];
        } else {
            nextLast -= 1;
            ret = items[nextLast];
        }

        if (size < items.length / 4) {
            resize(items.length / 2, 3);
        }
        return ret;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (nextLast > nextFirst) {
            return items[nextFirst + index + 1];
        } else {
            if (nextFirst + index + 1 < items.length) {
                return items[nextFirst + index + 1];
            } else {
                return items[index - items.length  + 1 + nextFirst];
            }
        }
    }
}
