package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameLogic {

    private Grid grid;
    private Board board;
    private Territory territory;
    private Territory[][] territoryArray;
    private Text textToDisplay;

    private Player p2 = new Player("Red", new Picture(10, 10, "Resources/tank1.png")); //correct this
    private Player p1 = new Player("Blue", new Picture(10 + 400, 10 + 400, "Resources/Sombreiro 02 transparente (200 X 200).png")); //correct this
    private Player activePlayer = p1;

    private int rounds = 1; //if we want to keep track of the number of rounds.
    //private int maxRounds = 11; //doubtful
    private RoundStage roundStage = RoundStage.ATTACK; //começamos no ataque

    //private boolean attackDone = false;

    public void init() {

        grid = new Grid(3, 3);
        board = new Board(grid, 3, 3);
        board.setGame(this);
        territoryArray = board.getTerritories();
        grid.init(territoryArray);

        textToDisplay = new Text(290, 290, "Press Any Key");
        textToDisplay.draw();
        textToDisplay.grow(60, 60);

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setGame(this);
        keyboard.setBoard(board);
        keyboard.setGrid(grid);
        keyboard.runKeyboard();

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        //grid.movementImage();
        grid.showNumberSoldiers();
        p1.getPicture().draw();
        p2.getPicture().draw();
    }

    public void round() { //"o round é um jogo automático"; "premir uma tecla -> acção. dependendo da roundStage, significados diferentes."

        System.out.println("Current Round: " + rounds);

        textToDisplay.setText(roundStage.toString());

        if (roundStage == RoundStage.REINFORCE) {
            board.increment();
            roundStage = RoundStage.ATTACK;
            return;
        }

        if (roundStage == RoundStage.ATTACK) {
            attack();
            roundStage = RoundStage.REINFORCE;
        }

        if (activePlayer == p2) {
            rounds++;
        }

        // TODO: 11/10/2018 Make sure that `rounds` is only incremented after two players have played
        activePlayer = activePlayer == p1 ? p2 : p1;
    }

    public void attack() {

        //while (!attackDone) {

        //check if territoryArray that we are attacking from has more than 1 soldier
        //System.out.println(board.verifyTerritorySelected());
        if (board.getTerritoryOrigin().getSoldiers() <= 1) {
            //choose another territoryArray to attack from
            //  System.out.println("verify if territory only 1");
            return;
        }
            /* //gets the territoryArray (movement) that the player wants to attack (defined in Player).
            //check if the movement is allowed
            //board.moveToTerritory(movement);
            //if not allowed return choose new movement
            //needs to keep checking if new move is allowed...
            //return; */

        //check if territories have different owners
        Territory territoryAttack = board.verifyTerritorySelected();

        System.out.println("ter get P " + territoryAttack.getPlayer().getColor());
        if (territoryAttack.getPlayer() == activePlayer) {
            return;
        }

        board.battle(); // Changes in the board.battle method
        grid.loadNumberSoldiers();
        grid.showNumberSoldiers();
        //attackDone = true;
    }
    //}

  /*  public void reinforce() {

        while (!reinforceDone) {

            //board.moveToTerritory(moveChoice);
            //moveActivePlayer(move);

            if (board.getTerritoryOrigin().getSoldiers() <= 1) {
                return;
            }

            //gets the territoryArray (movement) that the player wants to reinforce (defined in Player).

            Territory territoryReinforce = board.verifyTerritorySelected();

            if (territoryReinforce.getPlayer() != activePlayer) {
                return;
            }

            board.reinforce();
            reinforceDone = true;
        }
    } */

    public void moveActivePlayer(Movement movement) {

        Movement move = movement;

        switch (move) {

            case LEFT:
                activePlayer.move(Movement.LEFT);
                return;

            case UP:
                activePlayer.move(Movement.UP);
                return;

            case RIGHT:
                activePlayer.move(Movement.RIGHT);
                return;

            case DOWN:
                activePlayer.move(Movement.DOWN);
                return;
        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public int getRounds() {
        return rounds;
    }

    public enum RoundStage {
        REINFORCE,
        ATTACK

    }
}