package shapes;

import lab2_ver2.Main;
import java.util.Objects;

public class PointImpl implements Point 
{

    private final float x;
    private final float y;

    public PointImpl(float x, float y) 
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() 
    {
        return x;
    }

    @Override
    public float getY() 
    {
        return y;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointImpl point = (PointImpl) o;
        return Math.abs(x - point.x) < Main.EPSILON
                && Math.abs(y - point.y) < Main.EPSILON;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() 
    {
        return "PointImpl : " +
                "\nx = " + x +
                "\ny = " + y;
    }
}

