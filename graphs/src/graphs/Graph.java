package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> 
{
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;
	
	public Graph() 
	{
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}
	
	public void addVertex(String vertexName,E data) 
	{
		if (adjacencyMap.containsKey(vertexName))
		{
			throw new IllegalArgumentException("Vertex already exists.");
		}
		else
		{
			adjacencyMap.put(vertexName, new HashMap<String, Integer>());
			dataMap.put(vertexName, data);
		}
	}
	
	public void addDirectedEdge(String startVertexName, String endVertexName,
            int cost)
	{
		if (adjacencyMap.containsKey(startVertexName) == false || adjacencyMap.
				containsKey(endVertexName) == false)
		{
			throw new IllegalArgumentException("Vertices don't exist.");
		}
			
		adjacencyMap.get(startVertexName).put(endVertexName ,cost);
	}
	
	public String toString()
	{	
		String returnString = "";
		int i = 0;
		TreeSet<String> newTree = new TreeSet<String>();
		for (String newNode : adjacencyMap.keySet())
		 {
			newTree.add(newNode);
		 }
		returnString += "Vertices: [";
	
		for(String node : newTree)
		{	
			if (i == newTree.size() - 1)
			{
				returnString += node + "]";
			}
			else
			{
				returnString += node + ", ";
			}
			i++;
		}
		
		returnString += "\nEdges:\n";
	
		for (String node : newTree)
		{
			i = 0;
		returnString += "Vertex(" + node + ")--->{";
		for (String adjNode : adjacencyMap.get(node).keySet())
		{
			if(i == adjacencyMap.get(node).keySet().size() - 1)
			{
				returnString += adjNode + "=" + adjacencyMap.get(node).get(adjNode);
			}
			else
			{
				returnString += adjNode + "=" + adjacencyMap.get(node).get(adjNode) + ", ";
			}
			i++;
		}
		returnString += "}\n";
		}
		
		return returnString;
	}
	
	public Map<String,Integer> getAdjacentVertices(String vertexName)
	{
		return adjacencyMap.get(vertexName);
	}
	
	public int getCost(String startVertexName, String endVertexName)
	{
			return adjacencyMap.get(startVertexName).get(endVertexName);
	}
	
	public Set<String> getVertices()
	{
		Set<String> newTreeSet = new TreeSet<String>();
		newTreeSet = dataMap.keySet();
		
		return newTreeSet;
	}

	public E getData(String vertex) 
	{
		if (dataMap.containsKey(vertex) == false)
		{
			throw new IllegalArgumentException("Vertices don't exist.");
		}
		return dataMap.get(vertex);
	}
	
	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) 
	{
		Stack<String> discovered = new Stack<String>();
		Set<String> visited = new HashSet<String>();
		discovered.push(startVertexName);
		String currentNode = "";
		Set<String> newTreeSet = new TreeSet<String>();
		newTreeSet = adjacencyMap.keySet();
		
		if (adjacencyMap.containsKey(startVertexName) == false)
		{
			throw new IllegalArgumentException("Vertex doesn't exist.");
		}
		
		while (discovered.empty() == false)
		{
			currentNode = discovered.peek();
			discovered.pop();
		
			if (visited.contains(currentNode) == false)
			{	
				visited.add(currentNode);
				callback.processVertex(currentNode, dataMap.get(currentNode));
				newTreeSet = adjacencyMap.get(currentNode).keySet();
				for (String adjNode : newTreeSet)
				{	
					if (visited.contains(adjNode) == false)
					{
						discovered.push(adjNode);
					}
				}
			}
		}
	}
	
	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback) 
	{
		
		Queue<String> discovered = new PriorityQueue<String>();
		Set<String> visited = new HashSet<String>();
		discovered.add(startVertexName);
		String currentNode = "";
		Set<String> newTreeSet = new TreeSet<String>();
		newTreeSet = adjacencyMap.keySet();
		
		if (adjacencyMap.containsKey(startVertexName) == false)
		{
			throw new IllegalArgumentException("Vertex doesn't exist.");
		}
		
		while (discovered.size() != 0)
		{
			currentNode = discovered.peek();
			discovered.remove();
			
			if (visited.contains(currentNode) == false)
			{
				visited.add(currentNode);
				callback.processVertex(currentNode, dataMap.get(currentNode));
				newTreeSet = adjacencyMap.get(currentNode).keySet();
				for (String adjNode : newTreeSet)
				{	
					if (visited.contains(adjNode) == false)
					{
						discovered.add(adjNode);
					}
				}

			}
		}
		
	}
	
	public int doDijkstras(String startVertexName, String endVertexName,
        ArrayList<String> shortestPath)
	{
		ArrayList<String> d = new ArrayList<String>();
		Set<String> pathSet=new HashSet<String>();
		Vertices findVertex = null;
		Map<String, String> shortest = new TreeMap<String, String>();
		PriorityQueue<Vertices> vertices = new PriorityQueue<Vertices>();
		Map<String, Integer> costMap = new HashMap<String, Integer>();
		
		if (adjacencyMap.containsKey(startVertexName) == false ||adjacencyMap.
				containsKey(endVertexName) == false )
		{
			throw new IllegalArgumentException ("Does not contain");
		}
		
		for(String vertexName : adjacencyMap.keySet())
		{	
			if (vertexName == startVertexName)
			{
				vertices.offer(new Vertices(vertexName, 0));
			}
			else
			{
				vertices.offer(new Vertices(vertexName, 10000));
			}
		
		}
		
		shortest.put(startVertexName, "");
		
		while (vertices.isEmpty() == false && pathSet.contains(endVertexName) == false)
		{
			costMap.put(vertices.peek().getName(), vertices.peek().getPriority());
			
			for (String vertexName : adjacencyMap.get(vertices.peek().getName()).keySet())
			{	
				findVertex = findVertice(vertices, vertexName);
				if (((adjacencyMap.get(vertices.peek().getName()).get(vertexName))
					+ (vertices.peek().getPriority())) < 
						findVertex.getPriority(vertices))
				{
					findVertex.setPriority(vertices,((adjacencyMap.get(vertices.
							peek().getName()).get(vertexName)) + 
							(vertices.peek().getPriority())));
					if (shortest.containsKey(vertexName) == false)
					{
						shortest.put(vertexName, vertices.peek().getName());
					}
					else
					{
						shortest.replace(vertexName,vertices.peek().getName());
					}
				}
			} 
			
		vertices.poll();
		}
		
		if (shortest.containsKey(endVertexName) == false)
		{
			shortestPath.add("None");
			costMap.replace(endVertexName, -1);
		}
		
		else 
		{
			String k = endVertexName;
			
			d.add(endVertexName);
			while (shortest.get(k).compareTo("") != 0)
			{
				d.add(shortest.get(k));
				k = shortest.get(k);
			}
	
 			for (int i = d.size() - 1; i >= 0; i--)
			{
 				shortestPath.add(d.get(i));
			}
		}
		
	return costMap.get(endVertexName);
	}
	
	private Vertices findVertice(PriorityQueue<Vertices> vertices, String vertexName)
	{
		for (Vertices v : vertices)
		{
			if (v.getName().equals(vertexName) == true)
			{
				return v;
			}
		}
	
		return new Vertices(vertexName, 10000);
	}
}