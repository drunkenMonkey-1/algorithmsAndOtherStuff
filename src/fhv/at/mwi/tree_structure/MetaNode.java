package fhv.at.mwi.tree_structure;

/**
 * Just a class to save a tree node without the children
 * @param <V> Type of the node value
 * @param <K> Type of the property value
 */

public class MetaNode<V extends  Comparable, K> {

    private V _value;
    private K _property;

    public MetaNode(V value, K property){
        _value = value;
        _property = property;
    }


    public K getProperty() {
        return _property;
    }

    public void setProperty(K _property) {
        this._property = _property;
    }

    public V getValue() {
        return _value;
    }

    public void setValue(V _value) {
        this._value = _value;
    }
}
