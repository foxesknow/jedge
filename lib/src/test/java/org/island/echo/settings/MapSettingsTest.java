package org.island.echo.settings;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapSettingsTest {
    @Test
    public void initialization_NullMap() {
        assertThrows(Exception.class, () -> new MapSettings(null));
    }

    @Test
    public void hasSetting() {
        Map<String, Object> map = Map.of("Name", "Jack", "Age", 42);
        var settings = new MapSettings(map);

        var name = settings.getSetting("Name");
        assertTrue(name.isPresent());
        assertEquals(name.get(), "Jack");
    }

    @Test
    public void hasSettingCaseInsensitive() {
        Map<String, Object> map = Map.of("Name", "Jack", "Age", 42);
        var settings = new MapSettings(map);

        var name = settings.getSetting("name");
        assertTrue(name.isPresent());
        assertEquals(name.get(), "Jack");
    }

    @Test
    public void settingNotPresent() {
        Map<String, Object> map = Map.of("Name", "Jack", "Age", 42);
        var settings = new MapSettings(map);

        var name = settings.getSetting("Location");
        assertFalse(name.isPresent());
    }
}
