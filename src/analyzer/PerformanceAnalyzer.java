package analyzer;

import java.util.Random;

/**
 * Compares Linear vs Binary search and BFS vs DFS, showing steps and time,
 * and records every row in the ResultLog (main menu option 8).
 */
public class PerformanceAnalyzer {

    private static final int TRIALS = 100;   // random searches used for the "average" case

    public static void runComparison(int n, Graph graph) {
        Random random = new Random();
        int[] data = SearchOperations.generateRandom(n);

        // Sorting is needed before binary search; measure what it costs
        long sortStart = System.nanoTime();
        int[] sorted = SearchOperations.insertionSort(data);
        long sortNanos = System.nanoTime() - sortStart;

        // Warm-up: lets the JVM optimise the code so timings are more stable
        for (int i = 0; i < 2000; i++) {
            SearchOperations.linearSearch(data, data[random.nextInt(n)]);
            SearchOperations.binarySearch(sorted, sorted[random.nextInt(n)]);
        }
        graph.bfs(0);
        graph.dfs(0);

        // ---- Searching: best, average, worst ----
        SearchResult linBest  = SearchOperations.linearSearch(data, data[0]);           // first element
        SearchResult linWorst = SearchOperations.linearSearch(data, -1);                // not in data
        SearchResult binBest  = SearchOperations.binarySearch(sorted, sorted[(n - 1) / 2]); // middle element
        SearchResult binWorst = SearchOperations.binarySearch(sorted, -1);              // not in data

        long linSteps = 0, linTime = 0, binSteps = 0, binTime = 0;
        for (int t = 0; t < TRIALS; t++) {
            int target = data[random.nextInt(n)];            // a value that exists
            SearchResult l = SearchOperations.linearSearch(data, target);
            SearchResult b = SearchOperations.binarySearch(sorted, target);
            linSteps += l.steps;  linTime += l.nanos;
            binSteps += b.steps;  binTime += b.nanos;
        }

        // ---- Graph traversal from vertex 0 ----
        TraversalResult bfs = graph.bfs(0);
        TraversalResult dfs = graph.dfs(0);

        // ---- Print the table ----
        System.out.println("\n=============================================");
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.println("Search data size n = " + n + " | Graph: V = " + graph.getVertexCount()
                + ", E = " + graph.getEdgeCount());
        System.out.println();
        System.out.printf("%-16s %-15s %-26s %8s %12s%n", "Operation", "Algorithm", "Case", "Steps", "Time (ns)");
        System.out.println("-----------------------------------------------------------------------------");

        row("Search", "Linear Search", "Best (first element)",         linBest.steps,  linBest.nanos);
        row("Search", "Linear Search", "Average (" + TRIALS + " trials)", (double) linSteps / TRIALS, linTime / TRIALS);
        row("Search", "Linear Search", "Worst (not found)",            linWorst.steps, linWorst.nanos);
        row("Search", "Binary Search", "Best (middle element)",        binBest.steps,  binBest.nanos);
        row("Search", "Binary Search", "Average (" + TRIALS + " trials)", (double) binSteps / TRIALS, binTime / TRIALS);
        row("Search", "Binary Search", "Worst (not found)",            binWorst.steps, binWorst.nanos);
        row("Graph Traversal", "BFS", "Start vertex 0",                bfs.steps,      bfs.nanos);
        row("Graph Traversal", "DFS", "Start vertex 0",                dfs.steps,      dfs.nanos);

        System.out.println("=============================================");
        System.out.println("Sorting cost before binary search (insertion sort): " + sortNanos + " ns");
        System.out.println("BFS max queue size: " + bfs.peakStorage + " | DFS max recursion depth: " + dfs.peakStorage);

        int maxBinarySteps = (int) Math.ceil(Math.log(n + 1) / Math.log(2));
        System.out.println("\nWhy the results differ:");
        System.out.println("- Linear search is O(n): worst case checks all " + n + " elements.");
        System.out.println("- Binary search is O(log n): at most about " + maxBinarySteps
                + " steps for n = " + n + ", but it needs sorted data.");
        System.out.println("- BFS and DFS are both O(V + E): same steps for the same graph, but BFS");
        System.out.println("  stores a queue (width of the graph) while DFS uses recursion depth (path length).");
        System.out.println("- Steps are exact; time in ns varies between runs (JVM, CPU load).");
    }

    /** Prints one table row and records it in the ResultLog. */
    private static void row(String operation, String algorithm, String caseDesc, double steps, long nanos) {
        String stepsText = (steps == Math.rint(steps))
                ? String.valueOf((long) steps)
                : String.format("%.1f", steps);
        System.out.printf("%-16s %-15s %-26s %8s %12d%n", operation, algorithm, caseDesc, stepsText, nanos);
        ResultLog.add(operation, algorithm, (int) Math.round(steps), nanos, caseDesc);
    }
}