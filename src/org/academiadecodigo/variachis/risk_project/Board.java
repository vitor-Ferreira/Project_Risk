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
        System.out.println(numCols);
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
        System.out.println("destiny: " + territoryDestiny.getSoldiers());
        System.out.println("origin: " + territoryOrigin.getSoldiers());
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
        System.out.println("atackTroops " + attackTroops);//--______________________sout here
        int defendTroops = territoryDestiny.getSoldiers() - attackTroops;
        System.out.println("defenderTroops " + defendTroops);//--______________________sout here
        if (attackTroops == defendTroops) {
            territoryDestiny.afterBattle(1); //adds the result of battle on the territory
            territoryOrigin.setSoldiersOut(attackTroops);
            System.out.println(" Draw - Territory destiny troops: " + territoryDestiny.getSoldiers());
            return;
        }
        if (attackTroops > defendTroops) {
            int newAmount = attackTroops - defendTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            System.out.println(territoryDestiny.getPlayer().getColor());
            territoryDestiny.setPlayer(territoryOrigin.getPlayer());
            System.out.println(territoryOrigin.getPlayer().getColor());
            territoryOrigin.setSoldiersOut(attackTroops);
            System.out.println("Attack wins - Territory destiny troops: " + territoryDestiny.getSoldiers());
            return;
        }
        if (attackTroops < defendTroops) { //Correct condition, error due to the fact we only have 3 territories.
            int newAmount = defendTroops - attackTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            territoryOrigin.setSoldiersOut(attackTroops);
            System.out.println("new ammount: " + newAmount);//_______________________SOUT HERE
            System.out.println("Defense wins - Territory destiny troops: " + territoryDestiny.getSoldiers());
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
                System.out.println(territoriesArray[i][j] + ":::" + i + j);
                System.out.println(territoriesArray[i][j].getSoldiers());

                //we will need to do a for inside a for to create a map like a 3x3
            }
        }
    }

    // get the array with the territories
    public Territory[][] getTerritories() {
        System.out.println(territoriesArray);
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
        for (int i = 0; i < numCols - 1; i++) {
            for (int j = 0; j < numRows - 1; j++) {
                if (territoriesArray[i][j].isSelected()) {
                    territory = territoriesArray[i][j];
                }
            }
        }
        return territory;
    }

    //move the player
    public void moveToTerritory(Movement movement) {
        System.out.println("sadasdasdasdasdasdasd");
        Territory territory = verifyTerritorySelected();//verify which territory is selected
        territoryOrigin = territory;
        numberSoldiersAttacking = territory.getSoldiers();
        System.out.println("dfdfdfdfdf");
        switch (movement) {

            case LEFT:
                grid.moveLeft();

            case UP:
                grid.moveUp();
                if (territory.getRow() > 0) {
                    if (territoriesArray[territory.getColumn()][territory.getRow() - 1].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn()][territory.getRow() - 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();

                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() - 1];
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].select();
                    territory.unselect();

                    System.out.println("destiny " + territoryDestiny.getSoldiers());
                    System.out.println("Attack " + numberSoldiersAttacking);
                    //int i = territory.getRow() - 1;

                    //numberSoldiersDefending = territoriesArray[territory.getRow() - 1].getSoldiers();
                    return;
                }
                territory.select();
                System.out.println("ffffffffffffffffffff");
                return;

            case RIGHT:
                grid.moveRight();

            case DOWN:
                System.out.println("trtrtrtrtrtttttttttt");
                grid.moveDown();
                if (territory.getRow() < numRows - 1) {
                    if (territoriesArray[territory.getColumn()][territory.getRow() + 1].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn()][territory.getRow() + 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].setSoldiersIn(territory.getSoldiers() + 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() + 1];
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].select();
                    territory.unselect();

                    //int i = territory.getRow() + 1;
                    //System.out.println("destiny " + territoryDestiny.getSoldiers());
                    //System.out.println("Attack " + numberSoldiersAttacking);
                    System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());

                    //numberSoldiersDefending = territoriesArray[territory.getRow() + 1].getSoldiers();
                    return;
                }
                territory.select();
                System.out.println("fddfffffffff");
                return;
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

        territoriesArray[1][2].setPlayer(player);
        System.out.println(territoriesArray[1][2]);
        territoriesArray[1][2].setSoldiersIn(20);
        //System.out.println(territoriesArray[1][2].getPlayer().getColor());
    }

    public void beginRoundP1() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[0][0].select();
        territoryOrigin = territoriesArray[0][0];
    }

    public void beginRoundP2() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[1][2].select();
        territoryOrigin = territoriesArray[1][2];

    }
}