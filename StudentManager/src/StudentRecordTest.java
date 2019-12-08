import java.io.IOException;

public class StudentRecordTest extends student.TestCase{
    
    /**
     * fields    
     */
    private StudentRecord sr;
    
    /**
     * 
     */
    public void setUp() {
        MemoryHandle name = new MemoryHandle(0,10);
        MemoryHandle essay = new MemoryHandle(20,30);
        sr = new StudentRecord(name, essay);
    }
    
    /**
     * 
     * @throws IOException
     */
    public void testStudentRecord() throws IOException {
        MemoryHandle name = new MemoryHandle(0,10);
        MemoryHandle essay = new MemoryHandle(20,30);
        
        assertEquals(name.getStart(), sr.getName().getStart());
        assertEquals(name.getLength(), sr.getName().getLength());
        CommandFileParser parser  = new CommandFileParser();
        parser.readFile("SampleInput.txt", 64, "ajaytest.bin");
        assertEquals(essay.getStart(), sr.getEssay().getStart());
        assertEquals(essay.getLength(), sr.getEssay().getLength());
        
        sr.setName(name);
        sr.setEssay(essay);
    }
}
