package org.academiadecodigo.variachis.risk_project;

public class Player {

    private String color;
    //private int soldiersOnTerritory;
    private int totalSoldiers;
    private Territory initialTerritory;
    //private CurrentlyOwnedTerritories[] currentlyOwnedTerritories; //list of the player's owned territories. not needed for now.
    //private int numberOfCurrentlyOwnedTerritories;
    private Territory currentlySelectedTerritory;

    public Player(String color, int initialSoldiers) {
        //maybe randomInitialTerritory is not needed here.
        //perhaps even the initialSoldiers int. All players start with the same number - 20 - after all.

        this.color = color;
        this.totalSoldiers = initialSoldiers;
        //this.initialTerritory = randomInitialTerritory;
//
    }

    /* public int checkSoldiersOnTerritory(Territory territory) {
        return
    } */

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTotalSoldiers() {
        return totalSoldiers;
    }

    public void setTotalSoldiers(int addToTotalSoldiers) {
        this.totalSoldiers += addToTotalSoldiers;
    }

    public Territory getInitialTerritory() {
        return initialTerritory;
    }

    public void setInitialTerritory(Territory initialTerritory) {
        this.initialTerritory = initialTerritory;
    }

    /* public String getCurrentlyOwnedTerritories() {
        //get current total territories.
        //not needed for now, as each territory knows who it belongs to.
        for (int i = 0; i < currentlyOwnedTerritories.length; i++) {
            System.out.println(currentlyOwnedTerritories[i]); // <--
        }
    } */

    /* public void addConqueredTerritory(Territory territory) { //add newly conquered territory to list (not yet done) and counter
        numberOfCurrentlyOwnedTerritories += 1;
    } */

    /* public int getNumberOfCurrentlyOwnedTerritories() {
        return numberOfCurrentlyOwnedTerritories;
    } */

    /* public void territorySelection() {


    } */

    ///// ----- ///// Movement Orders ///// ----- /////

    public Movement move(Movement movement) { //for now, allsoldiers-1 are moved

        return movement; //attention: keyboard input.
    }

    /* public void attack(Player player, Movement movement) { //attack what territory
        //attack should be on gameLogic

    }

    public void reinforce(Player player, Movement movement, int soldiers) { //reinforce what territory, reinforce with how many soldiers
        //reinforcement should be on gameLogic (also, for now, reinforcements moveAll-1, like the attack does)

    } */
}