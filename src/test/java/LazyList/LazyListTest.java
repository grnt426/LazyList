package LazyList;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class LazyListTest extends TestCase {

	LazyList list;

	public void setUp() throws Exception {
		list = new LazyList();
	}

	public void testAddElement() throws Exception {
		list.addElement(4);
		assertEquals(1, list.getSize());
	}

	public void testGetElement() throws Exception {
		int element = 4;
		list.addElement(element);
		assertEquals(element, list.getElement(0));
	}

	public void testMultiply() throws Exception {
		int element = 4;
		int multiplier = 2;
		list.addElement(element);
		list.multiply(multiplier);
		assertEquals( (element * multiplier), list.getElement(0));
	}
}
