public class CircularArrayQueue<T> {

    private Object[] data;
    private int front, rear;

    public CircularArrayQueue(int capacity) {
        data = new Object[capacity];
        front = rear = -1;
    }

    // add element at the back of the queue
    public void offer(T elem) {
        if (isFull()) resize();
        else if (isEmpty()) front++;
        rear = (rear + 1) % data.length;
        data[rear] = elem;
    }

    // remove first element of the queue
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        T temp = (T) data[front];
        if (front == rear) front = rear = -1; // if there was only one element in the queue
        else front = (front + 1) % data.length;
        return temp;
    }

    // peek element in front of the queue
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        else return (T) data[front];
    }

    // peek element at the back of the queue
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        else return (T) data[rear];
    }

    public void resize() {
        // create a new bigger queue that will contain elements of current queue
        Object[] newArray = new Object[data.length * 2];
        int i = 0; // to iterate over new queue
        int j = front; // to iterate over current queue
        // copy elements
        do {
            newArray[i++] = data[front];
            j = (j + 1) % data.length;
        } while (j != front);
        front = 0;
        rear = data.length - 1;
        data = newArray;
    }

    public boolean isFull() {
        return (rear + 1) % data.length == front;
    }

    public boolean isEmpty() {
        return front == -1;
    }

}
