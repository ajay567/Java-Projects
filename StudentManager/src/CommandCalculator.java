import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
 * This class helps the CommandFileParser class. Most of the
 * calculations and print outs for the commands are handled by
 * this class.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class CommandCalculator {

    /**
     * Constructor
     */
    public CommandCalculator() {
        // Does Nothing
    }


    /**
     * It loads the csv file into the memory manager
     * and the hash table and prints warnings if
     * required
     * 
     * @param fileName
     *            name of the csv file
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @throws IOException
     */
    public void loadStudentData(
        String fileName,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        File input = new File(fileName);
        Scanner scan = new Scanner(input);
        String[] currentLine = null;
        while (scan.hasNext()) {
            String newLine = scan.nextLine();
            currentLine = newLine.split(",");
            String name = "";
            if (!currentLine[0].equals("")) {
                name = currentLine[1].trim() + " " + currentLine[3].trim();
                byte[] b = name.getBytes();
                MemoryHandle hand = manager.getBlock(b.length);
                if (myTable.get(currentLine[0].trim()) != null) {
                    manager.removeBlock(hand);
                    System.out.println("Warning: Student " + currentLine[0]
                        .trim() + " " + currentLine[1].trim() + " "
                        + currentLine[3].trim()
                        + " is not loaded since a student "
                        + "with the same pid exists.");
                }
                else if (!myTable.put(currentLine[0].trim(), new StudentRecord(
                    hand, null))) {
                    manager.removeBlock(hand);
                    System.out.println(
                        "Warning: There is no free place in the bucket to "
                            + "load student " + currentLine[0].trim() + " "
                            + currentLine[1].trim() + " " + currentLine[3]
                                .trim() + ".");
                }
                else {
                    fil.seek(hand.getStart());
                    fil.write(b);
                }

            }
        }
        scan.close();

    }


    /**
     * The insert method inserts students into the memory file
     * if they do not already exist.
     * 
     * @param pid
     *            id of the student used as key value
     * @param name
     *            student's name
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @return true if insert was successful
     * @throws IOException
     */
    public boolean insert(
        String pid,
        String name,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        boolean checker = false;
        byte[] b = name.getBytes();
        MemoryHandle hand = manager.getBlock(b.length);
        if (myTable.get(pid) != null) {
            manager.removeBlock(hand);
            System.out.println(name + " insertion failed since the pid " + pid
                + " belongs to another student.");
        }
        else if (!myTable.put(pid, new StudentRecord(hand, null))) {
            manager.removeBlock(hand);
            System.out.println(name
                + " insertion failed. Attempt to insert in a full bucket.");
        }
        else {
            System.out.println(name + " inserted.");
            fil.seek(hand.getStart());
            fil.write(b);
            checker = true;
        }

        return checker;
    }


    /**
     * This method takes care of the remove command. It removes
     * the existence of a student record from the entire
     * program.
     * 
     * @param pid
     *            id of the student used as key value
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @throws IOException
     */
    public void remove(
        String pid,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        if (myTable.get(pid) == null) {
            System.out.println(pid + " is not found in the database.");
        }
        else {
            StudentRecord record = myTable.get(pid);

            if (myTable.remove(pid)) {
                byte[] b = new byte[record.getName().getLength()];
                fil.seek(record.getName().getStart());
                fil.readFully(b, 0, record.getName().getLength());
                ByteBuffer wrapped = ByteBuffer.wrap(b);
                String name = StandardCharsets.UTF_8.decode(wrapped).toString();

                manager.removeBlock(record.getName());
                if (record.getEssay() != null) {
                    manager.removeBlock(record.getEssay());
                }

                System.out.println(pid + " with full name " + name
                    + " is removed from " + "the database.");
            }
            else {
                System.out.println(pid + " is not found in the database.");
            }
        }
    }


    /**
     * The clear command removes the essay for a student
     * record.
     * 
     * @param pid
     *            id of the student used as key value
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @throws IOException
     */
    public void clear(
        String pid,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        String name = "";
        if (myTable.get(pid) == null) {
            System.out.println(pid + " is not found in the database.");
        }
        else {
            StudentRecord record = myTable.get(pid);
            if (record.getEssay() != null) {
                manager.removeBlock(record.getEssay());
                myTable.get(pid).setEssay(null);
            }
            byte[] b = new byte[record.getName().getLength()];
            fil.seek(record.getName().getStart());
            fil.readFully(b, 0, record.getName().getLength());
            ByteBuffer wrapped = ByteBuffer.wrap(b);
            name = StandardCharsets.UTF_8.decode(wrapped).toString();
            System.out.println("record with pid " + pid + " with full name "
                + name + " is cleared.");
        }

    }


    /**
     * The search method checks the existence of a provided
     * student record. If it exists then it prints out its
     * information.
     * 
     * @param pid
     *            id of the student used as key value
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @throws IOException
     */
    public void search(
        String pid,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable)
        throws IOException {

        if (myTable.get(pid) == null) {
            System.out.println(
                "Search Failed. Couldn't find any student with ID " + pid);
        }
        else {
            StudentRecord record = myTable.get(pid);

            // name
            byte[] b = new byte[record.getName().getLength()];
            fil.seek(record.getName().getStart());
            fil.readFully(b, 0, record.getName().getLength());
            ByteBuffer wrapped = ByteBuffer.wrap(b);
            String name = StandardCharsets.UTF_8.decode(wrapped).toString();

            // essay
            if (record.getEssay() != null) {
                b = new byte[record.getEssay().getLength()];
                fil.seek(record.getEssay().getStart());
                fil.readFully(b, 0, record.getEssay().getLength());
                wrapped = ByteBuffer.wrap(b);
                String essayVal = StandardCharsets.UTF_8.decode(wrapped)
                    .toString();

                System.out.print(pid + " " + name + ":");
                System.out.println(essayVal);
            }
            else {
                System.out.println(pid + " " + name + ":");
            }
        }

    }


    /**
     * The print command prints out the names of students in the hash
     * table and their respective slots. It also prints out the
     * free list present in the memory manager.
     * 
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @throws IOException
     */
    public void print(
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        System.out.println("Students in the database:");
        ArrayList<StudentRecord> studentRecords = myTable.getValueArray();
        for (int i = 0; i < studentRecords.size(); i++) {
            if (studentRecords.get(i) != null) {
                byte[] b = new byte[studentRecords.get(i).getName()
                    .getLength()];
                fil.seek(studentRecords.get(i).getName().getStart());
                fil.readFully(b, 0, studentRecords.get(i).getName()
                    .getLength());
                ByteBuffer wrapped = ByteBuffer.wrap(b);
                String name = StandardCharsets.UTF_8.decode(wrapped).toString();
                System.out.println(name + " at slot " + i);
            }
        }
        manager.printFreeList();
    }


    /**
     * This command is only used if there is a successful
     * insert or update command. It provides a essay for
     * the student which is saved in the memory file.
     * 
     * @param pid
     *            id of the student used as key value
     * @param fullName
     *            name of the student
     * @param essay
     *            string supposed to be stored as a essay
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @throws IOException
     */
    public void essayInsert(
        String pid,
        String fullName,
        String essay,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        StudentRecord record = myTable.get(pid);
        if (record.getEssay() != null) {
            manager.removeBlock(record.getEssay());
        }
        byte[] b = essay.getBytes();
        MemoryHandle hand = manager.getBlock(b.length);
        fil.seek(hand.getStart());
        fil.write(b);
        myTable.get(pid).setEssay(hand);
        System.out.println("essay saved for " + fullName);

    }


    /**
     * The update command updates the name of a student if the
     * pid matches. If it does not then the student record
     * is inserted.
     * 
     * @param pid
     *            id of the student used as key value
     * @param name
     *            name of the student
     * @param fil
     *            file object
     * @param myTable
     *            hash table
     * @param manager
     *            memory manager object
     * @return true if update is successful
     * @throws IOException
     */
    public boolean update(
        String pid,
        String name,
        RandomAccessFile fil,
        HashTable<String, StudentRecord> myTable,
        MemoryManager manager)
        throws IOException {

        boolean checker = false;
        if (myTable.get(pid) == null) {
            checker = insert(pid, name, fil, myTable, manager);
        }
        else {
            StudentRecord record = myTable.get(pid);

            byte[] c = new byte[record.getName().getLength()];
            fil.seek(record.getName().getStart());
            fil.readFully(c, 0, record.getName().getLength());
            ByteBuffer wrapped = ByteBuffer.wrap(c);
            String oldName = StandardCharsets.UTF_8.decode(wrapped).toString();

            if (!oldName.equals(name)) {
                manager.removeBlock(record.getName());
                byte[] b = name.getBytes();
                MemoryHandle hand = manager.getBlock(b.length);
                fil.seek(hand.getStart());
                fil.write(b);
                myTable.get(pid).setName(hand);
            }
            checker = true;
            System.out.println("Student " + pid + " updated to " + name);
        }
        return checker;
    }

}
