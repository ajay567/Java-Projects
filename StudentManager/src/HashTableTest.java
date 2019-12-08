import java.util.ArrayList;

public class HashTableTest extends student.TestCase{
    private HashTable<String, Integer> myTable;
    
    public void setUp() {
        myTable = new HashTable<String, Integer>(128);
    }
    
    public void testPut() {
        myTable = new HashTable<String, Integer>(32);
        for(int i=0; i<32; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
        assertFalse(myTable.put("Table full", 2));
    }
    
    public void testGet() {
        for(int i=0; i<50; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
        for(int i=0; i<50; i++) {
            assertTrue(myTable.get("Testing"+ i).equals(i));
        }
        
        assertNull(myTable.get("null String"));
        
        
        myTable = new HashTable<String, Integer>(32);
        for(int i=0; i<32; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        for(int i=0; i<32; i++) {
            assertTrue(myTable.get("Testing"+ i).equals(i));
        }
    }
    
    public void testRemove() {
        for(int i=0; i<50; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
        for(int i=49; i>20; i--) {
            assertTrue(myTable.remove("Testing"+ i));
//            System.out.println(i);
//            myTable.remove("Testing"+ i);
        }
        assertFalse(myTable.remove("Testing"+ 49));
        assertTrue(myTable.remove("Testing"+ 20));
        
        ArrayList<Integer> values;
        boolean[] deleted;
        System.out.println("Test Remove 1");
        values = myTable.getValueArray();
        deleted = myTable.getDeletedArray();
        for(int i=0; i<100; i++) {
            System.out.println(i + ": "+ values.get(i));
            if(deleted[i]) {
                System.out.println("[DEL]");
            }
        }
        
        for(int i=30; i<50; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
        assertNull(myTable.get("Some value"));
        
        for(int i=0; i<20; i++) {
//            assertTrue(myTable.remove("Testing"+ i));
            assertTrue(myTable.get("Testing"+ i).equals(i));
        }
        
        for(int i=20; i<30; i++) {
            assertNull(myTable.get("Testing"+ i));
        }
        
        for(int i=30; i<50; i++) {
//            assertTrue(myTable.remove("Testing"+ i));
            assertTrue(myTable.get("Testing"+ i).equals(i));
        }
        
        for(int i=0; i<10000; i++) {
            myTable.put("Testing"+ i, i);
        }
        
        System.out.println("Test Remove 2");
        values = myTable.getValueArray();
        deleted = myTable.getDeletedArray();
        for(int i=0; i<100; i++) {
            System.out.println(i + ": "+ values.get(i));
            if(deleted[i]) {
                System.out.println("[DEL]");
            }
        }
        
        for(int i=0; i<20; i++) {
            assertTrue(myTable.remove("Testing"+ i));
            assertNull(myTable.get("Testing"+ i));
            
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
    }
    
    public void testGetValueArray() {
        for(int i=0; i<50; i++) {
            assertTrue(myTable.put("Testing"+ i, i));
        }
        
        System.out.println("Test Remove 3");
        ArrayList<Integer> values = myTable.getValueArray();
        for(int i=0; i<100; i++) {
            System.out.println(i + ": "+ values.get(i));
        }
        
    }
}
