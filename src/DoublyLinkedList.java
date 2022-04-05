public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // empty list, O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    // add an element to the beginning, O(1)
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    // add an element to the tail, O(1)
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // remove first value, O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = head.data; // data we want to return
        head = head.next;
        size--;
        if (isEmpty()) tail = null;
        head.prev = null; // memory clean
        return data;
    }

    // remove last value, O(1)
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = tail.data; // data we want to return
        tail = tail.prev;
        size--;
        if (isEmpty()) head = null;
        tail.next = null; // memory clean
        return data;
    }

    // remove an arbitrary node, O(1)
    private T remove(Node<T> node) {
        // if the node to remove is either at the head or at the tail
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();
        // make adjacent pointers skip over the node to be removed
        node.next.prev = node.prev;
        node.prev.next = node.next;
        // data we want to return
        T data = node.data;
        // memory clean
        node.data = null;
        node.prev = node.next = null;

        size--;
        return data;
    }

    // remove a node at index, O(n)
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        int i;
        Node<T> trav;
        // search from front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) trav = trav.next;
            // search from back of the list
        } else {
            for (i = size - 1, trav = tail; i != index; i--) trav = trav.prev;
        }
        return remove(trav);
    }

    // remove particular value, O(n)
    public boolean remove(Object obj) {
        Node<T> trav;
        // search for null object
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            // search for non-null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // find index of particular value, 0(n)
    public int indexOf(Object obj) {
        int index;
        Node<T> trav;
        // search for null object
        if (obj == null) {
            for (trav = head, index = 0; trav != null; trav = trav.next, index++) {
                if (trav.data == null) return index;
            }
            // search for non-null object
        } else {
            for (trav = head, index = 0; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) return index;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while(trav.next != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        return sb.append(trav.data + "]").toString();
    }

    // peek first value if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    // peek last value if it exists, O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
