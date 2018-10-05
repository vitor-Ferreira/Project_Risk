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

    // add 1 troop to every territory who has a player owner
    @Override
    public void increment(Player player) {
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
        int attack = territoryAttack.getSoldiers() - 1;
        int defend = territoryDefend.getSoldiers();
        territoryAttack.guardianSoldier();// waiting for method name to put soldier on territory = 1

        if (attack > defend) {
            territoryDefend.setSoldiersIn(attack - defend);
            changePlayerTerritory(territoryAttack.getPlayer(), territoryDefend);
            return;
        }
        if (defend > attack) {
            territoryDefend.setSoldiersIn(defend - attack);
        }
    }

    @Override
    //not done yet
    public boolean victory(Player player1, Player player2) {
        int countP1 = 0;
        int countP2 = 0;
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            if (territoriesArray[i].getPlayer().equals(player1)) {
                countP1++;
                continue;
            }
            if (territoriesArray[i].getPlayer().equals(player2)) {
                countP2++;
                continue;
            }
        }
        if (countP1 == 0 || countP2 == 0) {
            return true;
        }
        return false;
    }

    @Override
    //change the owner of a territory
    public void changePlayerTerritory(Player player, Territory territory) {
        territory.setPlayer(player);
    }

    //intantiates each territory using a grid filosofy
    public void territoryMaker() {

        for (int i = 0; i < territoriesArray.length - 1; i++) {
            territoriesArray[i] = new Territory(i, 1);// territory(Row, Col)
        }
    }

    // get the array with the territories
    public Territory[] getTerritory() {
        return this.territoriesArray;
    }

    //finds the selected territory and verifies if is in the border of the game board
    @Override
    public boolean allowsMoviment(Movement movement) {
        Territory territory;
        for (int i = 0; i < territoriesArray.length; i++) {
            if (territoriesArray[i].isSelected()) {
                territory = territoriesArray[i];
                switch (movement) {
                    case DOWN:
                        if (numRows == territory.getRow()) {
                            return false;
                        }
                    case UP:
                        if (numRows == 0) {
                            return false;
                        }
                    case LEFT:
                        if (numCols == 0) {
                            return false;
                        }
                    case RIGHT:
                        if (numCols == territory.getCol()) {
                            return false;
                        }
                }
            }
        }
        return true;
    }
}
