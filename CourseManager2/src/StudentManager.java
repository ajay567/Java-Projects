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
public class StudentManager {

    /**
     * fields
     */
    private ArrayList<Student> list = new ArrayList<Student>();


    /**
     * Constructor
     */
    public StudentManager() {
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
    public void readsStudentFile(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner scan = new Scanner(input);

        String currentLine[] = null;
        while (scan.hasNextLine()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");
            Student student = new Student(currentLine[0],
                currentLine[1].toLowerCase(), currentLine[3].toLowerCase());
            student.setMiddleName(currentLine[2].toLowerCase());
            list.add(student); 
        }
        scan.close();
    }
    
    public void readsStudentDataFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] fileContents = Files.readAllBytes(path);
        
        ByteBuffer wrapped = ByteBuffer.wrap(fileContents, 10, 4);
        int count = wrapped.getInt();
        
        int offsetPos = 14;
        for(int i=0; i<count; i++) {
            //get pid
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, 8);
            long pid = wrapped.getLong();
            String paddedPID = String.format("%09d", pid);
            offsetPos+=8;
            
            //get first name
            int endPos = offsetPos;
            while(fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos-offsetPos);
            String firstName = StandardCharsets.UTF_8.decode(wrapped).toString();
            offsetPos=endPos+1;
            
            //get middle name
            endPos = offsetPos;
            while(fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos-offsetPos);
            String middleName = StandardCharsets.UTF_8.decode(wrapped).toString();
            offsetPos=endPos+1;
            
            //get last name
            endPos = offsetPos;
            while(fileContents[endPos] != 36) {
                endPos++;
            }
            wrapped = ByteBuffer.wrap(fileContents, offsetPos, endPos-offsetPos);
            String lastName = StandardCharsets.UTF_8.decode(wrapped).toString();
            offsetPos=endPos+1+8;
            
            //create student object
            Student student = new Student(paddedPID,
                firstName.toLowerCase(), lastName.toLowerCase());
            student.setMiddleName(middleName.toLowerCase());
            list.add(student);
            
        }
    }


    /**
     * 
     * @return
     */
    public ArrayList<Student> studentList() {
        return list;
    }

    public void writeStudentDataFile(String filename) throws IOException {
        
        DataOutputStream os = new DataOutputStream(new FileOutputStream(filename, false));
        os.writeBytes("VTSTUDENTS");
        os.writeInt(list.size());
        
        for(int i=0; i<list.size(); i++) {
            Student temp = list.get(i);
            long pid = Long.valueOf(temp.getID()).longValue();
            os.writeLong(pid);
            os.writeBytes(temp.getFirstName());
            os.writeBytes("$");
            if(temp.getMiddleName() != null && !temp.getMiddleName().isEmpty()) {
                os.writeBytes(temp.getMiddleName());
            }
            os.writeBytes("$");
            os.writeBytes(temp.getLastName());
            os.writeBytes("$");
            os.writeBytes("GOHOKIES");
        }
        
        os.close(); 
    }
    
    
}
