import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Class that represents the Graph DataStructure
 * 
 * @author Caterine G. Asselborn
 */
public class Graph implements GraphInterface<Town, Road>
{
    private Map<Town, Set<Road>> adjacencyList;
    private Map<Town, Integer> distances;
    private Map<Town, Road> predecessors;

    public Graph()
    {
        this.adjacencyList = new HashMap<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();
    }

    @Override
    public boolean addVertex(Town v)
    {
        // if town is null throw exception
        if (v == null)
        {
            throw new NullPointerException();
        }

        // if town already exists return false
        if (this.containsVertex(v))
        {
            return false;
        }

        // add town to the Graph
        adjacencyList.put(v, new HashSet<>());

        return true;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
        // if the town is null throw exception
        if (sourceVertex == null || destinationVertex == null)
        {
            throw new NullPointerException();
        }

        // if the town does not exist throw exception
        if (!this.containsVertex(sourceVertex) || !this.containsVertex(destinationVertex))
        {
            throw new IllegalArgumentException("Source or destination towns not found in the graph.");
        }

        Road road = new Road(sourceVertex, destinationVertex, weight, description);

        // Add road to sourceVertex
        adjacencyList.get(sourceVertex).add(road);

        // Add road to destinationVertex
        adjacencyList.get(destinationVertex).add(road);

        return road;

    }

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex)
    {
        if (sourceVertex == null || destinationVertex == null)
        {
            return null;
        }

        if (!this.containsVertex(sourceVertex) || !this.containsVertex(destinationVertex))
        {
            return null;
        }

        for (Road road : adjacencyList.get(sourceVertex))
        {
            if (road.contains(destinationVertex))
            {
                return road;
            }
        }

        return null;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex)
    {
        // if vertices are null
        if (sourceVertex == null || destinationVertex == null)
        {
            return false;
        }

        // if vertices do not exist return false
        if (!this.containsVertex(sourceVertex) || !this.containsVertex(destinationVertex))
        {
            return false;
        }

        // returns true if edge exists
        return this.getEdge(sourceVertex, destinationVertex) != null;

    }

    @Override
    public boolean containsVertex(Town v)
    {
        if (v == null)
        {
            return false;
        }

        return adjacencyList.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet()
    {
        // All roads set
        Set<Road> allRoads = new HashSet<>();

        for (Set<Road> townRoads : adjacencyList.values())
        {
            allRoads.addAll(townRoads);
        }

        return allRoads;
    }

    @Override
    public Set<Road> edgesOf(Town vertex)
    {
        // if town (vertex) is null throw exception
        if (vertex == null)
        {
            throw new NullPointerException();
        }

        // if town (vertex) does not exist throw exception
        if (!this.containsVertex(vertex))
        {
            throw new IllegalArgumentException("Source or destination towns not found in the graph.");
        }

        return adjacencyList.get(vertex);
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
        // if town (vertex) does not exits
        if (!this.containsVertex(sourceVertex) || !this.containsVertex(destinationVertex))
        {
            return null;
        }

        // if the edge does not exists
        Road edgeToRemove = getEdge(sourceVertex, destinationVertex);

        if (edgeToRemove == null)
        {
            return null;
        }

        // if weight is greater -1 we need to check if it is equal to the edgeToRemove
        // weight
        boolean needToCheckWeight = weight > -1;
        boolean isEdgeWeightCorrect = weight == edgeToRemove.getWeight();

        if (needToCheckWeight && !isEdgeWeightCorrect)
        {
            return null;
        }

        // if the name of the road is not null and it matches the edgeToRemove name
        boolean needToCheckEdgeName = description != null;
        if (needToCheckEdgeName && !description.equals(edgeToRemove.getName()))
        {
            return null;
        }

        // After all checks are passed then we remove the edge
        adjacencyList.get(sourceVertex).remove(edgeToRemove);
        adjacencyList.get(destinationVertex).remove(edgeToRemove);

        return edgeToRemove;
    }

    @Override
    public boolean removeVertex(Town v)
    {
        // if town is null OR does not exist in Map return false
        if (v == null || !this.containsVertex(v))
        {
            return false;
        }

        // Remove town (key) and its roads (values)
        Set<Road> roadsToRemove = adjacencyList.remove(v);

        // then remove all roads (values) in all other entries
        for (Set<Road> roadSet : adjacencyList.values())
        {
            roadSet.removeAll(roadsToRemove);
        }

        return true;
    }

    @Override
    public Set<Town> vertexSet()
    {
        return adjacencyList.keySet();
    }

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
    {
        dijkstraShortestPath(sourceVertex);

        List<Town> path = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        Town currentTown = destinationVertex;

        // Check if a path exists
        if (predecessors.get(currentTown) == null)
        {
            return result; // return an empty list if no path exists
        }

        while (predecessors.get(currentTown) != null)
        {
            Road connectingRoad = predecessors.get(currentTown);
            Town previousTown = connectingRoad.getSource().equals(currentTown) ? connectingRoad.getDestination() : connectingRoad.getSource();

            // Add current town to our list of steps
            path.add(currentTown);

            // Setup the previous town for processing
            currentTown = previousTown;
        }

        // Add the source
        path.add(sourceVertex);

        // Reverse the path because it is ordered from destination to source
        Collections.reverse(path);

        // Build the path description
        Town current = path.get(0);

        for (int i = 1; i < path.size(); i++)
        {
            Town nextTownInPath = path.get(i);

            Road roadConnectingTowns = getEdge(current, nextTownInPath);

            String stepDescription = String.format("%s via %s to %s %d mi", current.getName(), roadConnectingTowns.getName(),
                    nextTownInPath.getName(), roadConnectingTowns.getWeight());
            
            // Add description to our result
            result.add(stepDescription);

            current = nextTownInPath;
        }

        return result;
    }

    @Override
    public void dijkstraShortestPath(Town sourceVertex)
    {
        // Use priority queue to track verticies to process
        PriorityQueue<TownDistancePair> priorityQueue = new PriorityQueue<>();

        // Clear any results from previous execution of this algorithm
        distances.clear();
        predecessors.clear();

        // Fill up shortest distances collection
        Set<Town> allTowns = this.vertexSet();
        for (Town town : allTowns)
        {
            if (town.equals(sourceVertex))
            {
                // Shortest distance for source vertext should be 0
                distances.put(town, 0);
            } else
            {
                // Distance to all other towns should be "infinity" until reviewed
                distances.put(town, Integer.MAX_VALUE);
            }

            // Add town to queue for processing
            priorityQueue.add(new TownDistancePair(town, distances.get(town)));
        }

        // While a town is in the queue, it means that it has not yet been visited. When the queue is empty, we have visited all of our towns.
        while (!priorityQueue.isEmpty())
        {
            // Get the next unvisited town with the shortest distance
            Town currentTown = priorityQueue.poll().getTown();
            
            // Get all of the adjacent roats for this town
            Set<Road> adjacentRoads = edgesOf(currentTown);

            for (Road road : adjacentRoads)
            {
                // Get the adjacent town (necessary because graph is undirected)
                Town adjacent = road.getDestination().equals(currentTown) ? road.getSource() : road.getDestination();
                int roadWeight = road.getWeight();

                // Calculate the new distance following this path
                int newDistance = distances.get(currentTown) + roadWeight;

                // Checks if our new distance is shorter than our currently calculated shortest distance
                if (newDistance < distances.get(adjacent))
                {
                    distances.put(adjacent, newDistance);
                    predecessors.put(adjacent, road);
                    priorityQueue.add(new TownDistancePair(adjacent, newDistance));
                }
            }
        }
    }

    /**
     * Holds the town and the distance to that down. Implmenets Comparable to allow for 
     * prioritization in PriorityQueue 
     * @author Caterine G. Asselborn
     */
    private class TownDistancePair implements Comparable<TownDistancePair>
    {
        private Town town;
        private int distance;

        public TownDistancePair(Town town, int distance)
        {
            this.town = town;
            this.distance = distance;
        }

        public Town getTown()
        {
            return town;
        }

        @Override
        public int compareTo(TownDistancePair other)
        {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
