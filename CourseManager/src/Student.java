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
 * @version 2019.9.21
 *
 */
public class Student implements Comparable<Student> {

    /**
     * fields
     */
    private String firstName;
    private String lastName;
    private String name;
    private int score;
    private String grade;
    private String id;


    /**
     * 
     */
    public Student() {
        // Does Nothing
    }


    /**
     * 
     * @param newId
     *            String
     */
    public void setID(String newId) {
        id = newId;
    }


    /**
     * 
     * @return id
     */
    public String getID() {
        return id;
    }


    /**
     * 
     * @param fName
     *            String
     */
    public void setFirstName(String fName) {
        firstName = fName;

    }


    /**
     * 
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * 
     * @param lName
     *            String
     */
    public void setLastName(String lName) {
        lastName = lName;

    }


    /**
     * 
     * @return String
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * 
     * @param newName
     *            String
     */
    public void setName(String newName) {

        name = newName;
    }


    /**
     * 
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * 
     * @param newScore
     *            String
     */
    public void setScore(int newScore) {
        score = newScore;
    }


    /**
     * 
     * @return String
     */
    public int getScore() {
        return score;
    }


    /**
     * 
     * @param newGrade
     *            String
     */
    public void setGrade(String newGrade) {
        grade = newGrade;
    }


    /**
     * 
     * @return String
     */
    public String getGrade() {
        return grade;
    }


    /**
     * 
     */
    @Override
    public int compareTo(Student other) {
        Student obj = (Student)other;
        int a = getLastName().compareTo(obj.getLastName());
        if (a == 0) {
            a = getFirstName().compareTo(obj.getFirstName());
        }
        getLastName().compareTo(obj.getLastName());
        return a;
    }
}
