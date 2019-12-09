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
 * This class creates an object for a student which is used as
 * the values in the hash table. It contains two things. The
 * name and the essay handle.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class StudentRecord {

    /**
     * fields
     */
    private MemoryHandle name;
    private MemoryHandle essay;


    /**
     * Constructor takes two parameters.
     * 
     * @param name
     *            handle for name
     * @param essay
     *            handle for essay
     */
    public StudentRecord(MemoryHandle name, MemoryHandle essay) {
        this.name = name;
        this.essay = essay;
    }


    /**
     * Getter method for name handle
     * 
     * @return the name handle
     */
    public MemoryHandle getName() {
        return this.name;
    }


    /**
     * Setter method for name handle
     * 
     * @param name
     *            the name handle
     */
    public void setName(MemoryHandle name) {
        this.name = name;
    }


    /**
     * Getter method for essay handle
     * 
     * @return the essay handle
     */
    public MemoryHandle getEssay() {
        return this.essay;
    }


    /**
     * Setter method for essay handle
     * 
     * @param essay
     *            the essay handle
     */
    public void setEssay(MemoryHandle essay) {
        this.essay = essay;
    }
}
