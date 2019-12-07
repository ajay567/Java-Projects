import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

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
 * This class contains the main method. It just has to create the
 * objects of the required classes and call methods that perform
 * external sorting. The methods are passed the names of the
 * files that have to be sorted.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class CommandCalculator {

    public CommandCalculator() {

    }


    public void loadStudentData(String fileName, RandomAccessFile fil)
        throws IOException {

        File input = new File(fileName);
        Scanner scan = new Scanner(input);

        String[] currentLine = null;
        while (scan.hasNext()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");
            String name = "";
            if (currentLine[2].length() != 0) {
                name = currentLine[1] + " " + currentLine[2] + " "
                    + currentLine[3];
            }
            else {
                name = currentLine[1] + " " + currentLine[3];
            }
            fil.writeBytes(name);
        }
        scan.close();
    }
    
    public void insert(String pid, String name,RandomAccessFile fil) {
                
    }
    
    public void remove(String pid, RandomAccessFile fil) {
        
    }
    
    public void clear(String pid, RandomAccessFile fil) {
        
    }
    
    public void search(String pid, RandomAccessFile fil) {
        
    }
    
    public void print(RandomAccessFile fil) {
        
    }

}
