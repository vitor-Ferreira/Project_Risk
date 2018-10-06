package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private int numRows;
    private int numCols;
    private Territory[] territoriesArray;
    private Territory territoryDestiny;// territory destiny when player moves


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
    public int reinforce(Territory territoryReinforce) {// do reinforce-------------------------------------------
        territoryDestiny.setSoldiersIn(territoryReinforce.getSoldiers()-1);
        

        territory.setSoldiersIn(amount);
        return amount;
    }

    @Override
    public void battle(Territory territoryAttack) {// find a way to know how we find the territoriy attaker and defender
        int attack = territoryAttack.getSoldiers() - 1;
        int defend = territoryDestiny.getSoldiers();
        territoryAttack.guardianSoldier();//put 1 soldier

        if (attack > defend) {
            territoryDestiny.setSoldiersIn(attack - defend);
            changePlayerTerritory(territoryAttack.getPlayer(), territoryDestiny);
            return;
        }
        if (defend > attack) {
            territoryDestiny.setSoldiersIn(defend - attack);
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

    //instantiates each territory using a grid filosofy
    public void territoryMaker() {
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            territoriesArray[i] = new Territory(i, 0);// territory(Row, Col)
        }
    }

    // get the array with the territories
    public Territory[] getTerritory() {
        return this.territoriesArray;
    }

    //finds the selected territory and verifies if is in the border of the game board
    @Override
    public boolean allowsMovement(Movement movement) {
        Territory territory;
        for (int i = 0; i < territoriesArray.length - 1; i++) {
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
                        if (numCols == territory.getColumn()) {
                            return false;
                        }
                }
            }
        }
        return true;
    }


    //finds the territorie who is selected
    public Territory verifyTerritorySelected() {
        Territory territory = territoriesArray[0];
        for (int i = 0; i < territoriesArray.length - 1; i++) {
            if (territoriesArray[i].isSelected()) {
                territory = territoriesArray[i];
            }
        }
        return territory;
    }

    public void moveToTerritory(Movement movement) {
        Territory territory = verifyTerritorySelected();
        switch (movement) {
            case UP:
                for (int i = 0; i < territoriesArray.length - 1; i++) {
                    if (territory.getRow() == territoriesArray[i].getRow() + 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        territoryDestiny =territoriesArray[i];
                        return;
                    }
                }
            case DOWN:
                for (int i = 0; i < territoriesArray.length - 1; i++) {
                    if (territory.getRow() == territoriesArray[i].getRow() - 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        territoryDestiny =territoriesArray[i];
                        return;
                    }
                }
            case RIGHT:
                for (int i = 0; i < territoriesArray.length - 1; i++) {
                    if (territory.getColumn() == territoriesArray[i].getColumn() + 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        territoryDestiny =territoriesArray[i];
                        return;
                    }
                }
            case LEFT:
                for (int i = 0; i < territoriesArray.length - 1; i++) {
                    if (territory.getColumn() == territoriesArray[i].getColumn() - 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        territoryDestiny =territoriesArray[i];
                        return;
                    }
                }

        }
    }
}
