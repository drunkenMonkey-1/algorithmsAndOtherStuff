package fhv.at.mwi.expression_parser;

import fhv.at.mwi.graph_structure.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphReader {

    private static Graph<Integer> _graph;
    private ExpressionScanner _scanner;
    private Map<Object, Vertex> _vertices = new HashMap<>();
    private List<Edge> _edges= new LinkedList<>();

    /**
     * Read a graph from a file
     * @param filePath path to txt file including a graph definition
     * @throws InvalidExpressionException
     */
    public GraphReader(String filePath) throws InvalidExpressionException {
        _scanner = new ExpressionScanner(new File(filePath));
        String current = _scanner.getNext(3);
        if(current.equals("G={")){
            DelimParser<String> titleParser = new DelimParser<>( ";", "}", new StringExpression());
            System.out.println("Graph: " + titleParser.parse(_scanner));
            DelimParser<Integer> graphTypeParser = new DelimParser<>( ";", "}", new IntegerExpression());
            int type = graphTypeParser.parse(_scanner);
            if(type == 0){
                _graph = new Graph<>(StructType.MATRIX, 1);
            } else if (type == 1){
                _graph = new Graph<>(StructType.LIST, 1);
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


    /**
     *
     * @return
     */
    public Map<Object, Vertex> getVerticeMap(){
        return _vertices;
    }

    /**
     * Return a vertex that was read by the parser by it's label
     * @param label String or something else associated with the wanted vertex
     * @return Vertex by given key label
     */
    public Vertex getVertexByLabel(Object label){
        return _vertices.get(label);
    }

    /**
     *
     * @return
     */
    public List<Vertex> getVerticeList(){
        List<Vertex> out = new LinkedList<>();
        for(Map.Entry<Object, Vertex> e : _vertices.entrySet()){
            out.add(e.getValue());
        }
        return out;
    }

    /**
     * Return the graph, that was read by the parser
     * @return A graph with vertices and edges
     */
    public static Graph getGraph() {
        return _graph;
    }
}
