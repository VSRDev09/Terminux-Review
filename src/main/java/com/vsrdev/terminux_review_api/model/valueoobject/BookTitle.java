package com.vsrdev.terminux_review_api.model.valueoobject;

public class BookTitle {

    private final String value;

    public BookTitle(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Titulo invalido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookTitle)) return false;
        BookTitle that = (BookTitle) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
