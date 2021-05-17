package view;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.NotSatisfiedException;
import function.SignatureManager;


class ViewController {
	private SignatureManager manager;
	
	ViewController() {
		manager = new SignatureManager();
	}
	
	String btnGenerateKeyHandler() throws FileNotFoundException {
		System.out.println("btnGenerateKey clicked");
		
		String directoryPath = exeFileSaver("������ Ű ����");
		
		System.out.println(directoryPath);
		
		if(directoryPath != null) {
			manager.generateAndSaveKeyPair(directoryPath);	
		}
		
		return directoryPath;
	}
	
	String btnSelectPrivateKeyHandler() {
		String filename = exeKeyChooser("Private Key ����");
		
		return filename;
	}
	
	String btnSelectFileForSignHandler() {
		String filename = exeFileChooser("������ ���� ����");
		
		return filename;
	}
	
	void btnSignHandler(String routePrivateKey, String routeFileForSign)
			throws NotSatisfiedException, InvalidKeyException, FileNotFoundException {

		if(routePrivateKey.equals("") ||  routeFileForSign.equals("")) {
			throw new NotSatisfiedException();
		}
		
		String directoryPath = exeFileSaver("������ ���� ����");
		
		if(directoryPath != null) {
			manager.signAndSaveFile(routeFileForSign, routePrivateKey, directoryPath);
			
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
		String filename = exeKeyChooser("Public Key ����");
		
		return filename;
	}
	
	boolean btnVerifyHandler(String routeOriginFile, String routeFileForVerify, String routePublicKey) 
			throws NotSatisfiedException, InvalidKeyException, FileNotFoundException {
		
		if(routeOriginFile.equals("") 
				|| routeFileForVerify.equals("")
				|| routePublicKey.equals("")) {
			throw new NotSatisfiedException();
		}
		
		return manager.verify(routeOriginFile, routeFileForVerify, routePublicKey);

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
	
	String exeKeyChooser(String keyword) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(keyword);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PEM File", "pem"); // filter Ȯ���� �߰�
        chooser.setFileFilter(filter); // ���� ���͸� �߰�
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
