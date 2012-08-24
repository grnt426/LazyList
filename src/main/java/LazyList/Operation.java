package LazyList;

/**
 * Author:      Grant Kurtz
 */
public interface Operation {

	public int evaluate(int lhs);

	public int evaluateInverse(int lhs);
}
