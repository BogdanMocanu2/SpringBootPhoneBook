package com.bogdan.demo.enums;

public enum CategoryDescription {

    parent("parent"),
    grandparent("grandparent"),
    son("son"),
    daughter("daughter"),
    aunt("aunt"),
    uncle("uncle");

    private final String value;

    CategoryDescription(String value) {
        this.value = value;
    }

    public String getField() {
        return this.value;
    }
}
