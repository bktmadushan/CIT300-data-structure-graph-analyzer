# Data Structure & Graph Performance Analyzer

**CIT300 — Data Structures and Algorithms**
**Graded Practical Assignment 2 (Week 12)**
**Sri Lanka Technological Campus (SLTC)**

---

## Group Members

- **Member 1:** B.K.T Madushan (`23DA2-0038`)
- **Member 2:** W.N.A.L Pamodi (`23DA2-0394`)
- **Member 3:** R.M.D.N.L Dissanayaka (`23DA2-0205`)
- **Member 4:** K.D.C.R Jayarathne (`23DA2-0324`)

---

## Overview

A Java console application that demonstrates the practical implementation of fundamental data structures, searching algorithms, graph traversal algorithms, and algorithm performance analysis. The system allows users to perform operations on arrays, stacks, queues, linked lists, and graphs while recording relevant performance information, including step counts and execution times.

This project was collaboratively developed by the four group members listed above.

---

## Team Responsibilities and Contributions

Each group member was responsible for specific components of the project. The individual implementations were integrated into one Java application.

### Member 1 — B.K.T Madushan (`23DA2-0038`)

**Main responsibility:** Array Operations and Searching Algorithms

- Implemented the array component, including insertion, deletion, searching, and displaying elements.
- Developed the `InputHelper` class to handle and validate user input.
- Implemented linear search and binary search algorithms.
- Implemented insertion sort to prepare a copy of the array for binary search.
- Developed the `SearchResult` class to store search results, including the found index, step count, execution time, and search status.
- Contributed to the `ResultLog` class to record operation and performance information.
- Demonstrated the array operations, searching algorithms, and searching performance comparison.

**Main files:** `InputHelper.java`, `ArrayOperations.java`, `SearchOperations.java`, `SearchResult.java`, `ResultLog.java`

### Member 2 — W.N.A.L Pamodi (`23DA2-0394`)

**Main responsibility:** Stack and Queue Implementation

- Implemented the stack using an array.
- Developed the push, pop, peek, and display operations.
- Implemented stack overflow and underflow handling.
- Implemented the queue using a circular array.
- Developed the enqueue, dequeue, peek, and display operations.
- Added checks for full and empty queue conditions.
- Demonstrated the Last In, First Out (LIFO) and First In, First Out (FIFO) principles.

**Main files:** `MyStack.java`, `MyQueue.java`

### Member 3 — R.M.D.N.L Dissanayaka (`23DA2-0205`)

**Main responsibility:** Linked List and Graph Implementation

- Implemented the `Node` class for storing linked list data and references.
- Developed the linked list operations, including insertion, deletion, searching, and displaying elements.
- Implemented the graph using an adjacency list.
- Developed operations to add vertices and edges and display graph connections.
- Implemented Breadth-First Search (BFS) for graph traversal.
- Implemented Depth-First Search (DFS) using recursion.
- Contributed to demonstrating graph traversal performance and the time complexity of graph algorithms.

**Main files:** `Node.java`, `MyLinkedList.java`, `Graph.java`

### Member 4 — K.D.C.R Jayarathne (`23DA2-0324`)

**Main responsibility:** System Integration, Performance Analysis, and Testing

- Developed and integrated the main menu and submenus in `Main.java`.
- Integrated the different data structures and algorithms into one application.
- Implemented the `PerformanceAnalyzer` class for comparing linear search and binary search.
- Added performance reporting for dataset size, target value, search results, steps, and execution time.
- Implemented the display of time complexity information for the main data structures and algorithms.
- Tested the integrated application and checked the functionality of the different components.
- Demonstrated the main menu, performance analysis, result logging, and overall system integration.

**Main files:** `Main.java`, `PerformanceAnalyzer.java`

> **Shared component:** `ResultLog.java` is used to store relevant search and graph traversal results for display through the main menu.

---

## Technologies Used

- **Programming language:** Java
- **Java version:** JDK 8 or higher
- **IDE:** Eclipse
- **Version control:** Git and GitHub
- **Application type:** Console-based Java application
- **Libraries:** Java standard library

The main data structure implementations and searching algorithms are developed as separate Java classes. Standard Java collections are also used where appropriate, including for graph representation and traversal support.

---

## Main System Features

The application provides the following main menu options.

### 1. Array Operations
- Insert at the end or at a specific position
- Delete by position or by value
- Linear search
- Display the array

### 2. Stack Operations
- Push, pop, peek, and display
- Handles full (overflow) and empty (underflow) stack conditions

### 3. Queue Operations
- Enqueue, dequeue, peek, and display using a circular array
- Handles full and empty queue conditions

### 4. Linked List Operations
- Insert at the head, at the tail, or at a position
- Delete by value or by position
- Search and display

### 5. Searching Algorithms
- Linear search on the current array
- Binary search on a sorted copy of the current array

### 6. Graph Operations
- Add vertices and edges
- Display the graph
- Perform BFS and DFS traversal

### 7. Performance Comparison
- Compare linear search and binary search using the current array or randomly generated data
- Display time complexity information

### 8. Display All Results
- Display the search and graph traversal results recorded during the session

### 9. Exit
- Exit the application

### Additional Features

- Input validation for menu selections and data entry.
- Error handling for invalid positions and unsupported operations.
- Checks for full and empty stacks and queues.
- Separate classes for data structures and algorithms.
- Performance information based on step counts and execution time.
- Graph traversal using BFS and DFS.
- A shared result log for reviewing supported operations.
- A menu-driven interface that allows users to interact with each component.

---

## Project Structure

The project is organized into a Java package named `analyzer`.

```text
DSGraphAnalyzer/
└── src/
    └── analyzer/
        ├── Main.java
        ├── InputHelper.java
        ├── ArrayOperations.java
        ├── MyStack.java
        ├── MyQueue.java
        ├── Node.java
        ├── MyLinkedList.java
        ├── SearchOperations.java
        ├── SearchResult.java
        ├── Graph.java
        ├── PerformanceAnalyzer.java
        └── ResultLog.java
```

### Description of Main Classes

| Class                      | Description                                                                         |
| -------------------------- | ----------------------------------------------------------------------------------- |
| `Main.java`                | Provides the main menu, submenus, and integration of the components                 |
| `InputHelper.java`         | Reads and validates user input                                                      |
| `ArrayOperations.java`     | Implements array insertion, deletion, searching, and display                        |
| `MyStack.java`             | Implements stack operations using an array                                          |
| `MyQueue.java`             | Implements queue operations using a circular array                                  |
| `Node.java`                | Represents a node in the linked list                                                |
| `MyLinkedList.java`        | Implements linked list operations                                                   |
| `SearchOperations.java`    | Implements linear search, binary search, insertion sort, and random data generation |
| `SearchResult.java`        | Stores the result of a searching operation                                          |
| `Graph.java`               | Implements graph representation, vertex and edge operations, BFS, and DFS           |
| `PerformanceAnalyzer.java` | Compares searching algorithms and displays complexity information                   |
| `ResultLog.java`           | Stores recorded operation and performance results                                   |

---

## Algorithm Complexity Summary

The following table summarizes the general time complexities of the main operations implemented in the project.

| Data Structure / Algorithm | Operation                             | Time Complexity                               |
| -------------------------- | ------------------------------------- | --------------------------------------------- |
| Array                      | Access by index                       | O(1)                                          |
| Array                      | Insert at end when space is available | O(1)                                          |
| Array                      | Insert or delete at a position        | O(n)                                          |
| Array                      | Linear search                         | O(n)                                          |
| Stack                      | Push, pop, and peek                   | O(1)                                          |
| Circular Queue             | Enqueue, dequeue, and peek            | O(1)                                          |
| Linked List                | Insert at head                        | O(1)                                          |
| Linked List                | Search for a value                    | O(n)                                          |
| Linked List                | Insert at tail or position            | O(n)                                          |
| Linked List                | Delete by value or position           | O(n)                                          |
| Searching                  | Linear search                         | O(n)                                          |
| Searching                  | Binary search on sorted data          | O(log n)                                      |
| Sorting                    | Insertion sort                        | O(n²) in the worst case                       |
| Graph                      | Add vertex                            | O(1) average                                  |
| Graph                      | Add edge with duplicate checking      | Depends on the number of neighboring vertices |
| Graph                      | BFS traversal                         | O(V + E)                                      |
| Graph                      | DFS traversal                         | O(V + E)                                      |

Here, **n** is the number of elements, **V** is the number of graph vertices, and **E** is the number of graph edges.

### Performance Analysis Notes

- **Linear search:** Examines elements one by one until the target is found or all elements have been checked.
- **Binary search:** Repeatedly reduces the search range by half. It requires sorted data.
- **Sorting before binary search:** The application sorts a copy of the current array before performing binary search. The sorting time is excluded from the reported binary-search execution time.
- **BFS and DFS:** Both have O(V + E) time complexity when using an adjacency list. However, they visit vertices in different orders and use different traversal strategies.
- **Step counts:** The reported count represents the steps or comparisons tracked by the relevant implementation. For graph traversal, it represents the number of vertices visited.
- **Execution time:** Measured in nanoseconds and can vary between runs because of system conditions and JVM execution.

---

## How to Run the Program

### Option 1 — Run using Eclipse

1. Clone or download the project repository from GitHub.
2. Open Eclipse.
3. Select **File → Import → General → Existing Projects into Workspace** (if the downloaded folder is an Eclipse project).
4. Select the project folder and complete the import.
5. Expand the `src` folder and open the `analyzer` package.
6. Open `Main.java`.
7. Right-click inside the editor and select **Run As → Java Application**.
8. Use the numbered menu displayed in the **Console** view.

> **Note:** If the downloaded project does not contain Eclipse project metadata, create a new Java project and place the `analyzer` package inside the `src` folder.

### Option 2 — Run using the Command Line

If the source files are located in `src/analyzer/`, open a terminal in the `src` directory and compile the Java files:

```bash
javac analyzer/*.java
```

Then run the application:

```bash
java analyzer.Main
```

> Make sure the Java Development Kit (JDK) is installed and available on the system path.

### Main Menu

The application displays a menu similar to the following:

```text
=============================================
      DATA STRUCTURE & GRAPH ANALYZER
=============================================
1. Array Operations
2. Stack Operations
3. Queue Operations
4. Linked List Operations
5. Searching Algorithms
6. Graph Operations
7. Performance Comparison
8. Display All Results
9. Exit
Enter your choice:
```

The exact wording may vary slightly depending on the current version of the source code.

---

## Suggested Demonstration Procedure

The following sequence can be used when demonstrating the application.

1. **Array Operations:** Insert several integers, display the array, and demonstrate insertion or deletion.
2. **Stack Operations:** Push multiple values, demonstrate peek, and pop an element.
3. **Queue Operations:** Enqueue several values, demonstrate peek, and dequeue an element.
4. **Linked List Operations:** Insert values, search for an element, and delete a value.
5. **Searching Algorithms:** Run linear search and binary search using a target value in the array.
6. **Graph Operations:** Add vertices and edges, display the graph, and run BFS and DFS from a selected starting vertex.
7. **Performance Comparison:** Compare linear search and binary search using the current array or randomly generated data.
8. **Display All Results:** View the search and graph traversal results recorded during the current session.

---

## GitHub Collaboration

Git and GitHub were used to manage the source code and support collaboration between group members.

The project was developed using component-based changes and commits. The following feature areas represent the main implementation responsibilities:

| Feature Area                | Main Components                                                |
| --------------------------- | -------------------------------------------------------------- |
| Array                       | `InputHelper.java`, `ArrayOperations.java`                     |
| Stack and Queue             | `MyStack.java`, `MyQueue.java`                                 |
| Linked List                 | `Node.java`, `MyLinkedList.java`                               |
| Searching                   | `SearchOperations.java`, `SearchResult.java`, `ResultLog.java` |
| Graph                       | `Graph.java`                                                   |
| Performance and Integration | `PerformanceAnalyzer.java`, `Main.java`                        |

The shared result log supports recording results from multiple components. The main application brings the implementations together through the menu-driven interface.

---

## Demonstration Video and Resources

**Google Drive link:** [Open the shared demonstration folder](https://drive.google.com/drive/folders/1nAusyKBVrtUl2beZsilHMDaVpmBz1_O4?usp=sharing)

The demonstration covers:

- Array operations and searching algorithms
- Stack and queue operations
- Linked list operations
- Graph creation and traversal using BFS and DFS
- Performance comparison and result logging
- System integration and testing

---

## Conclusion

The Data Structure & Graph Performance Analyzer demonstrates the practical implementation of fundamental data structures and algorithms using Java.

Through this project, the group explored how arrays, stacks, queues, linked lists, and graphs can be used to store and manage data. The project also demonstrates searching algorithms, graph traversal, time complexity, and basic performance measurement.

The application combines these components into one console-based system and provides an opportunity to understand their behavior through practical demonstrations.

The project also provided experience in object-oriented programming, modular software development, testing, version control, and collaborative development.

---

**Course:** CIT300 — Data Structures and Algorithms
**Institution:** Sri Lanka Technological Campus (SLTC)
**Project:** Data Structure & Graph Performance Analyzer