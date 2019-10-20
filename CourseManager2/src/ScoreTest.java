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
 * Binary Search Tree (BST) Implementation
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.10.20
 *
 */
public class ScoreTest extends student.TestCase {
    
    /**
     * Variable stubs for testing
     */
    private Score score;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        score = new Score("983057537", 100);
    }
    
    /**
     * Tests setId and getId methods
     */
    public void testId() {
        score.setId("9123455666");
        assertTrue(score.getID().equals("9123455666"));
    }
    
    /**
     * Tests setScore and getScore methods
     */
    public void testScore() {
        score.setScore(29);
        assertEquals(score.getScore(), 29);
    }
    
    /**
     * Tests the compareTo() method
     */
    public void testCompareTo() {
        Score score1 = new Score("983057537", 50);
        Score score2 = new Score("983057532", 60);
        assertEquals(score1.compareTo(score2), -1);
        assertEquals(score.compareTo(score2), 1);
    }

}
