import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Student test class to test all the methods of my Town class.
 * @author Caterine G. Asselborn
 */
public class Town_STUDENT_Test 
{
    @Test
    public void Constructor()
    {
        // Arrange
        String townName = "town1";

        // Act 
        Town result = new Town(townName);

        // Assert
        assertEquals(townName, result.getName());
    }

    @Test
    public void CopyConstructor()
    {
        // Arrange
        String townName = "town1";
        Town townToCopy = new Town(townName);

        // Act 
        Town result = new Town(townToCopy);

        // Assert
        assertEquals(townName, result.getName());
    }

    @Test
    public void CompareTo_sorts_correctly()
    {
        // Arrange
        Town townA = new Town("A");
        Town townZ = new Town("Z");

        // Act 
        int result = townA.compareTo(townZ);

        // Assert
        assertTrue(result < 0);

    }

    @Test
    public void Equals_same_names_are_equal()
    {
        // Arrange
        Town town1 = new Town("A");
        Town town2 = new Town("A");

        // Act 
        boolean areEqual = town1.equals(town2);

        // Assert
        assertTrue(areEqual);
    }

    @Test
    public void Equals_not_town_returns_false()
    {
        // Arrange
        Town town1 = new Town("A");
        String notTown = "A";

        // Act 
        boolean areEqual = town1.equals(notTown);

        // Assert
        assertFalse(areEqual);
    }

    @Test
    public void Equals_null_returns_false()
    {
        // Arrange
        Town town1 = new Town("A");

        // Act 
        boolean areEqual = town1.equals(null);

        // Assert
        assertFalse(areEqual);
    }
}
