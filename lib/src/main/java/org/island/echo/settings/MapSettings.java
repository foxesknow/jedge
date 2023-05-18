package org.island.echo.settings;

import java.util.*;

public class MapSettings implements Settings {
    private final Map<String, Object> m_Settings;

    public MapSettings(Map<String, Object> settings) {
        Objects.requireNonNull(settings);

        m_Settings = settings;
    }
    @Override
    public Optional<Object> getSetting(String name) {
        if(m_Settings.containsKey(name)) {
            return Optional.of(m_Settings.get(name));
        }

        return Optional.empty();
    }
}
