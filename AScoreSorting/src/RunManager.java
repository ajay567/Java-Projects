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
 * This class performs the Multiway Merge operation by taking help
 * from the Replacement Selection class. It takes 8 runs at a time
 * and merges them into a single run. This process takes place
 * recursively.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.09.11
 */
public class RunManager {

    /**
     * fields
     */
    private ArrayList<Integer> runLengths;
    private Apple[] outputBuffer;
    private int outPos;
    private RandomAccessFile fil;
    private byte[][] runContents;
    private int[] offsetPos;
    private boolean[] runComplete;
    private boolean[] fileExists;


    /**
     * Constructor with a list parameter.
     * 
     * @param runLengths
     *            array list of the runlengths
     * @throws FileNotFoundException
     */
    public RunManager(ArrayList<Integer> runLengths)
        throws FileNotFoundException {
        this.runLengths = runLengths;
        this.outputBuffer = new Apple[1024];
        this.outPos = 0;
        this.fileExists = new boolean[2];
    }


    /**
     * It merges all the runs from replacement selection and
     * writes it to the final output file.
     * 
     * @param finalOutputFile
     *            the final file
     * @throws IOException
     */
    public void mergeAllRuns(String finalOutputFile) throws IOException {
        String[] fileNames = { "runfile.bin", "runfile1.bin" };

        int mergePassNum = 0;
        // todo:handle size 1
        do {
            ArrayList<Integer> newRunLengths = new ArrayList<Integer>();
            for (int i = 0; i < runLengths.size(); i += 8) {
                int runCount = 8;
                int outFileNum = (mergePassNum + 1) % 2;
                String outFile = fileNames[outFileNum];

                boolean append = fileExists[outFileNum];
                if (runLengths.size() - i <= 8) {
                    runCount = runLengths.size() - i;
                    if (runLengths.size() <= 8) {
                        outFile = finalOutputFile;
                        append = false;
                    }
                }
                int runLen = mergeRuns(fileNames[mergePassNum % 2], outFile, i,
                    runCount, append);
                fileExists[outFileNum] = true;
                newRunLengths.add(runLen);
            }
            runLengths = newRunLengths;
            mergePassNum++;

        }
        while (runLengths.size() > 1);
    }


    /**
     * Helper method for merge runs that merges 8 runs at
     * a time.
     * 
     * @param inFile
     *            the input file
     * @param outFile
     *            the output file
     * @param startRun
     *            the run number
     * @param numRuns
     *            the number of runs to merge
     * @param appendFile
     *            a boolean variable
     * @return the length of the merged run
     * @throws IOException
     */
    private int mergeRuns(
        String inFile,
        String outFile,
        int startRun,
        int numRuns,
        boolean appendFile)
        throws IOException {

        int runTotal = 0;
        for (int i = 0; i < numRuns; i++) {
            runTotal += runLengths.get(startRun + i);
        }

        runComplete = new boolean[numRuns];
        fil = new RandomAccessFile(inFile, "r");
        loadRuns(inFile, startRun, numRuns);
        DataOutputStream os = new DataOutputStream(new FileOutputStream(outFile,
            appendFile));

        int newRunLength = 0;
        while (newRunLength < runTotal) {
            Apple max = new Apple(0, -1.0);
            int maxRun = -1;
            for (int i = 0; i < numRuns; i++) {
                if (!runComplete[i]) {
                    Apple tempApple = getNextRecord(startRun + i);
                    if (tempApple != null) {
                        if (tempApple.compareTo(max) > 0) {
                            max = tempApple;
                            maxRun = i;
                        }
                    }
                }
            }
            writeOutputBuffer(os, max);
            offsetPos[maxRun]++;

            if (offsetPos[maxRun] >= runLengths.get(startRun + maxRun)) {
                runComplete[maxRun] = true;
            }

            if (offsetPos[maxRun] % 1024 == 0 && !runComplete[maxRun]) {
                loadNextBlock(startRun + maxRun);
            }
            newRunLength++;
        }
        os.close();
        fil.close();
        return newRunLength;
    }


    /**
     * It populates the byte array initially.
     * 
     * @param inFile
     *            the input file
     * @param startNum
     *            the run number to start from
     * @param numRuns
     *            the number of runs to merge
     * @throws IOException
     */
    private void loadRuns(String inFile, int startNum, int numRuns)
        throws IOException {
        offsetPos = new int[numRuns]; // all zeros
        runContents = new byte[numRuns][1024 * 16];

        for (int i = 0; i < numRuns; i++) {
            if (!runComplete[i]) {
                loadNextBlock(startNum + i);
            }
        }
    }


    /**
     * The method to load the next block of data
     * 
     * @param runNum
     *            the run number to be loaded
     * @throws IOException
     */
    private void loadNextBlock(int runNum) throws IOException {
        int pos = runNum % 8;

        int fileOffset = 0;

        for (int i = 0; i < runNum; i++) {
            fileOffset += runLengths.get(i);
        }
        if (runLengths.get(runNum) > offsetPos[pos] + 1024) {
            fil.seek((fileOffset + offsetPos[pos]) * 16);
            fil.readFully(runContents[pos], 0, 1024 * 16);
        }
        else {
            int numRecords = (runLengths.get(runNum) - offsetPos[pos]);
            if (numRecords > 0) {
                fil.seek((fileOffset + offsetPos[pos]) * 16);
                fil.readFully(runContents[pos], 0, numRecords * 16);
            }
            else {
                runComplete[pos] = true;
            }
        }
    }


    /**
     * Method to get the next record as an apple object
     * 
     * @param runNum
     *            the run number
     * @return the apple record
     */
    private Apple getNextRecord(int runNum) {
        int pos = runNum % 8;
        if (offsetPos[pos] < runLengths.get(runNum)) {

            int startPos = (offsetPos[pos] % 1024) * 16;
            ByteBuffer wrapped = ByteBuffer.wrap(runContents[pos], startPos, 8);
            long pid = wrapped.getLong();

            wrapped = ByteBuffer.wrap(runContents[pos], startPos + 8, 8);
            double score = wrapped.getDouble();

            return new Apple(pid, score);

        }

        runComplete[runNum] = true;
        return null;
    }


    /**
     * This method writes to a binary file.
     * 
     * @param outFile
     *            the file to be written to
     * @param record
     *            the record to append to the buffer
     * @throws IOException
     */
    private void writeOutputBuffer(DataOutputStream outFile, Apple record)
        throws IOException {
        outputBuffer[outPos++] = record;
        if (outPos >= outputBuffer.length) {
            for (int i = 0; i < outPos; i++) {
                outFile.writeLong(outputBuffer[i].getPid());
                outFile.writeDouble(outputBuffer[i].getScore());
            }
            outPos = 0;
        }
    }

}
