package fhv.at.mwi.graph_structure;

import java.util.*;

public class AdjacencyList<E extends Comparable> extends AdjacencyStructure<E> {

    private HashMap<Vertex, Map<Vertex, E>> _adjList = new HashMap<>();
    private E _sentinel;

    public AdjacencyList(E sentinel){
        _sentinel = sentinel;
    }

    @Override
    public List print() {
        LinkedList<Object> _outList = new LinkedList<>();
        for (Map.Entry<Vertex, Map<Vertex, E>> entry : _adjList.entrySet()) {
            _outList.add(entry.getKey().getLabel() + " -> ");
            for(Map.Entry<Vertex, E> e : _adjList.get(entry.getKey()).entrySet()){
                _outList.add(" [" + e.getKey().getLabel() + ", " + e.getValue() + "]");
            }
            _outList.add("\n");
        }
        return _outList;
    }

    @Override
    public boolean addVertex(Vertex n) {
        if(!_adjList.containsKey(n)){
            _adjList.put(n, new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(Vertex n) {
        return false;
    }

    @Override
    public boolean connectVertices(Vertex n1, Vertex n2, E weight) {
        if(_adjList.containsKey(n1) && _adjList.containsKey(n2)){
            _adjList.get(n1).put(n2, weight);
            return true;
        }
        return false;
    }

    @Override
    public void connectVertices(Vertex n1, Vertex n2) {
        connectVertices(n1, n2, _sentinel);
    }

    @Override
    public void disconnectVertices(Vertex n1, Vertex n2) {
        Map<Vertex, E> _edges = _adjList.get(n1);
        _edges.remove(n2);
    }

    @Override
    public void doubleConnectVertices(Vertex n1, Vertex n2, E weight) {
        connectVertices(n1, n2, weight);
        connectVertices(n2, n1, weight);
    }

    @Override
    public void doubleConnectVertices(Vertex n1, Vertex n2) {
        doubleConnectVertices(n1, n2, _sentinel);
    }

    @Override
    public boolean verticesConnected(Vertex n1, Vertex n2) {
        if(_adjList.containsKey(n1)){
            return(_adjList.get(n1).get(n2) != null);
        }
        return false;
    }

    @Override
    public E getWeight(Vertex n1, Vertex n2) {
        if(_adjList.containsKey(n1)){
            return(_adjList.get(n1).get(n2));
        }
        return null;
    }

    @Override
    public void setWeight(Vertex n1, Vertex n2, E weight) {
        connectVertices(n1, n2, weight);            // weight will be updated by overwriting the key in the hashmap
    }

    @Override
    public int getNeighbourCount(Vertex n1, E maxWeight) {
        if(_adjList.containsKey(n1)) {
            return _adjList.get(n1).size();
        }
        return 0;
    }

    @Override
    public List<Vertex> getOutgoingVertices(Vertex n) {
        List<Vertex> _vertices = new LinkedList<>();
        for (Map.Entry<Vertex, E> entry : _adjList.get(n).entrySet()) {
            _vertices.add(entry.getKey());
        }
        return _vertices;
    }
}
