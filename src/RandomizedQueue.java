import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    //  Private variables for class
    private Item[] queue; // queue instance
    private int size = 0; // size of queue
    private int last = -1; // index of the last element
    private int n = 1; // maximum capacity of the array

    // Initializing a random empty array
    public RandomizedQueue() {
        queue = (Item[]) new Object[n];
    }

    // Check if array is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the size of the array
    public int size() {
        return size;
    }

    // Adding an element to the array
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        // Saving the element to the back of the array
        queue[++last] = item;

        // Expanding the size
        size++;

        // If needed, exapding the maximum capacity of the array
        if (last + 1 == n) {
            if (size < n / 2) defrag(n);
            else defrag(n * 2);
        }
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int rand = StdRandom.uniform(last + 1);
        while (queue[rand] == null) rand = StdRandom.uniform(last + 1);

        Item item = queue[rand];
        queue[rand] = null;
        size--;

        if (size < n / 4) defrag(n / 2);

        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int rand = StdRandom.uniform(last + 1);
        while (queue[rand] == null) rand = StdRandom.uniform(last + 1);

        return queue[rand];
    }

    private void defrag(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (queue[i] != null) {
                copy[counter++] = queue[i];
            }
        }
        queue = copy;
        n = capacity;
        last = counter - 1;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] items = (Item[]) new Object[n];
        private int current = 0, count = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < last + 1; i++) {
                if (queue[i] != null) {
                    items[count++] = queue[i];
                }
            }

            Item[] copy = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) copy[i] = items[i];
            items = copy;
            StdRandom.shuffle(items);
        }

        public boolean hasNext() {
            return current != count;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[current++];
        }
    }

    public static void main(String[] args) {
        // Initiating the que that stores characters
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        // Adding some elements to it
        queue.enqueue("To");
        queue.enqueue("be");
        queue.enqueue("or");
        queue.enqueue("not");

        // Printing out the size of the queue
        System.out.println(queue.size());

        // Sampling a random item
        String sample = queue.sample();
        System.out.println("Random item: " + sample);

        // Iterating over the queue
        Iterator itr = queue.iterator();
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());

        // Removing a random element
        String removed = queue.dequeue();
        System.out.println("Removed item: " + removed);
    }
}
