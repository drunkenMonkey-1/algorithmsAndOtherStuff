package fhv.at.mwi.expression_parser;

public class DelimParser<V> implements Expression<V>{

    private String _delim;
    private String _endMarker;
    private Expression<V> _parser;

    public DelimParser(String delim, String endMarker, Expression parser){
        _delim = delim;
        _endMarker = endMarker;
        _parser = parser;
    }

    @Override
    public V parse(ExpressionScanner scanner) throws InvalidExpressionException {
        V result = null;
        result = _parser.parse(scanner);
        String current = scanner.getNext();
        if(current.equals(_endMarker) || current.equals(_delim)){
            return result;
        } else{
            throw new InvalidExpressionException("End sequence " + _endMarker + " expected, found " + current);
        }
    }
}
