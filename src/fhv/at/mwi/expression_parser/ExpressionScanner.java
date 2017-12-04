package fhv.at.mwi.expression_parser;

import java.io.*;
import java.util.Scanner;

public class ExpressionScanner {

    private Scanner _scanner;
    private String _previousBuffer;

    /**
     *
     * @param in
     */
    public ExpressionScanner(File in){
        _previousBuffer = null;
        try {
            _scanner = new Scanner(in);
            /* Empty delimiter to read single characters as string */
            _scanner.useDelimiter("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getNext(){
        if(_previousBuffer != null){
            String _temp = _previousBuffer;
            _previousBuffer = null;
            return _temp;
        }
        String _current = _scanner.next();
        while(_current.equals(" ") || _current.equals("\n")){
            _current = _scanner.next();
        }
        return _current;
    }

    public boolean hasNext(){
        return _scanner.hasNext();
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

    public void setPrevious(String c){
        _previousBuffer = c;
    }

}
