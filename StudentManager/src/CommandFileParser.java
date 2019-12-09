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
 * All the commands from command file are parsed in this class.
 * Then the required output is produced by using the methods
 * from CommandCalculator. A instance of the hash table, memory
 * manager exist in this class.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class CommandFileParser {

    /**
     * Constructor
     */
    public CommandFileParser() {
        // Does Nothing
    }


    /**
     * This method uses the scanner to parse the commands
     * from the command file. Then the appropriate operations
     * are done for those command. It is the only method in this
     * class.
     * 
     * @param fileName
     *            name of the command file
     * @param tableSize
     *            hash table size
     * @param memoryFileName
     *            name of the memory file
     * @throws IOException
     */
    public void readFile(String fileName, int tableSize, String memoryFileName)
        throws IOException {

        // Creating a memory file
        DataOutputStream os = new DataOutputStream(new FileOutputStream(
            memoryFileName, false));
        os.close();
        RandomAccessFile fil = new RandomAccessFile(memoryFileName, "rw");

        // Creating a hash table
        HashTable<String, StudentRecord> myTable =
            new HashTable<String, StudentRecord>(tableSize);

        // Creating a memory manager object
        MemoryManager manager = new MemoryManager();

        CommandCalculator calculator = new CommandCalculator();
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        
        while (scan.hasNext()) {
            String command = scan.next();
            if (command.equals("loadstudentdata")) { // load starts
                String studentFileName = scan.next();
                System.out.println(studentFileName + " successfully loaded.");
                calculator.loadStudentData(studentFileName, fil, myTable,
                    manager);
            } // load ends
            if (command.equals("insert")) { // insert starts
                String pid = scan.next();
                String fullName = scan.next() + " " + scan.next();

                boolean checker = calculator.insert(pid, fullName, fil, myTable,
                    manager);
                if (checker && scan.hasNext("essay")) {
                    scan.next();
                    scan.next();
                    String essayVal = "";
                    int count = 0;
                    while (!scan.hasNext("essay")) {
                        String temp = scan.nextLine();
                        
                        if (!scan.hasNext("essay")) {
                            essayVal = essayVal + temp + "\n";
                        }
                        else {
                            essayVal = essayVal + temp;
                        }
                        count++;
                    }
                    if (count == 0) {
                        essayVal = essayVal + "\n";
                    }
                    calculator.essayInsert(pid, fullName, essayVal, fil,
                        myTable, manager);
                    scan.next();
                    scan.next();
                }
            } // insert ends
            if (command.equals("update")) { // update starts
                String pid = scan.next();
                String name = scan.next() + " " + scan.next();

                if (calculator.update(pid, name, fil, myTable, manager) && scan
                    .hasNext("essay")) {
                    scan.next();
                    scan.next();
                    String essayVal = "";
                    int count = 0;
                    while (!scan.hasNext("essay")) {
                        String temp = scan.nextLine();
                        if (!scan.hasNext("essay")) {
                            essayVal = essayVal + temp + "\n";
                        }
                        else {
                            essayVal = essayVal + temp;
                        }
                        count++;
                    }   
                    if (count == 0) {
                        essayVal = essayVal + "  \n";
                    }
                    calculator.essayInsert(pid, name, essayVal, fil, myTable,
                        manager);
                    scan.next();
                    scan.next();
                }
            } // update ends
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
            if (command.equals("essay")) { // essay starts
                scan.next();
                String essayVal = "";
                while (!scan.hasNext("essay")) {
                    String temp = scan.nextLine();
                    
                    if (!scan.hasNext("essay")) {
                        essayVal = essayVal + temp + "\n";
                    }
                    else {
                        essayVal = essayVal + temp;
                    }
                }
                scan.next();
                scan.next();
                System.out.println("essay commands can only follow successful "
                    + "insert or update commands");
            } // essay ends
        }
        scan.close();
    }

}
