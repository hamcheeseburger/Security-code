package view;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.swing.JFileChooser;

import domain.SignInfo;
import domain.VerifyInfo;
import exceptions.NotSatisfiedException;
import exceptions.ObjNullException;
import function.DigitSign;
import function.MyKeyPair;

public class ViewController {
	private VerifyInfo verifyInfo;
	private SignInfo signInfo;
	private MyKeyPair myKeyPair;
	private DigitSign digitSign;
	
	public ViewController() {
		verifyInfo = VerifyInfo.getInstance();
		signInfo = SignInfo.getInstance();
		myKeyPair = new MyKeyPair();
		digitSign = new DigitSign();
	}
	
	public String btnGenerateKeyHandler() throws ObjNullException{
		System.out.println("btnGenerateKey clicked");
		
		String directoryPath = exeFileSaver("생성한 키 저장");
		
		System.out.println(directoryPath);
		
		if(directoryPath == null) {
			throw new ObjNullException();
		}
		
		myKeyPair.generateKeyPair();
		myKeyPair.saveKeyPair(directoryPath);
		
		return directoryPath;
	}
	
	public String btnSelectPrivateKeyHandler() {
		String filename = exeFileChooser("Private Key 선택");
		
		if(filename != null) {
			signInfo.setRoutePrivateKey(filename);
		}
		
		return filename;
	}
	
	public String btnSelectFileForSignHandler() {
		String filename = exeFileChooser("서명할 파일 선택");
		if(filename != null) {
			signInfo.setRouteFileForSign(filename);
		}
		
		return filename;
	}
	
	public void btnSignHandler() throws ObjNullException, NotSatisfiedException {
		String privateKeyPath = signInfo.getRoutePrivateKey();
		String filePath = signInfo.getRouteFileForSign();
		
		if(privateKeyPath == null || privateKeyPath.equals("") || filePath == null || filePath.equals("")) {
			throw new NotSatisfiedException();
		}
		
		String directoryPath = exeFileSaver("서명한 파일 저장");
		
		if(directoryPath == null) {
			throw new ObjNullException();
		}
		try {
			digitSign.sign(filePath, privateKeyPath, directoryPath);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			System.err.println("Key가 유효하지 않음");
		}
	}
	
	public String btnSelectOriginFileHandler() {
		String filename = exeFileChooser("원본 파일 선택");
		if(filename != null) {
			verifyInfo.setRouteOriginFile(filename);
		}
		
		return filename;
	}
	
	public String btnSelectFileForVerifyHandler() {
		String filename = exeFileChooser("검증할 파일 선택");
		if(filename != null) {
			verifyInfo.setRouteFileForVerify(filename);
		}
		
		return filename;
	}
	
	public String btnSelectPublicKeyHandler() {
		String filename = exeFileChooser("Public Key 선택");
		if(filename != null) {
			verifyInfo.setRoutePublicKey(filename);
		}
		
		return filename;
	}
	
	public boolean btnVerifyHandler() throws NotSatisfiedException {
		String publicKeyPath = verifyInfo.getRoutePublicKey();
		String originFilePath = verifyInfo.getRouteOriginFile();
		String signedFilePath = verifyInfo.getRouteFileForVerify();
		boolean result = false;
		
		if(publicKeyPath == null || publicKeyPath.equals("") 
				|| originFilePath == null || originFilePath.equals("")
				|| signedFilePath == null || signedFilePath.equals("")) {
			throw new NotSatisfiedException();
		}
		
		try {
			result = digitSign.verify(originFilePath, signedFilePath, publicKeyPath);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			System.err.println("Key가 유효하지 않음");
		}
		
		return result;
	}
	
	public String exeFileChooser(String keyword) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(keyword);
		
		chooser.setCurrentDirectory(new File("."));

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION)  {

			File f = chooser.getSelectedFile();

			return f.getAbsolutePath();

		}

		return null;
	}
	
	public String exeFileSaver(String keyword) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(keyword);
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int returnVal = chooser.showSaveDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION)  {

			File f = chooser.getSelectedFile();

			return f.getAbsolutePath();

		}

		return null;
	}
}
