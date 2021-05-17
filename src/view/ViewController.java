package view;
import java.io.File;
import java.security.InvalidKeyException;

import javax.swing.JFileChooser;
import exceptions.NotSatisfiedException;
import exceptions.ObjNullException;
import function.DigitSign;
import function.MyKeyPair;

class ViewController {
	private MyKeyPair myKeyPair;
	private DigitSign digitSign;
	
	ViewController() {
		myKeyPair = new MyKeyPair();
		digitSign = new DigitSign();
	}
	
	String btnGenerateKeyHandler() throws ObjNullException{
		System.out.println("btnGenerateKey clicked");
		
		String directoryPath = exeFileSaver("������ Ű ����");
		
		System.out.println(directoryPath);
		
		if(directoryPath == null) {
			throw new ObjNullException();
		}
		
		myKeyPair.generateKeyPair();
		myKeyPair.saveKeyPair(directoryPath);
		
		return directoryPath;
	}
	
	String btnSelectPrivateKeyHandler() {
		String filename = exeFileChooser("Private Key ����");
		
		return filename;
	}
	
	String btnSelectFileForSignHandler() {
		String filename = exeFileChooser("������ ���� ����");
		
		return filename;
	}
	
	void btnSignHandler(String routePrivateKey, String routeFileForSign)
			throws ObjNullException, NotSatisfiedException {

		if(routePrivateKey.equals("") ||  routeFileForSign.equals("")) {
			throw new NotSatisfiedException();
		}
		
		String directoryPath = exeFileSaver("������ ���� ����");
		
		if(directoryPath == null) {
			throw new ObjNullException();
		}
		
		try {
			digitSign.sign(routeFileForSign, routePrivateKey, directoryPath);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			System.err.println("Key�� ��ȿ���� ����");
		}
	}
	
	String btnSelectOriginFileHandler() {
		String filename = exeFileChooser("���� ���� ����");

		return filename;
	}
	
	String btnSelectFileForVerifyHandler() {
		String filename = exeFileChooser("������ ���� ����");

		return filename;
	}
	
	String btnSelectPublicKeyHandler() {
		String filename = exeFileChooser("Public Key ����");
		
		return filename;
	}
	
	boolean btnVerifyHandler(String routeOriginFile, String routeFileForVerify, String routePublicKey) 
			throws NotSatisfiedException {
		boolean result = false;
		
		if(routeOriginFile.equals("") 
				|| routeFileForVerify.equals("")
				|| routePublicKey.equals("")) {
			throw new NotSatisfiedException();
		}
		
		try {
			result = digitSign.verify(routeOriginFile, routeFileForVerify, routePublicKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			System.err.println("Key�� ��ȿ���� ����");
		}
		
		return result;
	}
	
	String exeFileChooser(String keyword) {
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
	
	String exeFileSaver(String keyword) {
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
