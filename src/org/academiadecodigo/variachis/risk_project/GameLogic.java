package org.academiadecodigo.variachis.risk_project;

public class GameLogic implements Interface_Board {


    private Player p1;
    private Player p2;
    private Board board;


    public void round(Player player) {

        //Phase 1 - increment, uses the method increment from the board
        //to increment the troops in territories that have the player that is playing this round
        board.increment(player);


        //gets the territory (movement) that the player wants to attack.
        Territory movement = p1.move();

        //check if the movement is allowed
        if(!board.allowsMoviment(movement)){

            //if not allowed return choose new movement
            //needs to keep chacking if new move is allowed...
        }



        //check if territories have different owners
        Territory[] map = board.getTerritory();
        for(int i = 0; i<map.length; i++)
        {
            if(map[i].getPlayer() == p1){
                p1.move();
            }
            player.attack;

        }

        //check if territory that we are attacking from have +1 troop
        if(movement.getSoldiers() <= 1){
            //choose another territory to attack from
        }
        
        player.attack();

        //check if the territory belongs to the same player
        //needs to get the territory that you want to move your troops to, and check if its allowed
        //check if there is +1 troop
        player.reinforce();
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

}