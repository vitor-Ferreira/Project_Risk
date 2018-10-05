package org.academiadecodigo.variachis.risk_project;

public interface Interface_Board {

    public int putTroops(int amount, Territory territory);//put troops on the territory

    public void increment(Player player); //increase amount of troops at the beginning of the round

    public int limitBoardCol();// return the amount of cols on the grid

    public int limitBoardRow();//return the amount of rows on the grid

    public void battle(Territory territoryAttack, Territory territoryDefend); // decides the winner of a battle and decrease the troops

    public boolean victory(); // returns true when a player has no territories

    public void changePlayerTerritory(Player player, Territory territory);

    public Territory[] getTerritory();


}
