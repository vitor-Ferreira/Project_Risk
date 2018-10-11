package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Text;

public class GameLogic {

    private Grid grid;
    private Board board;
    private Territory territory;
    private Territory[][] territoryArray;
    private Text textToDisplay;

    private Player p1 = new Player("Blue");
    private Player p2 = new Player("Red");
    private Player activePlayer = p1;
    //private Movement moveChoice;

    private int rounds = 1; //if we want to keep track of the number of rounds.
    private int maxRounds = 11; //doubtful
    private RoundStage roundStage = RoundStage.ATTACK; //começamos no ataque

    private boolean attackDone = false;

    public void init() {

        grid = new Grid(3, 3);
        board = new Board(grid, 3, 3);
        territoryArray = board.getTerritories();
        grid.init(territoryArray);

        textToDisplay = new Text(400, 400, "Attack Phase");
        textToDisplay.draw();
        textToDisplay.grow(50, 50);
        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setGame(this);
        keyboard.setBoard(board);
        keyboard.setGrid(grid);
        keyboard.runKeyboard();

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        grid.movementImage();
        grid.showNumberSoldiers();
    }

    public void start() {

        System.out.println("Current Round: " + rounds);

        //System.out.println("round: " + rounds);

        //attackDone = false;

            /* if (rounds % 2 == 0) {
                System.out.println("Player 2's turn");
                board.beginRoundP2();
                round();
                rounds++;
            }

            board.beginRoundP1();
            System.out.println("Player 1's turn");*/
            round();
        /** plano de acção: em vez de fazer a estratégia em cima descrita, fazer algo com activePlayer. a cada vez que activePlayer faz uma jogada, muda
         * de P1 para P2 e vice-versa. Só há um activePlayer de cada vez. as jogadas (i.e movements, etc, aplicam-se em nome do activePlayer. **/
    }

    public void round() { //"o round é um jogo automático"; "premir uma tecla -> acção. dependendo da roundStage, significados diferentes."


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

        while (!attackDone) {

            //board.moveToTerritory(moveChoice);
            //moveActivePlayer(move);

            //check if territoryArray that we are attacking from has more than 1 soldier
            //if (board.verifyTerritorySelected().getSoldiers() <= 1) {
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

            if (territoryAttack.getPlayer() == activePlayer) {
                return;
            }

            board.battle(); // Changes in the board.battle method
            attackDone = true;
        }
    }

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

    /* public void movePlayer(Movement movement) {
        moveChoice = movement;
    } */

    public void moveActivePlayer(Movement movement) {

        Movement move = movement;

        switch (move) {

            case LEFT:
                if (!attackDone) {
                    board.moveToTerritory(Movement.LEFT);
                }
                return;

            case UP:
                if (!attackDone) {
                    board.moveToTerritory(Movement.UP);
                }
                return;

            case RIGHT:
                if (!attackDone) {
                    board.moveToTerritory(Movement.RIGHT);
                }
                return;

            case DOWN:
                if (!attackDone) {
                    board.moveToTerritory(Movement.DOWN);
                }
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

    public enum RoundStage {
        REINFORCE,
        ATTACK

    }
}