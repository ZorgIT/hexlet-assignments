package exercise;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
final class Sorter {
    private Sorter() {
    }
    public static List takeOldestMans(final List<Map<String, String>> users) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> men = users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted(Comparator.comparing(user ->
                        LocalDate.parse(user.get("birthday"), formatter)))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
        return men;
    }
}
// END
