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
 * The parser class takes care of all the commands in the input
 * file. It also takes care of printing.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.24.21
 *
 */
public class SectionManagerTest extends student.TestCase{

    private SectionManager manager;
    private CourseManager courseManager;
    private StudentManager studentManager;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        manager = new SectionManager();
        courseManager = new CourseManager();
        studentManager = new StudentManager();
    }

    /**
     * 
     */
    public void testGetTreeName() {
        BST<Student> name = manager.getTreeName();
        Student student = new Student("2324234", "Ajay", "Dalmia");
        name.insert(student);
        assertTrue(manager.getTreeName().toArray().get(0).getFirstName().equals("Ajay"));
    }
    
    /**
     * @throws IOException 
     * 
     */
    public void testGetTreePid() {
        BST<String> name = manager.getTreePID();
        String string = "2324234";
        name.insert(string);
        assertTrue(manager.getTreePID().toArray().get(0).equals("2324234"));
    }
    
    
    /**
     * @throws IOException
     * 
     */
    public void testGetTreeScore() throws IOException {
        BST<Score> name = manager.getTreeScore();
        ArrayList<Student> studentDatabaseList = studentManager.studentList();
        studentManager.readsStudentFile("students_ajay.csv");
        courseManager.readsCourseFile("cs3114_2_ajay.csv",studentDatabaseList);
        Score score = new Score("2324234", 100);
        name.insert(score);
        assertTrue(manager.getTreeScore().toArray().get(0).getID().equals("2324234"));
    }
    
    /**
     * @throws IOException 
     * 
     */
    public void testGetTreeSectionList() throws IOException {
        studentManager.readsStudentFile("students_ajay.csv");
        ArrayList<Student> studentDatabaseList = studentManager.studentList();
        courseManager.readsCourseFile("CS3114_ajay.csv", studentDatabaseList);
        ArrayList<Student> sectionList = manager.getSectionList();
        Student student = new Student("2324234", "Ajay", "Dalmia");
        sectionList.add(student);
        assertTrue(manager.getSectionList().get(0).getFirstName().equals("Ajay"));
        
    }
}
