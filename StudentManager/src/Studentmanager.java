import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class Studentmanager {

    // Compiler - Eclipse (Java version 10.0.2)
    // Operating System - Windows 10
    // Date completed - 19th of November 2019
    /**
     * The main method creates an object for ReplacementSelection,
     * RunManager and VTStudentsManager. Their methods
     * are called and passed the arguments as names of
     * files.
     * 
     * @param args
     *            input files
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Created Hash Set with " + 64 + " slots.");
        CommandFileParser parser = new CommandFileParser();
        parser.readFile("SampleInput.txt", 64,"ajay.bin");

    }

}
