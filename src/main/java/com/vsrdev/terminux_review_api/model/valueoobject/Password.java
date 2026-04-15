package com.vsrdev.terminux_review_api.model.valueoobject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Password {
    
    private final String hash;

    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

     public Password(String rawPassword) {

        if (rawPassword == null || rawPassword.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }

        this.hash = hash(rawPassword);
    }

    public boolean matches(String rawPassword) {
        return hash(rawPassword).equals(this.hash);
    }

    public String getHash() {
        return hash;
    }
}
