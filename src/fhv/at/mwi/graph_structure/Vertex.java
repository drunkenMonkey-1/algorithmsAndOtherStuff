package fhv.at.mwi.graph_structure;


public class Vertex<T> implements Comparable<Vertex>{

    private T _label;
    private int _property;

    public Vertex(){
        _label = null;
    }

    public Vertex(T label){
        _label = label;
        _property = -1;
    }

    public Vertex(T label, int property){
        _label = label;
        _property = property;
    }


    public void setLabel(T _label) {
        this._label = _label;
    }

    public T getLabel() {
        return _label;
    }

    public int getProperty() {
        return _property;
    }

    public void setProperty(int _property) {
        this._property = _property;
    }

    public boolean equals(Vertex o){
        return _label.equals(o);
    }

    @Override
    public int compareTo(Vertex o) {
        if(_label.equals(o._label) && _property == o.getProperty()){
            return 0;
        }
        return -1;
    }

    @Override
    public String toString(){
        return _label + " | " + Integer.toString(_property);
    }
}
