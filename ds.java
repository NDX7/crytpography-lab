import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class SimpleDigitalSignatureDSA {
    public static void main(String[] args) throws Exception {
        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message to sign: ");
        String message = scanner.nextLine();
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        scanner.close();

        // Generate DSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign the message
        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initSign(privateKey);
        signature.update(messageBytes);
        byte[] digitalSignature = signature.sign();
        System.out.println("Digital Signature (Base64): " + Base64.getEncoder().encodeToString(digitalSignature));

        // Verify the signature
        Signature verifier = Signature.getInstance("SHA256withDSA");
        verifier.initVerify(publicKey);
        verifier.update(messageBytes);
        boolean isValid = verifier.verify(digitalSignature);
        System.out.println("Signature Valid: " + isValid);
    }
}
