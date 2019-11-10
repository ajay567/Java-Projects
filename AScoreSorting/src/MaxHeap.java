
public class MaxHeap<E extends Comparable<E>>{
    private E[] heap;
    private int size; // current number of elements
    private int capacity; // max size
    
    // Constructor for empty heap
    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = (E[])new Comparable[this.capacity+1];
    }
    
    // Constructor for existing heap
    public MaxHeap(E[] heap, int size, int capacity) {
        this.heap = heap;
        this.size = size;
        this.capacity = capacity;
        buildHeap();
    }
    
    // Get current number of elements in the heap
    public int getSize() {
        return this.size;
    }
    
    private boolean isLeaf(int pos) {
        return ( pos >= size/2) && (pos < size);
    }
    
    private int leftchild(int pos) {
        if(pos >= size/2) {
            return -1;
        }
        return 2*pos + 1;
    }
    
    private int rightchild(int pos) {
        if(pos >= (size-1)/2) {
            return -1;
        }
        return 2*pos + 2;
    }
    
    private int parent(int pos) {
        if(pos <= 0) {
            return -1;
        }
        return (pos-1)/2;
    }
    
    public void insert(E key) {
        if(size >= capacity) {
            // heap is full
            return;
        }
        int curr = size++;
        heap[curr] = key;
        
        while((curr != 0) && (heap[curr].compareTo(heap[parent(curr)]) > 0)) {
            swapPos(curr, parent(curr));
            curr = parent(curr);
        }
    }
    
    public void buildHeap() {
        for(int i=size/2-1; i >= 0; i--) {
            siftdown(i);
        }
    }
    
    private void siftdown(int pos) {
        if((pos < 0) || (pos >= size)) {
            return;
        }
        while(!isLeaf(pos)) {
            int j = leftchild(pos);
            if((j<(size-1)) && (heap[j].compareTo(heap[j+1]) < 0)) {
                j++;
            }
            if(heap[pos].compareTo(heap[j]) >= 0) {
                return;
            }
            swapPos(pos, j);
            pos = j;
        }
    }
    
     // Remove and return maximum value
    public E removemax() {
        if (size == 0) {
            return null;  // Removing from empty heap
        }
        swapPos(0, --size); // Swap maximum with last value
        siftdown(0);   // Put new heap root val in correct place
        return heap[size];
    }

    // Remove and return element at specified position
    private E remove(int pos) {
        if ((pos < 0) || (pos >= size)) {
            return null; // Illegal heap position
        }
        if (pos == (size-1)) {
            size--; // Last element, no work to be done
        }
        else {
            swapPos(pos, --size); // Swap with last value
            update(pos);
        }
        return heap[size];
    }

    // Modify the value at the given position
    private void modify(int pos, E newVal) {
        if ((pos < 0) || (pos >= size)) return; // Illegal heap position
        heap[pos] = newVal;
        update(pos);
    }

    // The value at pos has been changed, restore the heap property
    private void update(int pos) {
        // If it is a big value, push it up
        while ((pos > 0) && (heap[pos].compareTo(heap[parent(pos)]) > 0)) {
            swapPos(pos, parent(pos));
            pos = parent(pos);
        }
        siftdown(pos); // If it is little, push down
    }
    
    private void swapPos(int a, int b) {
        E temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
         
    }
    
    private void printHeap() {
        for(int i=0; i<size; i++) {
            System.out.print(heap[i]);
        }
        System.out.println();
    }
}