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
 * This class is used as an object for the hash table. It contains
 * two things one is the key and the other is the value.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 * @param <K>
 *            key
 * @param <V>
 *            Value
 */
public class HashEntry<K, V> {

    /**
     * fields
     */
    private K key;
    private V value;


    /**
     * Constructor with two parameters
     * 
     * @param key
     *            identifies the record
     * @param value
     *            generic object
     */
    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Method to check if a key equals the
     * provided key.
     * 
     * @param key1
     *            the one to be checked
     * @return true if keys are equal
     */
    public boolean matchesKey(K key1) {
        return this.key.equals(key1);
    }


    /**
     * Getter method for value.
     * 
     * @return the value
     */
    public V getValue() {
        return this.value;
    }


    /**
     * Getter method for key.
     * 
     * @return the key
     */
    public K getKey() {
        return this.key;
    }


    /**
     * Setter method for value
     * 
     * @param value
     *            the value
     */
    public void setValue(V value) {
        this.value = value;
    }
}
