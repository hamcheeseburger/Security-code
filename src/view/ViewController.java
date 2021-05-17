package view;
import java.io.File;
import java.security.InvalidKeyException;

import javax.swing.JFileChooser;
import exceptions.NotSatisfiedException;
import exceptions.ObjNullException;
import function.DigitSign;
import function.MyKeyPair;

public class ViewController {
	private MyKeyPair myKeyPair;
	private DigitSign digitSign;
	
	public ViewController() {
		myKeyPair = new MyKeyPair();
		digitSign = new DigitSign();
	}
	
	public String btnGenerateKeyHandler() throws ObjNullException{
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
	
	public String btnSelectPrivateKeyHandler() {
		String filename = exeFileChooser("Private Key ����");
		
		return filename;
	}
	
	public String btnSelectFileForSignHandler() {
		String filename = exeFileChooser("������ ���� ����");
		
		return filename;
	}
	
	public void btnSignHandler(String routePrivateKey, String routeFileForSign) throws ObjNullException, NotSatisfiedException {

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
	
	public String btnSelectOriginFileHandler() {
		String filename = exeFileChooser("���� ���� ����");

		return filename;
	}
	
	public String btnSelectFileForVerifyHandler() {
		String filename = exeFileChooser("������ ���� ����");

		return filename;
	}
	
	public String btnSelectPublicKeyHandler() {
		String filename = exeFileChooser("Public Key ����");
		
		return filename;
	}
	
	public boolean btnVerifyHandler(String routeOriginFile, String routeFileForVerify, String routePublicKey) throws NotSatisfiedException {
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
