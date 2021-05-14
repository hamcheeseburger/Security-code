package exceptions;

@SuppressWarnings("serial")
public class ObjNullException extends Exception {
	public ObjNullException() {
		super("객체가 null 입니다.");
	}
}
