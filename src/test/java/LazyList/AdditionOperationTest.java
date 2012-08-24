package LazyList;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class AdditionOperationTest extends TestCase {

	private AdditionOperation additionOperation;

	private final int adder = 5;
	private final int element = 10;

	protected void setUp() throws Exception {
		additionOperation = new AdditionOperation(adder);
	}

	public void testEvaluate() throws Exception {
		assertEquals( (element + adder), additionOperation.evaluate(element));
	}

	public void testEvaluateInverse() throws Exception {
		assertEquals( (element - adder),
				additionOperation.evaluateInverse(element));
	}
}
