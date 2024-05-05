import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test class to test all the methods of the TownGraphManager with a different set of data than the TownGraphManagerTest provided.
 * @author Caterine G. Asselborn
 */
public class TownGraphManager_STUDENT_Test 
{
	private TownGraphManagerInterface graph;

    @Before
    public void setUp() throws Exception
    {
        graph = new TownGraphManager();
        String[] town = new String[12];

        for (int i = 1; i < 12; i++)
        {
            town[i] = "Town_" + i + "ville";
            graph.addTown(town[i]);
        }

        graph.addRoad(town[1], town[2], 2, "Road_101");
        graph.addRoad(town[1], town[3], 4, "Road_201");
        graph.addRoad(town[1], town[5], 6, "Road_301");
        graph.addRoad(town[3], town[7], 1, "Road_401");
        graph.addRoad(town[3], town[8], 2, "Road_501");
        graph.addRoad(town[4], town[8], 3, "Road_601");
        graph.addRoad(town[6], town[9], 3, "Road_701");
        graph.addRoad(town[9], town[10], 4, "Road_801");
        graph.addRoad(town[8], town[10], 2, "Road_901");
        graph.addRoad(town[5], town[10], 5, "Road_1001");
        graph.addRoad(town[10], town[11], 3, "Road_1101");
        graph.addRoad(town[2], town[11], 6, "Road_1201");
    }

    @After
    public void tearDown() throws Exception
    {
        graph = null;
    }

    @Test
    public void addRoad()
    {
        // Arrange
        String town1 = "Town_1ville";
        String town2 = "Town_11ville";
        int weight = 10;
        String roadName = "Road123";

        // Act
        boolean result = graph.addRoad(town1, town2, weight, roadName);

        // Assert
        assertTrue(result);
    }

    @Test
    public void getRoad()
    {
        // Arrange
        String town1 = "Town_1ville";
        String town2 = "Town_2ville";
        String expectedRoad = "Road_101";

        // Act
        String result = graph.getRoad(town1, town2);

        // Assert
        assertEquals(expectedRoad, result);
    }

    @Test
    public void addTown()
    {
        // Arrange
        String newTown = "Town_12ville";

        // Act
        graph.addTown(newTown);

        // Assert
        assertEquals(12, graph.allTowns().size());
    }

    @Test
    public void getTown()
    {
        // Arrange
        String town1 = "Town_1ville";

        // Act
        Town result = graph.getTown(town1);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void containsTown()
    {
        // Arrange
        String town1 = "Town_1ville";
        String newTown = "Town_12ville";

        // Act
        boolean town1Exists = graph.containsTown(town1);
        boolean newTownExists = graph.containsTown(newTown);

        // Assert
        assertTrue(town1Exists);
        assertFalse(newTownExists);
    }

    @Test
    public void containsRoadConnection()
    {
        // Arrange
        String town1 = "Town_1ville";
        String town2 = "Town_2ville";
        String town11 = "Town_11ville";

        // Act
        boolean result = graph.containsRoadConnection(town1, town2);
        boolean otherResult = graph.containsRoadConnection(town11, town1);

        // Assert
        assertTrue(result);
        assertFalse(otherResult);
    }

    @Test
    public void allRoads()
    {
        // Act
        ArrayList<String> result = graph.allRoads();

        // Assert
        assertEquals(12, result.size());
    }

    @Test
    public void deleteRoadConnection()
    {
        // Arrange
        String town2 = "Town_2ville";
        String town11 = "Town_11ville";

        // Act
        graph.deleteRoadConnection(town11, town2, "Road_1201");

        // Assert
        assertFalse(graph.containsRoadConnection(town11, town2));
    }

    @Test
    public void deleteTown()
    {
        // Arrange
        String town2 = "Town_2ville";

        // Act
        graph.deleteTown(town2);

        // Assert
        assertFalse(graph.containsTown(town2));
    }

    @Test
    public void allTowns()
    {
        // Act
        ArrayList<String> allTowns = graph.allTowns();

        // Assert
        assertEquals(11, allTowns.size());
    }

    @Test
    public void getPath()
    {
        // Arrange
        String town1 = "Town_1ville";
        String town11 = "Town_11ville";

        // Act
        ArrayList<String> path = graph.getPath(town1, town11);

        // Assert
        StringBuilder sb = new StringBuilder();

        for (String pathElement : path)
        {
            sb.append(pathElement);
            sb.append("\n");
        }

        assertEquals("Town_1ville via Road_101 to Town_2ville 2 mi\n" + 
        "Town_2ville via Road_1201 to Town_11ville 6 mi\n", sb.toString());
    }
}
