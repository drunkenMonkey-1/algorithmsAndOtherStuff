package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Vertex;

public class VertexExpression implements Expression<Vertex> {
    @Override
    public Vertex parse(ExpressionScanner scanner) throws InvalidExpressionException {
        String current = scanner.getNext();
        if(!current.equals("(")){
            throw new InvalidExpressionException("Start character ( expected, found " + current);
        }
        DelimParser<?> _labelParser = new DelimParser<>(",", ")", new StringExpression());
        DelimParser<Integer> _propertyParser = new DelimParser<>(")", ")", new IntegerExpression());
        Vertex result = new Vertex(_labelParser.parse(scanner), _propertyParser.parse(scanner));
        return result;
    }
}
