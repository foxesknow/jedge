package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SettingsManagerTest {
    @Test
    public void crackQualifiedName() {
        var pair = SettingsManager.crackQualifiedName("proc:id");
        assertThat(pair).isNotNull();
        assertThat(pair.item1()).isEqualTo("proc");
        assertThat(pair.item2()).isEqualTo("id");
    }

    @Test
    public void crackQualifiedName_NoDelimiter() {
        assertThatException().isThrownBy(() -> SettingsManager.crackQualifiedName("proc-id"));
    }

    @Test
    public void crackQualifiedName_NullQualifiedname() {
        assertThatException().isThrownBy(() -> SettingsManager.crackQualifiedName(null));
    }

    @Test
    public void isRegistered() {
        assertThat(SettingsManager.isRegistered("guid")).isTrue();
        assertThat(SettingsManager.isRegistered("foobar")).isFalse();
    }

    @Test
    public void getSettings() {
        assertThat(SettingsManager.getSettings("guid")).isNotNull();
        assertThat(SettingsManager.getSettings("foobar")).isNull();
    }
}

