package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private int numRows;
    private int numCols;
    private Territory[] territoriesArray;
    private Territory territoryOrigin;// territory destiny when player moves


    public Board(int numRows, int numCols) {
        this.numCols = numCols;
        this.numRows = numRows;
        territoriesArray = new Territory[numRows];
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
    public void reinforce(Territory territoryReinforce) {
        territoryReinforce.setSoldiersIn(territoryOrigin.getSoldiers() - 1);
        territoryOrigin.setSoldiersOut(1);

    }

    @Override
    public void battle(Territory territoryAttack) {
        int attack = territoryOrigin.getSoldiers() - 1;
        int defend = territoryAttack.getSoldiers();

        territoryOrigin.guardianSoldier();//put 1 soldier

        if (attack > defend) {
            territoryAttack.setSoldiersIn(attack - defend);
            System.out.println(territoryAttack.getSoldiers());
            changePlayerTerritory(territoryAttack.getPlayer(), territoryAttack);
            return;
        }
        if (defend > attack) {
            territoryAttack.setSoldiersIn(defend - attack);
        }
    }

    @Override
    //verify victory condition
    public boolean victory(Player player1, Player player2) {
        int countP1 = 0;
        int countP2 = 0;
        for (int i = 0; i < territoriesArray.length; i++) {

            if (territoriesArray[i].getPlayer() == null) {

            }
            if (territoriesArray[i].getPlayer() == player1) {
                countP1++;
            }
            if (territoriesArray[i].getPlayer() == player2) {
                countP2++;
            }
        }
        if (countP1 == 0 || countP2 == 0) {
            System.out.println("VICToRYYYY");
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
        for (int i = 0; i < territoriesArray.length; i++) {
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
                        if (numCols == territory.getColumn()) {
                            return false;
                        }
                }
            }
            moveToTerritory(movement);
            return true;
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

    //allows movement;
    public void moveToTerritory(Movement movement) {
        Territory territory = verifyTerritorySelected();
        territoryOrigin = territory;
        switch (movement) {
            case UP:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territoriesArray[i] == territory) {
                        territory.unselect();
                        territoriesArray[i + 1].select();
                        return;
                    }
                }
            case DOWN:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territoriesArray[i] == territory) {
                        if (i != 0) {
                            territory.unselect();
                            territoriesArray[i - 1].select();
                            return;
                        }
                    }
                }
            case RIGHT:
                /*for (int i = 0; i < territoriesArray.length; i++) {
                        if (territoriesArray[i] == territory) {
                            territory.unselect();
                            territoriesArray[i + 4].select();
                            return;*/
                return;


            case LEFT:
                /*for (int i = 0; i < territoriesArray.length; i++) {
                    if (territoriesArray[i] == territory) {
                        territory.unselect();
                        territoriesArray[i - 4].select();
                        return;
                    }
                }

        }*/
                return;

        }
    }

    public void addTerritoryToP1(Player player) {

        territoriesArray[0].select();
        territoriesArray[0].setPlayer(player);
        territoriesArray[0].setSoldiersIn(20);

    }

    public void addTerritoryToP2(Player player) {

        territoriesArray[2].setPlayer(player);
        territoriesArray[2].setSoldiersIn(20);
    }

    public void beginRoundP1() {
        territoriesArray[0].select();
    }

    public void beginRoundP2() {
        territoriesArray[2].select();
    }

    /*  public void moveToTerritory(Movement movement) {
        Territory territory = verifyTerritorySelected();
        territoryOrigin = territory;
        switch (movement) {
            case UP:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territory.getRow() == territoriesArray[i].getRow() + 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        return;
                    }
                }
            case DOWN:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territory.getRow() == territoriesArray[i].getRow() - 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        System.out.println();
                        return;
                    }
                }
            case RIGHT:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territory.getColumn() == territoriesArray[i].getColumn() + 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        return;
                    }
                }
            case LEFT:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territory.getColumn() == territoriesArray[i].getColumn() - 1) {
                        territory.unselect();
                        territoriesArray[i].select();
                        return;
                    }
                }

        }
    }*/
}
