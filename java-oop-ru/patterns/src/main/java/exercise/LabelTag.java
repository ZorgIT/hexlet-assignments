package exercise;


// BEGIN
public class LabelTag implements TagInterface {
    String type;
    TagInterface tagInterface;

    public LabelTag(String newType, TagInterface newTagInterface) {
        this.type = newType;
        this.tagInterface = newTagInterface;
    }

    @Override
    public String render() {
        return ("<label>" + type + tagInterface.render() + "</label>");
    }
}
// END
