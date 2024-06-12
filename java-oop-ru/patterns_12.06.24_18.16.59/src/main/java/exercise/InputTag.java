package hexlet.oop.l5.patternhw;

import lombok.AllArgsConstructor;

// BEGIN
@AllArgsConstructor
public class InputTag implements TagInterface {
    String type;
    String value;

    @Override
    public String render() {
        return ("<input type=\"" + type + "\" value=\"" + value+"\">");
    }
}
// END
