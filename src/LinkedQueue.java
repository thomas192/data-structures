public class LinkedQueue<T> implements Iterable<T> {

    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    public LinkedQueue() {
    }

    public LinkedQueue(T firstElem) {
        offer(firstElem);
    }

    // peek element in front of the queue
    public T peek() {
        if (list.isEmpty()) throw new RuntimeException("Queue Empty");
        return list.peekFirst();
    }

    // remove element in front of the queue
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }

    // add element to the back of the queue
    public void offer(T elem) {
        list.addLast(elem);
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
