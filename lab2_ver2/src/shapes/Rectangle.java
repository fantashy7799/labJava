package shapes;

import lab2_ver2.Main;
import java.util.Objects;


public class Rectangle implements Polygon 
{

    float perimeter = -1;
    float area = -1;

    private final Point topRightPoint;
    private final Point topLeftPoint;
    private final Point bottomRightPoint;
    private final Point bottomLeftPoint;
    private final int rotation;

    public Rectangle(Point topLeftPoint, Point topRightPoint,
                     Point bottomRightPoint, Point bottomLeftPoint) 
    {
        this(topLeftPoint, topRightPoint, bottomRightPoint, bottomLeftPoint, 0);
    }

    public Rectangle(Point topLeftPoint, Point topRightPoint, Point bottomRightPoint,
                     Point bottomLeftPoint, int rotation) {
        if (isCorrectParams(topLeftPoint, topRightPoint, bottomRightPoint, bottomLeftPoint, rotation)) 
        {
            this.topRightPoint = topRightPoint;
            this.topLeftPoint = topLeftPoint;
            this.bottomRightPoint = bottomRightPoint;
            this.bottomLeftPoint = bottomLeftPoint;
            this.rotation = rotation;
        } else 
        {
            throw new IllegalArgumentException("incorrect points for rectangle");
        }
    }

    @Override
    public float getPerimeter() 
    {
        if (perimeter == -1) 
        {
            perimeter = (float) (getSideLength(topLeftPoint, topRightPoint) + getSideLength(topRightPoint, bottomRightPoint)
                    + getSideLength(bottomRightPoint, bottomLeftPoint) + getSideLength(bottomLeftPoint, topLeftPoint));
        }
        return perimeter;
    }

    @Override
    public float getArea() 
    {
        if (area == -1) 
        {
            area = (float) (getSideLength(topLeftPoint, topRightPoint) * getSideLength(topRightPoint, bottomRightPoint));
        }
        return area;
    }

    @Override
    public int getRotation() 
    {
        return rotation;
    }

    public Point getTopRightPoint() 
    {
        return topRightPoint;
    }

    public Point getTopLeftPoint() 
    {
        return topLeftPoint;
    }

    public Point getBottomRightPoint() 
    {
        return bottomRightPoint;
    }

    public Point getBottomLeftPoint() 
    {
        return bottomLeftPoint;
    }

    private boolean isCorrectParams(Point topLeftPoint, Point topRightPoint, Point bottomRightPoint,
                                    Point bottomLeftPoint, int rotation) 
    {
        return rotation >= 0 && rotation <= 360
                && Math.abs(getSideLength(topLeftPoint, topRightPoint) - getSideLength(bottomLeftPoint, bottomRightPoint)) < Main.EPSILON
                && Math.abs(getSideLength(topLeftPoint, bottomLeftPoint) - getSideLength(topRightPoint, bottomRightPoint)) < Main.EPSILON
                && !(topLeftPoint.equals(topRightPoint) || topLeftPoint.equals(bottomRightPoint) || topLeftPoint.equals(bottomLeftPoint)
                || topRightPoint.equals(bottomRightPoint) || topRightPoint.equals(bottomLeftPoint) || bottomRightPoint.equals(bottomLeftPoint));
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Math.abs(perimeter - rectangle.perimeter) < Main.EPSILON &&
                Math.abs(area - rectangle.area) < Main.EPSILON &&
                rotation == rectangle.rotation &&
                topRightPoint.equals(rectangle.topRightPoint) &&
                topLeftPoint.equals(rectangle.topLeftPoint) &&
                bottomRightPoint.equals(rectangle.bottomRightPoint) &&
                bottomLeftPoint.equals(rectangle.bottomLeftPoint);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(perimeter, area, topRightPoint, topLeftPoint, bottomRightPoint, bottomLeftPoint, rotation);
    }

    @Override
    public String toString() 
    {
        return "Rectangle : " +
                "\nperimeter = " + getPerimeter() +
                "\narea = " + getArea() +
                "\ntopRightPoint = " + topRightPoint +
                "\ntopLeftPoint = " + topLeftPoint +
                "\nbottomRightPoint = " + bottomRightPoint +
                "\nbottomLeftPoint = " + bottomLeftPoint +
                "\nrotation = " + rotation;
    }
}
