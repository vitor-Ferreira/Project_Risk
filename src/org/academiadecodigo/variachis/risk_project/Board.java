package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private Grid grid;
    private int numCols;
    private int numRows;
    private Territory[][] territoriesArray;
    private Territory territoryOrigin;// territory destiny when player moves
    private Territory territoryDestiny;
    private int numberSoldiersAttacking;
    //private int numberSoldiersDefending;

    public Board(Grid grid, int numCols, int numRows) {
        this.grid = grid;
        this.numCols = numCols;
        this.numRows = numRows;
        territoriesArray = new Territory[numCols][numRows];
        territoryMaker();
    }

    @Override
    public int limitBoardCol() {
        //System.out.println(numCols);
        return numCols;

    }

    @Override
    public int limitBoardRow() {
        return numRows;
    }

    // add 1 troop to every territory who has a player owner
    @Override
    public void increment(Player player) {
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++)
                if (territoriesArray[i][j].getPlayer() == player) {
                    //System.out.println("Soldiers before increment: " + territoriesArray[i][j].getSoldiers());
                    territoriesArray[i][j].setSoldiersIn(1);
                    // System.out.println("Soldiers after increment: " + territoriesArray[i][j].getSoldiers());
                }
        }
    }

    @Override
    public void reinforce() {
        // territoryDestiny.setSoldiersIn(territoryDestiny.getSoldiers() + territoryOrigin.getSoldiers() - 1);

        //territoryOrigin.guardianSoldier();

        /* In case you want to replace the guardianAngel
        int originSoldiersLeft = territoryOrigin.getSoldiers() - (territoryOrigin.getSoldiers() - 1 );
        territoryOrigin.setSoldiersIn(originSoldiersLeft);*/

        //System.out.println("territory origin " + territoryOrigin.getSoldiers());//_______________________SOUT HERE
        //System.out.println("territory target " + territoryDestiny.getSoldiers());//_______________________SOUT HERE
    }

    @Override
    public void battle() {
        int attackTroops = numberSoldiersAttacking - 1;//territoryOrigin.getSoldiers() - 1;
        // System.out.println("attack troops: " + attackTroops);
        int defendTroops = territoryDestiny.getSoldiers() - attackTroops;
        // System.out.println("defend troops: " + defendTroops);
        if (attackTroops == defendTroops) {
            territoryDestiny.afterBattle(1); //adds the result of battle on the territory
            // System.out.println("territory destiny soldiers " + territoryDestiny.getSoldiers());
            territoryOrigin.setSoldiersOut(attackTroops);
            //  System.out.println("territory origin soldiers " + territoryOrigin.getSoldiers());
            return;
        }
        if (attackTroops > defendTroops) {
            territoryOrigin.setSoldiersOut(attackTroops);
            //  System.out.println("territory origin soldiers " + territoryOrigin.getSoldiers());
            int newAmount = attackTroops - defendTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            //   System.out.println("territory destiny soldiers " + territoryDestiny.getSoldiers());
            territoryDestiny.setPlayer(territoryOrigin.getPlayer());
            //   System.out.println("territory destiny soldiers " + territoryDestiny.getSoldiers());
            return;
        }
        if (attackTroops < defendTroops) { //Correct condition, error due to the fact we only have 3 territories.
            territoryOrigin.setSoldiersOut(attackTroops);
            //  System.out.println("territory origin soldiers " + territoryOrigin.getSoldiers());
            int newAmount = defendTroops - attackTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            //   System.out.println("territory destiny soldiers " + territoryDestiny.getSoldiers());
        }
    }

    @Override
    //verify victory condition
    public boolean victory(Player player1, Player player2) {
       /* int countP1 = 0;
        int countP2 = 0;
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                if (territoriesArray[i][j].getPlayer() == null) {

                }
                if (territoriesArray[i][j].getPlayer() == player1) {
                    countP1++;
                }
                if (territoriesArray[i][j].getPlayer() == player2) {
                    countP2++;
                }
            }
            if (countP1 == 0 || countP2 == 0) {
                System.out.println("VICToRYYYY");//_______________________SOUT HERE
                return true;
            }
            return false;
        }*/
        return false;//??????????????????????????
    }

    @Override
    //change the owner of a territory
    public void changePlayerTerritory(Player player, Territory territory) {
        territory.setPlayer(player);
    }

    //instantiates each territory using a grid filosofy
    public void territoryMaker() {


        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                territoriesArray[i][j] = new Territory(i, j);// territory(Col, Row)
                // System.out.println(territoriesArray[i][j] + ":::" + i + j);
                // System.out.println(territoriesArray[i][j].getSoldiers());

                //we will need to do a for inside a for to create a map like a 3x3
            }
        }
    }

    // get the array with the territories
    public Territory[][] getTerritories() {
        //  System.out.println(territoriesArray);
        return this.territoriesArray;
    }

    //finds the selected territory and verifies if is in the border of the game board
    @Override
    public boolean allowsMovement(Movement movement) {
        return true;
    }


    //finds the selected territory
    public Territory verifyTerritorySelected() {
        Territory territory = null;
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                if (territoriesArray[i][j].isSelected()) {
                    territory = territoriesArray[i][j];
                }
            }
        }
        return territory;
    }

    //move the player
    public void moveToTerritory(Movement movement) {

        Territory territory = verifyTerritorySelected();//verify which territory is selected
        territoryOrigin = territory;
        numberSoldiersAttacking = territory.getSoldiers();
        switch (movement) {
            case LEFT:
                if (territory.getColumn() > 0) {
                    grid.moveLeft();
                    if (territoriesArray[territory.getColumn() - 1][territory.getRow()].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn() - 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    //  System.out.println(territory.getSoldiers());
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() - 1][territory.getRow()];
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].select();
                    territory.unselect();

                    //System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                    return;
                }
                territory.select();
                return;

            case UP:
                if (territory.getRow() > 0) {
                    grid.moveUp();
                    if (territoriesArray[territory.getColumn()][territory.getRow() - 1].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn()][territory.getRow() - 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();

                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() - 1];
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].select();
                    territory.unselect();

                    //  System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                    return;
                }
                territory.select();
                return;

            case RIGHT:
                if (territory.getColumn() < numCols - 1) {
                    grid.moveRight();
                    if (territoriesArray[territory.getColumn() + 1][territory.getRow()].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn() + 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    //    System.out.println(territory.getSoldiers());
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() + 1][territory.getRow()];
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].select();
                    territory.unselect();

                    //  System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                    return;
                }
                territory.select();
                return;

            case DOWN:
                if (territory.getRow() < numRows - 1) {
                    grid.moveDown();
                    if (territoriesArray[territory.getColumn()][territory.getRow() + 1].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn()][territory.getRow() + 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].setSoldiersIn(territory.getSoldiers() + 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() + 1];
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].select();
                    territory.unselect();

                    //  System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                }
                territory.select();
        }
    }

    public Territory getTerritoryOrigin() {
        return territoryOrigin;
    }

    public void addTerritoryToP1(Player player) {
        //System.out.println(territoriesArray[0][0]);
        territoriesArray[0][0].select();
        //System.out.println(territoriesArray[0][0]);
        territoriesArray[0][0].setPlayer(player);
        territoriesArray[0][0].setSoldiersIn(20);
        territoryOrigin = territoriesArray[0][0];
        //System.out.println(territoriesArray[0][0].getPlayer().getColor());
        //System.out.println(territoriesArray[0][0].getSoldiers());
    }

    public void addTerritoryToP2(Player player) {

        territoriesArray[numCols - 1][numRows - 1].setPlayer(player);
        //   System.out.println(territoriesArray[2][2]);
        territoriesArray[numCols - 1][numRows - 1].setSoldiersIn(20);
        //System.out.println(territoriesArray[1][2].getPlayer().getColor());
    }

    public void beginRoundP1() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[0][0].select();
        //territoryOrigin = territoriesArray[0][0];
    }

    public void beginRoundP2() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[numCols - 1][numRows - 1].select();
        //  territoryOrigin = territoriesArray[numCols-1][numRows-1];

    }
}