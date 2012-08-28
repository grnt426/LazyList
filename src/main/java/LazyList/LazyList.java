package LazyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Author:      Grant Kurtz
 * <p/>
 * A specialized list for performing operations on a very large set of elements.
 * This list guarantees O(1) for all operations applied to the list.  Reads and
 * updates to the list are guaranteed to be O(n), where n is the number of
 * operations that have been applied to the list. Deletes are O(1).
 * <p/>
 * The primary advantage of the LazyList is to support quickly applying a small
 * number of operations that need to be performed on very large lists. Iterators
 * are provided that allow for easy iteration across the list, including an
 * iterator that updates each value and clears the list of operations for faster
 * reads.
 * <p/>
 * Note, that this class does NOT update the value of each element as it reads
 * it.  The only way to do this is to use a LazyUpdaterIterator to update
 * each element and then flush the operations list.
 */
public class LazyList implements Iterable<Integer> {

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

	/**
	 * Adds an element to the list.
	 *
	 * @param element The element to add.
	 */
	public void addElement(int element) {
		list.add(element);
	}

	/**
	 * Grabs an element from the list, applies any operations that may have
	 * been made to the list, and then returns the resulting value.
	 * <p/>
	 * This function has is O(n), where n is the number of operations applied to
	 * this list creation or since the last use of the LazyUpdaterIterator.
	 *
	 * @param index The position of the element to grab.
	 * @return The value of the element at that position.
	 */
	public int getElement(int index) {
		return applyOperations(list.get(index));
	}

	/**
	 * Grabs an element from the list, applied any operations that may have been
	 * made to the list, replaces the old value, and then returns the resulting
	 * value.
	 * <p/>
	 * Note, this function does NOT guarantee that the element returned has not
	 * already had its value updated.
	 * <p/>
	 * This function is O(n), where n is the number of operations applied to
	 * this list since creation or the last use of the LazyUpdaterIterator.
	 *
	 * @param i The position of the element to grab.
	 * @return The value of the element at that position.
	 */
	public int getAndUpdateElement(int i) {
		int element = getElement(i);
		list.set(i, element);
		return element;
	}

	/**
	 * @return The number of elements in the list.
	 */
	public int getSize() {
		return list.size();
	}

	/**
	 * Replaces the element at the given position with the newly supplied value.
	 * <p/>
	 * This function is O(2n), where n is the number of operations applied to
	 * this list since creation or the last use of the LazyUpdaterIterator.
	 *
	 * @param index The position of the element to replace
	 * @param value The value to replace the previous element with.
	 * @return The old value at the given index.
	 */
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

	/**
	 * Multiplies all elements in the list by the given factor.
	 *
	 * @param multiplier The amount to multiply each element by.
	 */
	public void multiply(int multiplier) {
		operations.add(new MultiplyOperation(multiplier));
	}

	/**
	 * Divides (integer division) all elements in the list by the given divisor.
	 *
	 * @param divisor The amount to divide each element by.
	 */
	public void divide(int divisor) {
		operations.add(new DivideOperation(divisor));
	}

	/**
	 * Adds a value to all elements in the list by the given adder.
	 *
	 * @param adder The amount to add to each element.
	 */
	public void add(int adder) {
		operations.add(new AdditionOperation(adder));
	}

	/**
	 * Subtracts a value from all elements in the list by the given subtractor.
	 *
	 * @param subtractor The amount to subtract by.
	 */
	public void subtract(int subtractor) {
		operations.add(new SubtractionOperation(subtractor));
	}

	/**
	 * Empties the list of operations from this LazyList.
	 */
	public void clearOperations() {
		operations.clear();
	}

	/**
	 * @return	A non-destructive iterator for iterating over each element in
	 * 			this list.
	 */
	public Iterator<Integer> iterator() {
		return new LazyIterator(this);
	}

	/**
	 * This iterator will forcibly replace each element in the list with each
	 * iteration, and then clear the list of operations once the whole LazyList
	 * has been iterated.
	 *
	 * @return	An updating iterator for iterating over each element in this
	 * 			list.
	 */
	public Iterator<Integer> getUpdaterIterator() {
		return new LazyUpdaterIterator(this);
	}

	/**
	 * Applies the full list of operations applied to this list to the given
	 * value.
	 *
	 * @param value The value to be transformed.
	 * @return The result of the applied operations.
	 */
	private int applyOperations(int value) {
		for (Operation o : operations) {
			value = o.evaluate(value);
		}
		return value;
	}

	/**
	 * Applies the mathematical inverse of all the current operations in this
	 * list to the given value.
	 *
	 * @param value The value to be transformed.
	 * @return The result of the applied operations.
	 */
	private int undoOperations(int value) {
		ListIterator<Operation> iterator = operations.listIterator(
				operations.size());
		while (iterator.hasPrevious()) {
			value = iterator.previous().evaluateInverse(value);
		}
		return value;
	}
}
