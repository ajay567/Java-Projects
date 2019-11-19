import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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
public class VTStudentsManager {

    private RandomAccessFile fil;


    /**
     * 
     */
    public VTStudentsManager() {
        // Does Nothing
    }


    /**
     * 
     * @param fileName
     * @throws IOException
     */
    public void printOutStudents(String fileName, String studentFileName) throws IOException {
       // System.out.println("printing: "+fileName);
        fil = new RandomAccessFile(fileName, "r");

        StudentFileParser parse = new StudentFileParser();
        parse.readsStudentDataFile(studentFileName);
        ArrayList<Student> student = parse.studentList();
        
        
        int countLines = 0;
        int countStudents = 0;
        int bytesTaken = 0;
        long fileLength = fil.length();

        while (bytesTaken < fileLength && countStudents <= 100) {
            byte[] buffer = readbytes(bytesTaken, fileLength, fil);
            bytesTaken += buffer.length;

            int startPos = 0;
            for (int i = 0; i < buffer.length / 16; i++) {
                ByteBuffer wrapped = ByteBuffer.wrap(buffer, startPos, 8);
                long pid = wrapped.getLong();
                startPos += 8;
                countLines++;
                if (matches(String.valueOf(pid))) {
                    wrapped = ByteBuffer.wrap(buffer, startPos, 8);
                    double score = wrapped.getDouble();
                    startPos += 8;
 
                    for (int j = 0; j < student.size(); j++) {
                        if (pid == student.get(j).getID()) {
                            System.out.println(pid + ", " + student.get(j)
                                .getName() + " at rank " + countLines
                                + " with Ascore " + score);
                            countStudents++;
                        }
                    }

                }
                else {
                    startPos += 8;
                }
            }

        }
        
//        fil.seek(0);
//        int printingPos = 0;
//        byte[] buffer200 = new byte[(int)fil.length()];
//        fil.read(buffer200);
//        for (int i = 0; i < fil.length()/16; i++ ) {
//            ByteBuffer wrapped = ByteBuffer.wrap(buffer200, printingPos, 8);
//            printingPos += 8;
//            long pid = wrapped.getLong();
//            
//            wrapped = ByteBuffer.wrap(buffer200, printingPos, 8);
//            double score = wrapped.getDouble();
//            printingPos += 8;
//            //System.out.println("Line:" + i + " Pid:" + pid + " Score:" + score);
//            System.out.println(pid + " " + score);
//        }

    }


    /**
     * 
     * @param bytesTaken
     * @param fileLength
     * @param fil
     * @return
     * @throws IOException
     */
    public byte[] readbytes(
        int bytesTaken,
        long fileLength,
        RandomAccessFile fil)
        throws IOException {
        byte[] buffer;
        if (bytesTaken + 1024 * 16 < fileLength) {
            buffer = new byte[1024 * 16];
            fil.seek(bytesTaken);
            fil.readFully(buffer, 0, 1024 * 16);
        }
        else {
            buffer = new byte[(int)(fileLength - bytesTaken)];
            fil.seek(bytesTaken);
            fil.readFully(buffer, 0, (int)(fileLength - bytesTaken));
        }
        return buffer;
    }


    /**
     * 
     * @param pid
     * @return
     */
    public boolean matches(String pid) {
        int count = 0;
        if (pid.charAt(0) == '9') {
            count++;
        }
        if (pid.charAt(1) == '0') {
            count++;
        }
        if (pid.charAt(2) == '9') {
            count++;
        }
        return (count == 3);
    }

}
