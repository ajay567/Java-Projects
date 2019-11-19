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
/**
 * This class performs the replacement selection by using the
 * max heap created by us.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.11.19
 */
public class ReplacementSelection {

    /**
     * fields
     */
    private Apple[] outputBuffer;
    private int outPos;
    private ArrayList<Integer> runLengths;
    private AppleFileParser parser;


    /**
     * Constructor which takes a file Name
     * 
     * @param fileName
     *            name of the file
     * @throws IOException
     */
    public ReplacementSelection(String fileName) throws IOException {
        outputBuffer = new Apple[1024];
        runLengths = new ArrayList<Integer>();
        parser = new AppleFileParser(fileName);
        outPos = 0;
    }


    /**
     * Method to implement external sort
     * 
     * @return an arraylist of run lengths
     * @throws IOException
     */
    public ArrayList<Integer> performExternalSort() throws IOException {

        int heapCap = 8 * 1024;
        int heapSize = 0;
        Apple[] heapArr = new Apple[heapCap];

        while (parser.hasNextRecord() && heapSize < heapCap) {
            heapArr[heapSize++] = parser.getNextRecord();
        }

        MaxHeap<Apple> heap = new MaxHeap<Apple>(heapArr, heapSize, heapCap);
        do {

            int runLength = 0;
            outPos = 0;
            while (heap.getSize() > 0) {
                // remove max
                outputBuffer[outPos] = heap.removemax();
                outPos += 1;

                // insert next element
                if (parser.hasNextRecord()) {
                    Apple nextRecord = parser.getNextRecord();
                    if (nextRecord.compareTo(outputBuffer[outPos - 1]) > 0) {
                        heap.modify(heap.getSize(), nextRecord);
                    }
                    else {
                        heap.insert(nextRecord);
                    }
                }
                else {
                    heap.incrementNullCount();
                }

                // output buffer full
                if (outPos >= outputBuffer.length) {
                    parser.writeRunFile(outputBuffer, outPos);
                    runLength += outPos;
                    outPos = 0;
                }
            }

            // run complete
            parser.writeRunFile(outputBuffer, outPos);
            runLength += outPos;
            runLengths.add(runLength);
            heap.reset();
        }
        while (parser.hasNextRecord() || heap.getSize() > 0);

        parser.closingFiles();
        return runLengths;
    }
}
