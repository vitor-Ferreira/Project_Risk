package org.academiadecodigo.variachis.risk_project;

public class Player {

    private String color;
    //private int soldiersOnTerritory;
    private int totalSoldiers;
    private Territory initialTerritory;
    private Territory currentTerritories;
    private int numberOfTerritories;

    public Player(String color, int initialSoldiers, Territory randomInitialTerritory) {

        this.color = color;
        this.totalSoldiers = initialSoldiers;
        this.initialTerritory = randomInitialTerritory;
        this.initialTerritory.setSoldiersIn(initialSoldiers);
        this.numberOfTerritories = 1;
    }

    /* public int checkSoldiersOnTerritory(Territory territory) {


    } */

    public int getTotalSoldiers() {

        return totalSoldiers;
    }

    public void setTotalSoldiers(int addTotalSoldiers) {

        this.totalSoldiers += addTotalSoldiers;
    }

    public Territory getInitialTerritory() {

        return initialTerritory;
    }

    public Territory getCurrentTerritories() { //get current total territories

        return currentTerritories;
    }

    public void addConqueredTerritory(Territory territory) { //add newly conquered territory to list (not yet done) and counter

        numberOfTerritories += 1;

    }

    public int getNumberTerritories() {

        return numberOfTerritories;
    }

    ///// ----- ///// ----- ///// ----- /////

    public void move(Movement movement, Territory territory) { //for now, all soldiers-1 are moved


    }

    public void attack(Territory toTerritory) {


    }

    public void reinforce(Territory fromTerritory, Territory toTerritory, int soldiers) {


    }
}