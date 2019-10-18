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
}
