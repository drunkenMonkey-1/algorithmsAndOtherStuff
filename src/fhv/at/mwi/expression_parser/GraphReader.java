package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.Graph;
import fhv.at.mwi.graph_structure.StructType;
import fhv.at.mwi.graph_structure.Vertex;
import fhv.at.mwi.graph_structure.VertexExistenceException;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphReader {

    private static Graph _graph;
    private ExpressionScanner _scanner;
    private Map<Object, Vertex> _vertices = new HashMap<>();
    private List<Edge> _edges= new LinkedList<>();

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
        insertToGraph(_vertices, _edges);
    }

    private void readGraph() throws InvalidExpressionException {
        String current = _scanner.getNext(3);
        if(current.equals("V={")){
            DelimParser<Vertex> vertexParser = new DelimParser<>( ";", "}", new VertexExpression());
            while(!vertexParser.endOfExpression()){
                Vertex add = vertexParser.parse(_scanner);
                _vertices.put(add.getLabel(), add);
            }
        }
        else if(current.equals("E={") && !_vertices.isEmpty()){
            DelimParser<Edge> edgeParser = new DelimParser<>( ";", "}", new EdgeExpression());
            while(!edgeParser.endOfExpression()){
                _edges.add(edgeParser.parse(_scanner));
            }
        } else{
            throw new InvalidExpressionException("Invalid graph type. V or E expected, found " + current);
        }
    }

    private void insertToGraph(Map vertices, List edges){
        for(Map.Entry<Object, Vertex> e:_vertices.entrySet()){
            try {
                _graph.add(e.getValue());
            } catch (VertexExistenceException e1) {
                e1.printStackTrace();
            }
        }
        for(Edge e:_edges){
            _graph.doubleConnect(extractVertexFromEdge(e.get_v1()), extractVertexFromEdge(e.get_v2()), (Integer) e.get_weight());
        }
    }

    private Vertex extractVertexFromEdge(Vertex u){
        return _vertices.get(u.getLabel());
    }

    public static Graph getGraph() {
        return _graph;
    }
}
