package LazyList;

import java.util.ArrayList;
import java.util.LinkedList;
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
	private Queue<Operation> operations;

	public LazyList(){
		list = new ArrayList<Integer>();
		operations = new LinkedList<Operation>();
	}

	public void addElement(int element){
		list.add(element);
	}

	public int getElement(int index){
		int element = list.get(index);
		for(Operation o : operations){
			element = o.evaluate(element);
		}
		return element;
	}

	public void multiply(int multiplier){
		operations.add(new MultiplyOperation(multiplier));
	}

	public int getSize(){
		return list.size();
	}
}
