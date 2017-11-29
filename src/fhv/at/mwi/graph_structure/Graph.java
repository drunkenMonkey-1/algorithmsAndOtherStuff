package fhv.at.mwi.graph_structure;

import java.util.List;

public class Graph {

    private AdjacencyStructure<Integer> _adStruct;

    public Graph(StructType st){
        switch (st){
            case LIST:
                _adStruct = new AdjacencyList<>();
                break;
            case MATRIX:
                _adStruct = new AdjacencyMatrix<>();
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
    public void add(Node n){
        _adStruct.addNode(n);
    }

    public void remove(Node n){
        _adStruct.removeNode(n);
    }

    public void connect(Node n1, Node n2, int weight){
        _adStruct.connectNodes(n1, n2, weight);
    }
    public void connect(Node n1, Node n2){
        _adStruct.connectNodes(n1, n2);
    }

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
