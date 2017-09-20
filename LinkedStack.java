//Name: Tianren Wang
//Student ID: 6040795
//ITI 1121-A00
//Assignment 5

/** Implements the interface <code>Stack</code> using linked elements.
 *
 *
 * @author  Marcel Turcotte (turcotte@eecs.uottawa.ca) and Frank T. Wang (fwang100@uottawa.ca)
 */

public class LinkedStack<E> implements Stack<E> {

    // Objects of the class Elem are used to store the elements of the
    // stack.
    
    private static class Elem<T> {
        private T value;
        private Elem<T> next;

        private Elem(T value, Elem<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    // Reference to the top element
    
    private Elem<E> top;

    /** Returns <code>true</code> if this stack is empty, and
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if this stack is empty, and
     * <code>false</code> otherwise.
     */

    public boolean isEmpty() {
        return top == null;
    }

    /** Inserts an element onto the stack.
     *
     * @param value the element to be inserted
     */

    public void push(E value) {

	if (value == null) {
	    throw new NullPointerException();
	}
	
        top = new Elem<E>(value, top);
    }

    /** Returns the top element, without removing it.
     *
     * @return the top element
     */

    public E peek() {

	// pre-condition: the stack is not empty
	
        return top.value;
    }

    /** Removes and returns the top element.
     *
     * @return the top element
     */

    public E pop() {

	// pre-condition: the stack is not empty
	
        E saved = top.value;
        top = top.next;
        return saved;

    }

    /** Removes the top element of the stack. The element inserted at
     * the bottom of the stack.
     */

    public void roll() {
        if (!isEmpty() && top.next != null){
            insertToBottom(pop(), top);
        }
    }

    /** Insert an element with a certain value to the bottom of the stack
     *
     * @param value the element to be inserted
     * @param iteration current recursive Elem iteration the function is on
     */

    public void insertToBottom(E value, Elem<E> iteration) {
        if (iteration.next == null){
            iteration.next = new Elem<E>(value, null);
        }
        else{
            insertToBottom(value, iteration.next);
        }
    }

    /** Removes the botttom element. The element is inserted on the
     * top of the stack.
     */

    public void unroll() {
        if (!isEmpty() && top.next != null){
            push(popBottom(top));
        }
    }

    /** Insert an element with a certain value to the bottom of the stack
     *
     * @param iteration current recursive Elem iteration the function is on
     * @return the bottom element of the stack
     */

    public E popBottom(Elem<E> iteration) {
        if (iteration.next.next == null){

            /**
            * The value to be brought to the top
            */
            E value = iteration.next.value;
            iteration.next = null;
            return value;
        }
        else{
            return popBottom(iteration.next);
        }
    }

    /** Returns a string representation of the stack.
     *
     * @return a string representation
     */

    @Override public String toString() {
	StringBuffer stackStr = new StringBuffer("{");

	Elem<E> current = top;
	
	while (current != null) {
	    stackStr.append(current.value);
	    if (current.next != null) {
		stackStr.append(",");
	    }
	    current = current.next;
	}
	stackStr.append("}");

	return stackStr.toString();
    }
    
}