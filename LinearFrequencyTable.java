//Name: Tianren Wang
//Student ID: 6040795
//ITI 1121-A00
//Assignment 5

import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using linked
 *  elements. The linked structure is circular and uses a dummy node.
 *
 * @author Frank T. Wang (fwang100@uottawa.ca)
 */

public class LinearFrequencyTable implements FrequencyTable {

    // Linked elements

    private static class Elem {

    	private String key;
    	private long count;
    	private Elem previous;
    	private Elem next;

    	private Elem(String key, Elem previous, Elem next) {
    	    this.key = key;
    	    this.count = 0;
    	    this.previous = previous;
    	    this.next = next;
    	}
    }

    private Elem head;
    private int size;

    /** Constructs and empty <strong>FrequencyTable</strong>.
     */

    public LinearFrequencyTable() {
    	head = new Elem(null, null, null); // dummy node
    	head.previous = head; // making the dummy node circular
    	head.next = head; // making the dummy node circular
    	size = 0;
    }

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */

    public int size() {
	   return size;
    }
  
    /** Returns the frequency value associated with this key.
     *
     *  @param key key whose frequency value is to be returned
     *  @return the frequency associated with this key
     *  @throws NoSuchElementException if the key is not found
     */

    public long get(String key) {

        /**
        * The reference to the Elem that the iteration is on 
        */
        Elem current = head.next;

        while (current != head && !current.key.equals(key)){
            current = current.next;
        }
        if (current == head){
            throw new NoSuchElementException();
        }
        else{
            return current.count;
        }
    }

    /** Creates an entry in the frequency table and initializes its
     *  count to zero. The keys are kept in order (according to their
     *  method <strong>compareTo</strong>).
     *
     *  @param key key with which the specified value is to be associated
     *  @throws IllegalArgumentException if the key was alreaddy present
     */

    public void init(String key) {

        /**
        * The reference to the Elem that the iteration is on 
        */
        Elem current = head.next;
        if (current == head){
            head.next = new Elem(key, head, head);
            head.previous = head.next;
            size++;
        }
        else{

            /**
            * Stores the value of the comparison operation
            */
            int comparison = current.key.compareTo(key);

            //Continues this loop as long as current is not right before head and 
            //key is bigger than the current value
            while (current.next != head && comparison < 0){
                current = current.next;
                comparison = current.key.compareTo(key);
            }
            if (comparison > 0){
                current.previous.next = new Elem(key, current.previous, current);
                current.previous = current.previous.next;
                size++;
            }
            else if (current.next == head){
                current.next = new Elem(key, head.previous, head);
                head.previous = current.next;
                size++;
            }
            else if (comparison == 0){
                throw new IllegalArgumentException();
            }
        }
    }

    /** The method updates the frequency associed with the key by one.
     *
     *  @param key key with which the specified value is to be associated
     *  @throws NoSuchElementException if the key is not found
     */

    public void update(String key) {

        /**
        * The reference to the Elem that the iteration is on 
        */
        Elem current = head.next;

        while (current != head && !current.key.equals(key)){
            current = current.next;
        }
        if (current == head){
            throw new NoSuchElementException();
        }
        else{
            current.count++;
        }
    }

    /** Returns the list of keys in order, according to the method
     *  <strong>compareTo</strong> of the key objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

        /**
        * The LinkedList that will eventually be returned 
        */
        LinkedList<String> result = new LinkedList<String> ();

        /**
        * The reference to the Elem that the iteration is on 
        */
        Elem current = head.next;
        while (current != head){
            result.addLast(current.key);
            current = current.next;
        }
        return result;
    }

    /** Returns an array containing the frequencies of the keys in the
     *  order specified by the method <strong>compareTo</strong> of
     *  the key objects.
     *
     *  @return an array of frequency counts
     */

    public long[] values() {

        /**
        * A variable to keep track of the size of the object
        */
        int n = size();

        /**
        * The array that will eventually be returned 
        */
        long [] frequencies = new long [n];

        /**
        * The reference to the Elem that the iteration is on 
        */
        Elem current = head.next;
        for(int i = 0; i < n;i++){
            frequencies[i] = current.count;
            current = current.next;
        }
        return frequencies;
    }

    /** Returns a <code>String</code> representations of the elements
     * of the frequency table.
     *  
     *  @return the string representation
     */

    public String toString() {

	StringBuffer str = new StringBuffer("{");
	Elem p = head.next;

	while (p != head) {
	    str.append("{key="+p.key+", count="+p.count+"}");
	    if (p.next != head) {
		str.append(",");
	    }
	    p = p.next;
	}
	str.append("}");
	return str.toString();
    }

}