package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    String tagName;
    Map<String, String> options;

    public Tag(String tagName, Map<String, String> options) {
        this.tagName = tagName;
        this.options = options;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<%s", tagName));
        if (!options.isEmpty()) {
            options.entrySet().stream()
                    .forEach(x -> {
                        sb.append(String.format(" %s=\"%s\"",
                                x.getKey(),
                                x.getValue()));
                    });
        }
        sb.append(String.format(">"));
        String result = sb.toString();
        return result;
    }
}
// END
