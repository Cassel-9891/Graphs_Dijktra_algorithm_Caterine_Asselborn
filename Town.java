/**
 * Class that represents the vertices that holds the names of the town and a lists of adjacent towns 
 * @author Caterine G. Asselborn
 */
public class Town implements Comparable<Town>
{
    //****************************Attributes***************************************/
    private String name;

    //****************************Constructors***************************************/
    /**
     * Constructor to create an instance of Town
     * @param name town's name
     */
    public Town(String name)
    {
        this.name = name;
    }

    /**
     * Copy Constructor 
     * @param town town object
     */
    public Town(Town town)
    {
        this.name = town.getName();
    }

    //****************************Methods********************************************/

    /**
     * Getter method to retrieve name of the town
     * @return townName 
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Compare to method
     * @param town town object
     * @return 0 if names are equal, a positive or negative number if the names are not equal
     */
    @Override
    public int compareTo(Town town)
    {
        return this.name.compareTo(town.getName());
    }

    /**
     * Method to generate hashcode
     * @return the hashcode for the name of the town 
     */
    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    /**
     * Method to check if town are equal
     * @param otherTown other town object
     * @return true if the towns names are equal, false if not
     */
    @Override
    public boolean equals(Object otherTown)
    {
        //check if it's null OR check if it is an instance of town
        if((otherTown == null) || !(otherTown instanceof Town))
        {
            return false;
        }
        
        return this.name.equals(otherTown.toString());
    }
    
    /**
     * Method to return the towm name
     * @return townName
     */
    @Override
    public String toString()
    {
        return this.name;
    }

}
