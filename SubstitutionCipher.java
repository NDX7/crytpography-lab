
import java.util.Scanner;

public class SubstitutionCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String KEY = "zyxwvutsrqponmlkjihgfedcba";
    
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toLowerCase();
        
        for (char character : plaintext.toCharArray()) {
            int index = ALPHABET.indexOf(character);
            
            if (index != -1) {
                ciphertext.append(KEY.charAt(index));
            } else {
                ciphertext.append(character);
            }
        }
        
        return ciphertext.toString();
    }
    
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toLowerCase();
        
        for (char character : ciphertext.toCharArray()) {
            int index = KEY.indexOf(character);
            
            if (index != -1) {
                plaintext.append(ALPHABET.charAt(index));
            } else {
                plaintext.append(character);
            }
        }
        
        return plaintext.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the message to encrypt: ");
        String originalMessage = scanner.nextLine();
        
        String encryptedMessage = encrypt(originalMessage);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
        
        scanner.close();
    }
}
