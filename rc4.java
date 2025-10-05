import java.util.*;
import javax.crypto.*;
import java.util.Base64;

public class SimpleRC4 {
    public static void main(String[] args) {
        try {
            String plainText = "hello world";
            System.out.println("Original text: " + plainText);

            // Generate the secret key
            SecretKey sk = generateRC4Key();

            // Encrypt method calling
            String encryptedText = encrypt(plainText, sk);
            System.out.println("Encrypted text is: " + encryptedText);

            // Decrypt method calling
            String decryptedText = decrypt(encryptedText, sk);
            System.out.println("Decrypted text is: " + decryptedText);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static SecretKey generateRC4Key() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("ARCFOUR");
        keyGen.init(128); // RC4 key size (40-2048 bits, 128 for example)
        return keyGen.generateKey();
    }

    public static String encrypt(String plainText, SecretKey sk) throws Exception {
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, sk);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey sk) throws Exception {
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, sk);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}
