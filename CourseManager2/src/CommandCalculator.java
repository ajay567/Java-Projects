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
public class CommandCalculator {

    /**
     * fields
     */

    /**
     * Constructor
     */
    public CommandCalculator() {
        // Do Nothing
    }


    /**
     *     
     */
    public ArrayList<SectionManager> scoreHelper(
        int newScore,
        ArrayList<SectionManager> course,
        int currentSection,
        String insertPid,
        String insertStudent) {

        if (newScore <= 100 && newScore >= 0) {

            Score scoreStudent = new Score("", 0);
            for (int i = 0; i < course.get(currentSection).getSectionList()
                .size(); i++) {
                if (course.get(currentSection).getSectionList().get(i).getID()
                    .equals(insertPid)) {
                    course.get(currentSection).getSectionList().get(i).setScore(
                        newScore);
                }
            }

            ArrayList<Score> arrayScore = course.get(currentSection)
                .getTreeScore().toArray();
            for (int i = 0; i < arrayScore.size(); i++) {

                if (arrayScore.get(i).getID().equals(insertPid)) {
                    scoreStudent = arrayScore.remove(i);
                    scoreStudent.setScore(newScore);
                }
            }
            course.get(currentSection).getTreeScore().clear();
            for (int i = 0; i < arrayScore.size(); i++) {
                course.get(currentSection).getTreeScore().insert(arrayScore.get(
                    i));
            }
            course.get(currentSection).getTreeScore().insert(scoreStudent);

            System.out.println("Update " + insertStudent + " record, score = "
                + newScore);
        }
        else {
            System.out.println("Scores have to be integers in range 0 to 100.");
        }

        return course;
    }


    /**
     * 
     */
    public void scoreHelperInvaid(Scanner scan) {
        scan.next();
        scan.nextInt();
        System.out.println("score command can only be called after an insert "
            + "command or a successful search command with "
            + "one exact output.");
    }


    /**
     * All the grading requirements are done using this
     * method. It goes through all the students in a
     * tree and assign them a specific grade.
     * 
     * @param studentArray
     *            ArrayList of students of a particular tree
     */
    public ArrayList<SectionManager> grade(
        ArrayList<SectionManager> studentArray,
        int i) {

        ArrayList<Student> current = studentArray.get(i).getSectionList();
        for (int j = 0; j < current.size(); j++) {
            if (current.get(j).getScore() >= 90) {
                studentArray.get(i).getSectionList().get(j).setGrade("A");
            }
            else if (current.get(j).getScore() >= 85) {
                studentArray.get(i).getSectionList().get(j).setGrade("A-");
            }
            else if (current.get(j).getScore() >= 80) {
                studentArray.get(i).getSectionList().get(j).setGrade("B+");
            }
            else if (current.get(j).getScore() >= 75) {
                studentArray.get(i).getSectionList().get(j).setGrade("B");
            }
            else if (current.get(j).getScore() >= 70) {
                studentArray.get(i).getSectionList().get(j).setGrade("B-");
            }
            else if (current.get(j).getScore() >= 65) {
                studentArray.get(i).getSectionList().get(j).setGrade("C+");
            }
            else if (current.get(j).getScore() >= 60) {
                studentArray.get(i).getSectionList().get(j).setGrade("C");
            }
            else if (current.get(j).getScore() >= 57.5) {
                studentArray.get(i).getSectionList().get(j).setGrade("C-");
            }
            else if (current.get(j).getScore() >= 55) {
                studentArray.get(i).getSectionList().get(j).setGrade("D+");
            }
            else if (current.get(j).getScore() >= 52.5) {
                studentArray.get(i).getSectionList().get(j).setGrade("D");
            }
            else if (current.get(j).getScore() >= 50) {
                studentArray.get(i).getSectionList().get(j).setGrade("D-");
            }
            else {
                studentArray.get(i).getSectionList().get(j).setGrade("F");
            }
        }

        System.out.println("grading completed");
        return studentArray;
    }


    /**
     * All the grading requirements are done using this
     * method. It goes through all the students in a
     * tree and assign them a specific grade.
     * 
     * @param studentArray
     *            ArrayList of students of a particular tree
     */
    public void stat(ArrayList<SectionManager> studentArray, int i) {

        // variables to count the number of students that
        // received a similar grade
        int a1 = 0;
        int a2 = 0;
        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int f = 0;

        ArrayList<Student> current = studentArray.get(i).getSectionList();
        for (int j = 0; j < current.size(); j++) {
            if (current.get(j).getGrade().equals("A")) {
                a1++;
            }
            else if (current.get(j).getGrade().equals("A-")) {
                a2++;
            }
            else if (current.get(j).getGrade().equals("B+")) {
                b1++;
            }
            else if (current.get(j).getGrade().equals("B")) {
                b2++;
            }
            else if (current.get(j).getGrade().equals("B-")) {
                b3++;
            }
            else if (current.get(j).getGrade().equals("C+")) {
                c1++;
            }
            else if (current.get(j).getGrade().equals("C")) {
                c2++;
            }
            else if (current.get(j).getGrade().equals("C-")) {
                c3++;
            }
            else if (current.get(j).getGrade().equals("D+")) {
                d1++;
            }
            else if (current.get(j).getGrade().equals("D")) {
                d2++;
            }
            else if (current.get(j).getGrade().equals("D-")) {
                d3++;
            }
            else {
                f++;
            }
        }

        System.out.println("Statistics​ of section " + i + ":");
        if (f != 0) {
            System.out.println(f + " students with grade F");
        }
        if (d3 != 0) {
            System.out.println(d3 + " students with grade D-");
        }
        if (d2 != 0) {
            System.out.println(d2 + " students with grade D");
        }
        if (d1 != 0) {
            System.out.println(d1 + " students with grade D+");
        }
        if (c3 != 0) {
            System.out.println(c3 + " students with grade C-");
        }
        if (c2 != 0) {
            System.out.println(c2 + " students with grade C");
        }
        if (c1 != 0) {
            System.out.println(c1 + " students with grade C+");
        }
        if (b3 != 0) {
            System.out.println(b3 + " students with grade B-");
        }
        if (b2 != 0) {
            System.out.println(b2 + " students with grade B");
        }
        if (b1 != 0) {
            System.out.println(b1 + " students with grade B+");
        }
        if (a2 != 0) {
            System.out.println(a2 + " students with grade A-");
        }
        if (a1 != 0) {
            System.out.println(a1 + " students with grade A");
        }

    }


    /**
     * All the grading requirements are done using this
     * method. It goes through all the students in a
     * tree and assign them a specific grade.
     * 
     * @param studentArray
     *            ArrayList of students of a particular tree
     */
    public void list(
        ArrayList<SectionManager> studentArray,
        int currentSection,
        String grade) {

        int total = 0;

        ArrayList<Student> current = studentArray.get(currentSection)
            .getSectionList();

        if (grade.contains("*")) {
            String tempChar = grade.substring(0, 1);
            for (int i = 0; i < current.size(); i++) {
                if (current.get(i).getGrade().contains(tempChar)) {
                    total++;
                    System.out.println(current.get(i).getID() + ", " + current
                        .get(i).getName() + ", score = " + current.get(i)
                            .getScore() + ", grade = " + current.get(i)
                                .getGrade());
                }
            }
        }

    }
}
