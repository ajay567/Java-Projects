import java.util.ArrayList;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * Test class for HashTable.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class HashTableTest extends student.TestCase {

    /**
     * fields
     */
    private HashTable<String, Integer> myTable;


    /**
     * Set up variables for testing
     */
    public void setUp() {
        myTable = new HashTable<String, Integer>(128);
    }


    /**
     * Tests put method of the HashTable
     */
    public void testPut() {
        myTable = new HashTable<String, Integer>(32);
        for (int i = 0; i < 32; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }
        assertFalse(myTable.put("Table full", 2));
    }


    /**
     * Tests the get method of the HashTable.
     */
    public void testGet() {
        for (int i = 0; i < 50; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }

        for (int i = 0; i < 50; i++) {
            assertTrue(myTable.get("Testing" + i).equals(i));
        }

        assertNull(myTable.get("null String"));

        myTable = new HashTable<String, Integer>(32);
        for (int i = 0; i < 32; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }
        for (int i = 0; i < 32; i++) {
            assertTrue(myTable.get("Testing" + i).equals(i));
        }
    }


    /**
     * Tests the remove method of HashTable, thoroughly.
     */
    public void testRemove() {
        for (int i = 0; i < 50; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }

        for (int i = 49; i > 20; i--) {
            assertTrue(myTable.remove("Testing" + i));
        }
        assertFalse(myTable.remove("Testing" + 49));
        assertTrue(myTable.remove("Testing" + 20));

        ArrayList<Integer> values;
        boolean[] deleted;
        System.out.println("Test Remove 1");
        values = myTable.getValueArray();
        deleted = myTable.getDeletedArray();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + values.get(i));
            if (deleted[i]) {
                System.out.println("[DEL]");
            }
        }

        for (int i = 30; i < 50; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }

        assertNull(myTable.get("Some value"));

        for (int i = 0; i < 20; i++) {
            assertTrue(myTable.get("Testing" + i).equals(i));
        }

        for (int i = 20; i < 30; i++) {
            assertNull(myTable.get("Testing" + i));
        }

        for (int i = 30; i < 50; i++) {
            assertTrue(myTable.get("Testing" + i).equals(i));
        }

        for (int i = 0; i < 10000; i++) {
            myTable.put("Testing" + i, i);
        }

        System.out.println("Test Remove 2");
        values = myTable.getValueArray();
        deleted = myTable.getDeletedArray();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + values.get(i));
            if (deleted[i]) {
                System.out.println("[DEL]");
            }
        }

        for (int i = 0; i < 20; i++) {
            assertTrue(myTable.remove("Testing" + i));
            assertNull(myTable.get("Testing" + i));

            assertTrue(myTable.put("Testing" + i, i));
        }

    }


    /**
     * Tests getValueArray method from HashTable.
     */
    public void testGetValueArray() {
        for (int i = 0; i < 50; i++) {
            assertTrue(myTable.put("Testing" + i, i));
        }

        System.out.println("Test Remove 3");
        ArrayList<Integer> values = myTable.getValueArray();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + values.get(i));
        }

    }
}
