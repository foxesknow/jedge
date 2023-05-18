package org.island.echo.settings;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class GuidSettingsTest {
    @Test
    public void unknownSetting() {
        var setting = new GuidSettings();
        var value = setting.getSetting("foo");
        assertThat(value.isPresent()).isFalse();
    }

    @Test
    public void appGuidStaysTheSame() {
        var setting = new GuidSettings();
        var value = setting.getSetting("app");
        assertThat(value).isNotEmpty();

        var secondTime = setting.getSetting("app");
        assertThat(secondTime).isNotEmpty();
        assertThat(value).hasValue(secondTime.get());
    }

    @Test
    public void newGuidStaysTheSame() {
        var setting = new GuidSettings();
        var value = setting.getSetting("new");
        assertThat(value).isNotEmpty();
        assertThat(value.get()).isNotNull();

        var secondTime = setting.getSetting("new");
        assertThat(secondTime).isNotEmpty();
        assertThat(value.get()).isNotEqualTo(secondTime.get());
    }
}
