package LazyList;

import junit.framework.TestCase;

/**
 * Author:      Grant Kurtz
 */
public class SubtractionOperationTest extends TestCase {

	private SubtractionOperation subtractionOperation;

	private final int subtractor = 5;
	private final int element = 10;

	protected void setUp() throws Exception {
		subtractionOperation = new SubtractionOperation(subtractor);
	}

	public void testEvaluate() throws Exception {
		assertEquals( (element - subtractor),
				subtractionOperation.evaluate(element));
	}

	public void testEvaluateInverse() throws Exception {
		assertEquals( (element + subtractor),
				subtractionOperation.evaluateInverse(element));
	}
}
