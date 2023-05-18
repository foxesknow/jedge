package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SettingsManagerTest {
    @Test
    public void crackQualifiedName() {
        var pair = SettingsManager.crackQualifiedName("proc:id");
        assertNotNull(pair);
        assertEquals(pair.item1(), "proc");
        assertEquals(pair.item2(), "id");
    }

    @Test
    public void crackQualifiedName_NoDelimiter() {
        assertThrows(Exception.class, () -> SettingsManager.crackQualifiedName("proc-id"));
    }

    @Test
    public void crackQualifiedName_NullQualifiedname() {
        assertThrows(Exception.class, () -> SettingsManager.crackQualifiedName(null));
    }
}

