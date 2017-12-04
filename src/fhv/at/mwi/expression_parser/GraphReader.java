package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Graph;
import fhv.at.mwi.graph_structure.StructType;

import java.io.File;

public class GraphReader {

    private static Graph _graph;

    public GraphReader(String filePath) throws InvalidExpressionException {
        ExpressionScanner scanner = new ExpressionScanner(new File(filePath));
        String current = scanner.getNext(3);
        if(current.equals("G={")){
            DelimParser<?> titleParser = new DelimParser<>( ";", "}", new StringExpression());
            System.out.println("Graph: " + titleParser.parse(scanner));
            DelimParser<?> graphTypeParser = new DelimParser<>( ";", "}", new IntegerExpression());
            int type = (Integer)graphTypeParser.parse(scanner);
            if(type == 0){
                _graph = new Graph(StructType.MATRIX);
            } else if (type == 1){
                _graph = new Graph(StructType.LIST);
            } else{
                throw new InvalidExpressionException("Invalid Graph type. Expected 0 or 1, found : " + type);
            }
        } else {
            throw new InvalidExpressionException("Invalid Graph definition at start of file: " + current);
        }
        System.out.println(scanner.getNext(3));
    }

    public static Graph get_graph() {
        return _graph;
    }
}
