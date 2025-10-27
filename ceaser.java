
import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the text to encrypt: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter the key (0-25): ");
        int key = scanner.nextInt();
        
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Message: " + encryptedText);
        
        System.out.println("\nDecrypting the message...");
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Message: " + decryptedText);
        
        scanner.close();
    }
    
    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) ('a' + (ch - 'a' + key) % 26);
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('A' + (ch - 'A' + key) % 26);
            }
            
            result.append(ch);
        }
        
        return result.toString();
    }
    
    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - (key % 26));
    }
}