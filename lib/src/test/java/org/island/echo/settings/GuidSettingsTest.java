package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuidSettingsTest {
    @Test
    public void unknownSetting() {
        var setting = new GuidSettings();
        var value = setting.getSetting("foo");
        assertFalse(value.isPresent());
    }

    @Test
    public void appGuidStaysTheSame() {
        var setting = new GuidSettings();
        var value = setting.getSetting("app");
        assertTrue(value.isPresent());
        assertNotNull(value.get());

        var secondTime = setting.getSetting("app");
        assertTrue(secondTime.isPresent());
        assertEquals(value.get(), secondTime.get());
    }

    @Test
    public void newGuidStaysChangesd() {
        var setting = new GuidSettings();
        var value = setting.getSetting("new");
        assertTrue(value.isPresent());
        assertNotNull(value.get());

        var secondTime = setting.getSetting("new");
        assertTrue(secondTime.isPresent());
        assertNotEquals(value.get(), secondTime.get());
    }
}
