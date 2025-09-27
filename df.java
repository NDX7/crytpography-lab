import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class df {
    private static BigInteger power(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter prime number (p): ");
        BigInteger p = new BigInteger(scanner.nextLine());

        System.out.print("Enter generator (g): ");
        BigInteger g = new BigInteger(scanner.nextLine());

        SecureRandom random = new SecureRandom();

        BigInteger privateKeyMoideen = new BigInteger(8, random);
        BigInteger privateKeyKanchana = new BigInteger(8, random);

        BigInteger publicKeyMoideen = power(g, privateKeyMoideen, p);
        BigInteger publicKeyKanchana = power(g, privateKeyKanchana, p);

        BigInteger sharedSecretMoideen = power(publicKeyKanchana, privateKeyMoideen, p);
        BigInteger sharedSecretKanchana = power(publicKeyMoideen, privateKeyKanchana, p);

        System.out.println("\nPublic parameters:");
        System.out.println("Prime (p): " + p);
        System.out.println("Generator (g): " + g);
        System.out.println();

        System.out.println("Private keys:");
        System.out.println("Moideen's private key: " + privateKeyMoideen);
        System.out.println("Kanchana's private key: " + privateKeyKanchana);
        System.out.println();

        System.out.println("Public keys:");
        System.out.println("Moideen's public key: " + publicKeyMoideen);
        System.out.println("Kanchana's public key: " + publicKeyKanchana);
        System.out.println();

        System.out.println("Shared secret keys:");
        System.out.println("Moideen's shared secret: " + sharedSecretMoideen);
        System.out.println("Kanchana's shared secret: " + sharedSecretKanchana);

        scanner.close();
    }
}
