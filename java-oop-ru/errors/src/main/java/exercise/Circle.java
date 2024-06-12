package exercise;

// BEGIN
public class Circle {
    private Point point;
    private int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public double getSquare() throws NegativeRadiusException {

        if (radius < 0) {
            throw new NegativeRadiusException("");
        }

        return Math.PI * Math.pow(this.radius, 2);
    }

    public int getRadius() {
        return radius;
    }

}
// END
