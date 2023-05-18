package org.island.echo.settings;

import java.util.*;

public class GuidSettings implements Settings {
    private static final UUID s_AppGuid = UUID.randomUUID();

    @Override
    public Optional<Object> getSetting(String name) {
        switch(name.toLowerCase()) {
            case "app":
                return Optional.of(s_AppGuid);

            case "new":
                return Optional.of(UUID.randomUUID());

            default:
                return Optional.empty();
        }
    }
}
