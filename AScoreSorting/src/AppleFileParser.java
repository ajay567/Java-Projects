import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
 * @version 2019.09.11
 */
public class AppleFileParser {

    /**
     * fields
     */
    private ArrayList<Apple> list = new ArrayList<Apple>();


    /**
     * Constructor
     */
    public AppleFileParser() {
        // Does Nothing
    }


    /**
     * Reads Student Input files in binary format
     * 
     * @param fileName
     *            Binary File to read
     * @throws IOException
     */
    public void readsAppleDataFile(String fileName) throws IOException {
        
        FileInputStream fileIs = new FileInputStream(fileName);
        ObjectInputStream is = new ObjectInputStream(fileIs);
        long pid = is.readLong();
        double score = is.readDouble();
        System.out.println(pid);
        System.out.println(score);
        is.close();

    }


    /**
     * Provides student list
     * 
     * @return ArrayList of all student objects read in
     */
    public ArrayList<Apple> appleList() {
        return list;
    }
}
