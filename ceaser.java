import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Taking user input
        System.out.print("Enter a message to encrypt: ");
        String text = scanner.next();
        
        System.out.print("Enter the key: ");
        int key = scanner.nextInt();
        
        // Convert string to char array for modification
        char[] textArray = text.toCharArray();
        
        // Visiting character by character
        for (int i = 0; i < textArray.length; i++) {
            char ch = textArray[i];
            
            // Check for valid characters
            if (Character.isLetterOrDigit(ch)) {
                
                // Lowercase characters
                if (Character.isLowerCase(ch)) {
                    ch = (char) ((ch - 'a' + key) % 26 + 'a');
                }
                // Uppercase characters
                else if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 'A' + key) % 26 + 'A');
                }
                // Numbers
                else if (Character.isDigit(ch)) {
                    ch = (char) ((ch - '0' + key) % 10 + '0');
                }
                
                // Adding encoded answer
                textArray[i] = ch;
            }
            // Invalid character
            else {
                System.out.println("Invalid Message");
                scanner.close();
                return;
            }
        }
        
        // Convert char array back to string
        String encryptedText = new String(textArray);
        System.out.println("Encrypted message: " + encryptedText);
        
        scanner.close();
    }
}