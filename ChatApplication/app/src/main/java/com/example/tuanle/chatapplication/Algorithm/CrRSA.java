package com.example.tuanle.chatapplication.Algorithm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class CrRSA {

    // Transformation
    private static final String cryptMode = "RSA";
    private static final String transMode = "RSA/NONE/OAEPWithSHA256AndMGF1Padding";

    // Initialization Vector
    private static final byte[] initVector =
            {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    // LOG.D Option
    private static final String logTAG = "AES";


    private static KeyPairGenerator kpg;
    private static KeyPair kp;
    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    /*
    --------------------------------------------------
    Generate Key Pair
    Get Public & Private Key
    --------------------------------------------------
    */
    public static void generateKey()
            throws NoSuchAlgorithmException {
        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();

    }

    public static String getPublicKey(PublicKey publicKey) {
        return encodedProcess(publicKey.getEncoded());
    }

    public static String getPrivateKey(PrivateKey privateKey) {
        return encodedProcess(privateKey.getEncoded());
    }

    /*
    --------------------------------------------------
    Encoding & Decoding Process
    Using Base64
    Base64.NO_WRAP for "\n" at the end
    --------------------------------------------------
    */
    private static String encodedProcess(byte[] cipher) {
        //NO_WRAP means "\n at the end"
        return Base64.encodeToString(cipher, Base64.NO_WRAP);
    }

    private static byte[] decodedProcess(String encodedCipher) {
        //NO_WRAP means "\n at the end"
        return Base64.decode(encodedCipher, Base64.NO_WRAP);
    }

    /*
    --------------------------------------------------
    Encrypting & Decrypting Process
    Using "RSA/NONE/OAEPWithSHA256AndMGF1Padding" transformation
    --------------------------------------------------
    */
    public static byte[] encryptedProcess(final String plain, PublicKey publicKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        //System.out.println("Encrypted?????" + org.apache.commons.codec.binary.Hex.encodeHexString(encryptedBytes));
        return encryptedBytes;
    }

    public static byte[] decryptedProcess(final byte[] encryptedBytes, PrivateKey privateKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher1.doFinal(encryptedBytes);
        return decryptedBytes;
    }


    public static final String encryptRSA(String plainText, PublicKey publicKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherBytes = encryptedProcess(plainText, publicKey);
        String cipherText = encodedProcess(cipherBytes);
//        Log.d("cipher","Pub d: "+cipherText);

        return cipherText;
    }

    public static final String decryptRSA(String cipherText, PrivateKey privateKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherBytes = decodedProcess(cipherText);
        String plainText = new String(decryptedProcess(cipherBytes, privateKey));
        Log.d("cipher","Plaintext: "+plainText);
        return plainText;
    }

    private CrRSA() {

    }
}
