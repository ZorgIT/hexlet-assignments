package exercise;

// BEGIN
public class Segment {
    Point begin;
    Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        int x1 = begin.getX();
        int y1 = begin.getY();
        int x2 = end.getX();
        int y2 = end.getY();
        return new Point(Math.round((x1 + x2) / 2),
                Math.round((y1 + y2) / 2));
    }
}
// END
