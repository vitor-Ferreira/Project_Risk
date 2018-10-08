package org.academiadecodigo.variachis.risk_project;

public interface Interface_Board {

    //put troops on the territory
    public void reinforce();

    //increase amount of troops at the beginning of the round
    public void increment(Player player);

    // return the amount of cols on the grid
    public int limitBoardCol();

    //return the amount of rows on the grid
    public int limitBoardRow();

    // decides the winner of a battle and decrease the troops
    public void battle();

    // returns true when a player has no territories
    public boolean victory(Player player1, Player player2);

    //Channges the pointer for the owner of this territory
    public void changePlayerTerritory(Player player, Territory territory);

    //allows reading access by the game class
    Territory[] getTerritory();

    boolean allowsMovement(Movement movement);


}
