package fhv.at.mwi.graph_structure;

import java.util.List;

public class AdjacencyList<E> extends AdjacencyStructure<E> {


    @Override
    public List print() {
        return null;
    }

    @Override
    public boolean addNode(Node n) {
        return false;
    }

    @Override
    public boolean removeNode(Node n) {
        return false;
    }

    @Override
    public void connectNodes(Node n1, Node n2, E weight) {

    }

    @Override
    public void connectNodes(Node n1, Node n2) {

    }

    @Override
    public void doubleConnectNodes(Node n1, Node n2, E weight) {

    }

    @Override
    public void doubleConnectNodes(Node n1, Node n2) {

    }

    @Override
    public boolean nodesConnected(Node n1, Node n2) {
        return false;
    }

    @Override
    public E getWeight(Node n1, Node n2) {
        return null;
    }

    @Override
    public void setWeight(Node n1, Node n2, E weight) {

    }

    @Override
    public int getNeighbourCount(Node n1) {
        return 0;
    }
}
