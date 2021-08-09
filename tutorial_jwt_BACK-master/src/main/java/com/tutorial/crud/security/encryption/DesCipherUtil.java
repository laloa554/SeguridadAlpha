package com.tutorial.crud.security.encryption;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DesCipherUtil {
 
    private DesCipherUtil() {
        throw new AssertionError("No DesCipherUtil instances for you!");
    }
 
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
 
    public static String encrypt(String encryptText, String key) {
 
        if (encryptText == null || key == null) {
            throw new IllegalArgumentException("encryptText or key must not be null");
        }
 
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
 
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(encryptText.getBytes(Charset.forName("UTF-8")));
            return Base64.getEncoder().encodeToString(bytes);
 
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException
            | BadPaddingException | NoSuchProviderException | IllegalBlockSizeException e) {
            throw new RuntimeException("encrypt failed", e);
        }
 
    }
 
    public static  String decrypt(String decryptText, String key) {
 
        if (decryptText == null || key == null) {
            throw new IllegalArgumentException("decryptText or key must not be null");
        }
 
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
 
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(decryptText));
            return new String(bytes, Charset.forName("UTF-8"));
 
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException
            | BadPaddingException | NoSuchProviderException | IllegalBlockSizeException e) {
            throw new RuntimeException("decrypt failed", e);
        }
    }
}

