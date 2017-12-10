package fhv.at.mwi.graph_structure;

public interface IOperator<V extends Number> {
    V operate(V otherValue);
}
