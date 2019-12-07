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
 * This class contains the main method. It just has to create the
 * objects of the required classes and call methods that perform
 * external sorting. The methods are passed the names of the
 * files that have to be sorted.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class HashTable<K,V> {
    // key: String pid
    // value: StudentRecord
    private HashEntry<K,V>[] table;
    private int capacity;
    private int bucketSize = 32;
    
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new HashEntry[capacity];
    }
    
    public boolean put(K key, V value) {
        HashEntry<K,V> newEntry = new HashEntry<K,V>(key, value);
        
        // get first free pos for key
        int pos = getEntryPos(key);
        if(pos >= 0) {
            table[pos] = newEntry;
            return true;  
        }
        
        // bucket full
        return false;
    }
    
    public V get(K key) {
        int pos = getEntryPos(key);
        HashEntry<K,V> entry = table[pos]; 
        
        if(entry != null) {
            return entry.getValue();
        }
        return null;
    }
    
    /**
     * Get the table index corresponding to a key
     * @param key Key to check
     * @return Index of the key if present in the hash table or 
     * the index of the first free slot if it is not. Returns -1
     * if there is no room in the table to insert.
     */
    private int getEntryPos(K key) {
        int pos = sfold(key.toString());
        int bucketStart = (pos/32) * 32;
        int bucketEnd = bucketStart + 32;
        boolean found = false;
        
        int i=0;
        int checkPos = pos;
        while(!found && i<bucketSize) {
            if(checkPos >= bucketEnd) {
                checkPos = bucketStart;
            }
            
            HashEntry<K, V> tempEntry = table[checkPos];
            if(tempEntry == null) {
                return checkPos;
            }
            else {
                if(tempEntry.matchesKey(key)) {
                    return checkPos;
                }
            }
            
            checkPos++;
            i++;
        }
        
        return -1;
    }
    
    private int sfold(String s) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
          char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
          long mult = 1;
          for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
          }
        }

        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
          sum += c[k] * mult;
          mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return (int)(Math.abs(sum) % this.capacity);
      }
}
