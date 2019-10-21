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
 * Score object contains the pid and the score.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.10.20
 */
public class Score implements Comparable<Score> {

    /**
     * fields
     */
    private String id;
    private int score;


    /**
     * Constructor
     * 
     * @param id
     *            pid of a student
     * @param score
     *            score of a student
     */
    public Score(String id, int score) {
        this.id = id;
        this.score = score;
    }


    /**
     * Setter method for id
     * 
     * @param newId
     *            Id to be set
     */
    public void setId(String newId) {
        id = newId;
    }


    /**
     * Getter method for id
     * 
     * @return Student's id
     */
    public String getID() {
        return id;
    }


    /**
     * Setter method for score
     * 
     * @param newScore
     *            Score to be set
     */
    public void setScore(int newScore) {
        score = newScore;
    }


    /**
     * Getter method for score
     * 
     * @return Student's score
     */
    public int getScore() {
        return score;
    }


    /**
     * Defines how to compare Student objects to implement Comparable
     * 
     * @param other
     *            score object
     * @return returns an integer
     */
    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.score, other.score);
    }

}
