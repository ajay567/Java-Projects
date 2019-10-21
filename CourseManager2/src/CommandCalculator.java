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
 * The CommandCalculator class is a helper class for parser. It
 * helps the parser to display the result for the commands.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.10.20
 */
public class CommandCalculator {

    /**
     * Constructor
     */
    public CommandCalculator() {
        // Do Nothing
    }


    /**
     * Method that helps to update already existing students scores
     * 
     * @param newScore
     *            the new score to be updated
     * @param course
     *            the course database list
     * @param currentSection
     *            the current section
     * @param insertPid
     *            pid to be checked for score
     * @param insertStudent
     *            the student who's score is to be updated
     * @return the course databaseList
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
            ArrayList<Student> arrayName = course.get(currentSection)
                .getTreeName().toArray();
            for (int i = 0; i < arrayScore.size(); i++) {

                if (arrayScore.get(i).getID().equals(insertPid)) {
                    scoreStudent = arrayScore.remove(i);
                    scoreStudent.setScore(newScore);
                }
            }
            for (int i = 0; i < arrayName.size(); i++) {

                if (arrayName.get(i).getID().equals(insertPid)) {
                    arrayName.get(i).setScore(newScore);
                }
            }
            course.get(currentSection).getTreeScore().clear();
            course.get(currentSection).getTreeName().clear();
            for (int i = 0; i < arrayScore.size(); i++) {
                course.get(currentSection).getTreeScore().insert(arrayScore.get(
                    i));
            }
            course.get(currentSection).getTreeScore().insert(scoreStudent);

            for (int i = 0; i < arrayName.size(); i++) {
                course.get(currentSection).getTreeName().insert(arrayName.get(
                    i));
            }

            System.out.println("Update " + insertStudent + " record, score = "
                + newScore);
        }
        else {
            System.out.println("Scores have to be integers in range 0 to 100.");
        }

        return course;
    }


    /**
     * Method that prints invalid score commands.
     * 
     * @param scan
     *            A scanner
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
     *            arraylist of the student database
     * @param i
     *            the current section
     * @return the course databaseList
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
     * Stat method helps to get the statistics of all the students with
     * the their respective grades.
     * 
     * @param studentArray
     *            arraylist of the student database
     * @param i
     *            the current section
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

        System.out.println("Statistics of section " + i + ":");
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
     * List gives the statistics for a particular grade
     * 
     * @param studentArray
     *            arraylist of the student database
     * @param currentSection
     *            the current section
     * @param grade
     *            specific grade for the section
     */
    public void list(
        ArrayList<SectionManager> studentArray,
        int currentSection,
        String grade) {

        int total = 0;

        ArrayList<Student> current = studentArray.get(currentSection)
            .getSectionList();

        System.out.println("Students with grade " + grade + " are:");
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
        else {
            for (int i = 0; i < current.size(); i++) {
                if (current.get(i).getGrade().equals(grade)) {
                    total++;
                    System.out.println(current.get(i).getID() + ", " + current
                        .get(i).getName() + ", score = " + current.get(i)
                            .getScore() + ", grade = " + current.get(i)
                                .getGrade());
                }
            }

        }

        System.out.println("Found " + total + " students");
    }


    /**
     * Helper method for the findpair command where the score range has
     * been specified.
     * 
     * @param check
     *            list of students
     * @param n
     *            the score for the range
     */
    public void findPairHasScore(ArrayList<Student> check, int n) {
        int val = 0;
        System.out.println("Students with score difference less than or equal "
            + n + ":");
        for (int i = 0; i < check.size(); i++) {
            for (int j = i + 1; j < check.size(); j++) {
                if (Math.abs(check.get(i).getScore() - check.get(j)
                    .getScore()) <= n) {
                    System.out.println(check.get(i).getName() + ", " + check
                        .get(j).getName());
                    val++;
                }
            }
        }
        System.out.println("found " + val + " pairs");

    }


    /**
     * Helper method for the findpair command where the score range has
     * not been specified.
     * 
     * @param check
     *            list of students
     */
    public void findPairHasNoScore(ArrayList<Student> check) {
        int val = 0;
        System.out.println(
            "Students with score difference less than or equal 0:");

        for (int i = 0; i < check.size(); i++) {
            for (int j = i + 1; j < check.size(); j++) {
                if (check.get(i).getScore() == check.get(j).getScore()) {
                    System.out.println(check.get(i).getName() + ", " + check
                        .get(j).getName());
                    val++;
                }
            }
        }
        System.out.println("found " + val + " pairs");

    }


    /**
     * This method helps to process the remove
     * command with name.
     * 
     * @param course
     *            the course database arraylist
     * @param nameToBeRemoved
     *            the name given by the command
     * @param pid
     *            the pid of the student to be removed
     * @param currentSection
     *            the current section in progress
     * @return the course databaseList
     */
    public ArrayList<SectionManager> removeName(
        ArrayList<SectionManager> course,
        String nameToBeRemoved,
        String pid,
        int currentSection) {
        int occurrence = 0;

        int removeValueSectionList = 0;
        int removeValuePidList = 0;
        int removeValueNameList = 0;
        int removeValueScoreList = 0;

        ArrayList<Student> sectionList = course.get(currentSection)
            .getSectionList();

        for (int i = 0; i < sectionList.size(); i++) {
            if (sectionList.get(i).getName().equals(nameToBeRemoved)) {
                occurrence++;
                pid = sectionList.get(i).getID();
            }
        }

        if (occurrence == 1) {
            ArrayList<String> pidList = course.get(currentSection).getTreePID()
                .toArray();
            ArrayList<Student> nameList = course.get(currentSection)
                .getTreeName().toArray();
            ArrayList<Score> scoreList = course.get(currentSection)
                .getTreeScore().toArray();

            for (int j = 0; j < sectionList.size(); j++) {
                if (sectionList.get(j).getID().equals(pid)) {
                    removeValueSectionList = j;
                }
            }
            for (int j = 0; j < pidList.size(); j++) {
                if (pidList.get(j).equals(pid)) {
                    removeValuePidList = j;
                }
            }
            for (int j = 0; j < nameList.size(); j++) {
                if (nameList.get(j).getID().equals(pid)) {
                    removeValueNameList = j;
                }
            }
            for (int j = 0; j < nameList.size(); j++) {
                if (scoreList.get(j).getID().equals(pid)) {
                    removeValueScoreList = j;
                }
            }
            sectionList.remove(removeValueSectionList);
            pidList.remove(removeValuePidList);
            nameList.remove(removeValueNameList);
            scoreList.remove(removeValueScoreList);

            course.get(currentSection).getTreeName().clear();
            course.get(currentSection).getTreeScore().clear();
            course.get(currentSection).getTreePID().clear();

            for (int j = 0; j < nameList.size(); j++) {
                course.get(currentSection).getTreeName().insert(nameList.get(
                    j));
            }
            for (int j = 0; j < scoreList.size(); j++) {
                course.get(currentSection).getTreeScore().insert(scoreList.get(
                    j));
            }
            for (int j = 0; j < pidList.size(); j++) {
                course.get(currentSection).getTreePID().insert(pidList.get(j));
            }

            System.out.println("Student " + nameToBeRemoved
                + " get removed from section " + currentSection);
        }
        else {
            System.out.println("Remove failed. Student " + nameToBeRemoved
                + " doesn't exist in section " + currentSection);
        }
        return course;
    }


    /**
     * This method helps to process the remove
     * command with pid.
     * 
     * @param course
     *            the course database arraylist
     * @param studentDatabaseList
     *            the list of the studentdatabase
     * @param pid
     *            the pid of the student to be removed
     * @param currentSection
     *            the current section in progress
     * @return the course databaseList
     */
    public ArrayList<SectionManager> removePidCommand(
        ArrayList<SectionManager> course,
        ArrayList<Student> studentDatabaseList,
        String pid,
        int currentSection) {
        boolean studentExists = false;

        for (int i = 0; i < studentDatabaseList.size(); i++) {
            if (studentDatabaseList.get(i).getID().equals(pid)) {
                studentExists = true;
            }
        }

        boolean studentExistsCourse = false;
        String name = "";
        int removeValueSectionList = 0;
        int removeValuePidList = 0;
        int removeValueNameList = 0;
        int removeValueScoreList = 0;
        if (studentExists) {
            ArrayList<Student> sectionList = course.get(currentSection)
                .getSectionList();
            ArrayList<String> pidList = course.get(currentSection).getTreePID()
                .toArray();
            ArrayList<Student> nameList = course.get(currentSection)
                .getTreeName().toArray();
            for (int j = 0; j < nameList.size(); j++) {
            }
            ArrayList<Score> scoreList = course.get(currentSection)
                .getTreeScore().toArray();

            for (int j = 0; j < sectionList.size(); j++) {
                if (sectionList.get(j).getID().equals(pid)) {
                    studentExistsCourse = true;
                    name = sectionList.get(j).getName();
                    removeValueSectionList = j;
                }
            }
            for (int j = 0; j < pidList.size(); j++) {
                if (pidList.get(j).equals(pid)) {
                    removeValuePidList = j;
                }
            }
            for (int j = 0; j < nameList.size(); j++) {
                if (nameList.get(j).getID().equals(pid)) {

                    removeValueNameList = j;
                }
            }
            for (int j = 0; j < nameList.size(); j++) {
                if (scoreList.get(j).getID().equals(pid)) {
                    removeValueScoreList = j;
                }
            }

            if (!studentExistsCourse) {
                System.out.println(
                    "Remove failed: couldn't find any student with id " + pid);
            }
            else {
                sectionList.remove(removeValueSectionList);
                pidList.remove(removeValuePidList);
                nameList.remove(removeValueNameList);
                scoreList.remove(removeValueScoreList);

                course.get(currentSection).getTreeName().clear();
                course.get(currentSection).getTreeScore().clear();
                course.get(currentSection).getTreePID().clear();

                for (int j = 0; j < nameList.size(); j++) {
                    course.get(currentSection).getTreeName().insert(nameList
                        .get(j));
                }
                for (int j = 0; j < scoreList.size(); j++) {
                    course.get(currentSection).getTreeScore().insert(scoreList
                        .get(j));
                }
                for (int j = 0; j < pidList.size(); j++) {
                    course.get(currentSection).getTreePID().insert(pidList.get(
                        j));
                }

                System.out.println("Student " + name
                    + " get removed from section " + currentSection);
            }
        }
        else {
            System.out.print("Remove failed: couldn't find any student with id "
                + pid);
        }
        return course;
    }


    /**
     * This method dumps the current section first by ID
     * then by Name and the by score.
     * 
     * @param course
     *            the course arraylist
     * @param currentSection
     *            the current section
     */
    public void dumpSection(
        ArrayList<SectionManager> course,
        int currentSection) {
        System.out.println("Section " + currentSection + " dump:");
        ArrayList<Student> dumpName = course.get(currentSection).getTreeName()
            .toArray();
        ArrayList<String> dumpPid = course.get(currentSection).getTreePID()
            .toArray();
        ArrayList<Score> dumpScore = course.get(currentSection).getTreeScore()
            .toArray();
        ArrayList<Student> sectionList = course.get(currentSection)
            .getSectionList();

        System.out.println("BST by ID:");
        for (int i = 0; i < dumpPid.size(); i++) {
            for (int j = 0; j < sectionList.size(); j++) {
                if (dumpPid.get(i).equals(sectionList.get(j).getID())) {
                    System.out.println(sectionList.get(j).getID() + ", "
                        + sectionList.get(j).getName() + ", score = "
                        + sectionList.get(j).getScore());
                }
            }
        }

        System.out.println("BST by name:");
        for (int i = 0; i < dumpName.size(); i++) {
            for (int j = 0; j < sectionList.size(); j++) {
                if (dumpName.get(i).getID().equals(sectionList.get(j)
                    .getID())) {
                    System.out.println(sectionList.get(j).getID() + ", "
                        + sectionList.get(j).getName() + ", score = "
                        + sectionList.get(j).getScore());
                }
            }
        }

        System.out.println("BST by score:");
        for (int i = 0; i < dumpScore.size(); i++) {
            for (int j = 0; j < sectionList.size(); j++) {
                if (dumpScore.get(i).getID().equals(sectionList.get(j)
                    .getID())) {
                    System.out.println(sectionList.get(j).getID() + ", "
                        + sectionList.get(j).getName() + ", score = "
                        + sectionList.get(j).getScore());
                }
            }
        }

        System.out.println("Size = " + dumpScore.size());
    }


    /**
     * This method merges all section into the current
     * section.
     * 
     * @param course
     *            the coursedatabase list
     * @param currentSection
     *            the current section
     * @param mergedSectionList
     *            the list all sections already used for merge
     * @return the course databaseList
     */
    public ArrayList<SectionManager> merge(
        ArrayList<SectionManager> course,
        int currentSection,
        ArrayList<Integer> mergedSectionList) {
        boolean canBeMerged = false;

        for (int l = currentSection; l < course.size(); l++) {
            if (!course.get(l).getSectionList().isEmpty()) {
                canBeMerged = true;
            }
        }
        if (!canBeMerged) {
            mergedSectionList.add(currentSection);
            for (int i = 0; i < currentSection; i++) {
                boolean mergeListChecker = false;

                for (int k = 0; k < mergedSectionList.size(); k++) {
                    if (mergedSectionList.get(k) == i) {
                        mergeListChecker = true;
                    }
                }

                if (!mergeListChecker) {
                    ArrayList<Student> sectionList = course.get(i)
                        .getSectionList();
                    ArrayList<String> pidList = course.get(i).getTreePID()
                        .toArray();
                    ArrayList<Student> nameList = course.get(i).getTreeName()
                        .toArray();
                    ArrayList<Score> scoreList = course.get(i).getTreeScore()
                        .toArray();

                    for (int j = 0; j < nameList.size(); j++) {
                        course.get(currentSection).getTreeName().insert(nameList
                            .get(j));
                    }
                    for (int j = 0; j < scoreList.size(); j++) {
                        course.get(currentSection).getTreeScore().insert(
                            scoreList.get(j));
                    }
                    for (int j = 0; j < pidList.size(); j++) {
                        course.get(currentSection).getTreePID().insert(pidList
                            .get(j));
                    }
                    for (int j = 0; j < sectionList.size(); j++) {
                        course.get(currentSection).getSectionList().add(
                            sectionList.get(j));
                    }
                }

            }
            System.out.println("All sections merged at section "
                + currentSection);
        }
        else {

            System.out.println(
                "Sections could only be merged to an empty section. "
                    + "Section " + currentSection + " is not empty.");
        }
        return course;

    }
}
