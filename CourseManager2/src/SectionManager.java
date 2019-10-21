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
 * This method creates a section object for the sections of
 * the course. It has tree BST's and an array.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.19.20
 *
 */
public class SectionManager {

    /**
     * fields
     */
    private BST<Student> name = new BST<Student>();
    private BST<String> pid = new BST<String>();
    private BST<Score> score = new BST<Score>();
    private ArrayList<Student> sectionList = new ArrayList<Student>();


    /**
     * Constructor
     */
    public SectionManager() {
        // Does nothing
    }


    /**
     * Getter method for name BST.
     * 
     * @return the name BST
     */
    public BST<Student> getTreeName() {
        return name;
    }


    /**
     * Getter method for the PID BST
     * 
     * @return the tree for PID
     */
    public BST<String> getTreePID() {
        return pid;
    }


    /**
     * Getter method for the Score BST
     * 
     * @return the tree score
     */
    public BST<Score> getTreeScore() {
        return score;
    }


    /**
     * Getter method for the Array of a section.
     * 
     * @return the array list for a section.
     */
    public ArrayList<Student> getSectionList() {
        return sectionList;
    }

}
