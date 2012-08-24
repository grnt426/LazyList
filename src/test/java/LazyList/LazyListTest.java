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

	public void testMultiplySeveral() throws Exception{
		int element = 4;
		int multiplierOne = 2;
		int multiplierTwo = 4;
		list.addElement(element);
		list.multiply(multiplierOne);
		list.multiply(multiplierTwo);
		assertEquals( (element * multiplierOne * multiplierTwo),
				list.getElement(0));
	}

	public void testMultiplySeveralElements() throws Exception{
		int elementOne = 4;
		int elementTwo = 6;
		int multiplier = 3;
		list.addElement(elementOne);
		list.addElement(elementTwo);
		list.multiply(multiplier);
		assertEquals( (elementOne * multiplier), list.getElement(0));
		assertEquals( (elementTwo * multiplier), list.getElement(1));
	}
}
