package function;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SignatureManager {
	private static final String SIG_STAMP = "_SIGNATURED";
	private static final String EXTENTION = ".txt";
	private MyKeyPair myKeyPair;
	private DigitSign digitSign;
	
	public SignatureManager() {
		myKeyPair = new MyKeyPair();
		digitSign = new DigitSign();
	}
	
	public boolean generateAndSaveKeyPair(String path)
			throws FileNotFoundException {
		boolean result = false;
		if(myKeyPair.generateKeyPair()) {
			myKeyPair.saveKeyPair(path);
			result = true;
		}
		
		return result;
	}
	
	public void signAndSaveFile(String dataFilename, String keyFilename, String fileSavePath)
			throws InvalidKeyException, FileNotFoundException {
		PrivateKey privateKey = myKeyPair.restorePrivateKey(keyFilename);
		
		byte [] signature = digitSign.sign(dataFilename, privateKey);
		
		File file = new File(dataFilename);
		System.out.println(file.getName());
		String [] splited = file.getName().split("\\.");
		System.out.println(splited[0]);
		
		fileSavePath = fileSavePath + "/" + splited[0] + SIG_STAMP + EXTENTION;
		
//		생성한 전자서명 쓰기
		digitSign.writeFile(fileSavePath, signature);
	}
	
	public boolean verify(String dataFilename, String sigFilename, String keyFilename) 
			throws InvalidKeyException, FileNotFoundException {
		
		PublicKey publicKey = myKeyPair.restorePublicKey(keyFilename);
		
		return digitSign.verify(dataFilename, publicKey, sigFilename);
	}
	
}
