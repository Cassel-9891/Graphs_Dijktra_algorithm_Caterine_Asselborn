import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Student test class to test all the methods of my Road class.
 * @author Caterine G. Asselborn
 */
public class Road_STUDENT_Test 
{
    @Test
    public void Constructor()
    {
        // Arrange
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        int weight = 10;
        String roadName = "road1";

        // Act 
        Road road = new Road(town1, town2, weight, roadName);

        // Assert
        assertEquals(roadName, road.getName());
        assertEquals(town1, road.getSource());
        assertEquals(town2, road.getDestination());
        assertEquals(weight, road.getWeight());
    }

    @Test
    public void Constructor_default_weight()
    {
        // Arrange
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        String roadName = "road1";

        // Act 
        Road road = new Road(town1, town2, roadName);

        // Assert
        assertEquals(roadName, road.getName());
        assertEquals(town1, road.getSource());
        assertEquals(town2, road.getDestination());
        assertEquals(1, road.getWeight());
    }

    @Test
    public void contains_town_returns_true()
    {
        // Arrange
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        String roadName = "road1";

        // Act 
        Road road = new Road(town1, town2, roadName);

        // Assert
        assertTrue(road.contains(town1));
        assertTrue(road.contains(town2));
    }

    @Test
    public void contains_town_not_present_returns_false()
    {
        // Arrange
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        String roadName = "road1";

        // Act 
        Road road = new Road(town1, town2, roadName);

        // Assert
        assertFalse(road.contains(new Town("town3")));
    }

    @Test
    public void compareTo_sorts_correctly()
    {
        // Arrange
        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");
        String road1Name = "road1";
        Road road1 = new Road(town1, town2, road1Name);

        Town town3 = new Town("Town3");
        Town town4 = new Town("Town4");
        String road2Name = "road2";
        Road road2 = new Road(town3, town4, road2Name);

        // Act 
        int result = road1.compareTo(road2);

        // Assert
        assertTrue(result < 0);
    }
}
