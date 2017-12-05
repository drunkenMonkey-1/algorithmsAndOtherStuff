package fhv.at.mwi.graph_structure;

import java.io.*;
import java.util.List;

/**
 * Structure to manage nodes and edges of a graph
 * @param <E>
 */
public abstract class AdjacencyStructure<E extends Comparable>{

    protected AdjacencyStructure() {
    }

    public abstract List print();

    public abstract boolean addVertex(Vertex n);
    public abstract boolean removeVertex(Vertex n);

    public abstract boolean connectVertices(Vertex n1, Vertex n2, E weight);
    public abstract void connectVertices(Vertex n1, Vertex n2);
    public abstract void disconnectVertices(Vertex n1, Vertex n2);
    public abstract void doubleDisconnectVertices(Vertex n1, Vertex n2);
    public abstract void doubleConnectVertices(Vertex n1, Vertex n2, E weight);
    public abstract void doubleConnectVertices(Vertex n1, Vertex n2);

    public abstract boolean verticesConnected(Vertex n1, Vertex n2);
    public abstract E getWeight(Vertex n1, Vertex n2);
    public abstract void setWeight(Vertex n1, Vertex n2, E weight);

    public abstract int numOfOutgoingNeighbour(Vertex n1);
    public abstract int numOfIngoingNeighbour(Vertex n1);
    public abstract List<Vertex> getOutgoingVertices(Vertex n);
    public abstract Vertex getNextOutgoingVertex(Vertex n);

}
