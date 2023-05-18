package org.island.echo.settings;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsSettingsTest {
    @Test
    public void headIsNull() {
        var other = new GuidSettings();
        assertThrows(NullPointerException.class, () -> new ConsSettings(null, other));
    }

    @Test
    public void tailIsNull() {
        var other = new GuidSettings();
        assertThrows(NullPointerException.class, () -> new ConsSettings(other, null));
    }

    @Test
    public void hasSetting() {
        var head = new MapSettings(Map.of("Name", "Jack"));
        var tail = new MapSettings(Map.of("Age", 42));

        var settings = new ConsSettings(head, tail);
        var name = settings.getSetting("Name");
        assertTrue(name.isPresent());
        assertEquals(name.get(), "Jack");

        var age = settings.getSetting("age");
        assertTrue(age.isPresent());
        assertEquals(age.get(), 42);
    }
}
