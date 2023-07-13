package com.example.buva.text.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class SignatureUtil {

    private final String timestamp;
    private final String accessKey;
    private final String secretKey;

    public SignatureUtil(String timestamp, String accessKey, String secretKey) {
        this.timestamp = timestamp;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String makeSignature() throws IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "GET";
        String url = "/photos/puppy.jpg?query1=&query2";
        System.out.println("UtilTimestamp"+timestamp);
        System.out.println("SignatureUtil"+ secretKey);

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

}
