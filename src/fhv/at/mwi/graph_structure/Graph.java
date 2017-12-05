package fhv.at.mwi.graph_structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {

    private AdjacencyStructure<Integer> _adStruct;
    private List<Vertex> _startVertices = new LinkedList<>();
    private static final int _defaultWeight = 1;
    private Stack<Vertex> _rStack = new Stack<>();
    private List<Vertex> _path = new LinkedList<>();

    /**
     * Create a new graph
     * @param st The Struct type depends on how you use the graph. Can be StructType.MATRIX oder StructType.LIST
     */
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

    /**
     * Check if a graph contains a open eulerian path
     * @param startVertex Random start vertex, where the search is started
     * @return True if an open eulerian path is present
     */
    public boolean checkForOpenEulerianPath(Vertex startVertex){
        _startVertices.clear();
        int oddCount = check(startVertex);
        if(oddCount == 2){
            return true;
        }
        return false;
    }

    /**
     * Check if a graph contains a closed eulerian path
     * @param startVertex Random start vertex, where the search is started
     * @return True if an closed eulerian path is present
     */
    public boolean checkForClosedEulerianPath(Vertex startVertex){
        _startVertices.clear();
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
            _startVertices.add(startVertex);
            oddVertices = 1;
        }
        for (Vertex vertex : _adStruct.getOutgoingVertices(startVertex)) {
            if(vertex.getProperty() != 1) {
                oddVertices += check(vertex);
            }
        }
        return oddVertices;
    }

    /**
     * Get a possible eulerian path from a graph
     * @param startVertex A random vertex in the graph
     * @return A list of vertices whose order of vertices represents an eulerian path
     * @throws GraphMethodException If the graph is no eulerian path, an exception is thrown
     */
    public List<Vertex> getEulerianPath(Vertex startVertex) throws GraphMethodException {
        Vertex startV;
        /* Eulerian path -> take vertice with odd no. of outgoing neighbours */
        if(checkForOpenEulerianPath(startVertex)){
            startV = _startVertices.get(0);
        }
        /* Eulerian circuit -> take random vertex */
        else if(checkForClosedEulerianPath(startVertex)){
            startV = startVertex;
        } else {
            throw new GraphMethodException("No eulerian circle or path in this graph");
        }
        _rStack.clear();
        _path.clear();
        /* Recursive eulerian path search */
        searchPath(startV);
        /* Reconnect previously disconnected edges from the searchPath method */
        for(int i = 1; i < _path.size(); i++){
            doubleConnect(_path.get(i-1), _path.get(i));
        }
        return _path;
    }
    private void searchPath(Vertex start){
        /* If the node has no neighbours, it means, that he has been visited so many times, so that */
        /* there are no edges left to go through.  That means he is a part of the euler path. */
        /* Pop the next element from the stack and check if it has any other neighbour, that could be visited */
        if(_adStruct.numOfOutgoingNeighbour(start) == 0 && !_rStack.empty()){
            _path.add(start);
            searchPath(_rStack.pop());
        }
        /* If the vertex has 1 or more neighbours, visit a random neighbour  */
        /* and add the current vertex to the stack, to mark him so that all his other neighbours*/
        /* can be searched, when he is taken from the stack */
        else if(_adStruct.numOfOutgoingNeighbour(start) != 0){
            Vertex next = _adStruct.getNextOutgoingVertex(start);
            /* Mark the edge that was just visited as visited by disconnecting the edge */
            doubleDisconnectVertices(start, next);
            _rStack.push(start);
            searchPath(next);
        }
        /* If the a vertex has no neighbours and the Stack is empty, the method will abort here */
    }

}
