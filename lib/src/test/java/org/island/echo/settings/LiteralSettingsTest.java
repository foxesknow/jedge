package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LiteralSettingsTest {
    @Test
    void returnsValuePassedIn() {
        var setting = new LiteralSettings();
        var value = setting.getSetting("hello");
        assertThat(value).hasValue("hello");
    }
}
