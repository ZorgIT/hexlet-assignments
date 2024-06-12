package hexlet.oop.l5.patternhw;

import lombok.AllArgsConstructor;

// BEGIN
@AllArgsConstructor
public class LabelTag implements TagInterface {
    String type;
    TagInterface tagInterface;

    @Override
    public String render() {
        return ("<label>" + type + tagInterface.render() + "</label>");
    }
}
// END
