package shapes;


public interface  Shape
{

    
    float getArea();

    default int getRotation() 
    {
        return 0;
    }

}
