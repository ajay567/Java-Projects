import java.io.FileNotFoundException;
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
 * Testing class for CommandCalculator and Parser.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.24.21
 *
 */
public class CommandCalculatorTest extends student.TestCase {

    /**
     * fields
     */
    private ArrayList<BST<Student>> list;
    private CommandCalculator input;


    /**
     * The two fields have been initialized.
     */
    public void setUp() {
        list = new ArrayList<BST<Student>>();
        input = new CommandCalculator();
    }


    /**
     * Tests createId method for CommandCalculator.
     */
    public void testCreateID() {
        input.section(10);
        assertTrue(input.createID(1, "", 2).equals("020001"));
        assertTrue(input.createID(11, "", 2).equals("020011"));
        assertTrue(input.createID(111, "", 2).equals("020111"));

    }


    /**
     * Tests the ScoreHelper method in CommandCalculator.
     */
    public void testScoreHelper() {
        Student student = new Student("Ajay", "Dalmia");
        input.scoreHelper(101, student, "Ajay");
        input.scoreHelper(99, student, "Ajay");
        assertEquals(student.getScore(), 99);

    }


    /**
     * Tests the printInsertHelper method in CommandCalculator.
     */
    public void testPrintInsertHelper() {
        Student student = new Student("Ajay", "Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.search2Param(list, 1, student);
        input.printInsertHelper(student, 1, list);
        assertTrue(student.getName().equals("Ajay Dalmia"));

    }


    /**
     * Tests the second score helper method in CommandCalculator.
     */
    public void testScoreHelper2() {
        Student student = new Student("Ajay", "Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.scoreHelper2(30, student, "Ajay Dalmia", 1, list);
        input.scoreHelper2(130, student, "Ajay Dalmia", 1, list);
        assertEquals(student.getScore(), 30);

    }


    /**
     * Tests the search1Helper method in CommandCalculator.
     */
    public void testSearch1Helper() {
        ArrayList<Student> arr = new ArrayList<Student>();
        Student student = new Student("Ajay", "Dalmia");
        Student student1 = new Student("Akshay", "Dalmia");
        Student student2 = new Student("Sparsh", "Bansal");

        arr.add(student);
        arr.add(student1);
        arr.add(student2);
        assertEquals(input.search1Helper(arr, "Dalmia", 1, 1), 3);
        assertEquals(input.search1Helper(arr, "Akshay Dalmia", 0, 1), 0);
        assertEquals(input.search1Helper(arr, "Akshay", 1, 1), 2);
    }


    /**
     * Tests the reads file method in parser.
     * 
     * @throws FileNotFoundException
     */
    public void testReadsFile() throws FileNotFoundException {
        Parser parser = new Parser();
        Student student = new Student("Ajay", "Dalmia");
        parser.readsFile("SampleInput3.txt");
        assertTrue(student.getFirstName().equals("Ajay"));
    }


    /**
     * Tests the third score helper method in CommandCalculator.
     */
    public void testScoreHelper3() {
        Student student = new Student("Ajay", "Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.scoreHelper3(30, student, "Ajay", "Dalmia", 1, list);
        input.scoreHelper3(130, student, "Ajay", "Dalmia", 1, list);
        assertEquals(student.getScore(), 30);

    }


    /**
     * Tests the dumpSection method in CommandCalculator.
     */
    public void testDumpSection() {
        ArrayList<Student> arr = new ArrayList<Student>();
        Student student = new Student("Ajay", "Dalmia");
        student.setID("020001");
        student.setScore(99);
        arr.add(student);
        list.add(new BST<Student>());
        input.dumpSection(arr, list, 0);
        assertTrue(student.getName().equals("Ajay Dalmia"));

    }


    /**
     * Tests the grade method in CommandCalculator.
     */
    public void testGrade() {
        ArrayList<Student> arr = new ArrayList<Student>();
        int sum = 93;

        for (int i = 0; i < 30; i++) {
            Student student = new Student("Ajay", "Dalmia");
            student.setScore(sum);
            sum = sum - 2;
            arr.add(student);
        }
        input.grade(arr);

        ArrayList<Student> arr1 = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student("Ajay", "Dalmia");
            student.setScore(100);
            arr1.add(student);
        }
        input.grade(arr1);

        ArrayList<Student> arr2 = new ArrayList<Student>();
        Student student = new Student("Ajay", "Dalmia");
        student.setScore(20);
        arr2.add(student);
        input.grade(arr2);

        assertEquals(arr.get(0).getScore(), 93);
        assertEquals(arr1.get(0).getScore(), 100);
        assertEquals(arr2.get(0).getScore(), 20);
        assertEquals(arr.size(), 30);
        assertEquals(arr1.size(), 5);
        assertEquals(arr2.size(), 1);

    }

}
