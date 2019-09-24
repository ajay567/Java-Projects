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

// Binary Search Tree implementation
/**
 * 
 * @author <Ajay Dalmia> <ajay99>
 * @author <Amit Ramesh> <amitr>
 * @version 2019.9.21
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    /**
     * 
     */
    private BSTNode<E> root; // Root of the BST
    private int nodecount; // Number of nodes in the BST
    private BSTIterator<E> iterator;


    // constructor
    /**
     * 
     */
    BST() {
        root = null;
        nodecount = 0;
    }


    // Reinitialize tree
    /**
     * 
     */
    public void clear() {
        root = null;
        nodecount = 0;
    }


    // Insert a record into the tree.
    // Records can be anything, but they must be Comparable
    // e: The record to insert.
    /**
     * 
     * @param e
     *            E
     */
    public void insert(E e) {
        root = inserthelp(root, e);
        nodecount++;
    }


    // Remove a record from the tree
    // key: The key value of record to remove
    // Returns the record removed, null if there is none.
    /**
     * 
     * @param key
     *            E
     * @return E
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
     * 
     * @param key
     *            E
     * @return E
     */
    public E find(E key) {
        return findhelp(root, key);
    }


    // Return the number of records in the dictionary
    /**
     * 
     * @return integer
     */
    public int size() {
        return nodecount;
    }


    /**
     * 
     * @param rt
     *            BSTNode<E>
     * @param key
     *            key
     * @return E
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
     * 
     * @param rt
     *            BSTNode<E>
     * @param e
     *            E
     * @return BSTNode<E>
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
     * 
     * @param rt
     *            BSTNode<E>
     * @param key
     *            key
     * @return BSTNode<E>
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
     * 
     * @return ArrayList
     */
    public ArrayList<E> toArray() {
        ArrayList<E> list = new ArrayList<E>();
        inOrder(root, list);
        return list;
    }


    /**
     * 
     * @param root1
     *            BSTNode
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void inOrder(BSTNode root1, ArrayList<E> list) {
        if (root1 == null) {
            return;
        }

        inOrder(root1.left(), list);
        list.add((E)root1.value());
        inOrder(root1.right(), list);

    }


    /**
     * 
     * @param rt
     *            BSTNode<E>
     * @return
     */
    private BSTNode<E> getmax(BSTNode<E> rt) {
        if (rt.right() == null) {
            return rt;
        }
        return getmax(rt.right());
    }


    /**
     * 
     * @param rt
     *            BSTNode<E>
     * @return
     */
    private BSTNode<E> deletemax(BSTNode<E> rt) {
        if (rt.right() == null) {
            return rt.left();
        }
        rt.setRight(deletemax(rt.right()));
        return rt;
    }


    public BSTIterator<E> Iterator() {
        // private BSTIterator<Integer> bstIter;
        return new BSTIterator<E>(root);
    }


    // Iterator Class
    /**
     * 
     * @author Ajay Dalmia
     * @author <Amit Ramesh> <amitr>
     *
     * @param <E>
     */
    private class BSTIterator<E extends Comparable<? super E>>
        implements Iterator<E> {

        /**
         * fields
         */
        private Stack<BSTNode<E>> stack;


        /**
         * 
         * @param root
         *            BSTNode<E>
         */
        public BSTIterator(BSTNode<E> root) {
            stack = new Stack<BSTNode<E>>();
            while (root != null) {
                stack.push(root);
                root = root.left();
            }
        }


        /**
         * 
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }


        /**
         * 
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
