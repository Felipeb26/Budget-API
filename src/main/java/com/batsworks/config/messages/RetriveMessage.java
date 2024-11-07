package com.batsworks.config.messages;

import com.batsworks.config.enums.ErrorCodeMessage;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
public class RetriveMessage {

    private static final String BUNDLE_NAME = "i18n/messages";

    public static String get(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return bundle.getString(key);
    }

    public static String get(ErrorCodeMessage errorCodeMessage) {
        return get(errorCodeMessage.getCode(), Locale.ROOT);
    }

    public static String get(ErrorCodeMessage errorCodeMessage, Object... args) {
        String message = get(errorCodeMessage.getCode(), Locale.ROOT);
        return format(message, args);
    }

    private static String format(String value, Object... args) {
        args = args == null ? new Object[]{} : args;
        var argsuments = value.replaceAll("[^\\d ]+", "").trim().split(" ");

        for (var index = 0; index < argsuments.length; index++) {
            if (!argsuments[index].isBlank()) {
                value = value.replaceAll("[{}]", "");
                var argument = args.length <= index ? " " : args[index].toString();
                value = value.replace(argsuments[index], argument);
            }
        }

        return value;
    }

}
