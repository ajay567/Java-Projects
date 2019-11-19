import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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
/**
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.09.11
 */
public class RunManager {

    private ArrayList<Integer> runLengths;
    private Apple[] outputBuffer;
    int outPos;
    private RandomAccessFile fil;
    byte[][] runContents;
    int[] offsetPos;
    private DataOutputStream os;
    private boolean[] runComplete;
    private boolean[] fileExists;


    /**
     * @throws FileNotFoundException
     * 
     */
    public RunManager(ArrayList<Integer> runLengths)
        throws FileNotFoundException {
        this.runLengths = runLengths;
        this.outputBuffer = new Apple[1024];
        this.outPos = 0;
        this.fileExists = new boolean[2];
    }


    public void mergeAllRuns(String finalOutputFile) throws IOException {
//        System.out.println("Merging all "+runLengths.size()+ " runs");
        String[] fileNames = { "runfile.data", "runfile1.data" };

        int mergePassNum = 0;
        while (runLengths.size() > 1) { 
            ArrayList<Integer> newRunLengths = new ArrayList<Integer>();
            for (int i = 0; i < runLengths.size(); i += 8) {
                int runCount = 8;
                int outFileNum = (mergePassNum + 1) % 2;
                String outFile = fileNames[outFileNum];
                if (runLengths.size() - i <= 8) {
                    runCount = runLengths.size() - i;
                    if(runLengths.size() <= 8) {
                        outFile = finalOutputFile;
                    }
                }
//                System.out.println("Creating run: " +i+" "+ runCount);
                int runLen = mergeRuns(fileNames[mergePassNum % 2],
                    outFile, i, runCount, fileExists[outFileNum]);
                fileExists[outFileNum] = true;
//                System.out.println("Created run len:" + runLen);
                newRunLengths.add(runLen);
            }
            runLengths = newRunLengths;
            mergePassNum++;
        }
    }


    private int mergeRuns(
        String inFile,
        String outFile,
        int startRun,
        int numRuns,
        boolean fileExists)
        throws IOException {
//        System.out.println("Merging runs " + startRun + " to "+ (startRun+numRuns) + " from " + inFile+" to "+ outFile);
        
//        System.out.println("Run lengths:");
        for(int i=0; i<numRuns; i++) {
//            System.out.println(runLengths.get(startRun+i));
        }
        
        runComplete = new boolean[numRuns];
        fil = new RandomAccessFile(inFile, "r");
        loadRuns(inFile, startRun, numRuns);
        os = new DataOutputStream(new FileOutputStream(outFile, fileExists));
        
        
        
        int newRunLength = 0;
        while (runNotFinished()) {
            Apple max = new Apple(0, -1.0);
            int maxRun = -1;
            for (int i = 0; i < numRuns; i++) {
                if(!runComplete[i]) {
                    Apple tempApple = getNextRecord(i);
                    if (tempApple != null) {
                        if (tempApple.compareTo(max) > 0) {
                            max = tempApple;
                            maxRun = i;
                        }
                    }
                }
            }
//            System.out.println("From run: "+maxRun+" pos: "+offsetPos[maxRun]);
            writeOutputBuffer(os, max);
            offsetPos[maxRun]++;
            
            if(offsetPos[maxRun] >= runLengths.get(startRun+maxRun)) {
                runComplete[maxRun] = true;
//                System.out.println("run complete:"+ maxRun);
            }
            
            if(offsetPos[maxRun] % 1024 == 0 && !runComplete[maxRun]) {
//                System.out.println("Input buffer "+(startRun+maxRun));
                loadNextBlock(startRun+maxRun);
            }
            newRunLength++;
        }
        
        return newRunLength;
    }


    private void loadRuns(String inFile, int startNum, int numRuns)
        throws IOException {
   //     System.out.println("Load runs");
        offsetPos = new int[numRuns]; // all zeros
        runContents = new byte[numRuns][1024*16];

        for (int i = 0; i < numRuns; i++) {
            if(!runComplete[i]) {
                loadNextBlock(startNum+i);
            }
        }
    }


    private void loadNextBlock(int runNum) throws IOException {
//        System.out.println("loadNextBlock: "+ runNum);
        int pos = runNum % 8;
//        System.out.println("offset: "+ offsetPos[pos]);
        
        int fileOffset = 0;
        
        for(int i=0; i<runNum; i++) {
            fileOffset += runLengths.get(i);
        }
        if (runLengths.get(runNum) > offsetPos[pos] + 1024) {
            fil.seek((fileOffset+offsetPos[pos]) * 16);
            fil.readFully(runContents[pos], 0, 1024 * 16);
        }
        else {
            int numRecords = (runLengths.get(runNum) - offsetPos[pos]);
            if(numRecords > 0) {
//                System.out.println("Last block for run: "+ numRecords);
                fil.seek((fileOffset+offsetPos[pos]) * 16);
                fil.readFully(runContents[pos], 0, numRecords * 16);
            }
            else {
//                System.out.println("No more records "+ numRecords);
                runComplete[pos] = true;
            }
        }
    }


    private Apple getNextRecord(int runNum) {
// if end of block load more records

        if (offsetPos[runNum] < runLengths.get(runNum)) {

            int startPos = (offsetPos[runNum] % 1024) * 16;
//            System.out.println("getNextRecord("+runNum+"):"+offsetPos[runNum]);
            ByteBuffer wrapped = ByteBuffer.wrap(runContents[runNum], startPos,
                8);
            long pid = wrapped.getLong();

            wrapped = ByteBuffer.wrap(runContents[runNum], startPos + 8, 8);
            double score = wrapped.getDouble();

            return new Apple(pid, score);

        }
        
        runComplete[runNum] = true;
        return null;
    }


    private boolean runNotFinished() {
        for (int i = 0; i < runComplete.length; i++) {
            if (!runComplete[i]) {
                return true;
            }
        }
        return false;
    }


    private void writeOutputBuffer(DataOutputStream outFile, Apple record)
        throws IOException {
        outputBuffer[outPos++] = record;
  //      System.out.println(outPos+ " Outputting: " + record.getScore() + ", "+ record.getPid());
        
        if (outPos >= outputBuffer.length) {
            for (int i = 0; i < outPos; i++) {
                outFile.writeLong(outputBuffer[i].getPid());
                outFile.writeDouble(outputBuffer[i].getScore());
            }
            outPos = 0;
        }
    }

}
