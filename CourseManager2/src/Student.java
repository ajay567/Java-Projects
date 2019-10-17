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
 * Student Class contains each student's information
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.24.21
 *
 */
public class Student implements Comparable<Student> {

    /**
     * Class variables
     */
    private Name nameObj;
    private String middleName; // Middle name
    private String name; // Full name (First and last name concatenated)
    private int score; // Student score
    private String grade; // Student letter grade
    private int id; // Student id


    /**
     * Student constructor method
     * 
     * @param firstName
     *            First name
     * @param lastName
     *            Last name
     */
    public Student(int id, String firstName, String lastName) {
        nameObj = new Name();
        this.id = id;
        String tempFName = nameObj.getFirstName();
        tempFName = firstName;
        String tempLName = nameObj.getLastName();
        tempLName = lastName;
        this.name = firstName + " " + lastName;
    }


    /**
     * Getter method for id
     * 
     * @return Student's id
     */
    public int getID() {
        return id;
    }


    /**
     * Getter method for first name
     * 
     * @return Student's first name
     */
    public String getFirstName() {
        return nameObj.getFirstName();
    }


    /**
     * Getter method for middle name
     * 
     * @return Student's middle name
     */
    public void setMiddleName(String tempMidName) {
        tempMidName = middleName;
    }


    /**
     * Getter method for middle name
     * 
     * @return Student's middle name
     */
    public String getMiddleName() {
        return middleName;
    }


    /**
     * Getter method for last name
     * 
     * @return Student's last name
     */
    public String getLastName() {
        return nameObj.getLastName();
    }


    /**
     * Getter method for full name
     * 
     * @return Student's full name
     */
    public String getName() {
        return name;
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
     * Setter method for grade
     * 
     * @param newGrade
     *            Student's letter grade
     */
    public void setGrade(String newGrade) {
        grade = newGrade;
    }


    /**
     * Getter method for grade
     * 
     * @return Student's letter grade
     */
    public String getGrade() {
        return grade;
    }


    /**
     * Defines how to compare Student objects to implement Comparable
     */
    @Override
    public int compareTo(Student other) {
        Student obj = (Student)other;
        int a = nameObj.getLastName().compareTo(obj.nameObj.getLastName());
        if (a == 0) {
            a = nameObj.getFirstName().compareTo(obj.nameObj.getFirstName());
        }
        nameObj.getLastName().compareTo(obj.nameObj.getLastName());
        return a;
    }
}
