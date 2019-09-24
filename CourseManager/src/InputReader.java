import java.util.ArrayList;
import java.util.Iterator;

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
public class InputReader {

    /**
     * 
     */
    public InputReader() {
        // Does Nothing
    }


    /**
     * 
     * @param treeIndex
     *            Integer
     */
    public void section(int treeIndex) {

        System.out.println("switch to section " + treeIndex);

    }


    /**
     * 
     * @param count
     *            Integer
     * @param id
     *            Integer
     * @param treeIndex
     *            Integer
     * @return String
     */
    public String createID(int count, String id, int treeIndex) {

        if (count < 10) {
            id = "0" + treeIndex + "000" + count;

        }
        else if (count < 100) {
            id = "0" + treeIndex + "00" + count;

        }
        else {
            id = "0" + treeIndex + "0" + count;
        }

        return id;
    }


    /**
     * 
     * @param score
     *            Integer
     * @param student
     *            String
     * @param name
     *            String
     */
    public void scoreHelper(int score, Student student, String name) {
        if (score <= 100 && score >= 0) {
            student.setScore(score);
            System.out.println("Update " + name + " record, score = " + score);
        }
        else {
            System.out.println("Scores have to be integers in range 0 to 100.");
        }

    }


    /**
     * 
     * @param student1
     *            String
     * @param treeIndex
     *            Integer
     * @param list
     *            ArrayList
     */
    public void printInsertHelper(
        Student student1,
        int treeIndex,
        ArrayList<BST<Student>> list) {
        System.out.println(list.get(treeIndex).find(student1).getName()
            + " is already in section " + treeIndex);
        System.out.println(list.get(treeIndex).find(student1).getID() + ", "
            + list.get(treeIndex).find(student1).getName() + ", score = " + list
                .get(treeIndex).find(student1).getScore());

    }


    /**
     * 
     * @param score
     *            Integer
     * @param student1
     *            Student
     * @param name
     *            String
     * @param treeIndex
     *            Integer
     * @param list
     *            ArrayList<BST<Student>>
     */
    public void scoreHelper2(
        int score,
        Student student1,
        String name,
        int treeIndex,
        ArrayList<BST<Student>> list) {
        if (score <= 100 && score >= 0) {
            list.get(treeIndex).find(student1).setScore(score);
            System.out.println("Update " + name + " record, score = " + score);
        }
        else {
            System.out.println("Scores have to be integers in range 0 to 100.");
        }

    }


    /**
     * 
     * @param check
     *            ArrayList<Student>
     * @param n1
     *            String
     * @param temp
     *            Integer
     * @param treeIndex
     *            Integer
     * @return Integer
     */
    public int search1Helper(
        ArrayList<Student> check,
        String n1,
        int temp,
        int treeIndex) {
        for (int i = 0; i < check.size(); i++) {
            if (check.get(i).getFirstName().equals(n1) || check.get(i)
                .getLastName().equals(n1)) {
                temp++;
            }
        }
        System.out.println("Search results for " + n1 + ":");
        if (temp == 0) {
            System.out.println(n1 + " was found in " + temp
                + " records in Section " + treeIndex);
        }
        else {
            for (int i = 0; i < check.size(); i++) {
                if (check.get(i).getFirstName().equals(n1) || check.get(i)
                    .getLastName().equals(n1)) {
                    System.out.println(check.get(i).getID() + ", " + check.get(
                        i).getName() + ", score = " + check.get(i).getScore());
                }
            }
            System.out.println(n1 + " was found in " + temp
                + " records in Section " + treeIndex);
        }

        return temp;

    }


    /**
     * 
     * @param list
     *            ArrayList
     * @param treeIndex
     *            Integer
     * @param student1
     *            Student
     */
    public void search2Param(
        ArrayList<BST<Student>> list,
        int treeIndex,
        Student student1) {
        System.out.println("Found " + list.get(treeIndex).find(student1).getID()
            + ", " + list.get(treeIndex).find(student1).getName() + ", score = "
            + list.get(treeIndex).find(student1).getScore());
    }


    /**
     * 
     * @param score
     *            Integer
     * @param student1
     *            Student
     * @param temp1
     *            String
     * @param temp2
     *            String
     * @param treeIndex
     *            Integer
     * @param list
     *            ArrayList
     */
    public void scoreHelper3(
        int score,
        Student student1,
        String temp1,
        String temp2,
        int treeIndex,
        ArrayList<BST<Student>> list) {
        if (score <= 100 && score >= 0) {
            list.get(treeIndex).find(student1).setScore(score);
            System.out.println("Update " + temp1 + " " + temp2
                + " record, score = " + score);
        }
        else {
            System.out.println("Scores have to be integers in range 0 to 100.");
        }

    }


    /**
     * 
     * @param check
     *            ArrayList
     * @param list
     *            ArrayList
     * @param treeIndex
     *            Integer
     */
    public void dumpSection(
        ArrayList<Student> check,
        ArrayList<BST<Student>> list,
        int treeIndex) {

        for (int i = 0; i < check.size(); i++) {

            System.out.println(check.get(i).getID() + ", " + check.get(i)
                .getName() + ", score = " + check.get(i).getScore());

        }

        System.out.println("Size = " + list.get(treeIndex).size());

    }


    /**
     * 
     * @param check
     *            ArrayList
     */
    public void grade(ArrayList<Student> check) {

        int a1 = 0;
        int a2 = 0;
        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int f = 0;

        for (int i = 0; i < check.size(); i++) {
            if (check.get(i).getScore() >= 90) {
                check.get(i).setGrade("A");
                a1++;
            }
            else if (check.get(i).getScore() >= 85) {
                check.get(i).setGrade("A-");
                a2++;
            }
            else if (check.get(i).getScore() >= 80) {
                check.get(i).setGrade("B+");
                b1++;
            }
            else if (check.get(i).getScore() >= 75) {
                check.get(i).setGrade("B");
                b2++;
            }
            else if (check.get(i).getScore() >= 70) {
                check.get(i).setGrade("B-");
                b3++;
            }
            else if (check.get(i).getScore() >= 65) {
                check.get(i).setGrade("C+");
                c1++;
            }
            else if (check.get(i).getScore() >= 60) {
                check.get(i).setGrade("C");
                c2++;
            }
            else if (check.get(i).getScore() >= 57.5) {
                check.get(i).setGrade("C-");
                c3++;
            }
            else if (check.get(i).getScore() >= 55) {
                check.get(i).setGrade("D+");
                d1++;
            }
            else if (check.get(i).getScore() >= 52.5) {
                check.get(i).setGrade("D");
                d2++;
            }
            else if (check.get(i).getScore() >= 50) {
                check.get(i).setGrade("D-");
                d3++;
            }
            else {
                check.get(i).setGrade("F");
                f++;
            }

        }

        System.out.println("grading completed:");
        if (f != 0) {
            System.out.println(f + " students with grade F");
        }
        if (d3 != 0) {
            System.out.println(d3 + " students with grade D-");
        }
        if (d2 != 0) {
            System.out.println(d2 + " students with grade D");
        }
        if (d1 != 0) {
            System.out.println(d1 + " students with grade D+");
        }
        if (c3 != 0) {
            System.out.println(c3 + " students with grade C-");
        }
        if (c2 != 0) {
            System.out.println(c2 + " students with grade C");
        }
        if (c1 != 0) {
            System.out.println(c1 + " students with grade C+");
        }
        if (b3 != 0) {
            System.out.println(b3 + " students with grade B-");
        }
        if (b2 != 0) {
            System.out.println(b2 + " students with grade B");
        }
        if (b1 != 0) {
            System.out.println(b1 + " students with grade B+");
        }
        if (a2 != 0) {
            System.out.println(a2 + " students with grade A-");
        }
        if (a1 != 0) {
            System.out.println(a1 + " students with grade A");
        }

    }


    /**
     * 
     * @param check
     *            ArrayList
     * @param n
     *            Integer
     */
    public void findPairHasScore(BST<Student> check, int n) {
        int val = 0;
        System.out.println("Students with score difference less than or equal "
            + n + ":");
        Iterator<Student> bstIter = check.iterator();
        Iterator<Student> bstIter1 = check.iterator();

        while (bstIter.hasNext()) {
            Student student1 = bstIter.next();
            bstIter1 = check.iterator();
            while (bstIter1.hasNext()) {
                Student student2 = bstIter1.next();

                if ((!student1.getName().equals(student2.getName())) && (Math
                    .abs(student1.getScore() - student2.getScore()) <= n)) {

                    System.out.println(student1.getName() + ", " + student2
                        .getName());
                    val++;

                }
            }

        }

        System.out.println("found " + val + " pairs");

    }


    /**
     * 
     * @param check
     *            ArrayList
     */
    public void findPairHasNoScore(BST<Student> check) {
        int val = 0;
        System.out.println(
            "Students with score difference less than or equal 0:");
        Iterator<Student> bstIter = check.iterator();
        Iterator<Student> bstIter1 = check.iterator();

        while (bstIter.hasNext()) {
            Student student1 = bstIter.next();
            bstIter1 = check.iterator();
            while (bstIter1.hasNext()) {
                Student student2 = bstIter1.next();

                if ((!student1.getName().equals(student2.getName()))
                    && (student1.getScore() == student2.getScore())) {
                    System.out.println(student1.getName() + ", " + student2
                        .getName());
                    val++;

                }
            }

        }

        System.out.println("found " + val + " pairs");

    }
}
