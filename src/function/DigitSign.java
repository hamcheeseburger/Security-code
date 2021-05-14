package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DigitSign {
	private MyKeyPair myKeyPair;
	private static final String algorithm = "SHA256withRSA";
	private static final String SIG_STAMP = "_sig";
	
	public DigitSign() {
		myKeyPair = new MyKeyPair();
	}
	
	public void sign(String dataFilename, String keyFilename, String fileSavePath) throws InvalidKeyException {
//		���� �б�
		byte[] content = readFile(dataFilename);
		byte [] signature = null;
		PrivateKey privateKey = myKeyPair.restorePrivateKey(keyFilename);
		
		Signature sig;
		try {
			sig = Signature.getInstance(algorithm);
			sig.initSign(privateKey);
			sig.update(content);
			signature = sig.sign();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(dataFilename);
		System.out.println(file.getName());
		
		fileSavePath = fileSavePath + "/" + file.getName() + SIG_STAMP + ".txt";
		
//		������ ���ڼ��� ����
		writeFile(fileSavePath, signature);
	}
	
	public boolean verify(String dataFilename, String sigFilename, String keyFilename) throws InvalidKeyException {
		byte [] originContent = readFile(dataFilename);
		byte [] signedContent = readFile(sigFilename);
		boolean rslt = false;
		
		PublicKey publicKey = myKeyPair.restorePublicKey(keyFilename);
		
		try {
			Signature sig = Signature.getInstance(algorithm);
			
			sig.initVerify(publicKey);
			sig.update(originContent);
			
			rslt = sig.verify(signedContent);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rslt;
	}
	
	public byte [] readFile(String filename) {
		byte [] readBuffer = null;
		FileInputStream fileStream = null; // ���� ��Ʈ��

	    try {
	       fileStream = new FileInputStream( filename );// ���� ��Ʈ�� ����

	       //���� ������ ���� ���� ����
	       readBuffer = new byte[ fileStream.available( ) ];
	       while (fileStream.read( readBuffer ) != -1) {}
	       
	       System.out.println(new String(readBuffer));
	    }
	   catch ( Exception e ) {
	            System.out.println( "���� ����� ����!!" + e );
	   } finally {
		   try {
			   fileStream.close( );//���� �ݱ�. ���⿡�� try/catch�� �ʿ��ϴ�.
		   }catch ( Exception e ) {
			   System.out.println( "�ݱ� ����" + e );
		   }
	   }
	    
	    return readBuffer;
	}
	
	public void writeFile(String filename, byte [] content) {
		FileOutputStream outputStream = null; // ���� ���� ��Ʈ��
		
		try {
			outputStream = new FileOutputStream(filename);
			outputStream.write(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			}catch(Exception e) {
				
			}
			
		}
	}
}
