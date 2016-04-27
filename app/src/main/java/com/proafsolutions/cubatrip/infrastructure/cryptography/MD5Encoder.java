package com.proafsolutions.cubatrip.infrastructure.cryptography;

import java.security.MessageDigest;

/**
 * Created by alejandro.clavijo on 4/27/2016.
 */
public class MD5Encoder {

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(java.security.NoSuchAlgorithmException missing) {
            return "Error.";
        }
    }
}
