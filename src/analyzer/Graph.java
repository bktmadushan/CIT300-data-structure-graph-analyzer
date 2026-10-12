package analyzer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Undirected graph stored as an ADJACENCY LIST:
 * adjacencyList.get(v) holds the neighbours of vertex v.
 * Vertices are numbered 0, 1, 2, ... in the order they are added.
 *
 * "Steps" in a traversal = vertices processed + edges examined,
 * which matches the O(V + E) complexity of BFS and DFS.
 */
public class Graph {

    private ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
    private int edgeCount = 0;

    public int getVertexCount() { return adjacencyList.size(); }
    public int getEdgeCount()   { return edgeCount; }

    public boolean hasVertex(int v) {
        return v >= 0 && v < adjacencyList.size();
    }

    /** Adds a new vertex and returns its number. O(1). */
    public int addVertex() {
        adjacencyList.add(new ArrayList<>());
        return adjacencyList.size() - 1;
    }

    /** Adds an undirected edge between two existing vertices. O(degree). */
    public boolean addEdge(int a, int b) {
        if (!hasVertex(a) || !hasVertex(b)) {
            System.out.println("Both vertices must exist (valid range: 0 to " + (getVertexCount() - 1) + ").");
            return false;
        }
        if (a == b) {
            System.out.println("Self-loops are not allowed.");
            return false;
        }
        if (adjacencyList.get(a).contains(b)) {
            System.out.println("Edge " + a + " - " + b + " already exists.");
            return false;
        }
        adjacencyList.get(a).add(b);
        adjacencyList.get(b).add(a);
        edgeCount++;
        return true;
    }

    /** Prints each vertex with its neighbours. */
    public void display() {
        if (adjacencyList.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Graph: " + getVertexCount() + " vertices, " + edgeCount + " edges");
        for (int v = 0; v < adjacencyList.size(); v++) {
            System.out.print("  Vertex " + v + " : ");
            ArrayList<Integer> neighbours = adjacencyList.get(v);
            if (neighbours.isEmpty()) {
                System.out.print("(no neighbours)");
            }
            for (int i = 0; i < neighbours.size(); i++) {
                System.out.print(neighbours.get(i) + (i < neighbours.size() - 1 ? ", " : ""));
            }
            System.out.println();
        }
    }

    /** Shows whether a vertex exists and who its neighbours are. */
    public void searchVertex(int v) {
        if (!hasVertex(v)) {
            System.out.println("Vertex " + v + " does not exist in the graph.");
            return;
        }
        ArrayList<Integer> neighbours = adjacencyList.get(v);
        System.out.println("Vertex " + v + " found. Degree: " + neighbours.size()
                + ", neighbours: " + neighbours);
    }

    /**
     * Breadth-First Search from a start vertex. O(V + E).
     * Uses our own MyQueue: visit the start, then all its neighbours,
     * then their neighbours, level by level.
     */
    public TraversalResult bfs(int start) {
        long startTime = System.nanoTime();

        boolean[] visited = new boolean[getVertexCount()];
        MyQueue queue = new MyQueue(getVertexCount());   // each vertex is enqueued at most once
        StringBuilder order = new StringBuilder();
        int steps = 0;
        int visitedCount = 0;
        int maxQueueSize = 1;

        visited[start] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            steps++;                                  // processing a vertex
            visitedCount++;
            if (order.length() > 0) order.append(" -> ");
            order.append(v);

            for (int neighbour : adjacencyList.get(v)) {
                steps++;                              // examining an edge
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.enqueue(neighbour);
                    if (queue.size() > maxQueueSize) {
                        maxQueueSize = queue.size();
                    }
                }
            }
        }

        long elapsed = System.nanoTime() - startTime;
        return new TraversalResult(order.toString(), visitedCount, steps, maxQueueSize, elapsed);
    }

    /**
     * Depth-First Search from a start vertex (recursive). O(V + E).
     * Goes as deep as possible along one path before backtracking.
     */
    public TraversalResult dfs(int start) {
        long startTime = System.nanoTime();

        boolean[] visited = new boolean[getVertexCount()];
        StringBuilder order = new StringBuilder();
        int[] counters = new int[3];   // [0] = steps, [1] = vertices visited, [2] = max depth

        dfsVisit(start, visited, order, counters, 1);

        long elapsed = System.nanoTime() - startTime;
        return new TraversalResult(order.toString(), counters[1], counters[0], counters[2], elapsed);
    }

    private void dfsVisit(int v, boolean[] visited, StringBuilder order, int[] counters, int depth) {
        visited[v] = true;
        counters[0]++;                                // processing a vertex
        counters[1]++;
        if (depth > counters[2]) counters[2] = depth;
        if (order.length() > 0) order.append(" -> ");
        order.append(v);

        for (int neighbour : adjacencyList.get(v)) {
            counters[0]++;                            // examining an edge
            if (!visited[neighbour]) {
                dfsVisit(neighbour, visited, order, counters, depth + 1);
            }
        }
    }

    /**
     * Builds a random connected graph. Each new vertex is first linked to an
     * earlier one (so everything is reachable), then extra random edges are
     * added until the requested number of edges is reached.
     */
    public static Graph createRandom(int vertices, int edges) {
        Random random = new Random();
        Graph g = new Graph();
        for (int i = 0; i < vertices; i++) {
            g.addVertex();
        }
        for (int i = 1; i < vertices; i++) {
            g.addEdge(i, random.nextInt(i));
        }

        int maxEdges = vertices * (vertices - 1) / 2;
        int target = Math.min(edges, maxEdges);
        int attempts = 0;
        while (g.edgeCount < target && attempts < target * 10) {
            int a = random.nextInt(vertices);
            int b = random.nextInt(vertices);
            if (a != b && !g.adjacencyList.get(a).contains(b)) {
                g.addEdge(a, b);
            }
            attempts++;
        }
        return g;
    }

    /** Builds a ready-made 7-vertex graph, handy for demos and the video. */
    public static Graph createSample() {
        Graph g = new Graph();
        for (int i = 0; i < 7; i++) {
            g.addVertex();
        }
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(4, 6);
        return g;
    }
}