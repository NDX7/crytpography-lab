import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text to encrypt: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key (8-25): ");
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

            // Use Character.isLowerCase for cleaner range checking
            if (Character.isLowerCase(ch)) {
                // Correct modular arithmetic formula
                ch = (char) ('a' + (ch - 'a' + key) % 26);
            
            // Use Character.isUpperCase for cleaner range checking
            } else if (Character.isUpperCase(ch)) {
                // Correct modular arithmetic formula
                ch = (char) ('A' + (ch - 'A' + key) % 26);
            }
            // Non-alphabetic characters are implicitly unchanged as they are
            // not reassigned 'ch = ...' inside the if/else if blocks

            result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String text, int key) {
        // Standard decryption method: encrypt with a key of (26 - key)
        return encrypt(text, 26 - (key % 26));
    }
}
