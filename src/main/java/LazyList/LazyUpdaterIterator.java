package LazyList;

import java.util.Iterator;

/**
 * Author:      Grant Kurtz
 *
 * Allows for iterating over a LazyList, but will update each element in the
 * list its final value after all operations are applied.  Once the whole
 * list has been iterated, this LazyUpdaterIterator will automatically clear
 * all operations within the LazyList.
 *
 * Note: This class is in no way concurrency safe.  Totally unpredictable
 * behavior is guaranteed if this LazyUpdaterIterator is used concurrently
 * with the base LazyList, a LazyIterator, or another LazyUpdaterIterator.
 */
public class LazyUpdaterIterator implements Iterator<Integer> {

	private LazyList lazyList;

	private int index;

	public LazyUpdaterIterator(LazyList lazyList) {
		this.lazyList = lazyList;
		index = 0;
	}

	public boolean hasNext() {
		return lazyList.getSize() < index;
	}

	public Integer next() {
		int element = lazyList.getAndUpdateElement(index++);
		if(index == lazyList.getSize())
			lazyList.clearOperations();
		return element;
	}

	public void remove() {
		// Maybe support this?
	}
}
