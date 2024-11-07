package com.batsworks.domain.enums;

public enum ExpenseType {

    SIMPLE("SIMPLE"),
    COMPOSED("COMPOSED");

    private final String type;

    ExpenseType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
