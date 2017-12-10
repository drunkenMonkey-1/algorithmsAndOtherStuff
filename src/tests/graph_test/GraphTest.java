package tests.graph_test;

import fhv.at.mwi.graph_algorithms.BellmanFordAlgorithm;
import fhv.at.mwi.graph_structure.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class GraphTest {


    @Test
    public void testPathFinder(){
        Graph<LongOperator> testGraph = new Graph<>(StructType.LIST, new LongOperator(1L, EOperator.MULTIPLY));
        Vertex vertices[] = {
                new Vertex<>("a"),      // 0
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


        BellmanFordAlgorithm bfa = new BellmanFordAlgorithm(testGraph.getAdStruct());
        bfa.operate();

        Iterator gIt = testGraph.print().iterator();
        while(gIt.hasNext()){
            System.out.printf("%10s", gIt.next());
        }

    }

}