package fhv.at.mwi.general;

import java.util.Comparator;

public class Position implements Comparator<Position>{
    private int _x;
    private int _y;
    private int _property;

    public Position(int x, int y){
        _x = x;
        _y = y;
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public int getProperty() {
        return _property;
    }

    public void setProperty(int _property) {
        this._property = _property;
    }

    public int rightMostElement(Position o2){
        if(o2.get_y() >= _y && o2.get_x() >= _x){
            return 1;
        }
        return -1;
    }

    public int positionToArrayIndex(int qsize){
        return (qsize * _y) + _x;
    }

    public static Position arrayIndexToPosition(int pos, int qsize){
        return new Position(pos % qsize, pos / qsize);
    }

    @Override
    public String toString(){
        return "x: " + _x + " | y: " + _y;
    }

    @Override
    public int compare(Position o1, Position o2) {
        if(o1.get_x() == o2.get_x() && o1.get_y() == o2.get_y()){
            return 0;
        }
        else if(o1.get_x() <= o2.get_x() && o1.get_y() <= o2.get_y()){
            return -1;
        } else{
            return 1;
        }
    }
}
