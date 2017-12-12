package fhv.at.mwi.graph_structure;

import fhv.at.mwi.graph_structure.Vertex;

public class Edge<E> {

    private Vertex _v1;
    private Vertex _v2;
    private boolean _visited;
    private E _weight;

    public Vertex get_v1() {
        return _v1;
    }

    public void set_v1(Vertex _v1) {
        this._v1 = _v1;
    }

    public Vertex get_v2() {
        return _v2;
    }

    public void set_v2(Vertex _v2) {
        this._v2 = _v2;
    }

    public E get_weight() {
        return _weight;
    }

    public void set_weight(E _weight) {
        this._weight = _weight;
    }


    public Edge(Vertex v1, Vertex v2, E weight){
        _v1 = v1;
        _v2 = v2;
        _weight = weight;
    }

    public Edge(Vertex v1, Vertex v2){
        this(v1, v2, null);
    }


    @Override
    public String toString(){
        return _v1.getLabel() + " | " + _v2.getLabel() + " - " + _weight;
    }

    public boolean isVisited() {
        return _visited;
    }

    public void setVisited(boolean _visited) {
        this._visited = _visited;
    }
}
