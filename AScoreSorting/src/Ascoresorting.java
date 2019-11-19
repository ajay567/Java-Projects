import java.io.EOFException;
import java.io.FileNotFoundException;
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
    public static void main(String[] args) {

//        args[0] = "sample128k.bin";
//        args[1] = "sample_vtstudents.data";
        ExternalSort sort;
        ArrayList<Integer> runLengths = null;
        try {
            sort = new ExternalSort("testing_Ajay.bin");
            runLengths = sort.performExternalSort();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
 
        try {
            RunManager runManager = new RunManager(runLengths);
            runManager.mergeAllRuns("output.bin");
        }
        catch (FileNotFoundException e) {
            System.out.print("Merge all Runs FileNotFoundException " + e.getMessage());
            e.printStackTrace();
        }
        catch (EOFException e){
            System.out.print(" Merge all Runs EOFException " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException f){
            System.out.print("Merge all Runs IOEException " + f.getMessage());
            f.printStackTrace();
        }

        
      //  long startTime = System.currentTimeMillis();
        VTStudentsManager test = new VTStudentsManager();
        try {
            test.printOutStudents("output.bin", "sample_vtstudents.data");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        long endTime = System.currentTimeMillis();
//        System.out.println("It took " + (endTime - startTime)
//            + " milliseconds");

    }

}
