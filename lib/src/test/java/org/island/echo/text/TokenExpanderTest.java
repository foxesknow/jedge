package org.island.echo.text;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TokenExpanderTest {
    @Test
    public void expandToken() {
        assertThat(TokenExpander.expandToken("guid:new", null)).isNotEmpty();
    }

    @Test
    public void expandTokenWithProperty() {
        assertThat(TokenExpander.expandToken("literal:hello|length", null)).isEqualTo("5");
    }

    @Test
    public void expandTokenWithAction_toLower() {
        assertThat(TokenExpander.expandToken("literal:Hello|||tolower", null)).isEqualTo("hello");
    }

    @Test
    public void expandTokenWithAction_trim() {
        assertThat(TokenExpander.expandToken("literal:  Hello  |||trim", null)).isEqualTo("Hello");
    }

    @Test
    public void expandTokenWithAction_trimStart() {
        assertThat(TokenExpander.expandToken("literal:  Hello  |||trimstart", null)).isEqualTo("Hello  ");
    }

    @Test
    public void expandTokenWithAction_trimEnd() {
        assertThat(TokenExpander.expandToken("literal:  Hello  |||trimend", null)).isEqualTo("  Hello");
    }
}
