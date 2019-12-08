
public class MemoryHandle {
    private int start;
    private int length;
    
    public MemoryHandle(int start, int length) {
        this.start = start;
        this.length = length;
        System.out.println("Start:" + start + " Length:" + length);
    }
    
    public int getStart() {
        return this.start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
}
