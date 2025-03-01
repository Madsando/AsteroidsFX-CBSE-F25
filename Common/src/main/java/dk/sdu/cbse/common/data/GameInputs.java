package dk.sdu.cbse.common.data;

import java.util.concurrent.ConcurrentHashMap;

public class GameInputs {
    private static ConcurrentHashMap<Enum<EGameInputs>, Boolean> keys;
    private static ConcurrentHashMap<Enum<EGameInputs>, Boolean> pkeys;

    public GameInputs() {
        keys = new ConcurrentHashMap<>();
        pkeys = new ConcurrentHashMap<>();
    }

    public void update() {
        pkeys.putAll(keys);
    }

    public void setInput(Enum<EGameInputs> key, boolean b) {
        keys.put(key, b);
    }

    public boolean isDown(Enum<EGameInputs> key) {
        return keys.getOrDefault(key, false);
    }

    public boolean isPressed(Enum<EGameInputs> key) {
        return keys.get(key) & !pkeys.get(key);
    }
}
