import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * This class is a helper class for replacement selection. It
 * reads the initial binary file in blocks, converts a record
 * to an apple object and sends to to the Replacement class.
 * It also writes the apple records back to a binary file.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class AppleFileParser {

    /**
     * fields
     */
    private int offsetPosNull;
    private RandomAccessFile fil;
    private DataOutputStream os;
    private long fileLength;
    private byte[] storedBuffer;
    private int bytesTaken;
    private int startPos;


    /**
     * Constructor that takes in a file name that is suppossed
     * to be read
     * 
     * @param fileName
     *            the file to be processed
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public AppleFileParser(String fileName) throws IOException {
        fil = new RandomAccessFile(fileName, "r");
        offsetPosNull = 0;
        fileLength = fil.length();
        os = new DataOutputStream(new FileOutputStream("runfile.bin", false));

        storedBuffer = new byte[1024 * 16];
        startPos = 0;
        bytesTaken = 1024 * 16;
        fil.readFully(storedBuffer, 0, 1024 * 16);
    }


    /**
     * Passes a record as an apple object to the
     * external sort class.
     * 
     * @return apple object
     * @throws IOException
     */
    public Apple getNextRecord() throws IOException {

        if (startPos + 16 > 1024 * 16) {
            fil.seek(bytesTaken);
            storedBuffer = new byte[1024 * 16];
            fil.readFully(storedBuffer, 0, 1024 * 16);
            bytesTaken += 1024 * 16;
            startPos = 0;
        }

        offsetPosNull += 16;

        ByteBuffer wrapped = ByteBuffer.wrap(storedBuffer, startPos, 8);
        long pid = wrapped.getLong();
        startPos += 8;

        wrapped = ByteBuffer.wrap(storedBuffer, startPos, 8);
        double score = wrapped.getDouble();
        startPos += 8;

        Apple apple = new Apple(pid, score);
        return apple;
    }


    /**
     * Checks if there are any more records to be processed
     * as apple objects.
     * 
     * @return true or false
     * @throws IOException
     */
    public boolean hasNextRecord() throws IOException {

        return (offsetPosNull + 16 <= fileLength);
    }


    /**
     * The apple objects in the output buffer are written as
     * bytes into the binary file.
     * 
     * @param outputBuffer
     *            the array to be written to a field
     * @param outPos
     *            the size of the array
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    public void writeRunFile(Apple[] outputBuffer, int outPos)
        throws IOException {
        for (int i = 0; i < outPos; i++) {
            os.writeLong(outputBuffer[i].getPid());
            os.writeDouble(outputBuffer[i].getScore());
        }
    }


    /**
     * Closing the two files that were opened in this class.
     * 
     * @throws IOException
     */
    public void closingFiles() throws IOException {
        os.close();
        fil.close();
    }

}
