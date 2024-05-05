import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

/**
 * Class to hold object of Graph
 * 
 * @author Caterine G. Asselborn
 */
public class TownGraphManager implements TownGraphManagerInterface
{
    private Graph townMap;

    public TownGraphManager()
    {
        this.townMap = new Graph();
    }

    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName)
    {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);

        try
        {
            this.townMap.addEdge(t1, t2, weight, roadName);
        } catch (Exception e)
        {
            return false;
        }

        return true;
    }

    @Override
    public String getRoad(String town1, String town2)
    {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);

        Road roadConnectingTowns = this.townMap.getEdge(t1, t2);
        
        if(roadConnectingTowns == null)
        {
            return null;
        }

        return roadConnectingTowns.getName();
    }

    @Override
    public boolean addTown(String v)
    {
        Town townToAdd = new Town(v);

        return this.townMap.addVertex(townToAdd);
    }

    @Override
    public Town getTown(String name)
    {
        Town townToFind = new Town(name);

        return this.containsTown(name) ? townToFind : null;
    }

    @Override
    public boolean containsTown(String v)
    {
        Town townToFind = new Town(v);

        return this.townMap.containsVertex(townToFind);
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2)
    {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);

        return this.townMap.containsEdge(t1, t2);
    }

    @Override
    public ArrayList<String> allRoads()
    {
        Set<Road> allRoads = this.townMap.edgeSet();
        ArrayList<String> result = new ArrayList<>();

        for (Road road : allRoads)
        {
            result.add(road.getName());
        }

        result.sort(Comparator.naturalOrder());

        return result;
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road)
    {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);

        Road removedRoad = this.townMap.removeEdge(t1, t2, -1, road);

        return removedRoad != null;
    }

    @Override
    public boolean deleteTown(String v)
    {
        Town townToRemove = new Town(v);

        return this.townMap.removeVertex(townToRemove);
    }

    @Override
    public ArrayList<String> allTowns()
    {
        Set<Town> allTowns = this.townMap.vertexSet();
        ArrayList<String> result = new ArrayList<>();

        for (Town town : allTowns)
        {
            result.add(town.getName());
        }

        result.sort(Comparator.naturalOrder());

        return result;
    }

    @Override
    public ArrayList<String> getPath(String town1, String town2)
    {
        Town t1 = new Town(town1);
        Town t2 = new Town(town2);

        return this.townMap.shortestPath(t1, t2);        
    }

    public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException
    {
        try (Scanner fileContent = new Scanner(selectedFile))
        {
            while (fileContent.hasNextLine())
            {
                // Reads the line
                String line = fileContent.nextLine();

                // Splits line based on ;
                String[] lineContent = line.split(";");

                // Gets towns
                Town source = new Town(lineContent[1]);
                Town destination = new Town(lineContent[2]);

                this.townMap.addVertex(source);            
                this.townMap.addVertex(destination);

                // Gets the road information
                String[] roadContent = lineContent[0].split(",");

                String roadName = roadContent[0];
                int roadLength = Integer.parseInt(roadContent[1]);

                this.townMap.addEdge(source, destination, roadLength, roadName);            
            }
        } catch (NumberFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
