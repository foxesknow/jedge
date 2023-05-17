package settings;

import java.util.*;

public interface Settings {
    Optional<Object> getSetting(String name);
}
