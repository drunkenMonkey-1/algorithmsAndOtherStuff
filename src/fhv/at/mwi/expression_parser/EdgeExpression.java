package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Edge;
import fhv.at.mwi.graph_structure.Vertex;

public class EdgeExpression implements Expression<Edge> {

    @Override
    public Edge parse(ExpressionScanner scanner) throws InvalidExpressionException {
        String current = scanner.getNext();
        if(!current.equals("[")){
            throw new InvalidExpressionException("Start character ( expected, found " + current);
        }
        DelimParser<?> _vertexParser = new DelimParser<>(",", "]", new StringExpression());
        DelimParser<Integer> _weightParser = new DelimParser<>(",", "]", new IntegerExpression());
        Edge<Integer> result = new Edge<>(new Vertex(_vertexParser.parse(scanner)),
                                          new Vertex(_vertexParser.parse(scanner)),
                                          _weightParser.parse(scanner));
        return result;
    }
}
