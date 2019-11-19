import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
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


    public void mergeAllRuns(String finalOutputFile)
        throws FileNotFoundException,
        EOFException,
        IOException {
// System.out.println("Merging all "+runLengths.size()+ " runs");
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
// System.out.println("Writing run: " +i+" "+ runCount + " "+outFile+ "
// exists:"+fileExists[outFileNum]);
                int runLen = mergeRuns(fileNames[mergePassNum % 2], outFile, i,
                    runCount, append);
                fileExists[outFileNum] = true;
//                System.out.println("Created run len:" + runLen);
                newRunLengths.add(runLen);
            }
//            int sumNewRun = 0;
//            int sumOldRun = 0;
//            for (int i = 0; i < newRunLengths.size(); i++) {
//                sumNewRun += newRunLengths.get(i);
//            }
//            for (int i = 0; i < runLengths.size(); i++) {
//                sumOldRun += runLengths.get(i);
//            }
//            System.out.println("Run lengths:" + sumOldRun + " NewRunLengths:" + sumNewRun);
            runLengths = newRunLengths;
            mergePassNum++;

        }
        while (runLengths.size() > 1);
    }


    private int mergeRuns(
        String inFile,
        String outFile,
        int startRun,
        int numRuns,
        boolean fileExists)
        throws FileNotFoundException,
        EOFException,
        IOException {
//        System.out.println("Merging runs " + startRun + " to " + (startRun
//            + numRuns) + " from " + inFile + " to " + outFile);
//
//        System.out.println("Run lengths:");
//        for (int i = 0; i < numRuns; i++) {
//            System.out.println(runLengths.get(startRun + i));
//        }

        int runTotal = 0;
        for (int i = 0; i < numRuns; i++) {
            runTotal += runLengths.get(startRun + i);
        }

        runComplete = new boolean[numRuns];
        fil = new RandomAccessFile(inFile, "r");
        loadRuns(inFile, startRun, numRuns);
        os = new DataOutputStream(new FileOutputStream(outFile, fileExists));

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
// System.out.println("From run: " + maxRun + " pos: "
// + offsetPos[maxRun]);
            writeOutputBuffer(os, max);
            offsetPos[maxRun]++;

            if (offsetPos[maxRun] >= runLengths.get(startRun + maxRun)) {
                runComplete[maxRun] = true;
// System.out.println("RunBUmber complete loadNextBlock"
// + " MAxRun:" + maxRun + " OffsetPos:" + offsetPos[maxRun]);
            }

            if (offsetPos[maxRun] % 1024 == 0 && !runComplete[maxRun]) {
// System.out.println("Input buffer "+(startRun+maxRun));
                loadNextBlock(startRun + maxRun);
            }
            newRunLength++;
        }
        os.close();
        fil.close();
        return newRunLength;
    }


    private void loadRuns(String inFile, int startNum, int numRuns)
        throws FileNotFoundException,
        EOFException,
        IOException {
        // System.out.println("Load runs");
        offsetPos = new int[numRuns]; // all zeros
        runContents = new byte[numRuns][1024 * 16];

        for (int i = 0; i < numRuns; i++) {
            if (!runComplete[i]) {
                loadNextBlock(startNum + i);
            }
        }
    }


    private void loadNextBlock(int runNum)
        throws FileNotFoundException,
        EOFException,
        IOException {
// System.out.println("loadNextBlock: "+ runNum);
        int pos = runNum % 8;
// System.out.println("offset: "+ offsetPos[pos]);

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
// System.out.println("Last block for run: "+ numRecords);
                fil.seek((fileOffset + offsetPos[pos]) * 16);
                fil.readFully(runContents[pos], 0, numRecords * 16);
            }
            else {
// System.out.println("RunBUmber complete loadNextBlock"
// + " RunNUm:" + runNum + " OffsetPos:" + offsetPos[runNum]);
                runComplete[pos] = true;
// for (int i = 0; i < runComplete.length; i++) {
// System.out.println(runComplete[i]);
// }
            }
        }
    }


    private Apple getNextRecord(int runNum) {
// if end of block load more records
        int pos = runNum % 8;
        if (offsetPos[pos] < runLengths.get(runNum)) {

            int startPos = (offsetPos[pos] % 1024) * 16;
// System.out.println("getNextRecord("+runNum+"):"+offsetPos[runNum]);
            ByteBuffer wrapped = ByteBuffer.wrap(runContents[pos], startPos, 8);
            long pid = wrapped.getLong();

            wrapped = ByteBuffer.wrap(runContents[pos], startPos + 8, 8);
            double score = wrapped.getDouble();

            return new Apple(pid, score);

        }

        runComplete[runNum] = true;
// System.out.println("RunBUmber complete getNextRecord" + " RunNUm:"
// + runNum + " OffsetPos:" + offsetPos[runNum] + " New:" + runLengths
// .get(runNum));
// for (int i = 0; i < runComplete.length; i++) {
// System.out.println(runComplete[i]);
// }
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
        throws FileNotFoundException,
        EOFException,
        IOException {
        outputBuffer[outPos++] = record;
        // System.out.println(outPos+ " Outputting: " + record.getScore() + ",
        // "+ record.getPid());

        if (outPos >= outputBuffer.length) {
            for (int i = 0; i < outPos; i++) {
                outFile.writeLong(outputBuffer[i].getPid());
                outFile.writeDouble(outputBuffer[i].getScore());
            }
            outPos = 0;
        }
    }

}
