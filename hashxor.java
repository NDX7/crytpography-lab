import java.util.Scanner;

public class XORHash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the text to hash: ");
            String input = sc.nextLine();
            int hash = computeXORHash(input);
            System.out.println("8-bit XOR Hash: " + hash);
        } finally {
            sc.close();
        }
    }

    public static int computeXORHash(String input) {
        byte[] bytes = input.getBytes(); // Convert string to bytes (default UTF-8)
        byte hash = 0; // Initialize 8-bit hash
        for (byte b : bytes) {
            hash ^= b; // XOR each byte with the current hash
        }
        return hash & 0xFF; // Convert to unsigned int (0–255)
    }
}
