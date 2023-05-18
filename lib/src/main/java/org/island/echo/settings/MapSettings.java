package org.island.echo.settings;

import java.util.*;

public class MapSettings implements Settings {
    private final Map<String, Object> m_Settings;

    public MapSettings(Map<String, ?> settings) {
        Objects.requireNonNull(settings);

        m_Settings = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        m_Settings.putAll(settings);
    }
    @Override
    public Optional<Object> getSetting(String name) {
        if(m_Settings.containsKey(name)) {
            return Optional.of(m_Settings.get(name));
        }

        return Optional.empty();
    }
}
