package fhv.at.mwi.graph_structure;

import java.util.List;

public class Graph {

    private AdjacencyStructure<Integer> _adStruct;
    private static final int _defaultWeight = 1;

    public Graph(StructType st){
        switch (st){
            case LIST:
                _adStruct = new AdjacencyList<>(_defaultWeight);
                break;
            case MATRIX:
                _adStruct = new AdjacencyMatrix<>(_defaultWeight);
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
    public void disconnectVertices(Vertex n1, Vertex n2) { _adStruct.disconnectVertices(n1, n2); }
    public void doubleDisconnectVertices(Vertex n1, Vertex n2) { _adStruct.doubleDisconnectVertices(n1, n2); }
    public void doubleConnect(Vertex n1, Vertex n2){
        _adStruct.doubleConnectVertices(n1, n2);
    }
    public List<Vertex> getNeighbours(Vertex n){ return _adStruct.getOutgoingVertices(n); }

    public AdjacencyStructure<Integer> getAdStruct() {
        return _adStruct;
    }



    public boolean checkForOpenEulerianPath(Vertex startVertex){
        int oddCount = check(startVertex);
        if(oddCount == 2){
            return true;
        }
        return false;
    }
    public boolean checkForClosedEulerianPath(Vertex startVertex){
        int oddCount = check(startVertex);
        if(oddCount == 0){
            return true;
        }
        return false;
    }

    private int check(Vertex startVertex){
        startVertex.setProperty(1);
        int oddVertices = 0;
        if((_adStruct.numOfOutgoingNeighbour(startVertex) % 2) != 0){
            oddVertices = 1;
        }
        for (Vertex vertex : _adStruct.getOutgoingVertices(startVertex)) {
            if(vertex.getProperty() != 1) {
                oddVertices += check(vertex);
            }
        }
        return oddVertices;
    }

    public void getEulerianPath(Vertex startVertex){

        for (Vertex vertex : _adStruct.getOutgoingVertices(startVertex)) {

        }
    }

}
