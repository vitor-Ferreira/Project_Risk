package org.academiadecodigo.variachis.risk_project;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        GameLogic game = new GameLogic();
        //Player p1 = new Player("red");
        //Player p2 = new Player("blue");

        game.init();
        game.start();

        /* Board board = new Board(3, 1);
        board.territoryMaker();
        board.addTerritoryToP1(game.getP1());
        board.addTerritoryToP2(game.getP2());

        board.increment(game.getP1());
        board.moveToTerritory(mov); ex:Movement.DOWN
        //System.out.println("Check the soldiers of the selected T: " + board.verifyTerritorySelected().getSoldiers());

        board.beginRoundP2();
        board.increment(game.getP2());
        board.moveToTerritory(mov); ex:Movement.UP
        // System.out.println("Check the soldiers of the selected T: " + board.verifyTerritorySelected().getSoldiers());

        board.battle();

        board.beginRoundP1();
        board.increment(game.getP1());
        board.moveToTerritory(mov); ex:Movement.UP
        board.reinforce(); */
    }
}