package exercise;


// BEGIN
public class InputTag implements TagInterface {
    String type;
    String value;

    public InputTag(String newType, String newValue) {
        this.type = newType;
        this.value = newValue;
    }

    @Override
    public String render() {
        return ("<input type=\"" + type + "\" value=\"" + value+"\">");
    }
}
// END
