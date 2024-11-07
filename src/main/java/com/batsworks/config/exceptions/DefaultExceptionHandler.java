package com.batsworks.config.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultExceptionHandler implements Serializable {
    private final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    private final String message;
    private final String path;
    private final String method;

    public DefaultExceptionHandler(String message, String path, String method) {
        this.message = limitMessageError(message);
        this.path = path;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getTime() {
        return time;
    }

    private String limitMessageError(String messageError){
        messageError = messageError.split("\n")[0];
        var beginIndex = messageError.contains("[") ? messageError.indexOf("[") + 1 : 0;
        var lastIndex = messageError.contains("\"") ? messageError.lastIndexOf("\"") : messageError.length();
        messageError = messageError.substring(beginIndex, lastIndex);
        return regex(messageError);
    }

    private static String regex(String value){
        value = value.replace("key", "");
        value = value.replace("_", " ");
        value = value.replaceAll("[^a-zA-Z0-9\\s:]", "");
        return value.trim();
    }
}
