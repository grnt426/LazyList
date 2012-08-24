package LazyList;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class MultiplyOperationTest extends TestCase {

	private MultiplyOperation mo;

	private final int product = 9;
	private final int mutliplier = 3;
	private final int lhs = 3;

	public void setUp() throws Exception {
		mo = new MultiplyOperation(mutliplier);
	}

	public void testEvaluate() throws Exception {
		assertEquals(product, mo.evaluate(lhs));
	}

	public void testEvaluateInverse() throws Exception {
		assertEquals(lhs, mo.evaluateInverse(product));
	}
}
