import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // Variables for class
    private int n; // Deque size
    private Node first; // First node
    private Node last; // Last node

    // Defining the iterator class
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // Inner class for node creation with references to forward and back nodes
    private class Node {
        Item item;
        Node next;
        Node before;
    }

    // Creates an empty deque object
    public Deque() {
        Node first = null;
        Node last = null;
        int n = 0;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the size of the deque
    public int size() {
        return n;
    }

    // Add an element to the front of the que
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Constructing a new node
        Node node = new Node();

        // Setting the reference to the before node as null because the new node
        // is the first in the deque
        node.before = null;

        // Storing data in the newly created node
        node.item = item;

        // Storing the references to the back node
        if (isEmpty()) {
            node.next = null;
            first = node;
            last = node;
        } else {
            first.before = node;
            node.next = first;
            first = node;
        }

        // Adding to the size of the deque
        n++;
    }

    // Add an element to the back of the que
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        ;
        // Constructing a new node
        Node node = new Node();

        // Setting the reference to the next node as null because the new node
        // is in the front of the queue
        node.next = null;

        // Storing data in that node
        node.item = item;

        // Storing the references to the next node
        if (isEmpty()) {
            node.before = null;
            last = node;
            first = node;
        } else {
            last.next = node;
            node.before = last;
            last = node;
        }

        // Adding to the size of the deque
        n++;
    }

    public Item removeFirst() {
        // Checking if the que is empty
        if (isEmpty()) throw new NoSuchElementException();

        // Extracting the item of the first node
        Item item = first.item;

        // One node backwards will be the new first node
        first = first.next;

        // Subtracting n
        n--;

        // The reference to the next node is terminated
        if (!isEmpty()) {
            first.before = null;
        }

        // Returning the item
        return item;
    }

    public Item removeLast() {
        // Checking if the que is empty
        if (isEmpty()) throw new NoSuchElementException();

        // Extracting the last nodes item
        Item item = last.item;

        // One node ahead will be the new last node
        last = last.before;

        // Subtracting n
        n--;

        // The reference to the last node is terminated
        if (!isEmpty()) {
            last.next = null;
        }

        // Returning the item
        return item;
    }

    public static void main(String[] args) {
        System.out.println("Initiating an empty deque");
        Deque<Character> deq = new Deque<Character>();
        System.out.println("size: " + deq.size());

        System.out.println("Adding one element to the back and removing");
        deq.addLast('1');
        deq.removeLast();

        System.out.println("Adding one element to the front and removing");
        deq.addFirst('1');
        deq.removeFirst();

        System.out.println("Adding 1, 2, 3, 4 to the front of the queue");
        deq.addFirst('1');
        deq.addFirst('2');
        deq.addFirst('3');
        deq.addFirst('4');

        System.out.println("size: " + deq.size());

        Iterator itr = deq.iterator();
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());

        System.out.println("Removing the first element");
        Character firstElement = deq.removeFirst();
        System.out.println("The first element was " + firstElement);
        System.out.println("size: " + deq.size());

        System.out.println("Removing the last element");
        Character lastElement = deq.removeLast();
        System.out.println("The last element was " + lastElement);
        System.out.println("size: " + deq.size());

        System.out.println("Initiating an empty deque once more");
        Deque<Character> deq2 = new Deque<Character>();

        System.out.println("Adding 1, 2, 3, 4 to the back of the queue");
        deq2.addLast('1');
        deq2.addLast('2');
        deq2.addLast('3');
        deq2.addLast('4');

        System.out.println("size: " + deq2.size());

        Iterator itr2 = deq2.iterator();
        System.out.println(itr2.next());
        System.out.println(itr2.next());
        System.out.println(itr2.next());
        System.out.println(itr2.next());
    }
}
