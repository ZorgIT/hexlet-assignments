package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
final class App {
    private App() {
    }
    public static List<Map<String, String>> findWhere(final List<Map<String,
            String>> books, final Map<String, String> where
            ) {
        List<Map<String, String>> result = new ArrayList<>();

        books.forEach(map -> {
            var matchingValuesCount = 0;
            for (String value: where.values()
            ) {
                matchingValuesCount = map.containsValue(value)
                        ? ++matchingValuesCount : matchingValuesCount;
            }
            if (matchingValuesCount == where.size()) {
                result.add(map);
            }
        });

        /*
        Additional solve:
        books.forEach(map -> {
            long matchingValuesCount = where.values().stream()
                .filter(value -> map.containsValue(value))
                .count();
             if (matchingValuesCount == where.size()) {
                result.add(map);
             }
         });
         */

        return result;
    }
}
//END
