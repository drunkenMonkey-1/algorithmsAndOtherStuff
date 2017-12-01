package fhv.at.mwi.expression_parser;

import java.io.*;
import java.util.Scanner;

public class ExpressionScanner {

    private Scanner _scanner;

    public ExpressionScanner(File in){
        try {
            _scanner = new Scanner(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getNext(){
        String _current = _scanner.next();
        if(_current.equals(" ") || _current.equals("\n")){
            _current = _scanner.next();
        }
        return _current;
    }

    /**
     *
     * @param charCount
     * @return
     */
    public String getNext(int charCount){
        StringBuilder nextCharsBuilder = new StringBuilder();
        int i = 0;
        while (i < charCount){
            nextCharsBuilder.append(this.getNext());
            i++;
        }
        return nextCharsBuilder.toString();

    }

}
