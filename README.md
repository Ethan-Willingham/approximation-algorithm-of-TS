# Approximation Algorithm for the Traveling Salesman Problem

A randomized greedy heuristic that finds a short Hamiltonian tour through a weighted graph, an approximation to the Traveling Salesman Problem.

Written in Java as "Deliverable E" on top of a course-provided graph framework (`Prog340`). The framework classes (`Prog340`, `Graph`, `Node`, `Edge`) were supplied by the instructor; the approximation itself is in [`DelivE.java`](DelivE.java).

## How it works

TSP is NP-hard, so an exact shortest tour is impractical at scale. `DelivE` approximates one instead:

1. It runs 25,000 randomized passes over the graph.
2. Each pass sorts every node's outgoing edges by distance and walks the graph greedily, nearest edge first, with a random tie-break so each pass explores a different route.
3. It keeps the shortest complete tour seen across all passes.

More passes means a better chance of landing near the optimal tour. The best tour and its total distance are written to a `<input>_out.txt` file next to the input graph.

## Running it

A small Swing app:

1. Compile (Java 8+): `javac *.java`
2. Run the driver: `java Prog340`
3. In the window, read a graph input file, then run Deliverable E.

## Files

| File | Role |
|------|------|
| `DelivE.java` | The TSP approximation. My work. |
| `Prog340.java` | Swing driver and menu. Instructor-provided framework. |
| `Graph.java`, `Node.java`, `Edge.java` | Graph data structures. Framework. |

A course project, kept as a record of the algorithm.
