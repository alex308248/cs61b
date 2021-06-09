public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    // Adds an item of type T to the front of the deque.
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
