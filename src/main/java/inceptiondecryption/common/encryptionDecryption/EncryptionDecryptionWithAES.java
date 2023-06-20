package inceptiondecryption.common.encryptionDecryption;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class EncryptionDecryptionWithAES {

    private static SecretKey generateSecretKey(String keyString) throws Exception {
        // Derive a 256-bit key using PBKDF2
        byte[] salt = "YourSalt".getBytes(StandardCharsets.UTF_8); // Replace with your own salt
        int iterationCount = 10000; // Adjust the iteration count according to your needs
        int keyLength = 256; // Use a key length of 256 bits
        KeySpec keySpec = new PBEKeySpec(keyString.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String encrypt(String plaintext) throws Exception {

        SecretKey yourSecretKey = generateSecretKey("YourSecretKey");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, yourSecretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {

        SecretKey yourSecretKey = generateSecretKey("YourSecretKey");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, yourSecretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


}
