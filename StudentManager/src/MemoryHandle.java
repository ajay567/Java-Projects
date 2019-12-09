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
 * A object for the handle is being created in this class. It
 * contains two things. The offset for a string and its length
 * in correspondence to the memory file.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class MemoryHandle {

    /**
     * fields
     */
    private int start;
    private int length;


    /**
     * Constructor with two parameters
     * 
     * @param start
     *            offset in the memory file
     * @param length
     *            length of the string
     */
    public MemoryHandle(int start, int length) {
        this.start = start;
        this.length = length;
    }


    /**
     * Getter method for start
     * 
     * @return the offset
     */
    public int getStart() {
        return this.start;
    }


    /**
     * Setter method for start
     * 
     * @param start
     *            the offset
     */
    public void setStart(int start) {
        this.start = start;
    }


    /**
     * Getter method for length
     * 
     * @return length of the string
     */
    public int getLength() {
        return this.length;
    }


    /**
     * Setter method for the length
     * 
     * @param length
     *            length of the string
     */
    public void setLength(int length) {
        this.length = length;
    }
}
