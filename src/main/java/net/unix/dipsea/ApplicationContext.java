package net.unix.dipsea;

/**
 * @author Unix
 * @since 29.09.2020
 */

public class ApplicationContext
{
    public static void main(String... args) {
        final Dipsea dipsea = new Dipsea();
        dipsea.init(Dipsea.Type.ENCRYPT, "example123");
        final String textToEncrypt = "tak bylo";
        final String encryptedText = dipsea.doFinal(textToEncrypt);
        System.out.println("Text to encrypt: " + textToEncrypt);
        System.out.println("Encrypted text: " + encryptedText);
        dipsea.init(Dipsea.Type.DECRYPT);
        System.out.println("Decrypted text: " + dipsea.doFinal(encryptedText));
    }
}