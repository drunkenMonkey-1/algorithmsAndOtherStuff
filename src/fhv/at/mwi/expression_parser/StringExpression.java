package fhv.at.mwi.expression_parser;

public class StringExpression implements Expression<String> {

    @Override
    public String parse(ExpressionScanner scanner) throws InvalidExpressionException {

        StringBuilder strBuilder = new StringBuilder();
        String current = scanner.getNext();
        if(!current.equals("\"")){
            throw new InvalidExpressionException("Start character \" expected - found " + current);
        }
        current = scanner.getNext();
        while(scanner.hasNext() && !current.equals("\"")){
            strBuilder.append(current);
            current = scanner.getNext();
        }
        if(!scanner.hasNext()){
            throw new InvalidExpressionException("Termination character \" expected - found " + current);
        }
        return strBuilder.toString();
    }
}
