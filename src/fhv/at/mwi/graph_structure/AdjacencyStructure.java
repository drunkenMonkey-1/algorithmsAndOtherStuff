package fhv.at.mwi.graph_structure;

import java.util.LinkedList;
import java.util.List;

/**
 * Structure to manage nodes and edges of a graph
 * @param <E>
 */
public abstract class AdjacencyStructure<E> {

    public abstract List print();

    public abstract boolean addNode(Node n);
    public abstract boolean removeNode(Node n);

    public abstract void connectNodes(Node n1, Node n2, E weight);
    public abstract void connectNodes(Node n1, Node n2);
    public abstract void doubleConnectNodes(Node n1, Node n2, E weight);
    public abstract void doubleConnectNodes(Node n1, Node n2);

    public abstract boolean nodesConnected(Node n1, Node n2);
    public abstract E getWeight(Node n1, Node n2);
    public abstract void setWeight(Node n1, Node n2, E weight);

    public abstract int getNeighbourCount(Node n1);

}
