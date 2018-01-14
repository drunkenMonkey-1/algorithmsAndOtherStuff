package tests.graph_test;

import fhv.at.mwi.graph_algorithms.FindPathsAlgorithm;
import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import fhv.at.mwi.graph_algorithms.PrimAlgorithm;
import fhv.at.mwi.graph_algorithms.SeriesConnectionAlgorithm;
import fhv.at.mwi.graph_structure.*;

import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.List;

class SeriesConnectionAlgorithmTest {


    @Test
    void validSeriesCircuit() {

        Graph<LongOperator> testGraph = new Graph<>(StructType.LIST, new LongOperator<Long>(1L, EOperator.ADD ));

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

        testGraph.doubleConnect(vertices[0], vertices[1]);
        testGraph.doubleConnect(vertices[0], vertices[2]);
        testGraph.doubleConnect(vertices[0], vertices[3]);
        testGraph.doubleConnect(vertices[0], vertices[6]);
        testGraph.doubleConnect(vertices[1], vertices[4]);
        testGraph.doubleConnect(vertices[1], vertices[5]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[3], vertices[5]);
        testGraph.doubleConnect(vertices[4], vertices[6]);
        testGraph.doubleConnect(vertices[5], vertices[6]);

        /* Calculate a valid series circuit from a given graph. Returns an adjacency structure with the
         * circuit in it, and all nodes connected */
        SeriesConnectionAlgorithm sca = new SeriesConnectionAlgorithm(testGraph.getAdStruct());

        /* Output the series circuit from sca */
        try {
            AdjacencyStructure ads = sca.operate();
            /* Output the adjacency structure */
            Iterator gIt = ads.print().iterator();
            while(gIt.hasNext()){
                System.out.printf("%10s", gIt.next());
            }

        } catch (GraphRequirementException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void validCircuitFindAllPossiblePathsTest(){
        Graph<LongOperator> testGraph = new Graph<>(StructType.LIST, new LongOperator<Long>(1L, EOperator.ADD ));

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

        testGraph.doubleConnect(vertices[0], vertices[1]);
        testGraph.doubleConnect(vertices[0], vertices[2]);
        testGraph.doubleConnect(vertices[0], vertices[3]);
        testGraph.doubleConnect(vertices[0], vertices[6]);
        testGraph.doubleConnect(vertices[1], vertices[4]);
        testGraph.doubleConnect(vertices[1], vertices[5]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[3], vertices[5]);
        testGraph.doubleConnect(vertices[4], vertices[6]);
        testGraph.doubleConnect(vertices[5], vertices[6]);

        /* To find the longest possible path a path finding algorithm is applied, that return all possible paths in
        * a graph from a start to an end node including cycles like visiting a vertex twice. In this case start and
        * end are the same, so a cycle is found, that includes all vertices. */
        FindPathsAlgorithm fpa = new FindPathsAlgorithm(testGraph.getAdStruct(), vertices[0], vertices[0]);
        List<List<Edge>> lv = fpa.findAll();
        for(List<Edge> l : lv){
            /* Only output paths with a length greater than 7, since you only want to know the longest path(s) */
            if(l.size()>= 7) {
                System.out.println(l);
            }
        }
    }

    @Test
    public void validCircuitPrimTest(){

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

        testGraph.doubleConnect(vertices[0], vertices[1]);
        testGraph.doubleConnect(vertices[0], vertices[2]);
        testGraph.doubleConnect(vertices[0], vertices[3]);
        testGraph.doubleConnect(vertices[0], vertices[6]);
        testGraph.doubleConnect(vertices[1], vertices[4]);
        testGraph.doubleConnect(vertices[1], vertices[5]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[2], vertices[4]);
        testGraph.doubleConnect(vertices[3], vertices[5]);
        testGraph.doubleConnect(vertices[4], vertices[6]);
        testGraph.doubleConnect(vertices[5], vertices[6]);


        PrimAlgorithm pa = new PrimAlgorithm(testGraph.getAdStruct(), vertices[0]);
        Iterator igr = null;
        try {
            igr = pa.operate().print().iterator();
        } catch (GraphRequirementException e) {
            e.printStackTrace();
        }
        while(igr.hasNext()){
            System.out.printf("%10s", igr.next());
        }
    }



    @Test
    public void validSeriesCircuit2(){
        Graph testGraph = new Graph<>(StructType.LIST, -1);
        Vertex vertices[] = {
                new Vertex<>("a"),      // 0
                new Vertex<>("b"),      // 1
                new Vertex<>("c"),      // 2
                new Vertex<>("d"),      // 3
                new Vertex<>("e"),      // 4
        };
        for (int i = 0; i < vertices.length; i++) {
            try {
                testGraph.add(vertices[i]);
            } catch (VertexExistenceException e) {
                e.printStackTrace();
            }
        }

        testGraph.doubleConnect(vertices[0], vertices[1]);
        testGraph.doubleConnect(vertices[0], vertices[3]);
        testGraph.doubleConnect(vertices[1], vertices[2]);
        testGraph.doubleConnect(vertices[2], vertices[4]);

        SeriesConnectionAlgorithm sca = new SeriesConnectionAlgorithm(testGraph.getAdStruct());

        try {
            AdjacencyStructure ads = sca.operate();
            /* Output the adjacency structure */
            Iterator gIt = ads.print().iterator();
            while(gIt.hasNext()){
                System.out.printf("%10s", gIt.next());
            }

        } catch (GraphRequirementException e) {
            e.printStackTrace();
        }

    }


}