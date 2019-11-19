import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Test class for AppleFileParser
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class AppleFileParserTest extends student.TestCase {

    /**
     * fields
     */
    private DataOutputStream output;


    /**
     * Set up variables for testing
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        output = new DataOutputStream(new FileOutputStream("testing_Ajay",
            false));
    }

    /**
     * Tests all the methods present in the AppleFile parser class
     * which includes getNextRecord, hasNextRecord and writeRunFIle.
     * 
     * @throws IOException
     */
    public void testAppleFileParser() throws IOException {
        Apple[] temp = new Apple[2048];
        Apple apple = null;
        Random rand = new Random();
        for (int i = 0; i < 2048; i++) {
            apple = new Apple(2345678, rand.nextDouble());
            temp[i] = apple;
        }
        for (int i = 0; i < 2048; i++) {
            output.writeLong(temp[i].getPid());
            output.writeDouble(temp[i].getScore());
        }

        Apple check = null;
        AppleFileParser parser = new AppleFileParser("testing_Ajay");
        parser.hasNextRecord();
        for (int i = 0; i < 2048; i++) {
            check = parser.getNextRecord();
        }
        parser.writeRunFile(temp, 5);
        assertEquals(check.getPid(), 2345678);
        assertFalse(parser.hasNextRecord());
    }

}
