import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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
public class CommandFileParser {

    /**
     * 
     */
    public CommandFileParser() {
        // Does Nothing
    }


    /**
     * 
     * @param fileName
     * @throws IOException
     */
    public void readFile(String fileName,int tableSize,String memoryFileName) throws IOException {

        DataOutputStream os = new DataOutputStream(new FileOutputStream(
            memoryFileName, false));
        os.close();
        RandomAccessFile fil = new RandomAccessFile(memoryFileName, "rw");

        HashTable<String, StudentRecord> myTable =
            new HashTable<String, StudentRecord>(tableSize);

        MemoryManager manager = new MemoryManager();

        CommandCalculator calculator = new CommandCalculator();

        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            String command = scan.next();
            if (command.equals("loadstudentdata")) {
                String studentFileName = scan.next();
                System.out.println(studentFileName + " successfully loaded.");
                calculator.loadStudentData(studentFileName, fil, myTable,
                    manager);
            }
            if (command.equals("insert")) {
                String pid = scan.next();
                String fullName = scan.next() + " " + scan.next();

                if (calculator.insert(pid, fullName, fil, myTable, manager)
                    && scan.hasNext("essay")) {
                    scan.next();
                    scan.next();
                    String essayVal = "";
                    while (!scan.hasNext("essay")) {
                        essayVal = essayVal + scan.nextLine();
                    }
                    ;
                    calculator.essayInsert(pid, fullName, essayVal, fil,
                        myTable, manager);
                    scan.next();
                    scan.next();
                }
            }
            if (command.equals("update")) {
                String pid = scan.next();
                String name = scan.next() + " " + scan.next();
                calculator.update(pid, name, fil, myTable, manager);

                if (scan.hasNext("essay")) {
                    scan.next();
                    scan.next();
                    String essayVal = "";
                    while (!scan.hasNext("essay")) {
                        essayVal = essayVal + scan.nextLine();
                    }
                    calculator.essayInsert(pid, name, essayVal, fil, myTable,
                        manager);
                    scan.next();
                    scan.next();
                }
            }
            if (command.equals("remove")) {
                String pid = scan.next();
                calculator.remove(pid, fil, myTable, manager);
            }
            if (command.equals("clear")) {
                String pid = scan.next();
                calculator.clear(pid, fil, myTable, manager);
            }
            if (command.equals("search")) {
                String pid = scan.next();
                calculator.search(pid, fil, myTable);
            }
            if (command.equals("print")) {
                calculator.print(fil, myTable, manager);
            }
            if (command.equals("essay")) {
                scan.next();
                String essayVal = "";
                while (!scan.hasNext("essay")) {
                    essayVal = essayVal + scan.nextLine();
                }
                scan.next();
                scan.next();
                System.out.println("essay commands can only follow successful "
                    + "insert or update commands");
            }
        }
//        System.out.println(fil.length());
//        manager.printNumBytes();
    }

}
