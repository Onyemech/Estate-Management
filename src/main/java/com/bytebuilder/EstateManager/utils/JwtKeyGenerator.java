package com.bytebuilder.EstateManager.utils;

import javax.crypto.KeyGenerator;
import java.util.Base64;

public class JwtKeyGenerator {
    public static String generateSecureKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
        keyGen.init(512); // 512 bits
        byte[] key = keyGen.generateKey().getEncoded();
        return Base64.getEncoder().encodeToString(key);
    }
}
