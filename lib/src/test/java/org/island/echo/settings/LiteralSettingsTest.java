package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LiteralSettingsTest {
    @Test
    void returnsValuePassedIn() {
        var setting = new LiteralSettings();
        var value = setting.getSetting("hello");
        assertTrue(value.isPresent());
        assertEquals(value.get(), "hello");
    }
}
