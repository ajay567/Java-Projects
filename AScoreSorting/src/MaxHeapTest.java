import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaxHeapTest {
    
    MaxHeap<Integer> myHeap;
    
    @Before
    public void setUp() throws Exception {
        myHeap = new MaxHeap<Integer>(10);
    }


//    @Test
//    public final void testMaxHeap1() {
//        Integer[] myList = {1,2,3,4,5,6,7,8,9,0};
//        myHeap = new MaxHeap<Integer>(myList, 10, 10);
//        
//        assertEquals(myHeap.getSize(), 10);
//        
//        for(int i=9; i>=0; i--) {
//            assertEquals(myHeap.removemax(), (Integer)i);
//        }
//        
//        assertNull(myHeap.removemax());
//        
//    }
    
    @Test
    public final void testInsert() {
        assertEquals(myHeap.getSize(), 0);
        myHeap.insert(21);
        myHeap.insert(31);
        myHeap.insert(25);
        myHeap.insert(42);
        myHeap.insert(11);
        myHeap.insert(99);
        myHeap.insert(50);
        myHeap.insert(30);
        myHeap.insert(80);
        myHeap.insert(48);
        
        assertEquals(myHeap.removemax(), (Integer)99);
        myHeap.insert(51);
        
        myHeap.removemax();
        myHeap.modify(myHeap.getSize(), 200);
        
        myHeap.removemax();
        myHeap.insert(88);
        
        myHeap.removemax();
        myHeap.modify(myHeap.getSize(), 300);
        
        
        
        
        
        assertEquals(myHeap.getSize(), 10);
        
        assertEquals(myHeap.getSize(), 6);
        
        
        assertEquals(myHeap.removemax(), (Integer)40);
        
        assertEquals(myHeap.removemax(), (Integer)29);
        assertEquals(myHeap.removemax(), (Integer)25);
        
        assertEquals(myHeap.getSize(), 2);
        
        myHeap.modify(myHeap.getSize(), 50);
        
        assertEquals(myHeap.removemax(), (Integer)25);
        
        myHeap.insert(21);
        
        assertEquals(myHeap.removemax(), (Integer)25);
        myHeap.insert(31);
        assertEquals(myHeap.removemax(), (Integer)25);
        myHeap.insert(25);
        
        assertEquals(myHeap.removemax(), (Integer)31);
        
    }

}
