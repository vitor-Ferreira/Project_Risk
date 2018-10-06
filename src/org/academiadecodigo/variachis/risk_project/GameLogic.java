package org.academiadecodigo.variachis.risk_project;

public class GameLogic implements Interface_Board {


    private Player p1;//Ver territorios
    private Player p2;
    private Board board = new Board(3, 1);
    private boolean attackDone = false;
    private boolean reinforceDone = false;

    private Territory[] territory = board.getTerritory();

    public GameLogic() {
        this.p1 = new Player("Red", 20, );// check territory
        this.p2 = new Player("Blue", 20, );// check territory

    }

    public void countRounds() {

        int rounds = 1;

        while (!board.victory(p1, p2)) {

            attackDone = false;

            if (rounds % 2 == 0) {
                round(p2);
                return;
            }

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

        Movement movement = player.move();

        while(!attackDone) {

            //check if territory that we are attacking from have +1 troop

            if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                //choose another territory to attack from
                return;
            }


            //gets the territory (movement) that the player wants to attack (defined in Player).


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

            board.battle(territoryAttack); // Changes in the board.battle method
            attackDone = true;
        }


    }

    public void reinforce(Player player) {

        Movement movement = player.move();

        while (!reinforceDone) {

            //Check if the territory that we are reinforcing from has more than 1 soldier
            if (board.verifyTerritorySelected().getSoldiers() <= 1) {
                return;
            }

            //gets the territory (movement) that the player wants to reinforce (defined in Player).

            if (!board.allowsMovement(movement)) {

                movement = player.move();
                return;

            }

            Territory territoryReinforce = board.verifyTerritorySelected();

            if (territoryReinforce.getPlayer() != player) {
                board.reinforce();
                reinforceDone = true;
            }


            //check if the territory belongs to the same player
            //needs to get the territory that you want to move your troops to, and check if its allowed
            //check if there is +1 troop
        }
    }


    @Override
    public int putTroops(int amount, Territory territory) {
        return 0;
    }

    @Override
    public void increment(Player player) {
        board.increment(player);
    }

    @Override
    public int limitBoardCol() {
        return 0;
    }

    @Override
    public int limitBoardRow() {
        return 0;
    }

    @Override
    public void battle(Territory territoryAttack, Territory territoryDefend) {
        board.battle(territoryAttack, territoryDefend);

    }

    @Override
    public void changePlayerTerritory(Player player, Territory territory) {

    }

    @Override
    public boolean victory() {
        return false;
    }

    @Override
    public Territory[] getTerritory() {
        return new Territory[0];
    }
}