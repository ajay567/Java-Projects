
public class StudentRecordTest extends student.TestCase{
    private StudentRecord sr;
    
    public void testStudentRecord() {
        MemoryHandle name = new MemoryHandle(0,10);
        MemoryHandle essay = new MemoryHandle(20,30);
        sr = new StudentRecord(name, essay);
        
        assertEquals(name.getStart(), sr.getName().getStart());
        assertEquals(name.getLength(), sr.getName().getLength());
        
        assertEquals(essay.getStart(), sr.getEssay().getStart());
        assertEquals(essay.getLength(), sr.getEssay().getLength());
        
        sr.setName(name);
        sr.setEssay(essay);
    }
}
