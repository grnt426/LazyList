package LazyList;

/**
 * Author:      Grant Kurtz
 */
public class SubtractionOperation implements Operation {

	private int subtractor;

	private AdditionOperation inverse;

	public SubtractionOperation(int subtractor) {
		this.subtractor = subtractor;
		inverse = AdditionOperation.createAdditionOperation(subtractor);
	}

	private SubtractionOperation() {

	}

	public int evaluate(int lhs) {
		return lhs - subtractor;
	}

	public int evaluateInverse(int lhs) {
		return inverse.evaluate(lhs);
	}

	public static SubtractionOperation createSubtractionOperation(
			int subtractor) {
		SubtractionOperation subtractorOperation = new SubtractionOperation();
		subtractorOperation.setSubtractor(subtractor);
		return subtractorOperation;
	}

	private void setSubtractor(int subtractor) {
		this.subtractor = subtractor;
	}
}
