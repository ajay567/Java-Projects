// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * This class implements a max heap and contains all the methods
 * required to perform operations in it.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 * @param <E>
 *            comparable
 */
public class MaxHeap<E extends Comparable<E>> {

    /**
     * fields
     */
    private E[] heap;
    private int size; // current number of elements
    private int capacity; // max size
    private int nullCount; // number of null values at the end
    private int heapInsertCount = 0;


    /**
     * Constructor which takes in the capacity of the
     * heap as a parameter
     * 
     * @param capacity
     *            the amount of values to keep in the heap
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = (E[])new Comparable[this.capacity + 1];
        this.nullCount = 0;
    }


    /**
     * Constructor with three parameters that builds a heap
     * based on the values provided to it.
     * 
     * @param heap
     *            generic heap type
     * @param size
     *            the size of the heap
     * @param capacity
     *            its capcity
     */
    public MaxHeap(E[] heap, int size, int capacity) {
        this.heap = heap;
        this.size = size;
        this.capacity = capacity;
        this.nullCount = 0;
        buildHeap();
        heapInsertCount += size;
    }


    /**
     * Method to get heap size
     * 
     * @return the heap size
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Method to reset the heap.
     */
    public void reset() {
        this.size = capacity - nullCount;
        buildHeap();
    }


    /**
     * Method to check if a particular node in the heap is
     * a leaf or not.
     * 
     * @param pos
     *            position of the node
     * @return true or false
     */
    public boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }


    /**
     * Method for the left Child of a node
     * in a heap.
     * 
     * @param pos
     *            position
     * @return integer value
     */
    public int leftchild(int pos) {
        if (pos >= size / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * Method of the parent of a heap.
     * 
     * @param pos
     *            position
     * @return integer value
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Method to insert a object in the heap.
     * 
     * @param key
     *            the object to be inserted.
     */
    public void insert(E key) {
        if (size >= capacity) {
            // heap is full
            return;
        }
        int curr = size++;
        heap[curr] = key;

        while ((curr != 0) && (heap[curr].compareTo(heap[parent(curr)]) > 0)) {
            swapPos(curr, parent(curr));
            curr = parent(curr);
        }

        heapInsertCount++;
    }


    /**
     * Method to build a heap based on the values avaialbe to us.
     */
    public void buildHeap() {
        // move null values to end
        for (int i = 0; i < nullCount; i++) {
            swapPos(i, capacity - 1 - i);
        }

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    /**
     * Helper method to build a heap.
     * 
     * @param pos
     *            position.
     */
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= size)) {
            return;
        }
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (size - 1)) && (heap[j].compareTo(heap[j + 1]) < 0)) {
                j++;
            }
            if (heap[pos].compareTo(heap[j]) >= 0) {
                return;
            }
            swapPos(pos, j);
            pos = j;
        }
    }


    /**
     * Method to remove and return the maximum value of a heap.
     * 
     * @return maximum value
     */
    public E removemax() {
        if (size == 0) {
            return null; // Removing from empty heap
        }
        swapPos(0, --size); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place

        E max = heap[size];
        heap[size] = null;

        return max;
    }


    /**
     * Method to modify a value in the heap at
     * a given position
     * 
     * @param pos
     *            position to be modified
     * @param newVal
     *            the new value
     */
    public void modify(int pos, E newVal) {
        if ((pos < 0) || (pos >= capacity)) {
            return; // Illegal heap position
        }
        heap[pos] = newVal;
        heapInsertCount++;
    }


    /**
     * Method of swapping two values in a heap
     * 
     * @param a
     *            position 1 to be swapped
     * @param b
     *            second position to be swapped
     */
    private void swapPos(int a, int b) {
        E temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;

    }


    /**
     * Method to check the garbage values in the heap.
     */
    public void incrementNullCount() {
        nullCount++;
    }

}
