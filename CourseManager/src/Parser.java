import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
public class Parser {

    /**
     * 
     */
    public Parser() {
        // Does Nothing
    }


    /**
     * 
     * @param fileName
     *            String
     * @throws FileNotFoundException
     */
    public void readsFile(String fileName) throws FileNotFoundException {

        File input = new File(fileName);

        Scanner scan = new Scanner(input);

        ArrayList<BST<Student>> list = new ArrayList<BST<Student>>();
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.add(new BST<Student>());
        list.add(new BST<Student>());

        int treeIndex = 1;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        while (scan.hasNext()) {
            String command = scan.next();
            InputReader read = new InputReader();

            // Section Command
            if (command.equals("section")) {
                treeIndex = scan.nextInt();
                read.section(treeIndex);

            }

            // insert command
            if (command.equals("insert")) {
                String fName = scan.next().toLowerCase();
                String lName = scan.next().toLowerCase();
                String name = fName + " " + lName;

                Student student1 = new Student();
                student1.setFirstName(fName);
                student1.setLastName(lName);

                if (list.get(treeIndex).find(student1) == null) {
                    Student student = new Student();
                    student.setFirstName(fName);
                    student.setLastName(lName);
                    student.setName(name);
                    System.out.println(name + " " + "inserted");

                    // Creating ID
                    String id = "";
                    if (treeIndex == 1) {
                        count1 = count1 + 1;
                        id = read.createID(count1, id, treeIndex);
                    }
                    else if (treeIndex == 2) {
                        count2 = count2 + 1;
                        id = read.createID(count2, id, treeIndex);
                    }
                    else {
                        count3 = count3 + 1;
                        id = read.createID(count3, id, treeIndex);
                    }
                    student.setID(id);

                    // Score command
                    int score = 0;
                    if (scan.hasNext("score")) {
                        scan.next();
                        score = scan.nextInt();
                        read.scoreHelper(score, student, name);

                    }

                    // insert student in tree
                    list.get(treeIndex).insert(student);
                }
                else {

                    read.printInsertHelper(student1, treeIndex, list);

                    // Score command
                    int score = 0;
                    if (scan.hasNext("score")) {
                        scan.next();
                        score = scan.nextInt();
                        read.scoreHelper2(score, student1, name, treeIndex,
                            list);

                    }

                }
            }

            // search command
            if (command.equals("search")) {
                String n1 = scan.next().toLowerCase();

                if (scan.hasNext("section") || scan.hasNext("insert") || scan
                    .hasNext("search") || scan.hasNext("score") || scan.hasNext(
                        "remove") || scan.hasNext("removesection") || scan
                            .hasNext("dumpsection") || scan.hasNext("grade")
                    || scan.hasNext("findpair")) { // start of search<A>

                    ArrayList<Student> check = list.get(treeIndex).toArray();
                    int temp = 0;
                    temp = read.search1Helper(check, n1, temp, treeIndex);

                    if (temp == 1) {

                        // Saving String values
                        String temp1 = "";
                        String temp2 = "";
                        for (int i = 0; i < check.size(); i++) {
                            if (check.get(i).getFirstName().equals(n1) || check
                                .get(i).getLastName().equals(n1)) {
                                temp1 = check.get(i).getFirstName();
                                temp2 = check.get(i).getLastName();
                            }
                        }

                        // Student object
                        Student student1 = new Student();
                        student1.setFirstName(temp1);
                        student1.setLastName(temp2);

                        // Score command
                        int score = 0;
                        if (scan.hasNext("score")) {
                            scan.next();
                            score = scan.nextInt();
                            read.scoreHelper3(score, student1, temp1, temp2,
                                treeIndex, list);

                        }

                    }

                    // invalid score command for search
                    if (scan.hasNext("score")) {
                        scan.next();
                        scan.nextInt();
                        System.out.println(
                            "score command can only be called after an insert "
                                + "command or a successful search command with "
                                + "one exact output.");

                    } // end of search<A>

                }
                else { // for Search<A,A>

                    String n2 = scan.next().toLowerCase();
                    String name = n1 + " " + n2;

                    Student student1 = new Student();
                    student1.setFirstName(n1);
                    student1.setLastName(n2);

                    // search<a,b>
                    if (list.get(treeIndex).find(student1) != null) {
                        read.search2Param(list, treeIndex, student1);

                        // Score command
                        int score = 0;
                        if (scan.hasNext("score")) {
                            scan.next();
                            score = scan.nextInt();
                            read.scoreHelper2(score, student1, name, treeIndex,
                                list);

                        }
                    }
                    else {
                        System.out.println("Search failed. Student " + name
                            + " doesn't exist in section " + treeIndex);
                    }

                } // end of search<A,A>

            } // search ends

            // dumpsection begins
            if (command.equals("dumpsection")) {

                System.out.println("Section " + treeIndex + " dump:");

                ArrayList<Student> check = list.get(treeIndex).toArray();

                read.dumpSection(check, list, treeIndex);

            } // dumpsection ends

            // remove begins
            if (command.equals("remove")) {
                String fName = scan.next().toLowerCase();
                String lName = scan.next().toLowerCase();
                String name = fName + " " + lName;

                // Student object
                Student student1 = new Student();
                student1.setFirstName(fName);
                student1.setLastName(lName);
                student1.setName(name);

                if (list.get(treeIndex).find(student1) != null) {
                    Student save = list.get(treeIndex).remove(student1);
                    System.out.println("Student " + save.getName()
                        + " get removed from section " + treeIndex);
                }
                else {
                    System.out.println("Remove failed. Student " + name
                        + " doesn't exist in section " + treeIndex);
                }
            } // remove ends

            // removeSection begins
            if (command.equals("removesection")) {

                if (scan.hasNextInt()) { // if Integer value is provided
                    int newIndex = scan.nextInt();
                    list.get(newIndex).clear();
                    if (newIndex == 1) {
                        count1 = 0;
                    }
                    else if (newIndex == 2) {
                        count2 = 0;
                    }
                    else {
                        count3 = 0;
                    }
                    System.out.println("Section " + newIndex + " removed");
                }
                else {
                    list.get(treeIndex).clear();
                    if (treeIndex == 1) {
                        count1 = 0;
                    }
                    else if (treeIndex == 2) {
                        count2 = 0;
                    }
                    else {
                        count3 = 0;
                    }

                    System.out.println("Section " + treeIndex + " removed");
                }
            } // close removesection

            // grade begins
            if (command.equals("grade")) {
                ArrayList<Student> check = list.get(treeIndex).toArray();
                read.grade(check);
            } // grade ends

            // findpair
            if (command.equals("findpair")) {
                ArrayList<Student> check = list.get(treeIndex).toArray();
                if (scan.hasNextInt()) {
                    int n = scan.nextInt();
                    read.findPairHasScore(check, n);

                }
                else {

                    read.findPairHasNoScore(check);

                }
            }

            // score command invalid
            if (command.equals("score")) {
                scan.nextInt();
                System.out.println(
                    "score command can only be called after an insert "
                        + "command or a successful search command with "
                        + "one exact output.");
            }
        }
        scan.close();
    }

}
