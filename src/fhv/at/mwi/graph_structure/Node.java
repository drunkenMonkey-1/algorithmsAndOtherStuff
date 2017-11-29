package fhv.at.mwi.graph_structure;


public class Node <T> {

    private T _label;
    private int _property;

    public Node(){
        _label = null;
    }

    public Node(T label){
        _label = label;
        _property = -1;
    }

    public Node(T label, int property){
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

    public boolean equals(Node o){
        return _label.equals(o);
    }

}
