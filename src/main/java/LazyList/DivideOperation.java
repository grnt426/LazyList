package LazyList;

/**
 * Author:      Grant Kurtz
 */
public class DivideOperation implements Operation{

	private int divisor;

	private MultiplyOperation inverse;

	public DivideOperation(int divisor){
		this.divisor = divisor;
		inverse = MultiplyOperation.createMultiplierOperation(divisor);
	}

	private DivideOperation(){

	}

	public int evaluate(int lhs) {
		return lhs / divisor;
	}

	public int evaluateInverse(int lhs) {
		return inverse.evaluate(lhs);
	}

	public static DivideOperation createDivideOperation(int divisor) {
		DivideOperation divideOp = new DivideOperation();
		divideOp.setDivisor(divisor);
		return divideOp;
	}

	private void setDivisor(int divisor) {
		this.divisor = divisor;
	}
}
