package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Graph;
import fhv.at.mwi.graph_structure.StructType;
import fhv.at.mwi.graph_structure.Vertex;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GraphReader {

    private static Graph _graph;
    private ExpressionScanner _scanner;
    private List<Vertex> _vertices = new LinkedList<>();

    public GraphReader(String filePath) throws InvalidExpressionException {
        _scanner = new ExpressionScanner(new File(filePath));
        String current = _scanner.getNext(3);
        if(current.equals("G={")){
            DelimParser<String> titleParser = new DelimParser<>( ";", "}", new StringExpression());
            System.out.println("Graph: " + titleParser.parse(_scanner));
            DelimParser<Integer> graphTypeParser = new DelimParser<>( ";", "}", new IntegerExpression());
            int type = (Integer)graphTypeParser.parse(_scanner);
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
        while(_scanner.hasNext()){
            readGraph();
        }

    }

    private void readGraph() throws InvalidExpressionException {
        String current = _scanner.getNext(3);
        if(current.equals("V={")){
            DelimParser<Vertex> vertexParser = new DelimParser<>( ";", "}", new VertexExpression());
            while(!vertexParser.endOfExpression()){
                _vertices.add(vertexParser.parse(_scanner));
            }
        }
        else if(current.equals("E={") && !_vertices.isEmpty()){

        } else{
            throw new InvalidExpressionException("Invalid graph type. V or E expected, found " + current);
        }
    }

    private void insertToGraph(List vertices, List edges){

    }

    public static Graph getGraph() {
        return _graph;
    }
}
