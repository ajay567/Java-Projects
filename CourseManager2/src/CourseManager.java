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

    private ArrayList<SectionManager> course = new ArrayList<SectionManager>();


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
    public void readsCourseFile(
        String fileName,
        ArrayList<Student> studentDatabaseList)
        throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scan = new Scanner(input);

        for (int i = 0; i < 22; i++) {
            course.add(new SectionManager());
        }

        String currentLine[] = null;
        while (scan.hasNextLine()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");

            boolean checkCourse = false;
            boolean checkStudent = false;
            boolean checkStudentDatabase = false;
            int courseNumberDuplicate = 0;
            int sectionId = Integer.parseInt(currentLine[0]);
            String pid = currentLine[1];
            String fName = currentLine[2].toLowerCase();
            String lName = currentLine[3].toLowerCase();
            int score = Integer.parseInt(currentLine[4]);
            String grade = currentLine[5];

            Student student = new Student(pid, fName, lName);
            student.setScore(score);
            student.setGrade(grade);

            // checking student database
            for (int i = 0; i < studentDatabaseList.size(); i++) {
                if (pid.equals(studentDatabaseList.get(i).getID()) && (!fName
                    .equals(studentDatabaseList.get(i).getFirstName()) || !lName
                        .equals(studentDatabaseList.get(i).getLastName()))) {
                    System.out.println("Warning: Student " + fName + " " + lName
                        + " " + "is not loaded to section " + sectionId + " "
                        + "since the corresponding pid belongs to another student.");
                }
                else if (pid.equals(studentDatabaseList.get(i).getID()) && fName
                    .equals(studentDatabaseList.get(i).getFirstName()) && lName
                        .equals(studentDatabaseList.get(i).getLastName())) {
                    checkStudentDatabase = true;
                }
            } // checking student database ends

            if (checkStudentDatabase == false) {
                System.out.println("Warning: Student " + fName + " " + lName
                    + " " + "is not loaded to section " + sectionId + " "
                    + "since he/she doesn't exist in the loaded student records.");
            }

            // checking existing sections
            for (int i = 0; i < 22; i++) {
                if (i == sectionId) {
                    i = i + 1;
                }
                ArrayList<Student> sectionList = course.get(i).getSectionList();
                for (int j = 0; j < sectionList.size(); j++) {
                    if (pid.equals(sectionList.get(j).getID())) {
                        checkCourse = true;
                        courseNumberDuplicate = j;
                    }
                }
            } // checking existing sections ends

            // checking current section
            String overLoadedStudent = "";
            ArrayList<Student> sectionList = course.get(sectionId)
                .getSectionList();
            for (int i = 0; i < sectionList.size(); i++) {
                if (pid.equals(sectionList.get(i).getID())) {
                    checkStudent = true;
                    course.get(sectionId).getSectionList().get(i).setGrade(
                        grade);
                    course.get(sectionId).getSectionList().get(i).setScore(
                        score);
                    overLoadedStudent = pid;
                }
            } // checking current section ends

            if (checkCourse) {
                System.out.println("Warning: ​Student " + fName + " " + lName
                    + " is not loaded to section" + sectionId
                    + " since he/she is already enrolled in section "
                    + courseNumberDuplicate);
            }
            else if (checkStudent) {
                Score tempScore = new Score(pid, score);
                ArrayList<Score> arrayScore = course.get(sectionId)
                    .getTreeScore().toArray();
                for (int i = 0; i < arrayScore.size(); i++) {
                    if (arrayScore.get(i).getID().equals(pid)) {
                        arrayScore.remove(i);
                    }
                }
                course.get(sectionId).getTreeScore().clear();
                for (int i = 0; i < arrayScore.size(); i++) {
                    course.get(sectionId).getTreeScore().insert(arrayScore.get(
                        i));
                }
                course.get(sectionId).getTreeScore().insert(tempScore);

            }
            else {
                Score tempScore = new Score(pid, score);
                course.get(sectionId).getTreeName().insert(student);
                course.get(sectionId).getTreePID().insert(pid);
                course.get(sectionId).getSectionList().add(student);
                course.get(sectionId).getTreeScore().insert(tempScore);
            }

        }
        scan.close();
    }


    /**
     * 
     * @return
     */
    public ArrayList<SectionManager> courseList() {
        return course;
    }

}
