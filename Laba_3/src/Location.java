/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals (Location  location){   // реализация метода equals ().
        if (this == location)                      // this текущий объект
            return true;
        else if (location == null)
            return false;
        else
            return location.hashcode() == this.hashcode();
    }
    // loc1.equals(loc2)

    public int hashcode(){             // реализация метода hashcode()
        int result  = 5;
        result = 31*result + xCoord;
        result = 31*result + yCoord;
        return result;
    }
}
