package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "10"));
        // Получение значения по ключу
        storage.get("key", "default"); // "10"
        storage.get("unknown", "default"); // "default"
        // Установка нового значения
        storage.set("key2", "value2");
        storage.get("key2", "default"); // "value2"
        // Удаление ключа
        storage.unset("key2");
        storage.get("key2", "default"); // "default"
        // Получение всех данных из базы
        Map<String, String> data = storage.toMap();
        System.out.println(data); // => {key=10};
    }

    public static void swapKeyValue(KeyValueStorage storages) {
        Map<String, String> data = storages.toMap();
        Map<String, String> swapped = new HashMap<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            swapped.put(entry.getValue(), entry.getKey());
            storages.unset(entry.getKey());
        }
        for (Map.Entry<String, String> entry : swapped.entrySet()) {
            storages.set(entry.getKey(), entry.getValue());
        }
    }
}
// END
