import java.io.IOException;
import java.util.ArrayList;

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
public class Ascoresorting {
    
    private static long startTime = System.currentTimeMillis();

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        
//        AppleFileParser appleParser = new AppleFileParser("sample128k.bin");
//
//        while(appleParser.hasNextRecord()) {
//            Apple apple = appleParser.getNextRecord();
//            System.out.println(apple.getPid() + " " + apple.getScore());
//        }
      
      ExternalSort sort =  new ExternalSort("sample128ktest.bin");
      ArrayList<Integer> runLengths = sort.performExternalSort();
      
      
      RunManager runManager = new RunManager(runLengths);
      runManager.mergeAllRuns("sample128ktest.bin");
      
      
      VTStudentsManager test = new VTStudentsManager();
//      test.printOutStudents(outputFile, "sample_vtstudents.data");
      test.printOutStudents("runFile1.data", "sample_vtstudents.data");
      
//      long endTime = System.currentTimeMillis();
//      System.out.println("It took " + (endTime - startTime) + " milliseconds");
      
    }

}
