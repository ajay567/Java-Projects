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
 * Test class for Apple
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 *
 */
public class AppleTest extends student.TestCase {

    /**
     * Variable stubs for testing
     */
    private Apple apple;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        apple = new Apple(983057537, 301.456);
    }


    /**
     * Tests getter method for pid
     */
    public void testGetPid() {
        assertEquals(apple.getPid(), 983057537);
    }


    /**
     * Tests getter method for Score
     */
    public void testGetScore() {
        assertEquals(apple.getScore(), 301.456, 1);
    }


    /**
     * Tests getter method for compareTo
     */
    public void testCompareTo() {
        Apple apple1 = new Apple(983057538, 302.456);
        Apple apple2 = new Apple(983057536, 300.456);
        assertEquals(apple.compareTo(apple1), -1);
        assertEquals(apple.compareTo(apple2), 1);
    }

}
