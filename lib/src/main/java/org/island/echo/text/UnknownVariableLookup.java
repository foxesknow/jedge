package org.island.echo.text;

import java.util.*;
import java.lang.*;

@FunctionalInterface
public interface UnknownVariableLookup {
    Optional<Object> lookup(String name);
}
