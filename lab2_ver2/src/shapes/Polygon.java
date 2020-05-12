package shapes;


public interface Polygon extends Shape 
{

    float getPerimeter();

    default double getSideLength(Point firstPoint, Point secondPoint) 
    {
        return Math.sqrt(
                Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                        + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }
}
