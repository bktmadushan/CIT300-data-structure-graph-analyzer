package analyzer;

/**
 * The outcome of one graph traversal (BFS or DFS).
 * peakStorage = largest queue size for BFS, or deepest recursion level for DFS.
 */
public class TraversalResult {

    public final String order;       // e.g. "0 -> 1 -> 2"
    public final int visitedCount;   // vertices visited
    public final int steps;          // vertices processed + edges examined
    public final int peakStorage;
    public final long nanos;

    public TraversalResult(String order, int visitedCount, int steps, int peakStorage, long nanos) {
        this.order = order;
        this.visitedCount = visitedCount;
        this.steps = steps;
        this.peakStorage = peakStorage;
        this.nanos = nanos;
    }
}