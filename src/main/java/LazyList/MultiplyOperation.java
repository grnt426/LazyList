package LazyList;

/**
 * Author:      Grant Kurtz
 */
public class MultiplyOperation implements Operation {

	private final int multiplier;

	public MultiplyOperation(int multiplier) {
		this.multiplier = multiplier;
	}

	public int evaluate(int lhs) {
		return lhs * multiplier;
	}
}
