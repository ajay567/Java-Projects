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
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.09.11
 */
public class Apple implements Comparable<Apple> {

    /**
     * fields
     */
    private long pid;
    private double score;


    /**
     * 
     * @param pid
     * @param score
     */
    public Apple(long pid, double score) {
        this.pid = pid;
        this.score = score;
    }


    /**
     * 
     * @return
     */
    public long getPid() {
        return pid;
    }


    /**
     * 
     * @return
     */
    public double getScore() {
        return score;
    }


    /**
     * Defines how to compare Apple objects to implement Comparable
     * 
     * @param other
     *            score object
     * @return returns an integer
     */
    @Override
    public int compareTo(Apple other) {
        return Double.compare(this.score, other.score);
    }

}