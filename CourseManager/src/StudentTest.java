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
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.9.21
 *
 */
public class StudentTest extends student.TestCase {

    /**
     * fields
     */
    private Student student;


    /**
     * 
     */
    public void setUp() {
        student = new Student();
    }


    /**
     * 
     */
    public void testName() {
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        student.setName("Ajay Dalmia");
        assertTrue(student.getFirstName().equals("Ajay"));
        assertTrue(student.getLastName().equals("Dalmia"));
        assertTrue(student.getName().equals("Ajay Dalmia"));

    }


    /**
     * 
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
     * 
     */
    public void testCompareTo() {
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");

        Student student1 = new Student();
        student1.setLastName("Dalmia");
        student1.setFirstName("Akshay");

        Student student2 = new Student();
        student2.setLastName("Himashu");
        student2.setFirstName("Sharma");

        assertEquals(student.compareTo(student1), -1);
        assertEquals(student.compareTo(student2), -4);
    }

}
