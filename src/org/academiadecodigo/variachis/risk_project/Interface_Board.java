package org.academiadecodigo.variachis.risk_project;

public interface Interface_Board {

    //increase amount of troops at the beginning of the round
    public void increment();

    // decides the winner of a battle and decrease the troops
    public void battle();

    //put troops on the territory
    public void reinforce();

    // return the amount of cols on the grid
    public int checkLimitBoardCol();

    //return the amount of rows on the grid
    public int checkLimitBoardRow();

    // returns true when a player has no territories
    public boolean victory(Player player1, Player player2);

    //Channges the pointer for the owner of this territory
    public void changePlayerTerritory(Player player, Territory territory);

    boolean allowsMovement(Movement movement);
}