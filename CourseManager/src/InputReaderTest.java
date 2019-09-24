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
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.9.21
 *
 */
public class InputReaderTest extends student.TestCase {

    /**
     * fields
     */
    private ArrayList<BST<Student>> list;
    private InputReader input;


    /**
     * 
     */
    public void setUp() {
        list = new ArrayList<BST<Student>>();
        input = new InputReader();
    }


    /**
     * 
     */
    public void testCreateID() {
        input.section(10);
        assertTrue(input.createID(1, "", 2).equals("020001"));
        assertTrue(input.createID(11, "", 2).equals("020011"));
        assertTrue(input.createID(111, "", 2).equals("020111"));

    }


    /**
     * 
     */
    public void testScore() {
        input.section(10);
        assertTrue(input.createID(1, "", 2).equals("020001"));
        assertTrue(input.createID(11, "", 2).equals("020011"));
        assertTrue(input.createID(111, "", 2).equals("020111"));

    }


    /**
     * 
     */
    public void testScoreHelper() {
        Student student = new Student();
        input.scoreHelper(101, student, "Ajay");
        input.scoreHelper(99, student, "Ajay");
        assertEquals(student.getScore(), 99);

    }


    /**
     * 
     */
    public void testPrintInsertHelper() {
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.search2Param(list, 1, student);
        input.printInsertHelper(student, 1, list);
        assertTrue(student.getName().equals("Ajay Dalmia"));

    }


    /**
     * 
     */
    public void testScoreHelper2() {
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.scoreHelper2(30, student, "Ajay Dalmia", 1, list);
        input.scoreHelper2(130, student, "Ajay Dalmia", 1, list);
        assertEquals(student.getScore(), 30);

    }


    /**
     * 
     */
    public void testSearch1Helper() {
        ArrayList<Student> arr = new ArrayList<Student>();
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        Student student1 = new Student();
        student1.setName("Akshay Dalmia");
        student1.setFirstName("Akshay");
        student1.setLastName("Dalmia");
        Student student2 = new Student();
        student2.setName("Sparsh Bansal");
        student2.setFirstName("Sparsh");
        student2.setLastName("Bansal");

        arr.add(student);
        arr.add(student1);
        arr.add(student2);
        assertEquals(input.search1Helper(arr, "Dalmia", 1, 1), 3);
        assertEquals(input.search1Helper(arr, "Akshay Dalmia", 0, 1), 0);
        assertEquals(input.search1Helper(arr, "Akshay", 1, 1), 2);
    }


    /**
     * @throws FileNotFoundException
     * 
     */
    public void testReadsFile() throws FileNotFoundException {
        Parser parser = new Parser();
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        parser.readsFile("SampleInput3.txt");
        assertTrue(student.getFirstName().equals("Ajay"));
    }


    /**
     * 
     */
    public void testScoreHelper3() {
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.get(1).insert(student);
        input.scoreHelper3(30, student, "Ajay", "Dalmia", 1, list);
        input.scoreHelper3(130, student, "Ajay", "Dalmia", 1, list);
        assertEquals(student.getScore(), 30);

    }


    /**
     * 
     */
    public void testDumpSection() {
        ArrayList<Student> arr = new ArrayList<Student>();
        Student student = new Student();
        student.setName("Ajay Dalmia");
        student.setFirstName("Ajay");
        student.setLastName("Dalmia");
        student.setID("020001");
        student.setScore(99);
        arr.add(student);
        list.add(new BST<Student>());
        input.dumpSection(arr, list, 0);
        assertTrue(student.getName().equals("Ajay Dalmia"));

    }


    /**
     * 
     */
    public void testGrade() {
        ArrayList<Student> arr = new ArrayList<Student>();
        int sum = 93;

        for (int i = 0; i < 30; i++) {
            Student student = new Student();
            student.setScore(sum);
            sum = sum - 2;
            arr.add(student);
        }
        input.grade(arr);

        ArrayList<Student> arr1 = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setScore(100);
            arr1.add(student);
        }
        input.grade(arr1);

        ArrayList<Student> arr2 = new ArrayList<Student>();
        Student student = new Student();
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
