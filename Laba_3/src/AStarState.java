import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private HashMap<Location, Waypoint> openTop = new HashMap<Location, Waypoint>();
    private HashMap<Location, Waypoint> closeTop = new HashMap<Location, Waypoint>(); //два нестатических поля

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()    //проверла все вершины в наборе открытых вершин, вернула ссылку с наименьшей общей стоимостью

    {
        boolean flag = true; // значение для cost не задано
        float cost = 0f;
        Waypoint p = null;
        for(Location location: openTop.keySet()){
            if (flag){
                p = openTop.get(location);
                cost = p.getTotalCost();
                flag = false;
            }
            else {
                Waypoint p0 = openTop.get(location);
                if (cost > p0.getTotalCost()) {
                    cost = p0.getTotalCost();
                    p = p0;
                }
            }
        }
        return p;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP){             //добавлять указанную вершину, если существует вершина хуже новой

        boolean flag = true;
        Location sLoc = null;
        for (Location location : openTop.keySet()){
            if (location.equals(newWP.loc)){
                flag = false;
                sLoc = location;
                break;
            }
        }
        if(flag){
            openTop.put(newWP.loc, newWP);
            return true;
        }
        else{
            if (newWP.getPreviousCost() < openTop.get(sLoc).getPreviousCost()) {
                openTop.put(newWP.loc, newWP);
                return true;
            }
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()           //возвращает количество точек в наборе открытых вершин
    {
        return openTop.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)  //перемещает вершину из набора открытых в закрытые (путем удаления)
    {
        closeTop.put(loc, openTop.get(loc));
        openTop.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)       //true, если в указанном местоположение встречается в наборе закрытых вершин
    {
        boolean flag = false;
        for (Location location : closeTop.keySet()){
            if (location.equals(loc)){
                flag = true;
            }
        }
        return flag;
    }

}
