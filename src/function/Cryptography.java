package function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Cryptography {

	void savePublicKey(PublicKey publicKey, String filename) {
		try (FileOutputStream stream = new FileOutputStream(filename)) {
			try(ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
				ostream.writeObject(publicKey);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	PublicKey restorePublicKey(String filename) {
		PublicKey publicKey = null;
		try (FileInputStream stream = new FileInputStream(filename)) {
			try(ObjectInputStream istream = new ObjectInputStream(stream)) {
				Object object= istream.readObject();
				publicKey = (PublicKey) object;
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
		
		return publicKey;
	}
	
	void savePrivateKey(PrivateKey privateKey, String filename) {
		try (FileOutputStream stream = new FileOutputStream(filename)) {
			try(ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
				ostream.writeObject(privateKey);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	PrivateKey restorePrivateKey(String filename) {
		PrivateKey privateKey = null;
		try (FileInputStream stream = new FileInputStream(filename)) {
			try(ObjectInputStream istream = new ObjectInputStream(stream)) {
				Object object= istream.readObject();
				privateKey = (PrivateKey) object;
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
		
		return privateKey;
	}
}
