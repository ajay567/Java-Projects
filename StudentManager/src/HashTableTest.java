
public class HashTableTest extends student.TestCase{
    private HashTable<String, Integer> myTable;
    
    public void setUp() {
        myTable = new HashTable<String, Integer>(100);
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
    }
}
