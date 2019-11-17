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
        Integer[] myList = {1,2,3,4,5,6,7};
        myHeap = new MaxHeap<Integer>(myList, 7, 7);
        
        assertEquals(myHeap.getSize(), 7);
        
        for(int i=7; i>0; i--) {
            assertEquals(myHeap.removemax(), (Integer)i);
        }
        
        assertNull(myHeap.removemax());
        
    }
    
    @Test
    public final void testMaxHeap2() {
        MaxHeap<Double> myHeap;
        Double[] myList = {999.4230041825355, 995.4062495543849, 683.1607533366878, 914.7365202614727, 897.2117099894964, 520.3444159730717, 670.6172357122157, 790.6492469424579, 833.7961470055732, 344.4436816511985, 716.7725202075407, 73.67314349762633, 416.85667013589034, 84.68124583566772, 613.249016195168, 256.4353357479768, 448.96459699672164, 240.89273013166402, 248.20771128183972, 167.04988242860753};
        myHeap = new MaxHeap<Double>(myList, 20, 20);
        
        assertEquals(myHeap.getSize(), 20);
        for(int i=0; i<20; i++) {
            System.out.println(myHeap.removemax());
        }
    }
    
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
              
    }

}
