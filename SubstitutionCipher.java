import java.util.Scanner;

public class SubstitutionCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get key and plaintext from user
        System.out.println("Enter 26-character key (e.g., QWERTYUIOPASDFGHJKLZXCVBNM):");
        String key = scanner.nextLine().toUpperCase();
        
        System.out.println("Enter plaintext:");
        String plaintext = scanner.nextLine().toUpperCase();
        
        // Perform encryption
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        
        // Perform decryption
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);
        
        scanner.close();
    }
    
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = alphabet.indexOf(c);
                ciphertext.append(key.charAt(index));
            } else {
                ciphertext.append(c); // Keep non-letters unchanged
            }
        }
        return ciphertext.toString();
    }
    
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = key.indexOf(c);
                plaintext.append(alphabet.charAt(index));
            } else {
                plaintext.append(c); // Keep non-letters unchanged
            }
        }
        return plaintext.toString();
    }
}
