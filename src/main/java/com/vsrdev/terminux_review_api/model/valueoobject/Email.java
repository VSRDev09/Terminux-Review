package com.vsrdev.terminux_review_api.model.valueoobject;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Email(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email nao pode ser vazio");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Email invalido");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
