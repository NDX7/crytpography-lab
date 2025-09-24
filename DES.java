import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DES {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the DES key (8 characters long):");
        String userKey = scanner.nextLine();
        if (userKey.length() != 8) {
            System.out.println("Error: DES key must be exactly 8 characters long.");
            scanner.close();
            return;
        }
        byte[] keyBytes = userKey.getBytes("UTF-8");
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "DES");
        System.out.println("Enter the message to encrypt:");
        String originalMessage = scanner.nextLine();
        scanner.close();
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(originalMessage.getBytes("UTF-8"));
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Message: " + encryptedMessage);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage = new String(decryptedBytes, "UTF-8");
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
