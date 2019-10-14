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
 * Node Class for the Binary Search Tree (BST)
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.24.21
 *
 * @param <E>
 *            The type of element to be stored in the BST
 */
class BSTNode<E extends Comparable<? super E>> {
    private E element; // Element for this node
    private BSTNode<E> left; // Pointer to left child
    private BSTNode<E> right; // Pointer to right child


    // Constructors methods:
    /**
     * Constructor method to create a node without a value
     */
    BSTNode() {
        left = null;
        right = null;
    }


    /**
     * Constructor method to create a node with a given value
     * 
     * @param val
     *            Element to be stored in the node
     */
    BSTNode(E val) {
        left = null;
        right = null;
        element = val;
    }


    /**
     * Constructor method to create a node with a given value and given child
     * nodes
     * 
     * @param val
     *            Element to be stored in the node
     * @param l
     *            Left child node
     * @param r
     *            Right child node
     */
    BSTNode(E val, BSTNode<E> l, BSTNode<E> r) {
        left = l;
        right = r;
        element = val;
    }


    // Getter and Setter methods:

    /**
     * Gets the value of the current node
     * 
     * @return Value of the node
     */
    public E value() {
        return element;
    }


    /**
     * Sets value of the current node
     * 
     * @param v
     *            Value to be set
     */
    public void setValue(E v) {
        element = v;
    }


    /**
     * Gets left child of current node
     * 
     * @return Left child node
     */
    public BSTNode<E> left() {
        return left;
    }


    /**
     * Set left child node of current node
     * 
     * @param p
     *            Left child node to be set
     */
    public void setLeft(BSTNode<E> p) {
        left = p;
    }


    /**
     * Gets right child of current node
     * 
     * @return Right child node
     */
    public BSTNode<E> right() {
        return right;
    }


    /**
     * Set right child node of current node
     * 
     * @param p
     *            Right child to be set
     */
    public void setRight(BSTNode<E> p) {
        right = p;
    }
}
