import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    /**
     * @throws FileNotFoundException
     * 
     */
    public RunManager(ArrayList<Integer> runLengths)
        throws FileNotFoundException {
        this.runLengths = runLengths;
        this.outputBuffer = new Apple[1024];
        this.outPos = 0;
        fil = new RandomAccessFile("runFile.data", "r");
    }


    public void mergeAllRuns() throws IOException {
        String[] fileNames = { "runfile.data", "runfile1.data" };

        int mergePassNum = 0;
        while (runLengths.size() > 1) {
            ArrayList<Integer> newRunLengths = new ArrayList<Integer>();
            for (int i = 0; i < runLengths.size(); i += 8) {
                int runCount = 8;
                if (runLengths.size() - i < 8) {
                    runCount = runLengths.size() - i;
                }
                int runLen = mergeRuns(fileNames[mergePassNum % 2],
                    fileNames[(mergePassNum + 1) % 2], i, runCount);
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
        int numRuns) throws IOException {
        loadRuns(inFile, startRun, numRuns);
        os = new DataOutputStream(new FileOutputStream(outFile, false));
        int newRunLength = 0;
        while (runNotFinished()) {
            Apple max = new Apple(0, 0);
            int maxRun = -1;
            for (int i = 0; i < numRuns; i++) {
                Apple tempApple = getNextRecord(i);
                if (tempApple != null) {
                    if (tempApple.compareTo(max) > 0) {
                        max = tempApple;
                        maxRun = i;
                    }
                }
            }
            writeOutputBuffer(os, max);
            offsetPos[maxRun]++;
            newRunLength++;
        }

        return newRunLength;
    }


    private void loadRuns(String inFile, int startNum, int numRuns) throws IOException {
        offsetPos = new int[numRuns]; // all zeros
        runContents = new byte[numRuns][1024];

        for (int i = 0; i < numRuns; i++) {
            loadNextBlock(i);
        }
    }


    private void loadNextBlock(int runNum) throws IOException {
        if (runLengths.get(runNum) > offsetPos[runNum] + 1024) {
            fil.seek(offsetPos[runNum] * 16);
            fil.readFully(runContents[runNum], 0, 1024 * 16);
        }
        else {
            fil.seek(offsetPos[runNum] * 16);
            fil.readFully(runContents[runNum], 0, (runLengths.get(runNum) - offsetPos[runNum])*16 );
        }
    }


    private Apple getNextRecord(int runNum) {
// if end of block load more records

        if (offsetPos[runNum] < runLengths.get(runNum)) {

            int startPos = offsetPos[runNum] * 16;
            ByteBuffer wrapped = ByteBuffer.wrap(runContents[runNum], startPos,
                8);
            long pid = wrapped.getLong();

            wrapped = ByteBuffer.wrap(runContents[runNum], startPos + 8, 8);
            double score = wrapped.getDouble();

            return new Apple(pid, score);

        }

        return null;
    }


    private boolean runNotFinished() {
        for (int i = 0; i < runLengths.size(); i++) {
            if (offsetPos[i] < runLengths.get(i)) {
                return true;
            }
        }
        return false;
    }


    private void writeOutputBuffer(DataOutputStream outFile, Apple record)
        throws IOException {
        outputBuffer[outPos++] = record;

        if (outPos >= outputBuffer.length) {
            outFile.writeLong(record.getPid());
            outFile.writeDouble(record.getScore());
            outPos = 0;
        }
    }

}
