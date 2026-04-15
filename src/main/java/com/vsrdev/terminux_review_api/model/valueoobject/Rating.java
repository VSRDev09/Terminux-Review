package com.vsrdev.terminux_review_api.model.valueoobject;

public class Rating {

    private final int value;

    public Rating(int value) {

        if (value < 0 || value > 10) {
            throw new IllegalArgumentException("Rating deve ser entre 0 e 10");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }

   

}
