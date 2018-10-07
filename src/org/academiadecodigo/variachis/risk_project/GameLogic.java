package org.academiadecodigo.variachis.risk_project;

public class GameLogic {


    private Player p1;//Ver territorios
    private Player p2;
    private Board board;
    private boolean attackDone = false;
    private boolean reinforceDone = false;
    private Territory territory;
    private Territory[] territoryArray;


    public GameLogic() {
        board = new Board(3, 1);
        territoryArray = board.getTerritory();
        this.p1 = new Player("Red");// check territoryArray
        this.p2 = new Player("Blue");// check territoryArray
        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
    }

    public Movement mov() {
        int mov = (int) Math.floor(Math.random() * 4);
        Movement movi;
        switch (mov) {
            case 0:
                movi = Movement.UP;
                break;
            case 1:
                movi = Movement.DOWN;
                break;
            case 2:
                movi = Movement.LEFT;
                break;
            default:
                movi = Movement.RIGHT;
        }
        return movi;
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


        Movement movi = mov();
        Movement movement = player.move(movi);
        System.out.println("move " + movi);

        while (!attackDone) {

            //check if territoryArray that we are attacking from have +1 troop

            /*if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                //choose another territoryArray to attack from
                System.out.println("verify if territory only 1");
                return;
            }*/


            //gets the territoryArray (movement) that the player wants to attack (defined in Player).


            //check if the movement is allowed
            if (!board.allowsMovement(movement)) {

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


    }

    public void reinforce(Player player) {

        Movement movi = mov();
        Movement movement = player.move(movi);


        while (!reinforceDone) {

            //Check if the territoryArray that we are reinforcing from has more than 1 soldier
            if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                return;
            }

            //gets the territoryArray (movement) that the player wants to reinforce (defined in Player).

            if (!board.allowsMovement(movement)) {


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


}