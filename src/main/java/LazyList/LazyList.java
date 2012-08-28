package LazyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Author:      Grant Kurtz
 *
 * A specialized list for performing operations on a very large set of elements.
 * This list guarantees O(1) for all operations applied to the list.  Reads and
 * updates to the list are guaranteed to be O(n), where n is the number of
 * operations that have been applied to the list. Deletes are O(1).
 *
 * The primary advantage of the LazyList is to support quickly applying a small
 * number of operations that need to be performed on very large lists. Iterators
 * are provided that allow for easy iteration across the list, including an
 * iterator that updates each value and clears the list of operations for faster
 * reads.
 *
 * Note, that this class does NOT update the value of each element as it reads
 * it.  The only way to do this is to use a LazyUpdaterIterator to update
 * each element and then flush the operations list.
 */
public class LazyList implements Iterable<Integer>{

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

	public int getAndUpdateElement(int i) {
		int element = getElement(i);
		list.set(i, element);
		return element;
	}

	public int getSize() {
		return list.size();
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

	public void divide(int divisor) {
		operations.add(new DivideOperation(divisor));
	}

	public void add(int adder) {
		operations.add(new AdditionOperation(adder));
	}

	public void subtract(int subtractor) {
		operations.add(new SubtractionOperation(subtractor));
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
		while (iterator.hasPrevious()) {
			value = iterator.previous().evaluateInverse(value);
		}
		return value;
	}

	public void clearOperations() {
		operations.clear();
	}

	public Iterator<Integer> iterator() {
		return new LazyIterator(this);
	}

	public Iterator<Integer> getUpdaterIterator(){
		return new LazyUpdaterIterator(this);
	}
}
