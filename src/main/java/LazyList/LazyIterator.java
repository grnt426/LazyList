package LazyList;

import java.util.Iterator;

/**
 * Author:      Grant Kurtz
 *
 * Allows for easy iteration over a LazyList.
 */
public class LazyIterator implements Iterator<Integer> {

	private LazyList lazyList;

	private int index;

	public LazyIterator(LazyList lazyList){
		this.lazyList = lazyList;
		index = 0;
	}

	public boolean hasNext() {
		return lazyList.getSize() < index;
	}

	public Integer next() {
		return lazyList.getElement(index++);
	}

	public void remove() {
		// Maybe support this?
	}
}
