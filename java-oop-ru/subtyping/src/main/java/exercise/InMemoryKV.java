package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {


    private HashMap<String, String> memoryDB;

    public InMemoryKV(Map<String, String> memoryDB) {
        this.memoryDB =  new HashMap<>(memoryDB);
    }

    @Override
    public void set(String key, String value) {
        memoryDB.put(key,value);
        //memoryDB.putIfAbsent(key, value);
    }

    @Override
    public void unset(String key) {
        if (memoryDB.containsKey(key)) {
            memoryDB.remove(key);
        }
    }

    @Override
    public String get(String key, String defaultValue) {
        if (memoryDB.containsKey(key)) {
            return memoryDB.get(key);
        }

        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(memoryDB);
    }
}
// END
