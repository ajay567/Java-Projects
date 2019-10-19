import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
 * @version 2019.24.21
 *
 * @param <E>
 *            The type of element to be stored in the BST
 */
public class CommandCalculatorTest extends student.TestCase {

    /**
     * fields
     */
    private CommandCalculator command;
    private CourseManager courseManager;
    private StudentManager studentManager;
    private Parser parser;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        command = new CommandCalculator();
        parser = new Parser();
        courseManager = new CourseManager();
        studentManager = new StudentManager();
    }


    public void testScoreHelper() throws IOException {
        studentManager.readsStudentFile("students_ajay.csv");
        ArrayList<Student> studentDatabaseList = studentManager.studentList();
        courseManager.readsCourseFile("CS3114_ajay.csv", studentDatabaseList);
        ArrayList<SectionManager> course = courseManager.courseList();
        parser.readsFile("SampleInput2.txt");
        Student student = new Student("983057537","Ajay","Dalmia");
        assertTrue(student.getFirstName().equals("Ajay"));
    }

}
