/**
 * Class that represents the edges of a Graph of towns
 * @author Caterine Asselborn
 */
public class Road implements Comparable<Road>
{
    //****************************Attributes***************************************/
    private Town source;
    private Town destination;
    private int degrees;
    private String name;

    //****************************Constructors***************************************/
    /**
     * Constructor to create instance of Road
     * @param source One town on the road
     * @param destination Another town on the road
     * @param degrees distance from one town to the other
     * @param name Name of the road
     */
    public Road(Town source, Town destination, int degrees, String name)
    {
        this.source = source;
        this.destination = destination;
        this.degrees = degrees;
        this.name = name;
    }

    /**
     *  Constructor with weight preset at 1
     * @param source One town on the road
     * @param destination Another town on the road
     * @param name Name of the road
     */
    public Road(Town source, Town destination, String name)
    {
        this(source, destination, 1, name);
    }

    //****************************Methods********************************************/

    /**
     * Getter method for road name
     * @return road name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Getter method for the source town
     * @return the first town on the road
     */
    public Town getSource()
    {
        return this.source;
    }

    /**
     * Getter method for the destination town
     * @return the second town on the road
     */
    public Town getDestination()
    {
        return this.destination;
    }

    /**
     * Getter method for the distance of the road (degrees)
     * @return the distance of the road
     */
    public int getWeight()
    {
        return this.degrees;
    }

    /**
     * Method to compare roads
     * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
     */
    @Override
    public int compareTo(Road road)
    {
        return this.getName().compareTo(road.getName());
    }

    /**
     * Method to check if a road contains a town
     * @param town a vertex of the graph
     * @return true only if the edge is connected to the given vertex
     */
    public boolean contains(Town town)
    {
        return this.getSource().equals(town) 
            || this.getDestination().equals(town);
    }

    /**
     * Equals method to see if a road is equal to another road
     * @param r road object to compare it to
     * @return Returns true if each of the ends of the road r is the same as the ends of this road. 
     *  Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
     */
    @Override
    public boolean equals(Object r)
    {
        // check it's null OR is not an instance of Road
        if(r == null || !(r instanceof Road))
        {
            return false;
        }

        // Casting object to be of type Road
        Road otherRoad = (Road)r;

        return this.contains(otherRoad.getSource()) && this.contains(otherRoad.getDestination());
    }

    /**
     * To string method
     * @return road name
     */
    @Override
    public String toString()
    {
        return this.getName();
    }

}
