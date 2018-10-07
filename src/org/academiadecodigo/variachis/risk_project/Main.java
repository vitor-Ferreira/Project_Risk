package org.academiadecodigo.variachis.risk_project;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        Grid g = new Grid(5, 15);
        g.init();

        //GameLogic game = new GameLogic();
        //game.countRounds();
        Player p1 = new Player("red");
        Player p2 = new Player("blue");


        Board board = new Board(3, 1);
        board.territoryMaker();
        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);

        board.increment(p1);
        board.moveToTerritory(Movement.DOWN);
        //System.out.println("Check the soldiers of the selected T: " + board.verifyTerritorySelected().getSoldiers());

        board.beginRoundP2();
        board.increment(p2);
        board.moveToTerritory(Movement.UP);
       // System.out.println("Check the soldiers of the selected T: " + board.verifyTerritorySelected().getSoldiers());

        board.battle();

        board.beginRoundP1();
        board.increment(p1);
        board.moveToTerritory(Movement.DOWN);
        board.reinforce();



    }
}
