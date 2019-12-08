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
 * This class contains the main method. It just has to create the
 * objects of the required classes and call methods that perform
 * external sorting. The methods are passed the names of the
 * files that have to be sorted.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class CommandCalculator {

    /**
     * 
     */
    public CommandCalculator() {
        // Does Nothing
    }


    /**
     * 
     * @param fileName
     * @param fil
     * @param myTable
     * @param manager
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
                name = currentLine[1] + " " + currentLine[3];
                byte[] b = name.getBytes();
//                System.out.println(name + " Name:" + name.length() + " Bytes Size:" + b.length);
                MemoryHandle hand = manager.getBlock(b.length);
                if (myTable.get(currentLine[0]) != null) {
                    manager.removeBlock(hand);
                    System.out.println("Warning: Student " + currentLine[0]
                        + " " + currentLine[1] + " " + currentLine[3]
                        + " is not loaded since a student "
                        + "with the same pid exists.");
                }
                else if (!myTable.put(currentLine[0], new StudentRecord(
                    hand))) {
                    manager.removeBlock(hand);
                    System.out.println(
                        "Warning: There is no free place in the bucket to "
                            + "load student " + currentLine[0] + " "
                            + currentLine[1] + " " + currentLine[3] + ".");
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
     * 
     * @param pid
     * @param name
     * @param fil
     * @param myTable
     * @param manager
     * @return
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
        else if (!myTable.put(pid, new StudentRecord(hand))) {
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
     * 
     * @param pid
     * @param fil
     * @param myTable
     * @param manager
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
            myTable.remove(pid);
            manager.removeBlock(record.getName());
            if (record.getEssay() != null) {
                manager.removeBlock(record.getEssay());
            }

            byte[] b = new byte[record.getName().getLength()];
            fil.seek(record.getName().getStart());
            fil.readFully(b, 0, record.getName().getLength());
            ByteBuffer wrapped = ByteBuffer.wrap(b);
            String name = StandardCharsets.UTF_8.decode(wrapped).toString();

            System.out.println(pid + " with full name " + name
                + " is removed from " + "the database.");
        }
    }


    /**
     * 
     * @param pid
     * @param fil
     * @param myTable
     * @param manager
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
 
                byte[] b = new byte[record.getName().getLength()];
                fil.seek(record.getName().getStart());
                fil.readFully(b, 0, record.getName().getLength());
                ByteBuffer wrapped = ByteBuffer.wrap(b);
                name = StandardCharsets.UTF_8.decode(wrapped).toString();
            }
            System.out.println("record with pid " + pid + " with full name "
                + name + " is cleared.");
        }

    }


    /**
     * 
     * @param pid
     * @param fil
     * @param myTable
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

                System.out.println(pid + " " + name + ":");
                System.out.println(essayVal);
            }
            else {
                System.out.println(pid + " " + name + ":");
            }
        }

    }


    /**
     * 
     * @param fil
     * @param myTable
     * @param manager
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
                
//                System.out.println("Start:" + studentRecords.get(i).getName()
//                    .getStart() + " Length:" + studentRecords.get(i).getName()
//                        .getLength());
//
//                if (studentRecords.get(i).getEssay() != null) {
//                    b = new byte[studentRecords.get(i).getEssay().getLength()];
//                    fil.seek(studentRecords.get(i).getEssay().getStart());
//                    fil.readFully(b, 0, studentRecords.get(i).getEssay()
//                        .getLength());
//                    wrapped = ByteBuffer.wrap(b);
//                    name = StandardCharsets.UTF_8.decode(wrapped).toString();
//                    System.out.println(name);
//                    System.out.println("Start:" + studentRecords.get(i)
//                        .getEssay().getStart() + " Length:" + studentRecords
//                            .get(i).getEssay().getLength());
//                }
            }
        }
        manager.printFreeList();
    }


    /**
     * 
     * @param pid
     * @param fullName
     * @param essay
     * @param fil
     * @param myTable
     * @param manager
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
// System.out.println(record.getEssay());
        if (record.getEssay() != null) {
            manager.removeBlock(record.getEssay());
        }
        byte[] b = essay.getBytes();
        MemoryHandle hand = manager.getBlock(b.length);
        fil.seek(hand.getStart());
        fil.write(b);
        myTable.get(pid).setEssay(hand);
        System.out.println("essay saved for " + fullName);
//        for (int i = 0; i < essay.length(); i++) {
//            if (essay.charAt(i) == ' ') {
//                System.out.print("z");
//            }
//            else {
//                System.out.print(essay.charAt(i));
//            }
//        }
    }


    /**
     * 
     * @param pid
     * @param name
     * @param fil
     * @param myTable
     * @param manager
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
            manager.removeBlock(record.getName());
            byte[] b = name.getBytes();
            MemoryHandle hand = manager.getBlock(b.length);
            fil.seek(hand.getStart());
            fil.write(b);
            myTable.get(pid).setName(hand);
            System.out.println("Student " + pid + " updated to " + name);
            checker = true;
        }
        return checker;
    }
    

}
