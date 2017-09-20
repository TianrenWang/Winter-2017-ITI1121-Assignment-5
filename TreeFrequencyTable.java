//Name: Tianren Wang
//Student ID: 6040795
//ITI 1121-A00
//Assignment 5

import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Frank T. Wang (fwang100@uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem {
    
        private String key;
        private long count;
    
        private Elem left;
        private Elem right;
    
        private Elem(String key) {
            this.key = key;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    private Elem root = null; // A reference to the root element
    private int size = 0; // The size of the tree

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */
    
    public int size() {
        return size;
    }
  
    /** Creates an entry in the frequency table and initializes its
     *  count to zero.
     *
     * @param key key with which the specified value is to be associated
     * @throws IllegalArgumentException if the key was already present
     */
  
    public void init(String key) {
        if (root == null){
            root = new Elem(key);
            size++;
        }
        else{

            /**
            * The reference to the Elem that the iteration is on 
            */
            Elem current = root;

            while (current != null){
                if (current.key.compareTo(key) > 0){
                    if (current.left == null){
                        current.left = new Elem(key);
                        size++;
                        current = null;
                    }
                    else{
                        current = current.left;
                    }
                }
                else if (current.key.compareTo(key) < 0){
                    if (current.right == null){
                        current.right = new Elem(key);
                        size++;
                        current = null;
                    }
                    else{
                        current = current.right;
                    }
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     * @throws NoSuchElementException if the key is not found
     */
  
    public void update(String key) {
        if (root == null){
            throw new NoSuchElementException();
        }
        else{

            /**
            * The reference to the Elem that the iteration is on 
            */
            Elem current = root;

            /**
            * Whether the key was found in the binary tree 
            */
            boolean found = false;

            while (current != null && !found){
                if (current.key.compareTo(key) > 0){
                    current = current.left;
                }
                else if (current.key.compareTo(key) < 0){
                    current = current.right;
                }
                else{
                    current.count++;
                    found = true;
                }
            }
            if (current == null){
                throw new NoSuchElementException();
            }
        }
    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) {
        if (root == null){
            throw new NoSuchElementException();
        }
        else{

            /**
            * The reference to the Elem that the iteration is on 
            */
            Elem current = root;
            while (current != null){
                if (current.key.compareTo(key) == -1){
                    current = current.left;
                }
                else if (current.key.compareTo(key) == 1){
                    current = current.right;
                }
                else{
                    return current.count;
                }
            }
            if (current == null){
                throw new NoSuchElementException();
            }
        }
        return 6;
    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

        /**
        * The LinkedList that will eventually be returned 
        */
        LinkedList<String> result = new LinkedList<String> ();

        if (root == null){
            return result;
        }
        addKeys(result, root);
        return result;
    }

    /** Add keys onto the LinkedList that is passed as argument
     *  in the correct order. This function uses recursion.
     *
     *  @param list the list that will be modified
     *  @param current the current root the recursion is on
     */
    private void addKeys(LinkedList<String> list, Elem current){
        if (current.left != null){
            addKeys(list, current.left);
        }
        list.addLast(current.key);
        if (current.right != null){
            addKeys(list, current.right);
        }
    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {

        /**
        * An empty array that will eventually be filled with values and returned 
        */
        long [] result = new long [size()];

        if (root == null){
            return result;
        }

        /**
        * Similar to the "keys" function, this list will contain frequencies of
        * keys in the order specified by the method compareTo of the key objects.
        * However, note that getValues() return a list of String rather than long.
        */
        LinkedList<String> values = getValues();

        for (int i = 0; i < result.length; i++){
            result[i] = Long.parseLong(values.get(0));
            values.removeFirst();
        }
        return result;
    }

    /** Returns the list of frequencies in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of frequencies in order
     */
    private LinkedList<String> getValues() {

        /**
        * The list that will be filled with frequencies and eventually returned
        */
        LinkedList<String> result = new LinkedList<String> ();

        if (root == null){
            return result;
        }
        addValues(result, root);
        return result;
    }

    /** Add frequencies onto the LinkedList that is passed as argument
     *  in the correct order. This function uses recursion.
     *
     *  @param list the list that will be modified
     *  @param current the current root the recursion is on
     */
    private void addValues(LinkedList<String> list, Elem current){
        if (current.left != null){
            addValues(list, current.left);
        }
        //Note: a string version of the count is added onto the list instead of the long
        //version, because generics cannot handle long
        list.addLast(current.count+"");
        if (current.right != null){
            addValues(list, current.right);
        }
    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
    
        if (current == null) {
            return "{}";
        }
    
        return "{" + toString(current.left) + "[" + current.key + "," + current.count + "]" + toString(current.right) + "}";
    }
}