package org.academiadecodigo.variachis.risk_project;

public class Player {

    private String color;
    private int soldiersOnTerritory;
    private int totalSoldiers;
    private Territory initialTerritory;
    private Territory currentTerritories;
    private int numberOfTerritories;

    public Player(String color, int initialSoldiers, Territory random) {

        this.color = color;
        this.totalSoldiers = initialSoldiers;
        this.initialTerritory = random;
        this.initialTerritory.setSoldiersIn(initialSoldiers);
        this.numberOfTerritories = 1;
    }

    public int checkSoldiersOnTerritory(Territory territory) {


    }

    public int getTotalSoldiers() {

        return totalSoldiers;
    }

    public Territory getInicialTerritory() {

        return initialTerritory;
    }


    public Territory getCurrentTerritories() {

        return currentTerritories;
    }

    public void addCurrentTerritory(Territory territory) { //add newly conquered terr. to list

        currentTerritories = territory; //
        numberOfTerritories += 1;

    }

    public int getNumberTerritories() {

        return numberOfTerritories;
    }

    public Territory move(Territory territory, int soldiers) {


    }

    public Territory attack (Territory territory, int soldiers) {


    }

    public Territory reinforce(Territory fromTerritory, Territory toTerritory, int soldiers) {


    }
}