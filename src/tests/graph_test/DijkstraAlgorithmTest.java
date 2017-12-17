package tests.graph_test;

import fhv.at.mwi.graph_algorithms.DijkstraAlgorithm;
import fhv.at.mwi.graph_structure.Graph;
import fhv.at.mwi.graph_structure.StructType;
import fhv.at.mwi.graph_structure.Vertex;
import fhv.at.mwi.graph_structure.VertexExistenceException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {


    @Test
    void findPathDijkstra() {

        Graph<Long> testGraph = new Graph<>(StructType.LIST, -1L);
        Vertex vertices[] = {
                new Vertex<>("a"),      // 0
                new Vertex<>("b"),      // 1
                new Vertex<>("c"),      // 2
                new Vertex<>("d"),      // 3
                new Vertex<>("e"),      // 4
                new Vertex<>("f"),      // 5
                new Vertex<>("g"),      // 6
        };
        for (int i = 0; i < vertices.length; i++) {
            try {
                testGraph.add(vertices[i]);
            } catch (VertexExistenceException e) {
                e.printStackTrace();
            }
        }

        testGraph.doubleConnect(vertices[0], vertices[1], 15L);
        testGraph.doubleConnect(vertices[0], vertices[3], 5L);
        testGraph.doubleConnect(vertices[1], vertices[2], 30L);
        testGraph.doubleConnect(vertices[1], vertices[3],10L);
        testGraph.doubleConnect(vertices[1], vertices[5], 10L);
        testGraph.doubleConnect(vertices[1], vertices[6], 5L);
        testGraph.doubleConnect(vertices[2], vertices[5], 45L);
        testGraph.doubleConnect(vertices[2], vertices[6], 20L);
        testGraph.doubleConnect(vertices[3], vertices[4], 25L);
        testGraph.doubleConnect(vertices[4], vertices[6], 30L);

        DijkstraAlgorithm dja = new DijkstraAlgorithm(testGraph.getAdStruct(), vertices[0], vertices[2]);
        List<Vertex> path = dja.operate();

        System.out.println(path);
    }

}