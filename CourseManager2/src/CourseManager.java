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
public class CourseManager {

    //private ArrayList<BST<Student>> course = new ArrayList<BST<Student>>();


    /**
     * Constructor
     */
    public CourseManager() {
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
    public void readsCourseFile(String fileName, ArrayList<Student> list)
        throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scan = new Scanner(input);

        for (int i = 0; i < 24; i++) {
            //course.add(new BST<Student>());
        }

        String currentLine[] = null;
        while (scan.hasNextLine()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");

            boolean checkList = false;
            boolean checkCourse = false;
            int courseNumberDuplicate = 0;
            int sectionId = Integer.parseInt(currentLine[0]);
            int pid = Integer.parseInt(currentLine[1]);
            String fName = currentLine[2].toLowerCase();
            String lName = currentLine[3].toLowerCase();
            int score = Integer.parseInt(currentLine[4]);
            String grade = currentLine[5];

            Student student = new Student(pid, fName, lName);
            student.setScore(score);
            student.setGrade(grade);

            for (int i = 0; i < list.size(); i++) {
                if (pid == list.get(i).getID() && fName.equals(list.get(i)
                    .getFirstName()) && lName.equals(list.get(i)
                        .getLastName())) {
                    checkList = true;
                }
                else if (pid == list.get(i).getID() && (!fName.equals(list.get(
                    i).getFirstName()) || !lName.equals(list.get(i)
                        .getLastName()))) {
                    System.out.println("Warning: Student " + fName + " " + lName
                        + " " + "is not loaded to section " + sectionId + " "
                        + "since the corresponding pid belongs to another student.");
                }
            }

            //for (int i = 0; i < 24; i++) {
            //    if (i == sectionId) {
            //        i = i + 1;
            //    }
            //    ArrayList<Student> tempList = course.get(i).toArray();
            //    for (int j = 0; j < tempList.size(); j++) {
            //        if (pid == tempList.get(j).getID()) {
            //            checkCourse = true;
            //            courseNumberDuplicate = i;
            //        }
            //    }
            //}

            if (checkList == false) {
                System.out.println("Warning: Student " + fName + " " + lName
                    + " " + "is not loaded to section " + sectionId + " "
                    + "since  he/she doesn't exist in the loaded student records.");
            }
            else if (checkCourse == true) {
                System.out.println("Warning: ​Student " + fName + " " + lName
                    + " " + " is not loaded to section" + sectionId
                    + " since he/she is already enrolled in section "
                    + courseNumberDuplicate);
            }
            else {
                
                
            }

        }
        scan.close();
    }



}
