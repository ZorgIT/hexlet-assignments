package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    String text;
    List<Tag> tags;

    public PairedTag(String tagName, Map<String, String> options, String text
            , List<Tag> tags) {
        super(tagName, options);
        this.text = text;
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(text);

        if (!tags.isEmpty()) {
            tags.stream()
                    .forEach(x -> sb.append(x.toString()));
        }

        sb.append(String.format("</%s>", tagName));
        String result = sb.toString();
        return result;
    }
}
// END
