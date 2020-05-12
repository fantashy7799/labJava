package shapes;

import lab2_ver2.Main;
import java.util.Objects;

public class Circle implements Ellipse 
{

    private float length = -1;
    private float area = -1;

    private final Point centre;
    private final float radius;

    public Circle(Point centre, float radius) 
    {
        if (radius > 0) {
            this.centre = centre;
            this.radius = radius;
        } else 
        {
            throw new IllegalArgumentException("incorrect points for circle");
        }
    }

    @Override
    public float getLength() 
    {
        if (length == -1) 
        {
            length = (float) (2 * Math.PI * radius);
        }
        return length;
    }

    @Override
    public float getArea() 
    {
        if (area == -1) 
        {
            area = (float) (Math.PI * Math.pow(radius, 2));
        }
        return area;
    }

    public Point getCentre() 
    {
        return centre;
    }

    public float getRadius() 
    {
        return radius;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Math.abs(area - circle.area) < Main.EPSILON &&
                Math.abs(length - circle.length) < Main.EPSILON &&
                Math.abs(radius - circle.radius) < Main.EPSILON &&
                centre.equals(circle.centre);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(length, area, centre, radius);
    }

    @Override
    public String toString() 
    {
        return "Circle : " +
                "\ncentre = " + centre +
                "\nradius = " + radius +
                "\narea = " + getArea() +
                "\nlength = " + getLength();
    }

}
