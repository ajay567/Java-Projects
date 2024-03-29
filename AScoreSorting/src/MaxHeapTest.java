import static org.junit.Assert.*;

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
 * Test class for MaxHeap
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class MaxHeapTest {

    /**
     * fields
     */
    private MaxHeap<Integer> myHeap;


    /**
     * Set up variables for testing
     * 
     * @throws Exception
     */
    public void setUp() throws Exception {
        myHeap = new MaxHeap<Integer>(10);
    }


    /**
     * Testing heap removemax method
     */
    public final void testMaxHeaSize() {
        Integer[] myList = { 1, 2, 3, 4, 5, 6, 7 };
        myHeap = new MaxHeap<Integer>(myList, 7, 7);

        assertEquals(myHeap.getSize(), 7);

        for (int i = 7; i > 0; i--) {
            assertEquals(myHeap.removemax(), (Integer)i);
        }

        assertNull(myHeap.removemax());

    }


    /**
     * Testing the size method of the heap
     */
    public final void testMaxHeapSize() {
        MaxHeap<Double> myHeap1;
        Double[] myList = { 999.4230041825355, 995.4062495543849,
            683.1607533366878, 914.7365202614727, 897.2117099894964,
            520.3444159730717, 670.6172357122157, 790.6492469424579,
            833.7961470055732, 344.4436816511985, 716.7725202075407,
            73.67314349762633, 416.85667013589034, 84.68124583566772,
            613.249016195168, 256.4353357479768, 448.96459699672164,
            240.89273013166402, 248.20771128183972, 167.04988242860753 };
        myHeap1 = new MaxHeap<Double>(myList, 20, 20);

        assertEquals(myHeap1.getSize(), 20);
    }


    /**
     * Testing the insert method of the heap
     */
    public final void testInsert() {
        assertEquals(myHeap.getSize(), 0);
        myHeap.insert(21);
        myHeap.insert(31);
        myHeap.insert(25);
        myHeap.insert(42);
        myHeap.insert(11);
        myHeap.insert(99);
        myHeap.insert(50);
        myHeap.insert(30);
        myHeap.insert(80);
        myHeap.insert(48);

        assertEquals(myHeap.removemax(), (Integer)99);
        myHeap.insert(51);
        myHeap.incrementNullCount();
        myHeap.removemax();
        myHeap.modify(myHeap.getSize(), 200);
        myHeap.removemax();
        myHeap.insert(88);
        myHeap.removemax();
        myHeap.modify(myHeap.getSize(), 300);
    }


    /**
     * Testing the leaf method for a heap.
     */
    public void testIsLeaf() {
        MaxHeap<Integer> myHeap1 = new MaxHeap<Integer>(10);
        myHeap1.insert(10);
        myHeap1.isLeaf(1);
        myHeap1.leftchild(4);
        myHeap1.parent(-1);
        for (int i = 0; i < 11; i++) {
            myHeap1.insert(20);
        }
        myHeap1.siftdown(-1);
        myHeap1.modify(20, 10);
        myHeap1.modify(-20, 10);
        assertFalse(myHeap.isLeaf(1));
    }

}
