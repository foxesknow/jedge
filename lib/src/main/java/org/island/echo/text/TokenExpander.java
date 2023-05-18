package org.island.echo.text;

import java.util.*;
import java.lang.reflect.*;

import org.island.echo.settings.*;

public final class TokenExpander {
    private TokenExpander(){}

    public static final String DEFAULT_BEGIN_TOKEN = "${";
    public static final String DEFAULT_END_TOKEN = "}";

    public static String expandToken(String token, UnknownVariableLookup unknownVariableLookup) {
        Objects.requireNonNull(token);

        var parts = token.split("\\|", 4);
        var variable = (parts.length > 0 ? parts[0] : null);
        if (variable == null) throw new IllegalArgumentException("token does not contain a variable: " + token);

        var property = (parts.length > 1 ? parts[1] : null);
        var formatting = (parts.length > 2 ? parts[2] : null);
        var action = (parts.length > 3 ? parts[3] : null);

        var originalVariable = variable;
        String namespace = null;

        // Is the variable in a namespace then get the parts
        var pivot = variable.indexOf(SettingsManager.NAMESPACE_SEPARATOR);
        if (pivot == -1) {
            namespace = null;
        } else {
            var pair = SettingsManager.crackQualifiedName(variable);
            namespace = pair.item1();
            variable = pair.item2();
        }

        Object value = null;

        if (namespace == null) {
            if (unknownVariableLookup != null) {
                value = unknownVariableLookup.lookup(originalVariable).orElse(null);
            }
        } else {
            // We'll ask the settings manager to do the work
            var settings = SettingsManager.getSettings(namespace);
            if (settings != null) {
                value = settings.getSetting(variable).orElse(null);
            } else {
                // It's not a registered setting, so we'll give the lookup a change to handle it
                if (unknownVariableLookup != null) {
                    value = unknownVariableLookup.lookup(originalVariable).orElse(null);
                }
            }
        }

        if (value == null) throw new IllegalArgumentException("could not resolve " + token);

        value = applyProperty(property, value);
        var result = applyFormatting(formatting, value);
        result = applyAction(action, result);

        return result;
    }

    private static Object applyProperty(String property, Object value) {
        if (property == null || property.isBlank()) return value;

        try {
            var theClass = value.getClass();
            var method = getMethod(theClass, property);
            if (method != null) {
                value = method.invoke(value);
            } else {
                var field = getField(theClass, property);
                if (field != null) {
                    value = field.get(value);
                }
            }
        }
        catch(Exception e) {
            value = null;
        }

        if ( value == null) throw new IllegalArgumentException("invalid property: " + property);

        return value;
    }

    private static String applyFormatting(String formatting, Object value) {
        if (formatting == null || formatting.isBlank()) return value.toString();

        return value.toString();
    }

    private static String applyAction(String action, String value) {
        if (action == null || action.isBlank()) return value;

        switch (action.toLowerCase()) {
            case "trim":        return value.trim();
            case "trimstart":   return value.stripLeading();
            case "trimend":     return value.stripTrailing();
            case "toupper":     return value.toUpperCase();
            case "tolower":     return value.toLowerCase();
            default:            throw new IllegalArgumentException("invalid action: " + action);
        }
    }

    private static Method getMethod(Class<?> theClass, String method) {
        try {
            return theClass.getMethod(method);
        } catch(NoSuchMethodException e) {
            return null;
        }
    }

    private static Field getField(Class<?> theClass, String field) {
        try {
            return theClass.getField(field);
        } catch(NoSuchFieldException e) {
            return null;
        }
    }
}
