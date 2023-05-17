package settings;

import java.util.*;

public class ConsSettings implements Settings {
    private final Settings m_Head;
    private final Settings m_Tail;

    public ConsSettings(Settings head, Settings tail) {
        Objects.requireNonNull(head);
        Objects.requireNonNull(tail);

        m_Head = head;
        m_Tail = tail;
    }


    @Override
    public Optional<Object> getSetting(String name) {
        var headValue = m_Head.getSetting(name);
        if(headValue.isPresent()) return headValue;

        return m_Tail.getSetting(name);
    }
}
