package fhv.at.mwi.quality_control;

import fhv.at.mwi.general.Position;
import fhv.at.mwi.general.SimpleMap;

import java.nio.channels.Pipe;
import java.util.LinkedList;
import java.util.Map;

public class QualityControl {

    private SimpleMap[][] _pattern;

    private int _matches;
    private Position _startPosition;

    public QualityControl(SimpleMap[][] pattern){
        _pattern = pattern;
        searchPattern();
    }

    public void calculateNewPattern(SimpleMap[][] pattern){
        _pattern = pattern;
        searchPattern();
    }

    private void searchPattern(){
        for(int x = 1; x < _pattern.length-1; x++){
            for(int y = 1; y < _pattern[x].length-1; y++){
               // System.out.printf("%s", _pattern[x][y].getValue());
                if(_pattern[x][y].getValue() == '*' && _pattern[x][y].isUsed() == false){
                    _startPosition = new Position(x, y);
                   searchNeighbours(_startPosition, _startPosition);
                }
            }
       // System.out.printf("\n");
        }
    }

    private void searchNeighbours(Position root, Position me){
        int validNeighbours = 0;
        LinkedList<Position> nextPos = new LinkedList<Position>();
        _pattern[me.get_x()][me.get_y()].set_used(true);
        System.out.println("matrix search start");
        for(int x = me.get_x()-1; x <= me.get_x()+1; x++){
            for(int y = me.get_y()-1; y <= me.get_y()+1; y++){
                Position currentPos = new Position(x, y);
                if(_pattern[x][y].getValue() == '*'  && currentPos.compare(currentPos, root) != 0
                                                     && currentPos.compare(currentPos, _startPosition) != 0
                                                     && currentPos.compare(currentPos, me) != 0
                                                     && _pattern[x][y].isUsed() == false){
                    System.out.println("Found at: " + currentPos.toString());
                    Position p = new Position(x, y);
                    nextPos.add(p);
                    validNeighbours++;
                }
                _pattern[x][y].set_used(true);
            }
        }
        if(validNeighbours == 0){
            System.out.println("no neighbours left - hole complete");
            _matches++;
        } else if(validNeighbours == 1){
            System.out.println("Next neighbour " + nextPos.toString());
            searchNeighbours(me, nextPos.getFirst());
        } else if(validNeighbours > 1){
            Position res = nextPos.get(0);
            for(int i = 1; i < nextPos.size(); i++){
                if(res.rightMostElement(nextPos.get(i)) == 1){
                    res = nextPos.get(i);
                }
            }
            System.out.println("Next neighbour " + res.toString());
            searchNeighbours(me, res);
        }

    }

    public int get_matches() {
        return _matches;
    }
}
