package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

public class TempMain {

    public static void main(String[] args){
        Game ttt = new Game(true, new MinMaxStrategy());
        ttt.doMove(new Position(0,0));
        ttt.doMove();
        ttt.doMove(new Position(0,1));
        ttt.doMove();
        ttt.doMove(new Position(1,2));
        ttt.doMove();
        System.out.println(ttt.printField());

    }
}
