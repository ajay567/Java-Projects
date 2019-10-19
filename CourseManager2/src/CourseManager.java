import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        while (scan.hasNext()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");

            if (!currentLine[0].equals("")) {
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
                    if (pid.equals(studentDatabaseList.get(i).getID())
                        && (!fName.equals(studentDatabaseList.get(i)
                            .getFirstName()) || !lName.equals(
                                studentDatabaseList.get(i).getLastName()))) {
                        System.out.println("Warning: Student " + fName + " "
                            + lName + " " + "is not loaded to section "
                            + sectionId + " "
                            + "since the corresponding pid belongs to another student.");
                    }
                    else if (pid.equals(studentDatabaseList.get(i).getID())
                        && fName.equals(studentDatabaseList.get(i)
                            .getFirstName()) && lName.equals(studentDatabaseList
                                .get(i).getLastName())) {
                        checkStudentDatabase = true;
                    }
                } // checking student database ends

                if (checkStudentDatabase == false) {
                    System.out.println("Warning: Student " + fName + " " + lName
                        + " " + "is not loaded to section " + sectionId
                        + " since he/she doesn't exist in the loaded student records.");
                }

                // checking existing sections
                for (int i = 0; i < 22; i++) {
                    if (i == sectionId) {
                        i = i + 1;
                    }
                    ArrayList<Student> sectionList = course.get(i)
                        .getSectionList();
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

                if (checkCourse == true) {
                    System.out.println("Warning: Student " + fName + " "
                        + lName + " is not loaded to section " + sectionId
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
                        course.get(sectionId).getTreeScore().insert(arrayScore
                            .get(i));
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
        }
        scan.close();
    }


    public void readsCourseDataFile(
        String fileName,
        ArrayList<Student> studentDatabaseList)
        throws IOException {

        Path path = Paths.get(fileName);
        byte[] fileContents = Files.readAllBytes(path);

        ByteBuffer wrapped = ByteBuffer.wrap(fileContents, 10, 4);
        int secCount = wrapped.getInt();

        for (int i = 0; i < 22; i++) {
            course.add(new SectionManager());
        }

        int offsetPos = 14;
        // loop over sections
        for (int i = 0; i < secCount; i++) {
            int sectionId = i;

            // get num students
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, 4);
            int studentCount = wrapped.getInt();
            offsetPos += 4;

            // loop over students
            for (int j = 0; j < studentCount; j++) {
                // get pid
                wrapped = ByteBuffer.wrap(fileContents, offsetPos, 8);
                long pidL = wrapped.getLong();
                String pid = String.format("%09d", pidL);
                offsetPos += 8;

                // get first name
                int endPos = offsetPos;
                while (fileContents[endPos] != 36) {
                    endPos++;
                }
                wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos
                    - offsetPos);
                String fName = StandardCharsets.UTF_8.decode(wrapped).toString()
                    .toLowerCase();
                offsetPos = endPos + 1;

                // get last name
                endPos = offsetPos;
                while (fileContents[endPos] != 36) {
                    endPos++;
                }
                wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos
                    - offsetPos);
                String lName = StandardCharsets.UTF_8.decode(wrapped).toString()
                    .toLowerCase();
                offsetPos = endPos + 1;

                // get score
                wrapped = ByteBuffer.wrap(fileContents, offsetPos, 4);
                int score = wrapped.getInt();
                offsetPos += 4;

                // get grade
                wrapped = ByteBuffer.wrap(fileContents, offsetPos, 2);
                String grade = StandardCharsets.UTF_8.decode(wrapped)
                    .toString();
                offsetPos += 2;

                boolean checkCourse = false;
                boolean checkStudent = false;
                boolean checkStudentDatabase = false;
                int courseNumberDuplicate = 0;

                /*
                 * int sectionId = Integer.parseInt(currentLine[0]);
                 * String pid = currentLine[1];
                 * String fName = currentLine[2].toLowerCase();
                 * String lName = currentLine[3].toLowerCase();
                 * int score = Integer.parseInt(currentLine[4]);
                 * String grade = currentLine[5];
                 * 
                 */

                Student student = new Student(pid, fName, lName);
                student.setScore(score);
                student.setGrade(grade);

                // checking student database
                for (int k = 0; k < studentDatabaseList.size(); k++) {
                    if (pid.equals(studentDatabaseList.get(k).getID())
                        && (!fName.equals(studentDatabaseList.get(k)
                            .getFirstName()) || !lName.equals(
                                studentDatabaseList.get(k).getLastName()))) {
                        System.out.println("Warning: Student " + fName + " "
                            + lName + " " + "is not loaded to section "
                            + sectionId + " "
                            + "since the corresponding pid belongs to another student.");
                    }
                    else if (pid.equals(studentDatabaseList.get(k).getID())
                        && fName.equals(studentDatabaseList.get(k)
                            .getFirstName()) && lName.equals(studentDatabaseList
                                .get(k).getLastName())) {
                        checkStudentDatabase = true;
                    }
                } // checking student database ends

                if (checkStudentDatabase == false) {
                    System.out.println("Warning: Student " + fName + " " + lName
                        + " " + "is not loaded to section " + sectionId + " "
                        + "since he/she doesn't exist in the loaded student records.");
                }

                // checking existing sections
                for (int k = 0; k < course.size(); k++) {
                    if (k == sectionId) {
                        k = k + 1;
                    }
                    ArrayList<Student> sectionList = course.get(k)
                        .getSectionList();
                    for (int l = 0; l < sectionList.size(); l++) {
                        if (pid.equals(sectionList.get(l).getID())) {
                            checkCourse = true;
                            courseNumberDuplicate = l;
                        }
                    }
                } // checking existing sections ends

                // checking current section
                String overLoadedStudent = "";
                ArrayList<Student> sectionList = course.get(sectionId)
                    .getSectionList();
                for (int k = 0; k < sectionList.size(); k++) {
                    if (pid.equals(sectionList.get(k).getID())) {
                        checkStudent = true;
                        course.get(sectionId).getSectionList().get(k).setGrade(
                            grade);
                        course.get(sectionId).getSectionList().get(k).setScore(
                            score);
                        overLoadedStudent = pid;
                    }
                } // checking current section ends

                if (checkCourse) {
                    System.out.println("Warning: Student " + fName + " "
                        + lName + " is not loaded to section" + sectionId
                        + " since he/she is already enrolled in section "
                        + courseNumberDuplicate);
                }
                else if (checkStudent) {
                    Score tempScore = new Score(pid, score);
                    ArrayList<Score> arrayScore = course.get(sectionId)
                        .getTreeScore().toArray();
                    for (int k = 0; k < arrayScore.size(); k++) {
                        if (arrayScore.get(k).getID().equals(pid)) {
                            arrayScore.remove(k);
                        }
                    }
                    course.get(sectionId).getTreeScore().clear();
                    for (int k = 0; k < arrayScore.size(); k++) {
                        course.get(sectionId).getTreeScore().insert(arrayScore
                            .get(k));
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
            offsetPos += 8;
        }

    }


    public void writeCourseDataFile(String fileName) throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream(
            fileName, false));
        os.writeBytes("CS3114atVT");

        // calculate number of sections
        int secCount = 0;
        for (int i = 0; i < course.size(); i++) {
            if (!course.get(i).isEmpty()) {
                secCount++;
            }
        }
        os.writeInt(secCount);

        for (int i = 1; i < secCount + 1; i++) {
            for (Student student : course.get(i).getSectionList()) {
                long pid = Long.valueOf(student.getID()).longValue();
                os.writeLong(pid);
                os.writeBytes(student.getFirstName());
                os.writeBytes("$");
                os.writeBytes(student.getLastName());
                os.writeBytes("$");
                os.writeInt(student.getScore());

                String grade = student.getGrade();
                if (grade.length() < 2) {
                    os.writeBytes(grade);
                    os.writeBytes(" ");
                }
                else {
                    os.writeBytes(grade);
                }
            }
            os.writeBytes("GOHOKIES");
        }

    }


    /**
     * 
     * @return
     */
    public ArrayList<SectionManager> courseList() {
        return course;
    }

}
