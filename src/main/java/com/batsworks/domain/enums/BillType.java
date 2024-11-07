package com.batsworks.domain.enums;

public enum BillType {


    PERSONAL("PERSONAL"),
    SHARED("SHARED"),
    ;

    private final String type;

    BillType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
