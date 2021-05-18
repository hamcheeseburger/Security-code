package exceptions;

@SuppressWarnings("serial")
public class GenerateKeyException extends Exception {
	public GenerateKeyException() {
		super("키 생성 불가");
	}
}
