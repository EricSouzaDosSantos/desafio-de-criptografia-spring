package com.challenge.encryption.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AESUtil {

    @Value("${api.security.algorithm.encrypt}")
    private String ALGORITHM;

    @Value("${api.security.key.encrypt}")
    private String KEY;

    public String encrypt(String value){
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to encrypt", e);
        }
    }

    public String decrypt(String encryptedValue){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decoded = Base64.getDecoder().decode(encryptedValue);
            byte[] originalValue = cipher.doFinal(decoded);
            return new String(originalValue);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to decrypt",e);
        }
    }

}
