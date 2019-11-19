import java.io.DataOutputStream;
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
 * Replacement selection is done in this class with the help of
 * the heap created.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class ReplacementSelectionTest extends student.TestCase {

    /**
     * fields
     */
    private DataOutputStream output1;
    private DataOutputStream output;


    /**
     * Set up variables for testing
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        output1 = new DataOutputStream(new FileOutputStream("testing_Ajay1.bin",
            false));
        output = new DataOutputStream(new FileOutputStream(
            "testing_Ajay189.bin", false));
    }


    /**
     * Tests the externalSort method with the right number
     * of records in the file.
     * 
     * @throws IOException
     */
    public void testPerformExternalSort() throws IOException {
        Apple[] temp = new Apple[9216];
        Apple apple = null;
        Random rand = new Random();
        for (int i = 0; i < 9216; i++) {
            apple = new Apple(2345678, rand.nextDouble());
            temp[i] = apple;
        }
        for (int i = 0; i < 9216; i++) {
            output1.writeLong(temp[i].getPid());
            output1.writeDouble(temp[i].getScore());
        }

        ReplacementSelection parser = new ReplacementSelection(
            "testing_Ajay1.bin");

        parser.performExternalSort();
        assertEquals(temp[0].getPid(), 2345678);
    }


    /**
     * Tests the externalSort method with the wrong number
     * of records in the file.
     * 
     * @throws IOException
     */
    public void testPerformExternalSortLessBlocks() throws IOException {

        Apple[] temp = new Apple[8192];
        Apple apple = null;
        Random rand = new Random();
        for (int i = 0; i < 8192; i++) {
            apple = new Apple(2345678, rand.nextDouble());
            temp[i] = apple;
        }
        for (int i = 0; i < 8192; i++) {
            output.writeLong(temp[i].getPid());
            output.writeDouble(temp[i].getScore());
        }

        ReplacementSelection parser = new ReplacementSelection(
            "testing_Ajay189.bin");
        ArrayList<Integer> list = parser.performExternalSort();
        RunManager test = new RunManager(list);
        test.mergeAllRuns("testing_Ajay189.bin");
        assertEquals(temp[0].getPid(), 2345678);
    }

}
