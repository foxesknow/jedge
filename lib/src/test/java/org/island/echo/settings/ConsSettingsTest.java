package org.island.echo.settings;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConsSettingsTest {
    @Test
    public void headIsNull() {
        var other = new GuidSettings();
        assertThatException().isThrownBy(() -> new ConsSettings(null, other));
    }

    @Test
    public void tailIsNull() {
        var other = new GuidSettings();
        assertThatException().isThrownBy(() -> new ConsSettings(other, null));
    }

    @Test
    public void hasSetting() {
        var head = new MapSettings(Map.of("Name", "Jack"));
        var tail = new MapSettings(Map.of("Age", 42));

        var settings = new ConsSettings(head, tail);
        var name = settings.getSetting("Name");
        assertThat(name).hasValue("Jack");

        var age = settings.getSetting("age");
        assertThat(age).hasValue(42);
    }
}
