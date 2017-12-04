package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Vertex;

public interface Expression<V> {
    public V parse(ExpressionScanner scanner ) throws InvalidExpressionException;
}
