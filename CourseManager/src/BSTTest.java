import java.util.ArrayList;
import student.TestCase;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
/**
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.9.21
 *
 */
public class BSTTest extends TestCase {

    /**
     * fields
     */
    private BST<Integer> bst;


    /**
     * 
     */
    public void setUp() {
        bst = new BST<Integer>();
    }


    /**
     * 
     */
    public void testBST() {
        BST<Integer> myBST = new BST<Integer>();
        assertEquals(myBST.size(), 0);
        assertNull(myBST.find(0));
    }


    /**
     * 
     */
    public void testClear() {
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.clear();
        assertEquals(bst.size(), 0);
        assertNull(bst.find(2));
    }


    /**
     * 
     */
    public void testInsert() {
        bst.insert(42);
        bst.insert(12);
        bst.insert(31);
        bst.insert(11);
        bst.insert(5);
        bst.insert(77);
        bst.insert(72);
        assertEquals(bst.size(), 7);
    }


    /**
     * 
     */
    public void testRemove() {
        bst.insert(42);
        bst.insert(12);
        bst.insert(31);
        bst.insert(11);
        bst.insert(5);
        bst.insert(77);
        bst.insert(72);
        bst.remove(42);
        bst.remove(12);
        bst.remove(31);
        bst.remove(12);
        bst.remove(11);
        assertEquals(bst.size(), 3);
    }


    /**
     * 
     */
    public void testRemoveNull() {
        bst.insert(42);
        bst.insert(12);
        bst.insert(31);
        bst.insert(11);
        bst.remove(42);
        bst.remove(31);
        bst.remove(11);
        assertEquals(bst.size(), 1);
        assertNull(bst.remove(8));
    }


    /**
     * 
     */
    public void testRemoveIsNull() {
        bst.insert(77);
        bst.insert(72);
        bst.remove(77);
        assertEquals(bst.size(), 1);
        assertNull(bst.remove(50));
    }


    /**
     * 
     */
    public void testToArray() {
        bst.insert(77);
        bst.insert(72);
        ArrayList<Integer> check = bst.toArray();
        assertEquals(check.size(), 2);
    }


    /**
     * 
     */
    public void testBSTNode() {
        BSTNode<Integer> node = new BSTNode<Integer>();
        @SuppressWarnings({ "unchecked", "rawtypes" })
        BSTNode node1 = new BSTNode(1, node, node);
        assertEquals(node1.value(), 1);
    }

}
