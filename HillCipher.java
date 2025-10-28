import java.util.Scanner;

public class HillCipher{
    // Key matrix for encryption
    static int[][] key = {{3, 3}, {2, 5}};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter text: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        
        if (text.length() % 2 != 0) {
            text += "X";
        }
        
        String encrypted = encrypt(text);
        String decrypted = decrypt(encrypted);
        
        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        sc.close();
    }
    
    static String encrypt(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';
            
            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;
            
            result += (char)(c1 + 'A');
            result += (char)(c2 + 'A');
        }
        return result;
    }
    
    static String decrypt(String text) {
        String result = "";
        
        // Inverse key calculation inline
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]);
        int detInverse = -1;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                detInverse = i;
                break;
            }
        }
        if (detInverse == -1) {
            System.err.println("Error: Key not invertible.");
            return "";
        }
        
        int[][] invKey = new int[2][2];
        invKey[0][0] = (key[1][1] * detInverse) % 26;
        invKey[0][1] = (-key[0][1] * detInverse) % 26;
        invKey[1][0] = (-key[1][0] * detInverse) % 26;
        invKey[1][1] = (key[0][0] * detInverse) % 26;

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';
            
            int p1 = (invKey[0][0] * a + invKey[0][1] * b);
            int p2 = (invKey[1][0] * a + invKey[1][1] * b);
            
            p1 = (p1 % 26 + 26) % 26;
            p2 = (p2 % 26 + 26) % 26;
            
            result += (char)(p1 + 'A');
            result += (char)(p2 + 'A');
        }
        return result;
    }
}
