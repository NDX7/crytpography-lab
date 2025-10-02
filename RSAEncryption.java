import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAEncryption {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Cipher cipher;

    public RSAEncryption() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    public String encrypt(String plainText) throws Exception {
        byte[] plainTextBytes = plainText.getBytes("UTF-8");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            RSAEncryption rsa = new RSAEncryption();
            String originalText = "Hello, this is a secret message!";
            System.out.println("Original text: " + originalText);
            String encryptedText = rsa.encrypt(originalText);
            System.out.println("Encrypted text: " + encryptedText);
            String decryptedText = rsa.decrypt(encryptedText);
            System.out.println("Decrypted text: " + decryptedText);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
