package LazyList;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

/**
 * Author:      Grant Kurtz
 */
public class LazyList {

	/**
	 * Contains the elements managed by the LazyList.
	 */
	private ArrayList<Integer> list;

	/**
	 * The ordered Operations that have been performed on this list.
	 */
	private ArrayList<Operation> operations;

	public LazyList() {
		list = new ArrayList<Integer>();
		operations = new ArrayList<Operation>();
	}

	public void addElement(int element) {
		list.add(element);
	}

	public int getElement(int index) {
		return applyOperations(list.get(index));
	}

	public int replace(int index, int value) {

		// Determine the actual value that is being replaced
		int replaced = list.get(index);
		replaced = applyOperations(replaced);

		// Reverse the operations on the new value to store correctly
		value = undoOperations(value);
		list.set(index, value);

		// Return the replaced value
		return replaced;
	}

	public void multiply(int multiplier) {
		operations.add(new MultiplyOperation(multiplier));
	}

	public void divide(int divisor){
		operations.add(new DivideOperation(divisor));
	}

	public int getSize() {
		return list.size();
	}

	private int applyOperations(int value) {
		for (Operation o : operations) {
			value = o.evaluate(value);
		}
		return value;
	}

	private int undoOperations(int value) {
		ListIterator<Operation> iterator = operations.listIterator(
				operations.size());
		while(iterator.hasPrevious()){
			value = iterator.previous().evaluateInverse(value);
		}
		return value;
	}
}
