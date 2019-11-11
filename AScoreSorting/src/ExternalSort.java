import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class ExternalSort {

    /**
     * fields
     */
    MaxHeap<Apple> heap;
    Apple[] outputBuffer = new Apple[1024];
    int outPos = 0;

    ArrayList<Integer> runLengths = new ArrayList<Integer>();


    /**
     * 
     * @param parser
     * @param fileName
     * @throws IOException
     */
    public ExternalSort(String fileName) throws IOException {

        AppleFileParser parser = new AppleFileParser(fileName);
        Apple[] heapArr = new Apple[8 * 1024];

        for (int i = 0; i < 8 * 1024; i++) {
            heapArr[i] = parser.getNextRecord();
        }

        heap = new MaxHeap<Apple>(heapArr, 8 * 1024, 8 * 1024);
        while (parser.hasNextRecord()) {
            
            int runLength = 0;
            outPos = 0;
            while (heap.getSize() > 0) {
                // remove max
                outputBuffer[outPos++] = heap.removemax();

                // insert next element
                if (parser.hasNextRecord()) {
                    Apple nextRecord = parser.getNextRecord();
                    if (nextRecord.compareTo(outputBuffer[outPos - 1]) < 0) {
                        heap.modify(heap.getSize(), nextRecord);
                    }
                    else {
                        heap.insert(nextRecord);
                    }
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
            System.out.println(runLength);
            heap.reset();
        }
    }
}
