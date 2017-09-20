//Name: Tianren Wang
//Student ID: 6040795
//ITI 1121-A00
//Assignment 5

import java.util.Arrays;
import java.io.IOException;

/** Minimalist test for the implementations of the frequency table.
 *
 * @author  Marcel Turcotte (turcotte@eecs.uottawa.ca)
 */

public class TestFrequencyTable {

    private static void init(FrequencyTable t, int k) {

	String[] alphabet = {"A", "C", "G", "T"};

	LinkedList<String> keys = new LinkedList<String>();

	for (String a : alphabet) {
	    keys.addLast(a);
	}

	boolean done = false;

	while (! done) {

	    String key = keys.get(0);

	    if (key.length() == k) {
		done = true;
	    } else {
		keys.removeFirst();
		for (String a : alphabet) {
		    keys.addLast(a+key);
		}
	    }
	    
	}

	for (int i=0; i<keys.size(); i++) {
	    t.init(keys.get(i));
	}

    }

    private static void update(FrequencyTable t, int k, String s) {
	for (int i=0; i < s.length() - k + 1; i++) {
            t.update(s.substring(i, i+k));
        }
    }

    public static void main(String[] args) {

    System.out.println("");
	StudentInfo.display();
	System.out.println("");

	int k = 5;

	String s = null;

	try {
	    s = Utils.readFile("data/NC_000913.txt");
	} catch (IOException e) {
	    s = "ATGGGCCGCAACCGGGCGAAAGAGGCGAAGTGGGGAGGGGGAGATCCCGAGGAGGATCTCCAACTAAAGA";
	}

	Utils.setType("LINEAR");

	FrequencyTable linear = Utils.getFrequencyTable();

	Utils.setType("TREE");

	FrequencyTable tree = Utils.getFrequencyTable();
	
	init(linear, k);
	init(tree, k);

	update(linear, k, s);
	update(tree, k, s);

	long[] xs = linear.values();
	long[] ys = tree.values();

	if (! Arrays.equals(xs, ys)) {
	    System.out.println("values are not equals!");
	    System.out.println("Linear");
	    System.out.println(Arrays.toString(xs));
	    System.out.println("Tree");
	    System.out.println(Arrays.toString(ys));
	}

	//Extra lines of code used to debug the linear and tree frequency table functions
	/*LinkedList<String> linearKeys = linear.keys();
	Iterator<String> linearIterator = linearKeys.iterator();
	LinkedList<String> treeKeys = tree.keys();
	Iterator<String>  treeIterator = treeKeys.iterator();


	for (int i = 0; i < linearKeys.size(); i++){
			System.out.println(i + ": " + linearIterator.next() + " = " + xs[i] + "::: " + treeIterator.next() + " = " + ys[i]);
	}*/
    }

}

// > java TestFrequencyTable
// ************************************************************
// *                                                          *
// *                                                          *
// *                                                          *
// *                                                          *
// ************************************************************
