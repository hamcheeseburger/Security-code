package function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MyKeyPair {
	private static final String keyAlgorithm = "RSA";
	private static final int keyLength = 1024;
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public MyKeyPair() {
		publicKey = null;
		privateKey = null;
	}
	
	public void generateKeyPair() {
		KeyPair keyPair = null;
		
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(keyAlgorithm);
			generator.initialize(keyLength);
			
			keyPair = generator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
	
	}
	
	public void saveKeyPair(String path) {
		String privateFilename = path + "/privateKey.txt";
		String publicFilename = path + "/publicKey.txt";
		
		System.out.println(privateFilename);
		System.out.println(publicFilename);
		
		try (FileOutputStream stream = new FileOutputStream(privateFilename)) {
			try(ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
				ostream.writeObject(this.privateKey);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try (FileOutputStream stream = new FileOutputStream(publicFilename)) {
			try(ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
				ostream.writeObject(this.publicKey);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	PublicKey restorePublicKey(String filename) {
		try (FileInputStream stream = new FileInputStream(filename)) {
			try(ObjectInputStream istream = new ObjectInputStream(stream)) {
				Object object= istream.readObject();
				this.publicKey = (PublicKey) object;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return this.publicKey;
	}
	
	PrivateKey restorePrivateKey(String filename) {
		try (FileInputStream stream = new FileInputStream(filename)) {
			try(ObjectInputStream istream = new ObjectInputStream(stream)) {
				Object object= istream.readObject();
				this.privateKey = (PrivateKey) object;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return this.privateKey;
	}
}
