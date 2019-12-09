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
 * The free spaces in the memory file are stored in this class.
 * It also helps to allocate blocks to the handles based on
 * the free blocks available.
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.12.09
 */
public class MemoryManager {

    /**
     * fields
     */
    private ArrayList<MemoryHandle> freeList;
    private int numBytes;


    /**
     * Constructor that instantiates the array list
     * and the file size.
     */
    public MemoryManager() {
        freeList = new ArrayList<MemoryHandle>();
        numBytes = 0;
    }


    /**
     * This method returns a memory handle based on
     * the free blocks available.
     * 
     * @param length
     *            handle's length
     * @return memory handle object
     */
    public MemoryHandle getBlock(int length) {
        for (int i = 0; i < freeList.size(); i++) {
            int blockLength = freeList.get(i).getLength();
            if (blockLength >= length) {
                MemoryHandle handle = new MemoryHandle(freeList.get(i)
                    .getStart(), length);
                // subtract size
                freeList.get(i).setLength(blockLength - length);
                freeList.get(i).setStart(freeList.get(i).getStart() + length);

                if (freeList.get(i).getLength() == 0) {
                    freeList.remove(i);
                }
                return handle;
            }
        }

        // create new block at end
        MemoryHandle handle = new MemoryHandle(numBytes, length);
        numBytes += length;
        return handle;

    }


    /**
     * RemoveBlocks add a free block to the free list as
     * it is being removed from memory file.
     * 
     * @param handle
     *            block to be removed
     */
    public void removeBlock(MemoryHandle handle) {

        // find first free block past handle
        int i = 0;
        while (i < freeList.size() && freeList.get(i).getStart() < handle
            .getStart()) {
            i++;
        }
        freeList.add(i, handle);

        if (i >= 1) {
            // check for overlap
            MemoryHandle prev = freeList.get(i - 1);
            MemoryHandle curr = freeList.get(i);
            if (prev.getStart() + prev.getLength() >= curr.getStart()) {
                // merge prev and i
                prev.setLength(curr.getLength() + curr.getStart() - prev
                    .getStart());
                freeList.remove(i);
                i--;
            }
        }

        if (i + 1 < freeList.size()) {
            MemoryHandle curr = freeList.get(i);
            MemoryHandle next = freeList.get(i + 1);
            if (curr.getStart() + curr.getLength() >= next.getStart()) {
                // merge curr and i+1
                curr.setLength(next.getLength() + next.getStart() - curr
                    .getStart());
                freeList.remove(i + 1);
            }
        }

        // if space at end remove last block
        MemoryHandle last = freeList.get(freeList.size() - 1);
        if (last.getStart() + last.getLength() >= numBytes) {
            numBytes -= last.getLength();
            freeList.remove(freeList.size() - 1);
        }
    }


    /**
     * This method helps to print free blocks present in
     * the array list.
     */
    public void printFreeList() {
        System.out.println("Free Block List:");
        for (int i = 0; i < freeList.size(); i++) {
            System.out.println("Free Block " + (i + 1) + " starts from Byte "
                + freeList.get(i).getStart() + " with length " + freeList.get(i)
                    .getLength());
        }
    }

}
