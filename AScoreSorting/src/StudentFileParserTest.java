import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
 * @version 2019.11.19
 */
public class StudentFileParserTest extends student.TestCase {

    /**
     * fields
     */
    private StudentFileParser studentFileParser;


    /**
     * Set up variables for testing
     * 
     * @throws FileNotFoundException
     */
    public void setUp() throws FileNotFoundException {
        studentFileParser = new StudentFileParser();
    }


    /**
     * Tests the readStudentDataFIle method
     * 
     * @throws IOException
     */
    public void testReadsStudentDataFile() throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream(
            "test1_Ajay.data", false));
        os.writeBytes("VTSTUDENTS");
        os.writeInt(1);
        os.writeLong(983057);
        os.writeBytes("Ajay");
        os.writeBytes("$");
        os.writeBytes("temp");
        os.writeBytes("$");
        os.writeBytes("Dalmia");
        os.writeBytes("$");
        os.writeBytes("GOHOKIES");
        os.close();
        studentFileParser.readsStudentDataFile("test1_Ajay.data");

        ArrayList<Student> student = studentFileParser.studentList();
        assertTrue(student.get(0).getFirstName().equals("ajay"));
    }


    /**
     * Tests the method present in VT manager to print out the
     * top 100 vt students
     * 
     * @throws IOException
     */
    public void testVTManager() throws IOException {
        DataOutputStream os1 = new DataOutputStream(new FileOutputStream(
            "VTManager.data", false));
        os1.writeBytes("VTSTUDENTS");
        os1.writeInt(1);
        os1.writeLong(5);
        os1.writeBytes("Ajay");
        os1.writeBytes("$");
        os1.writeBytes("temp");
        os1.writeBytes("$");
        os1.writeBytes("Dalmia");
        os1.writeBytes("$");
        os1.writeBytes("GOHOKIES");
        os1.close();
        VTStudentsManager manage = new VTStudentsManager();
        DataOutputStream output = new DataOutputStream(new FileOutputStream(
            "testing_Ajay2.data", false));
        Apple[] temp = new Apple[8192];
        Apple apple = null;
        Random rand = new Random();
        for (int i = 0; i < 8191; i++) {
            apple = new Apple(rand.nextLong(), rand.nextDouble());
            temp[i] = apple;
        }
        String s = "909000983057";
        temp[8191] = new Apple(Long.parseLong(s), rand.nextDouble());
        for (int i = 0; i < 8192; i++) {
            output.writeLong(temp[i].getPid());
            output.writeDouble(temp[i].getScore());
        }
        manage.printOutStudents("testing_Ajay2.data", "test1_Ajay.data");
        studentFileParser.readsStudentDataFile("test1_Ajay.data");

        ArrayList<Student> student = studentFileParser.studentList();
        output.close();
        assertTrue(student.get(0).getFirstName().equals("ajay"));

    }

}
