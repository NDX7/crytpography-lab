import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DESEncryption {
    private SecretKey secretKey;
    private Cipher cipher;

    public DESEncryption() throws Exception {
        // Initialize DES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); // DES uses 56-bit keys
        this.secretKey = keyGenerator.generateKey();
        
        // Initialize cipher
        this.cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    }

    public String encrypt(String plainText) throws Exception {
        // Convert input string to bytes
        byte[] plainTextBytes = plainText.getBytes("UTF-8");
        
        // Initialize cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        // Encrypt the text
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        
        // Convert to Base64 for readable string output
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        // Decode Base64 string to bytes
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        
        // Initialize cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        // Decrypt the text
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        
        // Convert back to string
        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            DESEncryption des = new DESEncryption();
            
            // Example usage
            String originalText = "Hello, this is a secret message!";
            System.out.println("Original text: " + originalText);
            
            // Encrypt
            String encryptedText = des.encrypt(originalText);
            System.out.println("Encrypted text: " + encryptedText);
            
            // Decrypt
            String decryptedText = des.decrypt(encryptedText);
            System.out.println("Decrypted text: " + decryptedText);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
