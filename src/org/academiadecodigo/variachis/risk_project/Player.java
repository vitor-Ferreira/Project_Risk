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

    public Territory getCurrentTerritories() {

        return currentTerritories;
    }

    public void addConqueredTerritory(Territory territory) { //add newly conquered terr. to list

         = territory; // <--
        numberOfTerritories += 1;

    }

    public int getNumberTerritories() {

        return numberOfTerritories;
    }

    ///// ----- ///// ----- ///// ----- /////

    /* public Territory move(Movement movement, Territory territory) { //for now, all soldiers-1 are moved



        .putSoldiersIn(soldiers); //just "territory" at the beginning doesn't make sense

    } */

    public Territory attack(Territory fromTerritory, Territory toTerritory) {

        

        board.battle;
    }

    public Territory reinforce(Territory fromTerritory, Territory toTerritory, int soldiers) {



        .setSoldiersOut(soldiers);
        .setSoldiersIn(soldiers);


    }
}