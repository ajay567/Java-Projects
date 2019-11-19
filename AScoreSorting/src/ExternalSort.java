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
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.09.11
 */
public class ExternalSort {

    /**
     * fields
     */
    private MaxHeap<Apple> heap;
    private Apple[] outputBuffer;
    private int outPos;
    private ArrayList<Integer> runLengths;
    private AppleFileParser parser;


    /**
     * 
     * @param parser
     * @param fileName
     * @throws IOException
     */
    public ExternalSort(String fileName) throws IOException {
        outputBuffer = new Apple[1024];
        runLengths = new ArrayList<Integer>();
        parser = new AppleFileParser(fileName);
        outPos = 0;
    }


    public ArrayList<Integer> performExternalSort() throws IOException {
        int readCount = 0;
        int writeCount = 0;
        
        
        int heapCap = 8*1024;
        int heapSize = 0;
        Apple[] heapArr = new Apple[heapCap];
        
        while(parser.hasNextRecord() && heapSize < heapCap) {
            heapArr[heapSize++] = parser.getNextRecord();
            readCount++;
        }

//        for (int i = 0; i < heapCap; i++) {
//            heapArr[i] = parser.getNextRecord();
//        }

        heap = new MaxHeap<Apple>(heapArr, heapSize, heapCap);
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
                    readCount++;
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
                    writeCount += outPos;
                    outPos = 0;
                }
            }

            // run complete            
            parser.writeRunFile(outputBuffer, outPos);
            runLength += outPos;
            writeCount += outPos;
            runLengths.add(runLength);
//            System.out.println(runLength);            
            heap.reset();
        } while (parser.hasNextRecord() || heap.getSize() > 0);
        
//        System.out.println("Read count:"+readCount);
//        System.out.println("Write count:"+writeCount);
//        
//        System.out.println("Heap insert count:"+heap.getInsertCount());
//        System.out.println("Heap remove:"+heap.getRemoveCount());
        parser.closingFiles();
//        System.out.println(runLengths.toString());
        return runLengths;
    }
}
