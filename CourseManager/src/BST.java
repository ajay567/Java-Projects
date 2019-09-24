import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

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
 * Binary Search Tree (BST) Implementation
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.9.21
 *
 * @param <E>
 *            The type of element to be stored in the BST
 */
public class BST<E extends Comparable<E>> {

    /**
     * Setting up class variables
     */
    private BSTNode<E> root; // Root of the BST
    private int nodecount; // Number of nodes in the BST


    /**
     * Constructor method
     */
    BST() {
        root = null;
        nodecount = 0;
    }


    /**
     * Clears all the nodes from the BST
     */
    public void clear() {
        root = null;
        nodecount = 0;
    }


    /**
     * Inserts a element into the tree
     * 
     * @param e
     *            The element to be inserted. Must implement the comparable
     *            interface.
     */
    public void insert(E e) {
        root = inserthelp(root, e);
        nodecount++;
    }


    // Remove a record from the tree
    // key: The key value of record to remove
    // Returns the record removed, null if there is none.
    /**
     * Removes the provided element from the tree
     * 
     * @param key
     *            The element to be removed.
     * @return The element that was removed, if successful and null otherwise.
     */
    public E remove(E key) {
        E temp = findhelp(root, key); // First find it
        if (temp != null) {
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }


    // Return the record with key value k, null if none exists
    // key: The key value to find
    /**
     * Finds the record with the given key
     * 
     * @param key
     *            The element to be searched for.
     * @return The record with the provided key or null if it is not found.
     */
    public E find(E key) {
        return findhelp(root, key);
    }


    // Return the number of records in the dictionary
    /**
     * Gets the number of elements currently in the BST
     * 
     * @return The current size
     */
    public int size() {
        return nodecount;
    }


    /**
     * Recursive helper function for finding an element in the BST
     * 
     * @param rt
     *            Root node to start searching from.
     * @param key
     *            Element to search for
     * @return Element if found and null otherwise
     */
    private E findhelp(BSTNode<E> rt, E key) {
        if (rt == null) {
            return null;
        }
        if (rt.value().compareTo(key) > 0) {
            return findhelp(rt.left(), key);
        }
        else if (rt.value().compareTo(key) == 0) {
            return rt.value();
        }
        else {
            return findhelp(rt.right(), key);
        }
    }


    /**
     * Recursive helper function for inserting an element
     * 
     * @param rt
     *            Root node to insert under
     * @param e
     *            Element to insert
     * @return Root node
     */
    private BSTNode<E> inserthelp(BSTNode<E> rt, E e) {
        if (rt == null) {
            return new BSTNode<E>(e);
        }
        if (rt.value().compareTo(e) >= 0) {
            rt.setLeft(inserthelp(rt.left(), e));
        }
        else {
            rt.setRight(inserthelp(rt.right(), e));
        }
        return rt;
    }


    /**
     * Recursive helper function for removing an element
     * 
     * @param rt
     *            Root node that element is under
     * @param key
     *            Element to be removed
     * @return Root node
     */
    private BSTNode<E> removehelp(BSTNode<E> rt, E key) {
        if (rt == null) {
            return null;
        }
        if (rt.value().compareTo(key) > 0) {
            rt.setLeft(removehelp(rt.left(), key));
        }
        else if (rt.value().compareTo(key) < 0) {
            rt.setRight(removehelp(rt.right(), key));
        }
        else {
            if (rt.left() == null) {
                return rt.right();
            }
            else if (rt.right() == null) {
                return rt.left();
            }
            else {
                BSTNode<E> temp = getmax(rt.left());
                rt.setValue(temp.value());
                rt.setLeft(deletemax(rt.left()));
            }
        }
        return rt;
    }


    /**
     * Convert the BST into an ArrayList
     * 
     * @return The elements of the BST in an ArrayList as ordered by an in-order
     *         traversal
     */
    public ArrayList<E> toArray() {
        ArrayList<E> list = new ArrayList<E>();
        inOrder(root, list);
        return list;
    }


    /**
     * Recursive helper function for in-order traversal.
     * 
     * @param root1
     *            Root node
     * @param list
     *            ArrayList to be populated
     */
    private void inOrder(BSTNode<E> root1, ArrayList<E> list) {
        if (root1 == null) {
            return;
        }

        inOrder(root1.left(), list);
        list.add((E)root1.value());
        inOrder(root1.right(), list);

    }


    /**
     * Helper function to get the largest node in the BST.
     * 
     * @param rt
     *            Root node under which to search
     * @return The node containing the largest value
     */
    private BSTNode<E> getmax(BSTNode<E> rt) {
        if (rt.right() == null) {
            return rt;
        }
        return getmax(rt.right());
    }


    /**
     * Helper function to delete the max node
     * 
     * @param rt
     *            Root node to search under
     * @return Root node
     */
    private BSTNode<E> deletemax(BSTNode<E> rt) {
        if (rt.right() == null) {
            return rt.left();
        }
        rt.setRight(deletemax(rt.right()));
        return rt;
    }


    /**
     * Creates an in-order traversal iterator for the BST
     * 
     * @return Iterator object
     */
    public BSTIterator<E> iterator() {
        return new BSTIterator<E>(root);
    }


    /**
     * In-order Traversal Iterator Class
     * 
     * @author <Ajay Dalmia> <ajay99>
     * @author <Amit Ramesh> <amitr>
     *
     * @param <E>
     *            The generic element type used by the BST
     */
    private class BSTIterator<E extends Comparable<? super E>>
        implements Iterator<E> {

        /**
         * Stack is used to maintain the order of the elements while traversing
         * them in-order.
         */
        private Stack<BSTNode<E>> stack;


        /**
         * Constructor for the iterator
         * 
         * @param root
         *            Root node of the BST
         */
        public BSTIterator(BSTNode<E> root) {
            stack = new Stack<BSTNode<E>>();
            while (root != null) {
                stack.push(root);
                root = root.left();
            }
        }


        /**
         * Indicates whether there is another element to traverse over
         * 
         * @return True if there is another element, False otherwise.
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }


        /**
         * Traverses to the next element
         * 
         * @return The next element in the traversal
         */
        public E next() {
            BSTNode<E> node = stack.pop();
            E value = node.value();
            if (node.right() != null) {
                node = node.right();
                while (node != null) {
                    stack.push(node);
                    node = node.left();
                }
            }
            return value;
        }
    }
}
