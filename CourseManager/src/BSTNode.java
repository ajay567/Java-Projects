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
 * @version 2019.9.21
 *
 * @param <E>
 */
class BSTNode<E extends Comparable<? super E>> {
    private E element; // Element for this node
    private BSTNode<E> left; // Pointer to left child
    private BSTNode<E> right; // Pointer to right child


    // Constructors
    /**
     * 
     */
    BSTNode() {
        left = null;
        right = null;
    }


    /**
     * 
     * @param val
     *            E
     */
    BSTNode(E val) {
        left = null;
        right = null;
        element = val;
    }


    /**
     * 
     * @param val
     *            type E
     * @param l
     *            BSTNode
     * @param r
     *            BSTNode
     */
    BSTNode(E val, BSTNode<E> l, BSTNode<E> r) {
        left = l;
        right = r;
        element = val;
    }


    // Get and set the element value
    /**
     * 
     * @return E
     */
    public E value() {
        return element;
    }


    /**
     * 
     * @param v
     *            E
     */
    public void setValue(E v) {
        element = v;
    }


    // Get and set the left child
    /**
     * 
     * @return BSTNode<E>
     */
    public BSTNode<E> left() {
        return left;
    }


    /**
     * 
     * @param p
     *            BSTNode<E>
     */
    public void setLeft(BSTNode<E> p) {
        left = p;
    }


    // Get and set the right child
    /**
     * 
     * @return BSTNode<E>
     */
    public BSTNode<E> right() {
        return right;
    }


    /**
     * 
     * @param p
     *            BSTNode<E>
     */
    public void setRight(BSTNode<E> p) {
        right = p;
    }
}
