import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class ds {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to sign: ");
            String message = scanner.nextLine();
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            Signature signer = Signature.getInstance("SHA256withDSA");
            signer.initSign(privateKey);
            signer.update(message.getBytes());
            byte[] signatureBytes = signer.sign();
            String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
            System.out.println("\nDigital Signature (Base64): " + signatureBase64);
            
            Signature verifier = Signature.getInstance("SHA256withDSA");
            verifier.initVerify(publicKey);
            verifier.update(message.getBytes());
            boolean isVerified = verifier.verify(signatureBytes);
            System.out.println("Signature verification result: " + (isVerified ? "Valid" : "Invalid"));
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
