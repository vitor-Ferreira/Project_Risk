package org.academiadecodigo.variachis.risk_project;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        //GameLogic game = new GameLogic();
        //game.countRounds();
        Player p1 = new Player("red", 20);
        Player p2 = new Player("blue", 20);


        Board board = new Board(3, 1);
        board.territoryMaker();
        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);

        board.increment(p1);
        board.moveToTerritory(Movement.DOWN);
        //System.out.println(board.verifyTerritorySelected().getSoldiers());

        board.beginRoundP2();
        board.increment(p2);
        board.moveToTerritory(Movement.UP);
        //System.out.println(board.verifyTerritorySelected().getSoldiers());

        board.battle();
        board.reinforce();



    }
}
