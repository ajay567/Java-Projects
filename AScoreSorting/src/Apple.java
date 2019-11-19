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
 * Apple Object class for score and pid of all the students.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class Apple implements Comparable<Apple> {

    /**
     * fields
     */
    private long pid;
    private double score;


    /**
     * Constructor that takes in two parameters
     * 
     * @param pid
     *            the pid of a student
     * @param score
     *            the score of a student
     */
    public Apple(long pid, double score) {
        this.pid = pid;
        this.score = score;
    }


    /**
     * Getter method for pid
     * 
     * @return pid value
     */
    public long getPid() {
        return pid;
    }


    /**
     * Getter method for score
     * 
     * @return score value
     */
    public double getScore() {
        return score;
    }


    /**
     * Method to compare the two apple objects based on the score
     * of the students.
     * 
     * @param other
     *            apple object
     * @return returns an integer
     */
    @Override
    public int compareTo(Apple other) {
        return Double.compare(this.score, other.score);
    }

}
