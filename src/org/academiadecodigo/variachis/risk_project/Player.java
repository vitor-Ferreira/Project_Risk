package org.academiadecodigo.variachis.risk_project;

public class Player {

    private String color;
    private int soldiers;
    private Territory currentTerritory;
    private int numberTerritories;

    public Player(String color, int soldiers, Territory random) {
        this.color = color;
        this.soldiers = soldiers;
        currentTerritory = random;
        numberTerritories = 1;
        currentTerritory.setSoldiersIn(soldiers);
    }

    public void setCurrentTerritory(Territory t) {
        currentTerritory = t;

    }

    public Territory getInicialTerritory() {
        return currentTerritory;
    }


    public Territory getCurrentTerritory() {
        return currentTerritory;
    }


}
