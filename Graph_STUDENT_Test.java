import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test class to test all the methods of the Graph with a different set
 * of data than the GraphTest provided.
 * 
 * @author Caterine G. Asselborn
 */
public class Graph_STUDENT_Test
{
    private GraphInterface<Town, Road> graph;

    @Before
    public void setUp() throws Exception
    {
        graph = new Graph();
        Town[] town = new Town[12];

        for (int i = 1; i < 12; i++)
        {
            town[i] = new Town("Town_" + i + "ville");
            graph.addVertex(town[i]);
        }

        graph.addEdge(town[1], town[2], 2, "Road_101");
        graph.addEdge(town[1], town[3], 4, "Road_201");
        graph.addEdge(town[1], town[5], 6, "Road_301");
        graph.addEdge(town[3], town[7], 1, "Road_401");
        graph.addEdge(town[3], town[8], 2, "Road_501");
        graph.addEdge(town[4], town[8], 3, "Road_601");
        graph.addEdge(town[6], town[9], 3, "Road_701");
        graph.addEdge(town[9], town[10], 4, "Road_801");
        graph.addEdge(town[8], town[10], 2, "Road_901");
        graph.addEdge(town[5], town[10], 5, "Road_1001");
        graph.addEdge(town[10], town[11], 3, "Road_1101");
        graph.addEdge(town[2], town[11], 6, "Road_1201");
    }

    @After
    public void tearDown() throws Exception
    {
        graph = null;
    }

    @Test
    public void add_vertex_correctly()
    {
        // Arrange
        Town newTown = new Town("town_12ville");

        // Act
        graph.addVertex(newTown);

        // Assert
        assertTrue(graph.containsVertex(newTown));
    }

    @Test
    public void add_vertex_thows_exception()
    {
        try
        {
            graph.addVertex(null);

            // if this lines executes then the test fails
            fail();
        } catch (NullPointerException e)
        {
            // this is the correct exception
        }
    }

    @Test
    public void add_vertex_is_already_in_graph()
    {
        // Arrange
        Town newTown = new Town("Town_11ville");

        // Act
        boolean result = graph.addVertex(newTown);

        // Assert
        assertFalse(result);
    }

    @Test
    public void add_edge_correctly()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = new Town("Town_12ville");
        graph.addVertex(destinationTown);

        // Act
        graph.addEdge(sourceTown, destinationTown, 10, "Road_1301");

        // Assert
        assertTrue(graph.containsEdge(sourceTown, destinationTown));
    }

    @Test
    public void addEdge_null_source_throws_NullPointerException()
    {
        // Arrange
        Town sourceTown = null;
        Town destinationTown = new Town("Town_12ville");
        graph.addVertex(destinationTown);

        // Act
        try
        {
            graph.addEdge(sourceTown, destinationTown, 10, "Road_1301");
            fail();
        } catch (NullPointerException npe)
        {
            // this is the correct exception
        }
    }

    @Test
    public void addEdge_null_destination_throws_NullPointerException()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = null;

        // Act
        try
        {
            graph.addEdge(sourceTown, destinationTown, 10, "Road_1301");
            fail();
        } catch (NullPointerException npe)
        {
            // this is the correct exception
        }
    }

    @Test
    public void addEdge_vertex_missing_throws_IllegalArgumentException()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = new Town("Town_12ville");

        // Act
        try
        {
            graph.addEdge(sourceTown, destinationTown, 10, "Road_1301");
            fail();
        } catch (IllegalArgumentException npe)
        {
            // this is the correct exception
        }
    }

    @Test
    public void getEdge_vertex_null_returns_null()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = null;

        // Act
        var result = graph.getEdge(sourceTown, destinationTown);

        // Assert
        assertNull(result);
    }

    @Test
    public void getEdge_vertex_missing_returns_null()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = new Town("Town_12ville");

        // Act
        var result = graph.getEdge(sourceTown, destinationTown);

        // Assert
        assertNull(result);
    }

    @Test
    public void getEdge_correctly()
    {
        // Arrange
        Town sourceTown = new Town("Town_10ville");
        Town destinationTown = new Town("Town_11ville");
        graph.addVertex(destinationTown);

        // Act
        Road result = graph.getEdge(sourceTown, destinationTown);

        // Assert
        assertNotNull(result);
        assertEquals(result.getName(), "Road_1101");
    }

    @Test
    public void containsEdge_vertex_null_returns_null()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = null;

        // Act
        var result = graph.containsEdge(sourceTown, destinationTown);

        // Assert
        assertFalse(result);
    }

    @Test
    public void containsEdge_vertex_missing_returns_null()
    {
        // Arrange
        Town sourceTown = new Town("Town_11ville");
        Town destinationTown = new Town("Town_12ville");

        // Act
        var result = graph.containsEdge(sourceTown, destinationTown);

        // Assert
        assertFalse(result);
    }

    @Test
    public void containsEdge_correctly()
    {
        // Arrange
        Town sourceTown = new Town("Town_10ville");
        Town destinationTown = new Town("Town_11ville");
        graph.addVertex(destinationTown);

        // Act
        boolean result = graph.containsEdge(sourceTown, destinationTown);

        // Assert
        assertTrue(result);
    }

    @Test
    public void containsVertex_vertex_null_returns_null()
    {
        // Arrange
        Town town = null;

        // Act
        var result = graph.containsVertex(town);

        // Assert
        assertFalse(result);
    }

    @Test
    public void containsVertex_vertex_missing_returns_null()
    {
        // Arrange
        Town town = new Town("Town_12ville");

        // Act
        var result = graph.containsVertex(town);

        // Assert
        assertFalse(result);
    }

    @Test
    public void containsVertex_correctly()
    {
        // Arrange
        Town town = new Town("Town_10ville");

        // Act
        boolean result = graph.containsVertex(town);

        // Assert
        assertTrue(result);
    }

    @Test
    public void edgeSet()
    {
        // Act
        Set<Road> result = graph.edgeSet();

        // Assert
        assertNotNull(result);
        assertEquals(12, result.size());
    }

    @Test
    public void edgesOf_null_vertex_throws_NullPointerException()
    {
        // Arrange
        Town town = null;

        // Act
        try
        {
            graph.edgesOf(town);
            fail();
        } catch (NullPointerException npe)
        {
            // this is the correct exception
        }
    }

    @Test
    public void edgesOf_vertex_missing_throws_IllegalArgumentException()
    {
        // Arrange
        Town sourceTown = new Town("Town_12ville");

        // Act
        try
        {
            graph.edgesOf(sourceTown);
            fail();
        } catch (IllegalArgumentException npe)
        {
            // this is the correct exception
        }
    }

    @Test
    public void edgesOf()
    {
        // Arrange
        Town town = new Town("Town_1ville");

        // Act
        Set<Road> result = graph.edgesOf(town);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void removeEdge()
    {
        // Arrange
        Town town1 = new Town("Town_1ville");
        Town town2 = new Town("Town_2ville");

        // Act
        graph.removeEdge(town1, town2, -1, null);

        // Assert
        assertFalse(graph.containsEdge(town1, town2));
    }

    @Test
    public void removeVertex()
    {
        // Arrange
        Town town1 = new Town("Town_1ville");

        // Act
        graph.removeVertex(town1);

        // Assert
        assertFalse(graph.containsVertex(town1));
    }

    @Test
    public void vertexSet()
    {
        // Act
        Set<Town> vertexSet = graph.vertexSet();

        // Assert
        assertEquals(11, vertexSet.size());
    }

    @Test
    public void shortestPath()
    {
        // Arrange
        Town town1 = new Town("Town_1ville");
        Town town11 = new Town("Town_11ville");

        // Act
        ArrayList<String> path = graph.shortestPath(town1, town11);

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
