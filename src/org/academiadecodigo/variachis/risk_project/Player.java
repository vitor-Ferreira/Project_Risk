package org.academiadecodigo.variachis.risk_project;

public class Player {

    private String color;
    //private int soldiersOnTerritory;
    private int totalSoldiers;
    private Territory initialTerritory;
    private Territory currentlyOwnedTerritories;
    private int numberOfCurrentlyOwnedTerritories;
    private Territory currentlySelectedTerritory;

    public Player(String color, int initialSoldiers, Territory randomInitialTerritory) {

        this.color = color;
        this.totalSoldiers = initialSoldiers;
        this.initialTerritory = randomInitialTerritory;
        this.initialTerritory.setSoldiersIn(initialSoldiers);
        this.numberOfCurrentlyOwnedTerritories = 1;
    }

    /* public int checkSoldiersOnTerritory(Territory territory) {

        return
    } */

    public int getTotalSoldiers() {

        return totalSoldiers;
    }

    public void setTotalSoldiers(int addToTotalSoldiers) {

        this.totalSoldiers += addToTotalSoldiers;
    }

    public Territory getInitialTerritory() {

        return initialTerritory;
    }

    public Territory getCurrentlyOwnedTerritories() { //get current total territories

        return currentlyOwnedTerritories;
    }

    public void addConqueredTerritory(Territory territory) { //add newly conquered territory to list (not yet done) and counter

        numberOfCurrentlyOwnedTerritories += 1;

    }

    public int getNumberTerritories() {

        return numberOfCurrentlyOwnedTerritories;
    }

    ///// ----- ///// ----- ///// ----- /////

    /* public void move(Movement movement, Territory territory) { //for now, all soldiers-1 are moved


    } */

    public void attack(Player player, Movement movement) { //attack what territory

        gameLogic.attack(this, movement);
    }

    public void reinforce(Player player, Movement movement, int soldiers) { //reinforce what territory, reinforce with how many soldiers

        gameLogic.reinforce(this, movement, soldiers);
    }

    /* public void select() {


    } */
}

