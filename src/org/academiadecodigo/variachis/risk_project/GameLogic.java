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
        grid = new Grid(3, 3); //Board may also needs to know a Grid instance.
        board = new Board(grid, 3, 3);
        territoryArray = board.getTerritories();
        grid.init(territoryArray); //corrigir: ao fecharmos esta janela, o processo não encerra logo.

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setBoard(board);
        keyboard.runKeyboard();

        this.p1 = new Player("Red");// check territoryArray
        this.p2 = new Player("Blue");// check territoryArray

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        grid.showNumberSoldiers();
        grid.movementImage();
    }

    public void start() {

        int rounds = 1;

        while (!board.victory(p1, p2)) {
            for (int i = 0; i < 11; i++) { //why 11? because if it's not 11, the for doesn't stop. this is a quick fix.

                round(p1);

            //    System.out.println("rounds: " + rounds);
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