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
 * @version 2019.24.21
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
        student = new Student("Ajay", "Dalmia");
    }


    /**
     * Tests getter methods for name
     */
    public void testName() {
        assertTrue(student.getFirstName().equals("Ajay"));
        assertTrue(student.getLastName().equals("Dalmia"));
        assertTrue(student.getName().equals("Ajay Dalmia"));
    }


    /**
     * Tests getter and setter methods for ID, Grade, and Score
     */
    public void testIdScoreGrade() {
        student.setID("01010");
        student.setGrade("A");
        student.setScore(100);
        assertTrue(student.getID().equals("01010"));
        assertTrue(student.getGrade().equals("A"));
        assertEquals(student.getScore(), 100);
    }


    /**
     * Tests the compareTo() method
     */
    public void testCompareTo() {
        Student student1 = new Student("Akshay", "Dalmia");
        Student student2 = new Student("Sharma", "Himanshu");
        assertEquals(student.compareTo(student1), -1);
        assertEquals(student.compareTo(student2), -4);
    }

}
