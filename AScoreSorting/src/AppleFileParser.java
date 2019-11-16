import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

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
 * @version 2019.09.11
 */
public class AppleFileParser {

    /**
     * fields
     */
    private int offsetPosNull;
    private RandomAccessFile fil;
    private DataOutputStream os; 
    private PrintWriter writer;
    private long fileLength;


    /**
     * Constructor
     * 
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public AppleFileParser(String fileName) throws IOException {
        fil = new RandomAccessFile(fileName, "r");
        offsetPosNull = 0;
        fileLength = 0;
        os = new DataOutputStream(new FileOutputStream("runFile.data", false));
        writer = new PrintWriter("runFile.txt", "UTF-8");
    }


    /**
     * Reads Student Input files in binary format
     * 
     * @param fileName
     *            Binary File to read
     * @throws IOException
     */
    public Apple getNextRecord() throws IOException {

        fileLength = fil.length();
        byte[] buffer;

        if (offsetPosNull + 16 <= fileLength) {
            buffer = new byte[16];
        }
        else {
            return null;
        }

        int offsetPos = 0;
        fil.readFully(buffer, offsetPos, 16);
        offsetPosNull += 16;

        ByteBuffer wrapped = ByteBuffer.wrap(buffer, 0, 8);
        long pid = wrapped.getLong();

        wrapped = ByteBuffer.wrap(buffer, 8, 8);
        double score = wrapped.getDouble();

        Apple apple = new Apple(pid, score);
        return apple;
    }


    /**
     * 
     * @return
     * @throws IOException
     */
    public boolean hasNextRecord() throws IOException {
        fileLength = fil.length();
        if (offsetPosNull + 16 <= fileLength) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * @throws IOException 
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException 
     * 
     */
    public void writeRunFile(Apple[] outputBuffer, int outPos) throws IOException {
        for (int i = 0; i < outPos; i++) {
            os.writeLong(outputBuffer[i].getPid());
            os.writeDouble(outputBuffer[i].getScore());
        }
    }

}
