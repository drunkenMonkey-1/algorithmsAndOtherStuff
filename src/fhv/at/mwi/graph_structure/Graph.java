package fhv.at.mwi.graph_structure;

import java.util.List;

public class Graph {

    private AdjacencyStructure<Integer> _adStruct;

    public Graph(StructType st){
        switch (st){
            case LIST:
                _adStruct = new AdjacencyList<>(Integer.MAX_VALUE);
                break;
            case MATRIX:
                _adStruct = new AdjacencyMatrix<>(Integer.MAX_VALUE);
                break;
            default:
                // exception
                break;
        }
    }


    public List print(){
        return _adStruct.print();
    }

    /**
     * Add a node to the graph
     * @param n
     */
    public void add(Vertex n) throws VertexExistenceException {
        if(!_adStruct.addVertex(n)){
            throw new VertexExistenceException("Vertex already existing");
        }
    }

    public void remove(Vertex n){
        _adStruct.removeVertex(n);
    }

    public void connect(Vertex n1, Vertex n2, int weight){
        _adStruct.connectVertices(n1, n2, weight);
    }
    public void connect(Vertex n1, Vertex n2){
        _adStruct.connectVertices(n1, n2);
    }
    public void doubleConnect(Vertex n1, Vertex n2, int weight){
        _adStruct.doubleConnectVertices(n1, n2, weight);
    }
    public void doubleConnect(Vertex n1, Vertex n2){
        _adStruct.doubleConnectVertices(n1, n2);
    }
    public List<Vertex> getNeighbours(Vertex n){ return _adStruct.getOutgoingVertices(n); }

    public AdjacencyStructure<Integer> getAdStruct() {
        return _adStruct;
    }



    public boolean checkForEulerianPath(){
        return false;
    }

    public List getEulerianPath(){
        return null;
    }

}
