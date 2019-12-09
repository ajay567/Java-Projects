import java.io.IOException;

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
 * Tests class for StudentRecord.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class StudentRecordTest extends student.TestCase {

    /**
     * fields
     */
    private StudentRecord sr;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        MemoryHandle name = new MemoryHandle(0, 10);
        MemoryHandle essay = new MemoryHandle(20, 30);
        sr = new StudentRecord(name, essay);
    }


    /**
     * Tests the methods from the student records
     * 
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public void testStudentRecord() throws IOException {
        MemoryHandle name = new MemoryHandle(0, 10);
        MemoryHandle essay = new MemoryHandle(20, 30);

        assertEquals(name.getStart(), sr.getName().getStart());
        assertEquals(name.getLength(), sr.getName().getLength());
        String[] args = { "SampleInput_ajay.txt", null, "64", "ajaytest.bin" };
        Studentmanager manager = new Studentmanager();
        manager.main(args);
        assertEquals(essay.getStart(), sr.getEssay().getStart());
        assertEquals(essay.getLength(), sr.getEssay().getLength());

        sr.setName(name);
        sr.setEssay(essay);
    }
}
