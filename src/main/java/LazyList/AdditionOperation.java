package LazyList;

/**
 * Author:      Grant Kurtz
 */
public class AdditionOperation implements Operation {

	private int adder;

	private SubtractionOperation inverse;

	public AdditionOperation(int adder) {
		this.adder = adder;
		inverse = SubtractionOperation.createSubtractionOperation(adder);
	}

	private AdditionOperation() {

	}

	public int evaluate(int lhs) {
		return lhs + adder;
	}

	public int evaluateInverse(int lhs) {
		return inverse.evaluate(lhs);
	}

	public static AdditionOperation createAdditionOperation(int adder) {
		AdditionOperation addOperation = new AdditionOperation();
		addOperation.setAdder(adder);
		return addOperation;
	}

	public void setAdder(int adder) {
		this.adder = adder;
	}
}
