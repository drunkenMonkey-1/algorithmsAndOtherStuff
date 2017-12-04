package fhv.at.mwi.graph_structure;

import fhv.at.mwi.expression_parser.GraphReader;
import fhv.at.mwi.expression_parser.InvalidExpressionException;

import java.util.Iterator;
import java.util.List;

public class GraphMain {

    public static void main(String[] args) {

        try {
            GraphReader gr = new GraphReader("graph_in.txt");
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        }

        Graph testGraph = new Graph(StructType.LIST);
        Vertex vertices[] = {new Vertex<>("a"),
                new Vertex<>("b"),
                new Vertex<>("c"),
                new Vertex<>("d"),
                new Vertex<>("a")
        };


        for (int i = 0; i < vertices.length; i++) {
            try {
                testGraph.add(vertices[i]);
            } catch (VertexExistenceException e) {
                e.printStackTrace();
            }
        }

        testGraph.doubleConnect(vertices[0], vertices[1], 1);
        testGraph.doubleConnect(vertices[0], vertices[2], 1);
        testGraph.doubleConnect(vertices[1], vertices[2], 1);

        Iterator<Vertex> n = testGraph.getNeighbours(vertices[0]).iterator();
        while (n.hasNext()){
            System.out.println(n.next().toString());
        }

        Iterator gIt = testGraph.print().iterator();
        while(gIt.hasNext()){
            System.out.printf("%10s", gIt.next());
        }
    }
}
