package view;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.GenerateKeyException;
import exceptions.NotSatisfiedException;
import function.SignatureManager;


class ViewController {
	private SignatureManager manager;
	ViewController() {
		manager = new SignatureManager();
	}
	
	String btnGenerateKeyHandler() throws GenerateKeyException {
		System.out.println("btnGenerateKey clicked");
		
		String directoryPath = exeFileSaver("생성한 키 저장");
		
		System.out.println(directoryPath);
		
		if(directoryPath != null) {
			if(!manager.generateAndSaveKeyPair(directoryPath)) {
				throw new GenerateKeyException();
			}
		}
		
		return directoryPath;
	}
	
	String btnSelectPrivateKeyHandler() {
		String filename = exeKeyChooser("Private Key 선택");
		
		return filename;
	}
	
	String btnSelectFileForSignHandler() {
		String filename = exeFileChooser("서명할 파일 선택");
		
		return filename;
	}
	
	void btnSignHandler(String routePrivateKey, String routeFileForSign)
			throws NotSatisfiedException, InvalidKeyException, FileNotFoundException, ClassCastException {

		if(routePrivateKey.equals("") ||  routeFileForSign.equals("")) {
			throw new NotSatisfiedException();
		}
		
		String directoryPath = exeFileSaver("서명한 파일 저장");
		
		if(directoryPath != null) {
			manager.signAndSaveFile(routeFileForSign, routePrivateKey, directoryPath);
			
		}
	}
	
	String btnSelectOriginFileHandler() {
		String filename = exeFileChooser("원본 파일 선택");

		return filename;
	}
	
	String btnSelectFileForVerifyHandler() {
		String filename = exeFileChooser("검증할 파일 선택");

		return filename;
	}
	
	String btnSelectPublicKeyHandler() {
		String filename = exeKeyChooser("Public Key 선택");
		
		return filename;
	}
	
	boolean btnVerifyHandler(String routeOriginFile, String routeFileForVerify, String routePublicKey) 
			throws NotSatisfiedException, InvalidKeyException, FileNotFoundException, ClassCastException {
		
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PEM File", "pem"); // filter 확장자 추가
        chooser.setFileFilter(filter); // 파일 필터를 추가
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
