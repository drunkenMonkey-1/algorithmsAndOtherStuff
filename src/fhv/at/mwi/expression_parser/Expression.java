package fhv.at.mwi.expression_parser;

public interface Expression<V> {
    public V parse(ExpressionScanner scanner ) throws InvalidExpressionException;
}
