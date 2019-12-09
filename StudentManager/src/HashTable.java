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
 * The Hash Table is implemented in this class. All the methods
 * like get and put are present here. It used the object we created
 * in HashEntry.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 * 
 * @param <K>
 *            key
 * @param <V>
 *            value
 */
public class HashTable<K, V> {

    /**
     * fields
     */
    private HashEntry<K, V>[] table;
    private boolean[] deleted;
    private int capacity;
    private int bucketSize = 32;


    /**
     * Constructor that takes capacity as a parameter
     * 
     * @param capacity
     *            the number of elements in a table
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new HashEntry[capacity];
        deleted = new boolean[capacity];
    }


    /**
     * This method inserts an element into the hash table.
     * 
     * @param key
     *            identifier for the table
     * @param value
     *            contains the information for the element
     * @return true if element is inserted
     */
    public boolean put(K key, V value) {
        HashEntry<K, V> newEntry = new HashEntry<K, V>(key, value);

        // get first free pos for key
        int pos = getEntryPos(key, true);
        if (pos >= 0) {
            table[pos] = newEntry;
            deleted[pos] = false;
            return true;
        }

        // bucket full
        return false;
    }


    /**
     * This method returns an element from the table as
     * requested
     * 
     * @param key
     *            identifier for the requested element
     * @return the element or null
     */
    public V get(K key) {
        int pos = getEntryPos(key, false);
        if (pos >= 0) {
            HashEntry<K, V> entry = table[pos];

            if (entry != null) {
                return entry.getValue();
            }
        }
        return null;
    }


    /**
     * This method removes a specific element from the table.
     * 
     * @param key
     *            identifier of element to be removed
     * @return true if remove is successful
     */
    public boolean remove(K key) {
        int pos = getEntryPos(key, false);
        if (pos >= 0) {
            if (deleted[pos]) {
                return false;
            }

            table[pos] = null;
            deleted[pos] = true;
            return true;
        }

        return false;
    }


    /**
     * Get the table index corresponding to a key
     * 
     * @param key
     *            Key to check
     * @return Index of the key if present in the hash table or
     *         the index of the first free slot if it is not. Returns -1
     *         if there is no room in the table to insert.
     */
    private int getEntryPos(K key, boolean insertion) {
        int pos = sfold(key.toString());
        int bucketStart = (pos / 32) * 32;
        int bucketEnd = bucketStart + 32;

        int i = 0;
        int checkPos = pos;
        while (i < bucketSize) {
            if (checkPos >= bucketEnd) {
                checkPos = bucketStart;
            }

            HashEntry<K, V> tempEntry = table[checkPos];

            if (tempEntry == null) {
                if (insertion) {
                    return checkPos;
                }
                else {
                    if (!deleted[pos]) {
                        return checkPos;
                    }
                }
            }
            else {
                if (tempEntry.matchesKey(key)) {
                    return checkPos;
                }
            }
            checkPos++;
            i++;
        }

        return -1;
    }


    /**
     * An array list of the hash table is returned using
     * this method.
     * 
     * @return array list of the table
     */
    public ArrayList<V> getValueArray() {
        ArrayList<V> valueArray = new ArrayList<V>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                valueArray.add((V)table[i].getValue());
            }
            else {
                valueArray.add(null);
            }
        }
        return valueArray;
    }


    /**
     * Method to get the array of deleted elements
     * 
     * @return boolean array
     */
    public boolean[] getDeletedArray() {
        return deleted;
    }


    /**
     * Sfold returns a position for the element based on its
     * key value.
     * 
     * @param s
     *            the key value
     * @return position in the hash table
     */
    private int sfold(String s) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return (int)(Math.abs(sum) % this.capacity);
    }
}
