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
 * This class contains the main method. It just had to create
 * a object for the CommandFileParser class and pass the
 * required parameters to it for the program to produce
 * the required output.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class Studentmanager {

    // Compiler - Eclipse (Java version 10.0.2)
    // Operating System - Windows 10
    // Date completed - 9th of December 2019
    /**
     * The main method creates an object for CommandFileParser. The
     * parameters are the name of the command file, the hash table
     * size and the memory file's name as parameters.
     * 
     * @param args
     *            input files
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Created Hash Set with " + args[2] + " slots.");
        CommandFileParser parser = new CommandFileParser();
        parser.readFile(args[0], Integer.parseInt(args[2]), args[3]);

    }

}
