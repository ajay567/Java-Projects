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
 * Test class for MemoryManager.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class MemoryManagerTest extends student.TestCase {

    /**
     * fields
     */
    private MemoryManager manager;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        manager = new MemoryManager();
    }


    /**
     * Tests the getBlock method from memory manager.
     */
    public void testGetBlock() {
        MemoryHandle handle1 = manager.getBlock(10);
        assertEquals(handle1.getLength(), 10);
        assertEquals(handle1.getStart(), 0);

        MemoryHandle handle2 = manager.getBlock(20);
        assertEquals(handle2.getLength(), 20);
        assertEquals(handle2.getStart(), 10);

        MemoryHandle handle3 = manager.getBlock(30);
        assertEquals(handle3.getLength(), 30);
        assertEquals(handle3.getStart(), 30);

        manager.removeBlock(handle3);
        manager.removeBlock(handle1);

        MemoryHandle handle4 = manager.getBlock(40);
        assertEquals(handle4.getLength(), 40);
        assertEquals(handle4.getStart(), 30);

        MemoryHandle handle5 = manager.getBlock(5);
        assertEquals(handle5.getLength(), 5);
        assertEquals(handle5.getStart(), 0);

        MemoryHandle handle6 = manager.getBlock(5);
        assertEquals(handle6.getLength(), 5);
        assertEquals(handle6.getStart(), 5);
    }


    /**
     * Tests the removeBlock from memory manager.
     */
    public void testRemoveBlock() {
        MemoryHandle handle1 = manager.getBlock(10);
        MemoryHandle handle2 = manager.getBlock(20);
        MemoryHandle handle3 = manager.getBlock(30);

        manager.removeBlock(handle1);
        manager.removeBlock(handle3);
        manager.removeBlock(handle2);

        MemoryHandle handle5 = manager.getBlock(60);
        assertEquals(handle5.getLength(), 60);
        assertEquals(handle5.getStart(), 0);

        manager.removeBlock(handle5);

        handle1 = manager.getBlock(10);
        handle2 = manager.getBlock(20);
        handle3 = manager.getBlock(30);

        manager.removeBlock(handle3);
        manager.removeBlock(handle2);

        MemoryHandle handle6 = manager.getBlock(50);
        manager.printFreeList();
        assertEquals(handle6.getLength(), 50);
        assertEquals(handle6.getStart(), 10);

    }
}
