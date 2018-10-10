package org.academiadecodigo.variachis.risk_project;

public class GameLogic {

    private Grid grid;

    private Player p1;
    private Player p2;
    private Player activePlayer;
    private Movement moveChoice;

    private Board board;
    private Territory territory;
    private Territory[][] territoryArray;

    private boolean attackDone = false;
    private boolean reinforceDone = false;

    public void init() {

        grid = new Grid(3, 3);
        board = new Board(grid, 3, 3);
        territoryArray = board.getTerritories();
        grid.init(territoryArray); //corrigir: ao fecharmos esta janela, o processo não encerra logo.

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setBoard(board);
        keyboard.runKeyboard();

        this.p1 = new Player("Blue");
        this.p2 = new Player("Red");

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        grid.showNumberSoldiers();
        grid.movementImage();
    }

    public void start() {

        int rounds = 1;
        for (int i = 0; i < 11; i++) { //why 11? because if it's not 11, the for doesn't stop. this is a quick fix.
            round(p1);
            //    System.out.println("round: " + rounds);
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

    public void round(Player player) {

        //Phase 1 - increment, uses the method increment from the board
        //to increment the troops in territories that have the player that is playing this round
        board.increment(player);

        // while (!attackDone) {
        attack();

        reinforce(player);

        activePlayer = activePlayer == p1 ? p2 : p1;
    }

    public void attack() {

        while (!attackDone) {

            board.moveToTerritory(moveChoice);

            //check if territoryArray that we are attacking from has more than 1 soldier
            //if (board.verifyTerritorySelected().getSoldiers() <= 1) {
            //System.out.println(board.verifyTerritorySelected());
            if (board.getTerritoryOrigin().getSoldiers() <= 1) {  /** PERHAPS CREATE GETTER IN BOARD **/
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

    public void reinforce(Player player) {

        while (!reinforceDone) {
            board.moveToTerritory(moveChoice);
            //Check if the territoryArray that we are reinforcing from has more than 1 soldier
            if (board.getTerritoryOrigin().getSoldiers() <= 1) {
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

    public void movePlayer(Movement movement) {
        moveChoice = movement;
    }
    
    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }
}