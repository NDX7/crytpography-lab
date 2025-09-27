import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class rc4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the plaintext: ");
            String plaintext = scanner.nextLine();

            System.out.print("Enter the key: ");
            String key = scanner.nextLine();

            byte[] ciphertext = encrypt(key.getBytes(), plaintext.getBytes());
            System.out.println("Ciphertext (hex): " + toHex(ciphertext));

            byte[] decryptedText = decrypt(key.getBytes(), ciphertext);
            System.out.println("Decrypted plaintext: " + new String(decryptedText));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "ARCFOUR");
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] key, byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "ARCFOUR");
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    private static String toHex(byte[] data) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : data) {
            hexString.append(String.format("%02X", b & 0xFF));
        }
        return hexString.toString();
    }
}
