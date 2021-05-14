package exceptions;

@SuppressWarnings("serial")
public class NotSatisfiedException extends Exception {
	public NotSatisfiedException() {
		super("요소 불충족");
	}
}
