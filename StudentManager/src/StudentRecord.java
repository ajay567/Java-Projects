
public class StudentRecord {
    private MemoryHandle name;
    private MemoryHandle essay;
    
    public StudentRecord(MemoryHandle name) {
        this.name = name;
    }
    
    public StudentRecord(MemoryHandle name, MemoryHandle essay) {
        this.name = name;
        this.essay = essay;
    }
    
    public MemoryHandle getName() {
        return this.name;
    }
    
    public void setName(MemoryHandle name) {
        this.name = name;
    }
    
    
    public MemoryHandle getEssay() {
        return this.essay;
    }
    
    public void setEssay(MemoryHandle essay) {
        this.essay = essay;
    }
}
