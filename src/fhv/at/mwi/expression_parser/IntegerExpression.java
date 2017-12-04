package fhv.at.mwi.expression_parser;

import fhv.at.mwi.general.EasyMath;

public class IntegerExpression implements Expression<Integer> {
    @Override
    public Integer parse(ExpressionScanner scanner) throws InvalidExpressionException {
        String current = scanner.getNext();
        StringBuilder lazy = new StringBuilder();
        while(current.charAt(0) <= '9' && current.charAt(0) >= '0'){
            lazy.append(current.charAt(0));
            current = scanner.getNext();
        }
        scanner.setPrevious(current);
        return Integer.parseInt(lazy.toString());
    }
}
