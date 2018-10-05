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
    public void increment() {// add 1 troop to every territory who has a player owner
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            if(territoriesArray[i].getPlayer()!=null){
            territoriesArray[i].setSoldiersIn(1);}
        }
    }

    @Override
    public int putTroops(int amount, Territory territory) {
        territory.setSoldiersIn(amount);
        return amount;
    }

    @Override
    public void battle(Territory territoryAttack, Territory territoryDefend) {
        int attack = territoryAttack.getSoldiers();
        int defend = territoryDefend.getSoldiers();
    }

    @Override
    public boolean victory() {
        return false;
    }

    @Override
    public void changePlayerTerritory(Player player, Territory territory) {
        territory.setPlayer(player);
    }

    public void territoryMaker() {
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            territoriesArray[i] = new Territory(i, 1);// territory(Row, Col)
        }
    }
}
