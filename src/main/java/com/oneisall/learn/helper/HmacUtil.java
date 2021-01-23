package com.oneisall.learn.helper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

/**
 * HmacSHA256 签名
 *
 * @author liuzhicong
 **/
public class HmacUtil {

    public static Boolean verify(String text, String signedSecret, String requestHmac) {
        return Objects.equals(generateHmac(text, signedSecret), requestHmac);
    }

    public static String generateHmac(String text, String signedSecret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(signedSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] bytes = mac.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalArgumentException("cannot generate hmac");
        }
    }
}
