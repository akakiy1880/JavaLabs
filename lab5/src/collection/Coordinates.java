package collection;

public class Coordinates {
    private Long x;
    private Long y;

    public Coordinates(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
