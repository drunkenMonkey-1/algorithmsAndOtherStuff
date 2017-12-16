package tests.graph_test;

import fhv.at.mwi.graph_algorithms.FindPathsAlgorithm;
import fhv.at.mwi.graph_structure.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

class GraphTest {

    @Test
    public void testPathFinder(){

        /* Graph with edge weights that indicate how to apply costs by an mathematical operator (+,-,*,/)  */
        Graph<LongOperator> testGraph = new Graph<>(StructType.LIST, new LongOperator(1L, EOperator.MULTIPLY));
        /* Nodes in the graph. A has an initial value of 33 */
        Vertex vertices[] = {
                new Vertex<>("a", 33),      // 0
                new Vertex<>("b"),      // 1
                new Vertex<>("c"),      // 2
                new Vertex<>("d"),      // 3
                new Vertex<>("e"),      // 4
                new Vertex<>("f"),      // 5
                new Vertex<>("g"),      // 6
                new Vertex<>("h"),      // 7
                new Vertex<>("i"),      // 8
                new Vertex<>("j"),      // 9
                new Vertex<>("k"),      // 10
                new Vertex<>("l")       // 11
        };


        for (int i = 0; i < vertices.length; i++) {
            try {
                testGraph.add(vertices[i]);
            } catch (VertexExistenceException e) {
                e.printStackTrace();
            }
        }

        /* Some nodes are connected with the corresponding weight -> undirected graph */
        testGraph.doubleConnect(vertices[0], vertices[1], new LongOperator(19L, EOperator.SUBTRACT));
        testGraph.doubleConnect(vertices[0], vertices[3], new LongOperator(5L, EOperator.MULTIPLY));
        testGraph.doubleConnect(vertices[0], vertices[5], new LongOperator(42L, EOperator.ADD));
        testGraph.doubleConnect(vertices[1], vertices[2], new LongOperator(171L, EOperator.ADD));
        testGraph.doubleConnect(vertices[1], vertices[3], new LongOperator(121L, EOperator.ADD));
        testGraph.doubleConnect(vertices[2], vertices[4], new LongOperator(5L, EOperator.DIVIDE));
        testGraph.doubleConnect(vertices[2], vertices[9], new LongOperator(3L, EOperator.MULTIPLY));
        testGraph.doubleConnect(vertices[3], vertices[4], new LongOperator(4L, EOperator.MULTIPLY));
        testGraph.doubleConnect(vertices[3], vertices[5], new LongOperator(3L, EOperator.DIVIDE));
        testGraph.doubleConnect(vertices[4], vertices[8], new LongOperator(21L, EOperator.SUBTRACT));
        testGraph.doubleConnect(vertices[5], vertices[7], new LongOperator(17L, EOperator.SUBTRACT));
        testGraph.doubleConnect(vertices[6], vertices[8], new LongOperator(8L, EOperator.DIVIDE));
        testGraph.doubleConnect(vertices[6], vertices[11], new LongOperator(3L, EOperator.DIVIDE));
        testGraph.doubleConnect(vertices[6], vertices[9], new LongOperator(12L, EOperator.MULTIPLY));
        testGraph.doubleConnect(vertices[7], vertices[8], new LongOperator(24L, EOperator.MULTIPLY));
        testGraph.doubleConnect(vertices[7], vertices[10], new LongOperator(285L, EOperator.ADD));
        testGraph.doubleConnect(vertices[8], vertices[10], new LongOperator(265L, EOperator.SUBTRACT));
        testGraph.doubleConnect(vertices[8], vertices[11], new LongOperator(317L, EOperator.ADD));
        testGraph.doubleConnect(vertices[9], vertices[11], new LongOperator(9L, EOperator.SUBTRACT));
        testGraph.doubleConnect(vertices[10], vertices[11], new LongOperator(3L, EOperator.MULTIPLY));

        /* Find all possible paths from a start to an end node and return a list with paths */
        FindPathsAlgorithm fpa = new FindPathsAlgorithm(testGraph.getAdStruct(), vertices[0], vertices[11]);
        List<List<Edge>> lv = fpa.findAll();
        //List<List<Vertex>> lv = fpa.operate();

        /* Output all calculated paths */
        /* a is the start node and l the end node*/
        /* As the task requires the following paths need to be found:
         *  - shortest path:
         *    assumption that negative end costs are not allowed the following paths is returned:
         *    [a | 33]  [b | 14]  [c | 185]  [e | 37]  [i | 16]  [g | 2]  [l | 0]
         *  - longest path:
         *    [a | b - [- 19]]  [b | c - [+ 171]]  [c | j - [* 3]]  [j | g - [* 12]]  [g | l - [/ 3]]  [l | i - [+ 317]]  [i | h - [* 24]]  [h | f - [- 17]]  [f | a - [+ 42]]  [a | d - [* 5]]  [d | e - [* 4]]  [e | i - [- 21]]  [i | k - [- 265]]  [k | l - [* 3]]
         *  - path with a cost of exactly 999:
         *    [a | 33]  [b | 14]  [d | 135]  [f | 45]  [h | 28]  [i | 672]  [g | 84]  [j | 1008]  [l | 999]
         *     */
        for (List<Edge> innerList : lv) {
            for (Edge edge : innerList) {
                System.out.printf(" [%s] ", edge.toString());
            }
            System.out.printf("\n");
        }

        /* Output the graph structure */
        Iterator gIt = testGraph.print().iterator();
        while(gIt.hasNext()){
            System.out.printf("%10s", gIt.next());
        }

    }

}