package exercise;

// BEGIN
public interface Home {
    double getArea();

    default double compareTo(Home another) {
        return this.getArea() == another.getArea() ? 0 :
                (this.getArea() > another.getArea() ? 1 : -1);
    }
}
// END
