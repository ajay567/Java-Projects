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
 * Test class for Student
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.10.20
 *
 */
public class StudentTest extends student.TestCase {

    /**
     * Variable stubs for testing
     */
    private Student student;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        student = new Student(983057537, "Ajay", "Dalmia");
    }


    /**
     * Tests getter methods for name
     */
    public void testName() {
        student.setMiddleName("NoName");
        assertTrue(student.getFirstName().equals("Ajay"));
        assertTrue(student.getLastName().equals("Dalmia"));
        assertTrue(student.getMiddleName().equals("NoName"));
        assertTrue(student.getName().equals("Ajay Dalmia"));
    }


    /**
     * Tests getter and setter methods for ID, Grade, and Score
     */
    public void testIdScoreGrade() {
        student.setScore(100);
        assertEquals(student.getID(), 983057537);
        assertEquals(student.getScore(), 100);
    }

}
