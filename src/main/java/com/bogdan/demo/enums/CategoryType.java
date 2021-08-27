package com.bogdan.demo.enums;

public enum CategoryType {

    friends("Friends"),
    family("Family"),
    acquaintance("Acquaintance");

    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

    public String getField() {
        return this.value;
    }
}
