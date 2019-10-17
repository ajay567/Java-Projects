import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
 * The parser class takes care of all the commands in the input
 * file. It also takes care of printing.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.24.21
 *
 */
public class Parser {

    /**
     * Constructor
     */
    public Parser() {
        // Does Nothing
    }


    /**
     * Readfile is responsible for all the commands. The three tree
     * sections are stored inside an ArrayList.
     * 
     * @param fileName
     *            input file
     * @throws FileNotFoundException
     */
    public void readsFile(String fileName) throws FileNotFoundException { 
        File input = new File(fileName);
        Scanner scan = new Scanner(input);
        
        ArrayList<Student> list = new ArrayList<Student>();
        //ArrayList<BST<Student>> course = new ArrayList<BST<Student>>();

        // Failed loadCourseData begins
        while (!scan.hasNext("loadstudentdata")) {
            String temp = scan.next();
            if (temp.equals("loadcoursedata")) {
                scan.next();
                System.out.println(
                    "Course Load Failed. You have to load Student Information file first.");
            }
        } // Failed loadCourseData ends

        // loadStudentData begins
        if (scan.hasNext("loadstudentdata")) {
            scan.next(); 
            String temp = scan.next();
            System.out.println(temp + " successfully loaded");
            StudentManager manager = new StudentManager();
            manager.readsStudentFile(temp);
            list = manager.studentList();
        } // loadStudentData ends

        // parser begins
        while (scan.hasNext()) {
            String command = scan.next();
            
            // loadCourseData begins
            if(command.equals("loadcoursedata")) {
                CourseManager manager = new CourseManager();
                String temp = scan.next();
                manager.readsCourseFile(temp,list);
                //course = manager.courseList();
                
            }
        }
    }

}
