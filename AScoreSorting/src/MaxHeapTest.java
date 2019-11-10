import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaxHeapTest {
    
    MaxHeap<Integer> myHeap;
    
    @Before
    public void setUp() throws Exception {
        myHeap = new MaxHeap<Integer>(10);
    }


    @Test
    public final void testMaxHeap1() {
        Integer[] myList = {1,2,3,4,5,6,7,8,9,0};
        myHeap = new MaxHeap<Integer>(myList, 10, 10);
        
        assertEquals(myHeap.getSize(), 10);
        
        for(int i=9; i>=0; i--) {
            assertEquals(myHeap.removemax(), (Integer)i);
        }
        
        assertNull(myHeap.removemax());
        
    }
    
    
    public final void testInsert() {
        assertEquals(myHeap.getSize(), 0);
        myHeap.insert(5);
        myHeap.insert(1);
        myHeap.insert(9);
        myHeap.insert(3);
        myHeap.insert(4);
        
        assertEquals(myHeap.getSize(), 5);
        
        assertEquals(myHeap.removemax(), (Integer)9);
        assertEquals(myHeap.removemax(), (Integer)5);
        assertEquals(myHeap.removemax(), (Integer)4);
        assertEquals(myHeap.removemax(), (Integer)3);
        assertEquals(myHeap.removemax(), (Integer)1);
        
    }

}
