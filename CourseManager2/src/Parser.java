import java.io.File;
import java.io.IOException;
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
     * @throws IOException
     */
    public void readsFile(String fileName) throws IOException {
        File input = new File(fileName);
        Scanner scan = new Scanner(input);

        ArrayList<Student> studentDatabaseList = new ArrayList<Student>();
        ArrayList<SectionManager> course = new ArrayList<SectionManager>();
        CommandCalculator commandCalculator = new CommandCalculator();

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
            if (temp.contains("csv")) {
                manager.readsStudentFile(temp);
            }
            else {
                manager.readsStudentDataFile(temp);
            }
            studentDatabaseList = manager.studentList();
        } // loadStudentData ends

        // section varaibles
        int currentSection = 1;

        // parser begins
        while (scan.hasNext()) {
            String command = scan.next();

            // loadCourseData begins
            if (command.equals("loadcoursedata")) {
                CourseManager manager = new CourseManager();
                String temp = scan.next();
                int endIndex = temp.indexOf('.');
                String courseName = temp.substring(0, endIndex);
                System.out.println(courseName
                    + " Course has been successfully loaded.");
                if (temp.contains("csv")) {
                    manager.readsCourseFile(temp, studentDatabaseList);
                }
                else {
                    manager.readsCourseDataFile(temp, studentDatabaseList);
                }
                course = manager.courseList();
            }

            // section command begins
            if (command.equals("section")) {
                currentSection = scan.nextInt();
                System.out.println("switch to section " + currentSection);
            } // section command ends

            // insert command begins
            if (command.equals("insert")) {
                String insertPid = scan.next();
                String insertFName = scan.next().toLowerCase();
                String insertLName = scan.next().toLowerCase();
                int insertScore = 0;
                String insertGrade = "F";
                Student insertStudent = new Student(insertPid, insertFName,
                    insertLName);
                insertStudent.setScore(insertScore);
                insertStudent.setGrade(insertGrade);

                // checking student database
                boolean checkStudentDatabase = false;
                boolean checkStudentEitherName = false;
                for (int i = 0; i < studentDatabaseList.size(); i++) {
                    if (insertPid.equals(studentDatabaseList.get(i).getID())
                        && (!insertFName.equals(studentDatabaseList.get(i)
                            .getFirstName()) || !insertLName.equals(
                                studentDatabaseList.get(i).getLastName()))) {
                        System.out.println(insertFName + " " + insertLName + " "
                            + "insertion failed. Wrong student information. "
                            + "ID belongs to another student");
                        checkStudentEitherName = true;
                        // invalid score command for search
                        if (scan.hasNext("score")) {
                            commandCalculator.scoreHelperInvaid(scan);
                        }
                    }
                    else if (insertPid.equals(studentDatabaseList.get(i)
                        .getID()) && insertFName.equals(studentDatabaseList.get(
                            i).getFirstName()) && insertLName.equals(
                                studentDatabaseList.get(i).getLastName())) {
                        checkStudentDatabase = true;
                    }

                } // checking student database ends

                if (checkStudentDatabase == false
                    && checkStudentEitherName == false) {
                    System.out.println(insertFName + " " + insertLName + " "
                        + "insertion failed Wrong "
                        + "student information. ID doesn't exist");

                    // invalid score command for search
                    if (scan.hasNext("score")) {
                        commandCalculator.scoreHelperInvaid(scan);
                    }
                }
                else {

                    // checking existing sections
                    boolean checkCourse = false;
                    for (int i = 0; i < course.size(); i++) {
                        if (i == currentSection) {
                            i = i + 1;
                        }
                        ArrayList<Student> sectionList = course.get(i)
                            .getSectionList();
                        for (int j = 0; j < sectionList.size(); j++) {
                            if (insertPid.equals(sectionList.get(j).getID())) {
                                checkCourse = true;
                            }
                        }
                    } // checking existing sections ends

                    // checking current section
                    boolean checkStudent = false;
                    ArrayList<Student> sectionList = course.get(currentSection)
                        .getSectionList();
                    for (int i = 0; i < sectionList.size(); i++) {
                        if (insertPid.equals(sectionList.get(i).getID())) {
                            checkStudent = true;
                        }
                    } // checking current section ends

                    if (checkCourse) {
                        System.out.println(insertFName + " " + insertLName + " "
                            + "is already registered in a different section");

                        // invalid score command for search
                        if (scan.hasNext("score")) {
                            commandCalculator.scoreHelperInvaid(scan);
                        }
                    }
                    else if (checkStudent) {
                        System.out.println(insertFName + " " + insertLName + " "
                            + "is already in section " + currentSection);

                        // invalid score command for search
                        if (scan.hasNext("score")) {
                            commandCalculator.scoreHelperInvaid(scan);
                        }
                    }
                    else if (checkStudentDatabase == true) {
                        Score scoreObj = new Score(insertPid, insertScore);
                        course.get(currentSection).getSectionList().add(
                            insertStudent);
                        course.get(currentSection).getTreeName().insert(
                            insertStudent);
                        course.get(currentSection).getTreePID().insert(
                            insertPid);
                        course.get(currentSection).getTreeScore().insert(
                            scoreObj);
                        System.out.println(insertFName + " " + insertLName
                            + " inserted");

                        // score begins
                        if (scan.hasNext("score")) {
                            scan.next();
                            int newScore = scan.nextInt();
                            course = commandCalculator.scoreHelper(newScore,
                                course, currentSection, insertPid, insertStudent
                                    .getName());
                        } // score ends

                    }
                }
            } // insert command ends

            // searchid <pid #> begins
            if (command.equals("searchid")) {
                String pid = scan.next();

                boolean searchResult = false;
                String name = "";
                int score = 0;
                for (int i = 0; i < course.get(currentSection).getSectionList()
                    .size(); i++) {
                    if (course.get(currentSection).getSectionList().get(i)
                        .getID().equals(pid)) {
                        name = course.get(currentSection).getSectionList().get(
                            i).getName();
                        score = course.get(currentSection).getSectionList().get(
                            i).getScore();
                        searchResult = true;
                    }

                }

                if (searchResult == true) {
                    System.out.println("Found " + pid + ", " + name
                        + ", score = " + score);

                    // score begins
                    if (scan.hasNext("score")) {
                        scan.next();
                        int newScore = scan.nextInt();
                        course = commandCalculator.scoreHelper(newScore, course,
                            currentSection, pid, name);
                    } // score ends

                }
                else {
                    System.out.println(
                        "Search Failed. Couldn't find any student with ID "
                            + pid);

                    // invalid score command for search
                    if (scan.hasNext("score")) {
                        commandCalculator.scoreHelperInvaid(scan);
                    }
                }
            } // searchid <pid #> ends

            // search begins
            if (command.equals("search")) {
                String fName = scan.next().toLowerCase();
                String tempName = "";

                // search <last name> begins
                if (scan.hasNext("section") || scan.hasNext("insert") || scan
                    .hasNext("search") || scan.hasNext("score") || scan.hasNext(
                        "remove") || scan.hasNext("clearsection") || scan
                            .hasNext("dumpsection") || scan.hasNext("grade")
                    || scan.hasNext("findpair") || scan.hasNext(
                        "loadstudentdata") || scan.hasNext("loadcoursedata")
                    || scan.hasNext("searchid") || scan.hasNext("stat") || scan
                        .hasNext("list") || scan.hasNext("merge") || scan
                            .hasNext("savestudentdata") || scan.hasNext(
                                "savecoursedata") || scan.hasNext(
                                    "clearcoursedata")) {
                    String pid = "";
                    System.out.println("search results for " + fName + ":");

                    ArrayList<Student> searchList = course.get(currentSection)
                        .getSectionList();
                    int total = 0;
                    for (int i = 0; i < searchList.size(); i++) {
                        if (searchList.get(i).getFirstName().equals(fName)
                            || searchList.get(i).getLastName().equals(fName)) {
                            System.out.println(searchList.get(i).getID() + ", "
                                + searchList.get(i).getName() + ", score = "
                                + searchList.get(i).getScore());
                            pid = searchList.get(i).getID();
                            tempName = searchList.get(i).getName();
                            total++;
                        }
                    }

                    if (total == 0) {
                        System.out.println(fName + " was found in " + total
                            + " records in section " + currentSection);

                        // invalid score command for search
                        if (scan.hasNext("score")) {
                            commandCalculator.scoreHelperInvaid(scan);
                        }
                    }
                    else {
                        System.out.println(fName + " was found in " + total
                            + " records in section " + currentSection);
                    }

                    if (total == 1) {
                        // score begins
                        if (scan.hasNext("score")) {
                            scan.next();
                            int newScore = scan.nextInt();
                            course = commandCalculator.scoreHelper(newScore,
                                course, currentSection, pid, tempName);

                        } // score ends
                    }

                } // search <last name> ends
                else { // search <first name> <last name> begins
                    String lName = scan.next().toLowerCase();
                    String name = fName + " " + lName;
                    String pid = "";

                    System.out.println("search results for " + name + ":");

                    ArrayList<Student> searchList = course.get(currentSection)
                        .getSectionList();
                    int total = 0;
                    for (int i = 0; i < searchList.size(); i++) {
                        if (searchList.get(i).getName().equals(name)) {
                            System.out.println(searchList.get(i).getID() + ", "
                                + searchList.get(i).getName() + ", score = "
                                + searchList.get(i).getScore());
                            total++;
                        }
                    }

                    if (total == 0) {
                        System.out.println(name + " was found in " + total
                            + " records in section " + currentSection);

                        // invalid score command for search
                        if (scan.hasNext("score")) {
                            commandCalculator.scoreHelperInvaid(scan);
                        }
                    }
                    else {
                        System.out.println(name + " was found in " + total
                            + " records in section " + currentSection);
                    }

                    if (total == 1) {
                        // score begins
                        if (scan.hasNext("score")) {
                            scan.next();
                            int newScore = scan.nextInt();
                            course = commandCalculator.scoreHelper(newScore,
                                course, currentSection, pid, name);
                        } // score begins
                    }

                } // search <first name> <last name> ends
            } // search ends

            // score command invalid
            if (command.equals("score")) {
                scan.nextInt();
                System.out.println(
                    "score command can only be called after an insert "
                        + "command or a successful search command with "
                        + "one exact output.");
            } // score ends

            // clearcoursedata begins
            ArrayList<SectionManager> savedCourse =
                new ArrayList<SectionManager>();
            if (command.equals("clearcoursedata")) {
                savedCourse = course;
                for (int i = 0; i < course.size(); i++) {
                    course.get(i).getSectionList().clear();
                    course.get(i).getTreeName().clear();
                    course.get(i).getTreePID().clear();
                    course.get(i).getTreeScore().clear();
                }
            } // clearcoursedata ends

            // dumpsection begins
            if (command.equals("dumpsection")) {
                commandCalculator.dumpSection(course, currentSection);
            } // dumpsection ends

            // remove begins
            if (command.equals("remove")) {
                String pid = scan.next();

                if (scan.hasNext("section") || scan.hasNext("insert") || scan
                    .hasNext("search") || scan.hasNext("score") || scan.hasNext(
                        "remove") || scan.hasNext("clearsection") || scan
                            .hasNext("dumpsection") || scan.hasNext("grade")
                    || scan.hasNext("findpair") || scan.hasNext(
                        "loadstudentdata") || scan.hasNext("loadcoursedata")
                    || scan.hasNext("searchid") || scan.hasNext("stat") || scan
                        .hasNext("list") || scan.hasNext("merge") || scan
                            .hasNext("savestudentdata") || scan.hasNext(
                                "savecoursedata") || scan.hasNext(
                                    "clearcoursedata")) {

                    course = commandCalculator.removePidCommand(course,
                        studentDatabaseList, pid, currentSection);
                } // remove <pid #> ends

                else { // remove <first name> <last name> begins
                    String nameToBeRemoved = pid.toLowerCase() + " " + scan
                        .next().toLowerCase();
                    course = commandCalculator.removeName(course,
                        nameToBeRemoved, pid, currentSection);
                } // remove <first name> <last name> ends
            } // remove ends

            // grade begins
            if (command.equals("grade")) {
                course = commandCalculator.grade(course, currentSection);
            } // grade ends

            // stat begins
            if (command.equals("stat")) {
                commandCalculator.stat(course, currentSection);
            } // stat ends

            // list begins
            if (command.equals("list")) {
                String grade = scan.next().toUpperCase();
                commandCalculator.list(course, currentSection, grade);
            } // list ends

            // findpair begins
            if (command.equals("findpair")) {
                ArrayList<Student> findPairList = course.get(currentSection)
                    .getSectionList();
                if (scan.hasNextInt()) {
                    int n = scan.nextInt();
                    commandCalculator.findPairHasScore(findPairList, n);
                }
                else {
                    commandCalculator.findPairHasNoScore(findPairList);
                }
            } // findpair ends

            // savestudentdata begins
            if (command.equals("savestudentdata")) {
                StudentManager manager = new StudentManager();
                manager.writeStudentDataFile(scan.next());
            } // savestudentdata ends

            // savecoursedata begins
            if (command.equals("savecoursedata")) {
                CourseManager manager = new CourseManager();
                manager.writeCourseDataFile(scan.next());
            } // savecoursedata ends
        }
    }
}
