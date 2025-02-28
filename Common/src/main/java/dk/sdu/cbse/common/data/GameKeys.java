package dk.sdu.cbse.common.data;

import java.util.concurrent.ConcurrentHashMap;

public class GameKeys {
    private static ConcurrentHashMap<Enum<EGameKeys>, Boolean> keys;
    private static ConcurrentHashMap<Enum<EGameKeys>, Boolean> pkeys;

    public GameKeys() {
        keys = new ConcurrentHashMap<>();
        pkeys = new ConcurrentHashMap<>();
    }

    public void update() {
        pkeys.putAll(keys);
    }

    public void setKey(Enum<EGameKeys> key, boolean b) {
        keys.put(key, b);
    }

    public boolean isDown(Enum<EGameKeys> key) {
        return keys.getOrDefault(key, false);
    }

    public boolean isPressed(Enum<EGameKeys> key) {
        return keys.get(key) & !pkeys.get(key);
    }
}
