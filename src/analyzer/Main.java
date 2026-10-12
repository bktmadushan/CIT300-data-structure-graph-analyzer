package analyzer;

/**
 * Entry point. Only the Array submenu is wired in for now;
 * the other options will be added as each component is built.
 */
public class Main {

    // Each structure is created the first time its menu is opened
    private static ArrayOperations array;
    private static MyStack stack;
    private static MyQueue queue;
    private static MyLinkedList linkedList = new MyLinkedList();
    private static int[] searchData = new int[0];   // data set used by the Searching menu
    private static Graph graph = new Graph();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=============================================");
            System.out.println("      DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 9);

            switch (choice) {
                case 1:
                    arrayMenu();
                    break;
                case 2:
                    stackMenu();
                    break;
                case 3:
                    queueMenu();
                    break;
                case 4:
                    linkedListMenu();
                    break;
                case 5:
                    searchMenu();
                    break;
                case 6:
                    graphMenu();
                    break;
                case 7:
                    performanceComparison();
                    break;
                case 8:
                    ResultLog.displayAll();
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("This option is not implemented yet.");
            }
        }
    }

    private static void performanceComparison() {
        System.out.println("\n------------- PERFORMANCE COMPARISON -------------");
        int n = InputHelper.readIntInRange("Search data size (10-10000): ", 10, 10000);

        System.out.println("Graph to use for BFS vs DFS:");
        System.out.println("1. Current graph");
        System.out.println("2. Random graph (larger, better for comparison)");
        int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 2);

        Graph selectedGraph;
        if (choice == 2) {
            int vertices = InputHelper.readIntInRange("Number of vertices (5-1000): ", 5, 1000);
            selectedGraph = Graph.createRandom(vertices, vertices * 2);
            System.out.println("Random graph created: " + selectedGraph.getVertexCount()
                    + " vertices, " + selectedGraph.getEdgeCount() + " edges.");
        } else if (graph.getVertexCount() == 0) {
            System.out.println("Current graph is empty - using the sample graph instead.");
            selectedGraph = Graph.createSample();
        } else {
            selectedGraph = graph;
        }

        PerformanceAnalyzer.runComparison(n, selectedGraph);
    }

    private static void graphMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("Vertices: " + graph.getVertexCount() + " | Edges: " + graph.getEdgeCount());
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. Search Vertex");
            System.out.println("5. BFS Traversal");
            System.out.println("6. DFS Traversal");
            System.out.println("7. Load Sample Graph");
            System.out.println("8. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1: {
                    int id = graph.addVertex();
                    System.out.println("Added vertex " + id + ".");
                    break;
                }
                case 2: {
                    if (graph.getVertexCount() < 2) {
                        System.out.println("Add at least two vertices first.");
                        break;
                    }
                    int a = InputHelper.readInt("Enter first vertex: ");
                    int b = InputHelper.readInt("Enter second vertex: ");
                    if (graph.addEdge(a, b)) System.out.println("Added edge " + a + " - " + b + ".");
                    break;
                }
                case 3:
                    graph.display();
                    break;
                case 4: {
                    if (graph.getVertexCount() == 0) {
                        System.out.println("Graph is empty.");
                        break;
                    }
                    int v = InputHelper.readInt("Enter vertex to search: ");
                    graph.searchVertex(v);
                    break;
                }
                case 5: {
                    int start = readStartVertex();
                    if (start == -1) break;
                    TraversalResult r = graph.bfs(start);
                    printTraversal("BFS", r, start, "Max queue size");
                    ResultLog.add("Graph Traversal", "BFS", r.steps, r.nanos,
                            "start=" + start + ", V=" + graph.getVertexCount() + ", E=" + graph.getEdgeCount());
                    break;
                }
                case 6: {
                    int start = readStartVertex();
                    if (start == -1) break;
                    TraversalResult r = graph.dfs(start);
                    printTraversal("DFS", r, start, "Max recursion depth");
                    ResultLog.add("Graph Traversal", "DFS", r.steps, r.nanos,
                            "start=" + start + ", V=" + graph.getVertexCount() + ", E=" + graph.getEdgeCount());
                    break;
                }
                case 7:
                    graph = Graph.createSample();
                    System.out.println("Sample graph loaded (7 vertices, 7 edges).");
                    graph.display();
                    break;
                case 8:
                    back = true;
                    break;
            }
        }
    }

    /** Asks for a start vertex; returns -1 if the graph is empty or the vertex is invalid. */
    private static int readStartVertex() {
        if (graph.getVertexCount() == 0) {
            System.out.println("Graph is empty. Add vertices first or load the sample graph.");
            return -1;
        }
        int start = InputHelper.readInt("Enter start vertex: ");
        if (!graph.hasVertex(start)) {
            System.out.println("Vertex " + start + " does not exist (valid range: 0 to "
                    + (graph.getVertexCount() - 1) + ").");
            return -1;
        }
        return start;
    }

    private static void printTraversal(String name, TraversalResult r, int start, String peakLabel) {
        System.out.println(name + " from vertex " + start + ": " + r.order);
        System.out.println("   Vertices visited: " + r.visitedCount + " | Steps: " + r.steps
                + " | " + peakLabel + ": " + r.peakStorage + " | Time: " + r.nanos + " ns");
    }

    private static void searchMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-------------- SEARCHING OPERATIONS --------------");
            System.out.println("Current data set size: " + searchData.length);
            System.out.println("1. Load Data from Array Component");
            System.out.println("2. Generate Random Data");
            System.out.println("3. Display Data");
            System.out.println("4. Linear Search");
            System.out.println("5. Binary Search");
            System.out.println("6. Compare Linear vs Binary");
            System.out.println("7. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    if (array == null || array.isEmpty()) {
                        System.out.println("Array component is empty. Add values in Array Operations first.");
                    } else {
                        searchData = array.toArray();
                        System.out.println("Loaded " + searchData.length + " values from the array.");
                    }
                    break;
                case 2: {
                    int n = InputHelper.readIntInRange("How many random numbers (1-10000)? ", 1, 10000);
                    searchData = SearchOperations.generateRandom(n);
                    System.out.println("Generated " + n + " random numbers (1 to 100000).");
                    break;
                }
                case 3:
                    displayData();
                    break;
                case 4: {
                    if (!hasSearchData()) break;
                    int target = InputHelper.readInt("Enter value to search: ");
                    SearchResult r = SearchOperations.linearSearch(searchData, target);
                    printSearchResult("Linear Search", r, target);
                    ResultLog.add("Search", "Linear Search", r.steps, r.nanos,
                            "target=" + target + ", n=" + searchData.length + (r.isFound() ? ", found" : ", not found"));
                    break;
                }
                case 5: {
                    if (!hasSearchData()) break;
                    int target = InputHelper.readInt("Enter value to search: ");
                    System.out.println("(Binary search needs sorted data, so a sorted copy is used.)");
                    int[] sorted = SearchOperations.insertionSort(searchData);
                    SearchResult r = SearchOperations.binarySearch(sorted, target);
                    printSearchResult("Binary Search", r, target);
                    ResultLog.add("Search", "Binary Search", r.steps, r.nanos,
                            "target=" + target + ", n=" + searchData.length + (r.isFound() ? ", found" : ", not found"));
                    break;
                }
                case 6: {
                    if (!hasSearchData()) break;
                    int target = InputHelper.readInt("Enter value to search: ");
                    int[] sorted = SearchOperations.insertionSort(searchData);
                    SearchResult lin = SearchOperations.linearSearch(searchData, target);
                    SearchResult bin = SearchOperations.binarySearch(sorted, target);

                    System.out.println("\n=============================================");
                    System.out.println("SEARCH COMPARISON (target = " + target + ", n = " + searchData.length + ")");
                    System.out.println("=============================================");
                    System.out.printf("%-18s %-10s %-12s %s%n", "Algorithm", "Steps", "Time (ns)", "Result");
                    System.out.printf("%-18s %-10d %-12d %s%n", "Linear Search", lin.steps, lin.nanos,
                            lin.isFound() ? "found" : "not found");
                    System.out.printf("%-18s %-10d %-12d %s%n", "Binary Search", bin.steps, bin.nanos,
                            bin.isFound() ? "found" : "not found");
                    System.out.println("=============================================");

                    ResultLog.add("Search", "Linear Search", lin.steps, lin.nanos,
                            "target=" + target + ", n=" + searchData.length + (lin.isFound() ? ", found" : ", not found"));
                    ResultLog.add("Search", "Binary Search", bin.steps, bin.nanos,
                            "target=" + target + ", n=" + searchData.length + (bin.isFound() ? ", found" : ", not found"));
                    break;
                }
                case 7:
                    back = true;
                    break;
            }
        }
    }

    /** Returns true if the search data set has values; otherwise prints a message. */
    private static boolean hasSearchData() {
        if (searchData.length == 0) {
            System.out.println("No data loaded. Use option 1 or 2 first.");
            return false;
        }
        return true;
    }

    /** Prints the search data (only the first 50 values if it is large). */
    private static void displayData() {
        if (!hasSearchData()) return;
        int limit = Math.min(searchData.length, 50);
        System.out.print("Data (" + searchData.length + " values): ");
        for (int i = 0; i < limit; i++) {
            System.out.print(searchData[i] + (i < limit - 1 ? ", " : ""));
        }
        if (searchData.length > limit) {
            System.out.print(" ... (" + (searchData.length - limit) + " more)");
        }
        System.out.println();
    }

    private static void printSearchResult(String algorithm, SearchResult r, int target) {
        if (r.isFound()) {
            System.out.println(algorithm + ": " + target + " found at index " + r.index + ".");
        } else {
            System.out.println(algorithm + ": " + target + " not found.");
        }
        System.out.println("   Steps: " + r.steps + " | Time: " + r.nanos + " ns");
    }

    private static void linkedListMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Delete at Position");
            System.out.println("6. Search");
            System.out.println("7. Display List");
            System.out.println("8. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 8);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value: ");
                    linkedList.insertAtHead(value);
                    System.out.println("Inserted " + value + " at the head.");
                    break;
                }
                case 2: {
                    int value = InputHelper.readInt("Enter value: ");
                    linkedList.insertAtTail(value);
                    System.out.println("Inserted " + value + " at the tail.");
                    break;
                }
                case 3: {
                    int position = InputHelper.readInt("Enter position: ");
                    int value = InputHelper.readInt("Enter value: ");
                    if (linkedList.insertAtPosition(position, value))
                        System.out.println("Inserted " + value + " at position " + position + ".");
                    break;
                }
                case 4: {
                    int value = InputHelper.readInt("Enter value to delete: ");
                    if (linkedList.deleteByValue(value)) System.out.println("Deleted " + value + ".");
                    break;
                }
                case 5: {
                    int position = InputHelper.readInt("Enter position to delete: ");
                    if (linkedList.deleteAtPosition(position))
                        System.out.println("Deleted node at position " + position + ".");
                    break;
                }
                case 6: {
                    int value = InputHelper.readInt("Enter value to search: ");
                    int position = linkedList.search(value);
                    if (position == -1) System.out.println(value + " not found.");
                    else System.out.println(value + " found at position " + position + ".");
                    break;
                }
                case 7:
                    linkedList.display();
                    break;
                case 8:
                    back = true;
                    break;
            }
        }
    }

    private static void stackMenu() {
        if (stack == null) {
            int capacity = InputHelper.readIntInRange("Enter stack capacity (1-100): ", 1, 100);
            stack = new MyStack(capacity);
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Stack");
            System.out.println("5. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to push: ");
                    if (stack.push(value)) System.out.println("Pushed " + value + ".");
                    break;
                }
                case 2: {
                    Integer value = stack.pop();
                    if (value != null) System.out.println("Popped " + value + ".");
                    break;
                }
                case 3: {
                    Integer value = stack.peek();
                    if (value != null) System.out.println("Top element: " + value);
                    break;
                }
                case 4:
                    stack.display();
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }

    private static void queueMenu() {
        if (queue == null) {
            int capacity = InputHelper.readIntInRange("Enter queue capacity (1-100): ", 1, 100);
            queue = new MyQueue(capacity);
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display Queue");
            System.out.println("5. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value to enqueue: ");
                    if (queue.enqueue(value)) System.out.println("Enqueued " + value + ".");
                    break;
                }
                case 2: {
                    Integer value = queue.dequeue();
                    if (value != null) System.out.println("Dequeued " + value + ".");
                    break;
                }
                case 3: {
                    Integer value = queue.peek();
                    if (value != null) System.out.println("Front element: " + value);
                    break;
                }
                case 4:
                    queue.display();
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }

    private static void arrayMenu() {
        if (array == null) {
            int capacity = InputHelper.readIntInRange("Enter array capacity (1-100): ", 1, 100);
            array = new ArrayOperations(capacity);
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Index");
            System.out.println("3. Delete at Index");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search (Linear)");
            System.out.println("6. Display Array");
            System.out.println("7. Return to Main Menu");

            int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt("Enter value: ");
                    if (array.insertAtEnd(value)) System.out.println("Inserted " + value + ".");
                    break;
                }
                case 2: {
                    int index = InputHelper.readInt("Enter index: ");
                    int value = InputHelper.readInt("Enter value: ");
                    if (array.insertAtIndex(index, value)) System.out.println("Inserted " + value + " at index " + index + ".");
                    break;
                }
                case 3: {
                    int index = InputHelper.readInt("Enter index to delete: ");
                    if (array.deleteAtIndex(index)) System.out.println("Deleted element at index " + index + ".");
                    break;
                }
                case 4: {
                    int value = InputHelper.readInt("Enter value to delete: ");
                    if (array.deleteByValue(value)) System.out.println("Deleted " + value + ".");
                    break;
                }
                case 5: {
                    int value = InputHelper.readInt("Enter value to search: ");
                    int index = array.linearSearch(value);
                    if (index == -1) System.out.println(value + " not found.");
                    else System.out.println(value + " found at index " + index + ".");
                    break;
                }
                case 6:
                    array.display();
                    break;
                case 7:
                    back = true;
                    break;
            }
        }
    }
}