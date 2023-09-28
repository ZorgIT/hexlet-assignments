package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
final class App {
    private App() {
    }
    public static List findWhere(final List<Map<String, String>> books,
                                      final Map<String, String> where
            ) {
        List result = new ArrayList();
        for (Map<String, String> map: books
             ) {
            var counter = 0;
            for (String value: where.values()
                 ) {
                counter = map.containsValue(value) ? ++counter : counter;
            }
            if (counter == where.size()) {
                result.add(map);
            }
        }

        return result;
    }
}
//END
