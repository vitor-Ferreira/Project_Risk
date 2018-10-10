package org.academiadecodigo.variachis.risk_project;

public class GameLogic {

    private Grid grid;

    private Player p1;//Ver territorios
    private Player p2;

    private Board board;
    private Territory territory;
    private Territory[][] territoryArray;

    private boolean attackDone = false;
    private boolean reinforceDone = false;

    public void init() {

        board = new Board(grid, 2, 3);
        grid = new Grid(2, 3, board); //Board may also needs to know a Grid instance.
        grid.init(); //corrigir: ao fecharmos esta janela, o processo não encerra logo.
        territoryArray = board.getTerritories();

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setBoard(board);
        keyboard.runKeyboard();

        /* Picture militaryHelmet = new Picture(grid.columnToX(0), grid.rowToY(0), "Resources/military_helmet-512.png");
        militaryHelmet.draw();
        militaryHelmet.grow(-100, -100);
        */

        this.p1 = new Player("Red");// check territoryArray
        this.p2 = new Player("Blue");// check territoryArray

        //grid.playerImagesShow();
        //grid.player2ImagesShow();

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
    }

    //no need for this now. commands go directly from keyboard to board.
    /* public void move(Movement movement) {
        //int move = (int) Math.floor(Math.random() * 4);
        Movement mov = movement;
        switch (mov) {
            case LEFT:
                mov = Movement.LEFT;
                break;
            case UP:
                board.moveToTerritory(Movement.UP);
                break;
            case RIGHT:
                mov = Movement.RIGHT;
                break;
            case DOWN:
                mov = Movement.DOWN;
        }
    }*/

    public void start() {

        int rounds = 1;

        while (!board.victory(p1, p2)) {
            for (int i = 0; i < 11; i++) { //why 11? because if it's not 11, the for doesn't stop. this is a quick fix.

                round(p1);

                System.out.println("rounds: " + rounds);
                attackDone = false;

                if (rounds % 2 == 0) {
                    System.out.println("p2's turn");
                    board.beginRoundP2();
                    round(p2);
                    rounds++;
                }

                board.beginRoundP1();
                System.out.println("p1's turn");
                round(p1);
                rounds++;
            }
        }
    }

    public void round(Player player) {

        //Phase 1 - increment, uses the method increment from the board
        //to increment the troops in territories that have the player that is playing this round
        board.increment(player);

        while (!attackDone) {
            attack(player);
        }

        reinforce(player);
    }

    public void attack(Player player) {

        //System.out.println("move " + movi);

        while (!attackDone) {

            //check if territoryArray that we are attacking from has more than 1 soldier
            //if (board.verifyTerritorySelected().getSoldiers() <= 1) {
            //System.out.println(board.verifyTerritorySelected());
            if (board.verifyTerritorySelected().getSoldiers() <= 1) {  /** PERHAPS CREATE GETTER IN BOARD **/
                //choose another territoryArray to attack from
                System.out.println("verify if territory only 1");
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

            if (territoryAttack.getPlayer() == player) {
                return;
            }

            board.battle(); // Changes in the board.battle method
            attackDone = true;
        }
    }

    public void reinforce(Player player) {

        while (!reinforceDone) {

            //Check if the territoryArray that we are reinforcing from has more than 1 soldier
            if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                return;
            }

            //gets the territoryArray (movement) that the player wants to reinforce (defined in Player).

            //if (!board.allowsMovement(movement)) {

            Territory territoryReinforce = board.verifyTerritorySelected();

            if (territoryReinforce.getPlayer() != player) {
                return;
            }

            board.reinforce();
            reinforceDone = true;
        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }
}