import java.io.IOException;

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
 * Coursemanager2 contains the main function.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.10.20
 *
 */
public class Coursemanager2 {

    // Compiler - Eclipse (Java version 10.0.2)
    // Operating System - Windows 10
    // Date completed - 20th of October 2019
    /**
     * The main method creates an object for the parser class.
     * It passes the file to parser by calling a method created in
     * parser.
     * 
     * @param args
     *            input files
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(); // Object for parser class
        parser.readsFile(args[0]);
    }

}
