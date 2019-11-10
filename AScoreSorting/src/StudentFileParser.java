import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.09.11
 */
public class StudentFileParser {
    
    /**
     * fields
     */
    private ArrayList<Student> list = new ArrayList<Student>();

    public StudentFileParser() {
        
    }
    
    /**
     * Reads Student Input files in binary format
     * @param fileName Binary File to read
     * @throws IOException
     */
    public void readsStudentDataFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] fileContents = Files.readAllBytes(path);

        ByteBuffer wrapped = ByteBuffer.wrap(fileContents, 10, 4);
        int count = wrapped.getInt();
        System.out.println(count);
        int offsetPos = 14;
        for (int i = 0; i < count; i++) {
            // get pid
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, 8);
            long pid = wrapped.getLong();
            String paddedPID = String.format("%09d", pid);
            paddedPID = "909" + paddedPID;
            long paddedPIDInt = Long.parseLong(paddedPID);
            offsetPos += 8;

            // get first name
            int endPos = offsetPos;
            while (fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos
                - offsetPos);
            String firstName = StandardCharsets.UTF_8.decode(wrapped)
                .toString();
            offsetPos = endPos + 1;

            // get middle name
            endPos = offsetPos;
            while (fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos
                - offsetPos);
            String middleName = StandardCharsets.UTF_8.decode(wrapped)
                .toString();
            offsetPos = endPos + 1;

            // get last name
            endPos = offsetPos;
            while (fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos
                - offsetPos);
            String lastName = StandardCharsets.UTF_8.decode(wrapped).toString();
            offsetPos = endPos + 1 + 8;

            // create student object
            Student student = new Student(paddedPIDInt, firstName.toLowerCase(),
                lastName.toLowerCase());
            student.setMiddleName(middleName.toLowerCase());
            list.add(student);

        }
    }


    /**
     * Provides student list
     * @return ArrayList of all student objects read in
     */
    public ArrayList<Student> studentList() {
        return list;
    }
    
}
