package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private int numRows;
    private int numCols;
    private Territory[] territoriesArray;


    public Board(int numRows, int numCols) {
        this.numCols = numCols;
        this.numRows = numRows;
        territoryMaker();
    }

    @Override
    public int limitBoardCol() {
        return numCols;
    }

    @Override
    public int limitBoardRow() {
        return numRows;
    }

    @Override
    public void increment(Player player) {// add 1 troop to every territory who has a player owner
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            if (territoriesArray[i].getPlayer() == player) {
                territoriesArray[i].setSoldiersIn(1);
            }
        }
    }

    @Override
    public int putTroops(int amount, Territory territory) {
        territory.setSoldiersIn(amount);
        return amount;
    }

    @Override
    public void battle(Territory territoryAttack, Territory territoryDefend) {
        int attack = territoryAttack.getSoldiers()-1;
        int defend = territoryDefend.getSoldiers();

        //incomplet
    }

    @Override
    public boolean victory() {
        return false;
    }

    @Override
    public void changePlayerTerritory(Player player, Territory territory) {//change the owner of a territory
        territory.setPlayer(player);
    }

    public void territoryMaker() {
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            territoriesArray[i] = new Territory(i, 1);// territory(Row, Col)
        }
    }


    public Territory[] getTerritory() {// get the array with the territories
        return this.territoriesArray;
    }
}
