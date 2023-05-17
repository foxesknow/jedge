package settings;

import java.util.*;

public class LiteralSettings implements Settings {
    @Override
    public Optional<Object> getSetting(String name) {
        return Optional.of(name);
    }
}
