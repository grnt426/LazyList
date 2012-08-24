package LazyList;

/**
 * Author:      Grant Kurtz
 */
public class MultiplyOperation implements Operation {

	private int multiplier;

	private DivideOperation inverse;

	public MultiplyOperation(int multiplier) {
		this.multiplier = multiplier;
		inverse = DivideOperation.createDivideOperation(multiplier);
	}

	private MultiplyOperation(){

	}

	public static MultiplyOperation createMultiplierOperation(int multiplier) {
		MultiplyOperation mo = new MultiplyOperation();
		mo.setMultiplier(multiplier);
		return mo;
	}

	public int evaluate(int lhs) {
		return lhs * multiplier;
	}

	public int evaluateInverse(int lhs) {
		return inverse.evaluate(lhs);
	}

	private void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
}
