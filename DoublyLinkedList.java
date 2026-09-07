public class DoublyLinkedList<E> {
    private Node header;  // Sentinel node at the beginning
    private Node trailer; // Sentinel node at the end
    private int size;

    public DoublyLinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.next = trailer;
        size = 0;
    }

    private class Node {
        E element;
        Node next;
        Node prev;

        Node(E element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public void addFirst(E element) {
        addBetween(element, header, header.next);
    }

    public void addLast(E element) {
        addBetween(element, trailer.prev, trailer);
    }

    private void addBetween(E element, Node predecessor, Node successor) {
        Node newest = new Node(element, predecessor, successor);
        predecessor.next = newest;
        successor.prev = newest;
        size++;
    }

    public E first() {
        if (isEmpty()) return null;
        return header.next.element;
    }

    public E last() {
        if (isEmpty()) return null;
        return trailer.prev.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = header.next;
        while (current != trailer) {
            sb.append(current.element).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    public void group() {
        if (size <= 1) return;

        Node current = header.next;
        Node lastNull = header;

        while (current != trailer) {
            Node nextNode = current.next;

            if (current.element == null) {
                // Remove current from its position
                current.prev.next = current.next;
                current.next.prev = current.prev;

                // Insert after lastNull
                current.prev = lastNull;
                current.next = lastNull.next;
                lastNull.next.prev = current;
                lastNull.next = current;

                lastNull = current;
            }

            current = nextNode;
        }
    }
}
