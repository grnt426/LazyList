package LazyList;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class DivideOperationTest extends TestCase {

	private DivideOperation divideOperation;

	private final int divisor = 3;
	private final int dividend = 9;
	private final int quotient = 3;

	protected void setUp() throws Exception {
		divideOperation = new DivideOperation(divisor);
	}

	public void testEvaluate() throws Exception {
		assertEquals(quotient, divideOperation.evaluate(dividend));
	}

	public void testEvaluateInverse() throws Exception {
		assertEquals(dividend, divideOperation.evaluateInverse(quotient));
	}
}
