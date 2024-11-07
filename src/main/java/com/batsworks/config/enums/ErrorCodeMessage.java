package com.batsworks.config.enums;

public enum ErrorCodeMessage {

    UNKNOW_ERROR("0"),
    GROUP_LIMIT_MESSAGE("1"),
    ;

    private final String code;

    ErrorCodeMessage(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
