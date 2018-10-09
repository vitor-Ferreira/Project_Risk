package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameLogic {

    private Grid grid;
    private Player p1;//Ver territorios
    private Player p2;
    private Board board;
    private boolean attackDone = false;
    private boolean reinforceDone = false;
    private Territory territory;
    private Territory[] territoryArray;
    //Movement movement = Movement.LEFT;

    public GameLogic() {

        board = new Board(1, 3);
        grid = new Grid(1, 3);
        grid.init(); //corrigir: ao fecharmos esta janela, o processo não encerra logo.
        territoryArray = board.getTerritory();

        /* Picture militaryHelmet = new Picture(grid.columnToX(0), grid.rowToY(0), "/Users/codecadet/Desktop/Project_Risk/Resources/military_helmet-512.png");
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

    public void move(Movement movement) {
        //int move = (int) Math.floor(Math.random() * 4);
        Movement movi = movement;
        switch (movement) {
            /* case LEFT:
                movi = Movement.LEFT;
                break; */
            case UP:
                movi = Movement.UP;
                break;
            /* case RIGHT:
                movi = Movement.RIGHT;
                break; */
            case DOWN:
                movi = Movement.DOWN;
        }
    }

    public void countRounds() {

        int rounds = 1;


        //while (!board.victory(p1, p2)) {
        for (int i = 0; i < 11; i++) {   /** why 11 ? **/

            //round(p1);

            System.out.println("rounds: " + rounds);
            attackDone = false;

            if (rounds % 2 == 0) {
                System.out.println("p2");
                board.beginRoundP2();
                round(p2);
                rounds++;
            }

            board.beginRoundP1();
            System.out.println("p1");
            round(p1);
            rounds++;
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

            //check if territoryArray that we are attacking from have +1 troop

            /*if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                //choose another territoryArray to attack from
                System.out.println("verify if territory only 1");
                return;
            }*/


            //gets the territoryArray (movement) that the player wants to attack (defined in Player).


            //check if the movement is allowed
            //board.moveToTerritory(movement);

            //if not allowed return choose new movement
            //needs to keep checking if new move is allowed...
            return;
        }


        //check if territories have different owners

        Territory territoryAttack = board.verifyTerritorySelected();

        if (territoryAttack.getPlayer() == player) {

            return;
        }

        board.battle(); // Changes in the board.battle method
        attackDone = true;
    }

    public void reinforce(Player player) {

        //Movement movement = player.move(movi); //??????????

        while (!reinforceDone) {

            //Check if the territoryArray that we are reinforcing from has more than 1 soldier
            if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                return;
            }

            //gets the territoryArray (movement) that the player wants to reinforce (defined in Player).

            //if (!board.allowsMovement(movement)) {

            return;

        }

        Territory territoryReinforce = board.verifyTerritorySelected();

        if (territoryReinforce.getPlayer() != player) {
            board.reinforce();
            reinforceDone = true;
        }

        //check if the territoryArray belongs to the same player
        //needs to get the territoryArray that you want to move your troops to, and check if its allowed
        //check if there is +1 troop
    }
}
