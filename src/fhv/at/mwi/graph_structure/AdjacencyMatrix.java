package fhv.at.mwi.graph_structure;

import java.util.*;

public class AdjacencyMatrix<E extends Comparable> extends AdjacencyStructure<E> {

    private int _vertexIndex = 0;
    private Map<Vertex, Integer> _indexNodeMap = new Hashtable<>();
    private LinkedList<Vertex> _verticies = new LinkedList<>();
    private E[][] _matrix;
    private E _defaultWeight;

    public AdjacencyMatrix(E defaultWeight) {
        _defaultWeight = defaultWeight;
    }

    @Override
    public List print() {
        LinkedList<Object> _outList = new LinkedList<>();
        int rows = _matrix.length;
        _outList.add("");
        for(Vertex v:_verticies){
            _outList.add(v.getLabel() + " ");
        }
        _outList.add("\n");
        for(int i = 0; i < rows; i++){
            int columns = _matrix[i].length;
            _outList.add( _verticies.get(i).getLabel() + " ");
            for(int x = 0; x < columns; x++){
                _outList.add( _matrix[i][x] + " ");
            }
            _outList.add("\n");
        }
        return _outList;
    }

    private Vertex getKeyByValue(Map<Vertex, Integer> map, Integer value) {
        for (Map.Entry<Vertex, Integer> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public boolean addVertex(Vertex n) {
        if(_indexNodeMap.containsKey(n)) {
            return false;
        }
        _indexNodeMap.put(n, _vertexIndex);
        _verticies.add(n);
        resizeMatrix();
        _vertexIndex++;
        return true;
    }

    private void resizeMatrix(){
        int mn = _vertexIndex+1;
        E[][] tempMatrix = (E[][]) new Comparable[mn][mn];
        if(_matrix != null){
            int rows = _matrix.length;
            for(int i = 0; i < rows; i++){
                int columns = _matrix[i].length;
                for(int x = 0; x < columns; x++){
                    tempMatrix[i][x] = _matrix[i][x];
                }
            }
        }
        _matrix = tempMatrix;
    }

    @Override
    public boolean removeVertex(Vertex n) {
        if(!_indexNodeMap.containsKey(n)){
            return false;
        }
        _indexNodeMap.remove(n);
        return true;
    }

    @Override
    public boolean connectVertices(Vertex n1, Vertex n2, E weight) {
        if(!_indexNodeMap.containsKey(n1) || !_indexNodeMap.containsKey(n2)){
            return false;
        }
        int y = _indexNodeMap.get(n1), x = _indexNodeMap.get(n2);
        _matrix[y][x] = weight;
        return true;
    }

    @Override
    public void connectVertices(Vertex n1, Vertex n2) {
        connectVertices(n1, n2, _defaultWeight);
    }

    @Override
    public void disconnectVertices(Vertex n1, Vertex n2) {
        connectVertices(n1, n2, null);
    }

    @Override
    public void doubleDisconnectVertices(Vertex n1, Vertex n2) {
        disconnectVertices(n1, n2);
        disconnectVertices(n2, n1);
    }

    @Override
    public void doubleConnectVertices(Vertex n1, Vertex n2, E weight) {
        connectVertices(n1, n2, weight);
        connectVertices(n2, n1, weight);
    }

    @Override
    public void doubleConnectVertices(Vertex n1, Vertex n2) {
        connectVertices(n1, n2, _defaultWeight);
        connectVertices(n2, n1, _defaultWeight);
    }

    @Override
    public boolean verticesConnected(Vertex n1, Vertex n2) {
        if(!_indexNodeMap.containsKey(n1) || !_indexNodeMap.containsKey(n2)){
            return false;
        }
        int y = _indexNodeMap.get(n1), x = _indexNodeMap.get(n2);
        if(_matrix[y][x] == null){
            return false;
        }
        return true;
    }

    @Override
    public E getWeight(Vertex n1, Vertex n2) {
        if(verticesConnected(n1, n2)){
            int y = _indexNodeMap.get(n1), x = _indexNodeMap.get(n2);
            return _matrix[y][x];
        }
        return null;
    }

    @Override
    public void setWeight(Vertex n1, Vertex n2, E weight) {
        int y = _indexNodeMap.get(n1), x = _indexNodeMap.get(n2);
        _matrix[y][x] = weight;
    }

    /**
     * Only count an out going neighbour if the weight is greater or equal than the weightTrigger
     * E.g. Ignore neighbours with weight 0 -> weight Trigger = 0
     * @param n1
     * @return
     */
    @Override
    public int numOfOutgoingNeighbour(Vertex n1) {
        int row = _indexNodeMap.get(n1);
        int count = 0;
        for(int i = 0; i < _matrix[row].length; i++){
            if(_matrix[row][i]!= null){
                count++;
            }
        }
        return count;
    }

    /**
     *
     * @param n1
     * @return
     */
    @Override
    public int numOfIngoingNeighbour(Vertex n1) {
        int col = _indexNodeMap.get(n1);
        int iLim = _matrix[0].length;
        int count = 0;
        for(int i = 0; i < iLim; i++){
            if(_matrix[i][col] != null){
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Vertex> getOutgoingVertices(Vertex n) {
        LinkedList<Vertex> neighbours = new LinkedList<>();
        int row = _indexNodeMap.get(n);
        for(int i = 0; i < _matrix[row].length; i++){
            if(_matrix[row][i] != null){
               neighbours.add(_verticies.get(i));
            }
        }
        return neighbours;
    }

    @Override
    public Vertex getNextOutgoingVertex(Vertex n) {
        List<Vertex> _allNeighbours = getOutgoingVertices(n);
        return _allNeighbours.get(0);
    }


}
