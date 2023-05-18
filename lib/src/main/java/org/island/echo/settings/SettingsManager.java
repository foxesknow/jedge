package org.island.echo.settings;

import java.util.*;

import org.island.echo.execution.*;

public class SettingsManager {
    private static final Object s_SyncRoot = new Object();
    private static final Map<String, Settings> s_Settings = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private SettingsManager(){}

    public static boolean isRegistered(String namespace) {
        Objects.requireNonNull(namespace);

        synchronized(s_SyncRoot) {
            return s_Settings.containsKey(namespace);
        }
    }

    public static Settings getSettings(String namespace) {
        Objects.requireNonNull(namespace);

        synchronized(s_SyncRoot) {
            return s_Settings.get(namespace);
        }
    }

    public static void register(String namespace, Settings settings) {
        Objects.requireNonNull(namespace);
        Objects.requireNonNull(settings);

        synchronized(s_SyncRoot) {
            var existing = s_Settings.get(namespace);
            if (existing == null) {
                s_Settings.put(namespace, settings);
            } else {
                // We need to combine the two
                var cons = new ConsSettings(settings, existing);
                s_Settings.put(namespace, cons);
            }
        }
    }

    static Pair<String, String> crackQualifiedName(String qualifiedName) {
        var pivot = qualifiedName.indexOf(':');
        if (pivot == -1) throw new IllegalArgumentException("invalid name");

        var namespace = qualifiedName.substring(0, pivot);
        var setting = qualifiedName.substring(pivot + 1);

        return new Pair<>(namespace, setting);
    }
}
