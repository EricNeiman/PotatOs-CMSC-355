package com.example.common;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class Util {

    //for passwords
    public String sha256(String s)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(s.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format( "%064x", new BigInteger( 1, digest ) );
    }
}


