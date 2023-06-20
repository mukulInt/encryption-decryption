package inceptiondecryption;

import inceptiondecryption.common.intercepter.EncryptionDecryptionWithAES;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;

@SpringBootApplication
public class InceptionDecryptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InceptionDecryptionApplication.class, args);
		System.out.println("yes");

//		try {
//			String plaintext = "Hello, World!";
//			System.out.println("Original text : " + plaintext);
//			String encryptedText = EncryptionDecryptionWithAES.encrypt(plaintext);
//			System.out.println("Encrypted text: " + encryptedText);
//			String decryptedText = EncryptionDecryptionWithAES.decrypt(encryptedText);
//			System.out.println("Decrypted text: " + decryptedText);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
