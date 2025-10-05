import java.util.*;
import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class RsaEncryption {
    public static void main(String[] args) {
        try {
            String plainText = "hello world";
            System.out.println("Original text: " + plainText);
            
            // Generating the key pair
            KeyPair keyPair = generateRSAKeyPair();
            
            // Encrypt method calling
            String encryptedText = encrypt(plainText, keyPair.getPublic());
            System.out.println("Encrypted text is: " + encryptedText);
            
            // Decrypt method calling
            String decryptedText = decrypt(encryptedText, keyPair.getPrivate());
            System.out.println("Decrypted text is: " + decryptedText);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // RSA key size (2048 bits for security)
        return keyGen.generateKeyPair();
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}
