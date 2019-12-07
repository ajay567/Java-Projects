
public class MemoryManagerTest extends student.TestCase{
    private MemoryManager manager;
    
    public void setUp() {
        manager = new MemoryManager();
    }
    
    public void testGetBlock() {
        MemoryHandle handle1 = manager.getBlock(10);
        assertEquals(handle1.getLength(), 10);
        assertEquals(handle1.getStart(), 0);
        
        MemoryHandle handle2 = manager.getBlock(20);
        assertEquals(handle2.getLength(), 20);
        assertEquals(handle2.getStart(), 10);
        
        MemoryHandle handle3 = manager.getBlock(30);
        assertEquals(handle3.getLength(), 30);
        assertEquals(handle3.getStart(), 30);
        
        manager.removeBlock(handle3);
        manager.removeBlock(handle1);
        
        MemoryHandle handle4 = manager.getBlock(40);
        assertEquals(handle4.getLength(), 40);
        assertEquals(handle4.getStart(), 30);
        
        MemoryHandle handle5 = manager.getBlock(5);
        assertEquals(handle5.getLength(), 5);
        assertEquals(handle5.getStart(), 0);
        
        MemoryHandle handle6 = manager.getBlock(5);
        assertEquals(handle6.getLength(), 5);
        assertEquals(handle6.getStart(), 5);
    }
    
    public void testRemoveBlock() {
        MemoryHandle handle1 = manager.getBlock(10);
        MemoryHandle handle2 = manager.getBlock(20);
        MemoryHandle handle3 = manager.getBlock(30);
        MemoryHandle handle4 = manager.getBlock(10);
        
        manager.removeBlock(handle1);
        manager.removeBlock(handle3);
        manager.removeBlock(handle2);
        
        MemoryHandle handle5 = manager.getBlock(60);
        assertEquals(handle5.getLength(), 60);
        assertEquals(handle5.getStart(), 0);
        
        manager.removeBlock(handle5);
        
        handle1 = manager.getBlock(10);
        handle2 = manager.getBlock(20);
        handle3 = manager.getBlock(30);
        
        manager.removeBlock(handle3);
        manager.removeBlock(handle2);
        
        MemoryHandle handle6 = manager.getBlock(50);
        assertEquals(handle6.getLength(), 50);
        assertEquals(handle6.getStart(), 10);
        
    }
}
