package com.bogdan.demo.enums;

public enum Fields {

    name("First Name"),
    email("e-Mail"),
    number("Phone number");

    private final String value;

    Fields(String value) {
        this.value = value;
    }

    public String getField() {
        return this.value;
    }

}
