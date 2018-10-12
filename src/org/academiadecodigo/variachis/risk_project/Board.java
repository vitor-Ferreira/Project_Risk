package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private GameLogic game;
    private Grid grid;
    private int numCols;
    private int numRows;
    private Territory[][] territoriesArray;
    /**
     * Problema em Destiny: começa nulo!
     **/
    private int numberSoldiersAttacking;
    private Territory territoryOrigin;
    private Territory territoryDestiny;

    /**
     * ter 2 territoryOrigin e territoryDestiny e incializá-los logo aqui nas propriedades
     **/
    //private int numberSoldiersDefending;
    public Board(Grid grid, int numCols, int numRows) {
        this.grid = grid;
        this.numCols = numCols;
        this.numRows = numRows;
        territoriesArray = new Territory[numCols][numRows];
        territoryMaker();
        territoryOrigin = territoriesArray[2][2]; /** Problema em Origin: começa nulo! **/ //territory destiny when player moves
        territoryDestiny = territoriesArray[2][2];
    }

    @Override
    public int checkLimitBoardCol() {
        return numCols;
    }

    @Override
    public int checkLimitBoardRow() {
        return numRows;
    }

    // add 1 troop to every territory who has a player owner
    @Override
    public void increment() {
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                //if (territoriesArray[i][j].getPlayer()) {
                //System.out.println(“Soldiers before increment: ” + territoriesArray[i][j].getSoldiers());
                int rng = (int) (Math.random() * 10);
                if (rng == 0) {
                    rng = 1;
                }

                if (rng <= 1) {
                    territoriesArray[i][j].setSoldiersIn(3);
                } else if (rng > 1 && rng <= 4) {
                    territoriesArray[i][j].setSoldiersIn(2);
                } else if (rng > 4) {
                    territoriesArray[i][j].setSoldiersIn(1);
                }
                System.out.println("Territories soldiers Incremented. Sodiers now: " + territoriesArray[i][j].getSoldiers());
            }
        }
    }



   /* @Override
    public void increment() {
        System.out.println("method is being called");
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                if (territoriesArray[i][j].getPlayer() != null) {
                    System.out.println("adding one soldier to: " + territoriesArray[i][j]);
                    //System.out.println("Player :" + territoriesArray[i][j].getPlayer() + "col"+i+"row"+j);
                    //if (territoriesArray[i][j].getPlayer()) {
                    //System.out.println("Soldiers before increment: " + territoriesArray[i][j].getSoldiers());
                    territoriesArray[i][j].setSoldiersIn(1);
                }
            }
        }
    }*/

    @Override
    public void reinforce() {
        territoryDestiny.setSoldiersIn(territoryDestiny.getSoldiers() + territoryOrigin.getSoldiers() - 1);
        territoryOrigin.guardianSoldier();

        /* In case you want to replace the guardianAngel
        int originSoldiersLeft = territoryOrigin.getSoldiers() - (territoryOrigin.getSoldiers() - 1 );
        territoryOrigin.setSoldiersIn(originSoldiersLeft);*/

        //System.out.println("territory origin soldiers: " + territoryOrigin.getSoldiers());
        //System.out.println("territory destiny soldiers: " + territoryDestiny.getSoldiers());
    }

    @Override
    public void battle() {
        System.out.println(territoryDestiny.getPlayer());
        int attackTroops = numberSoldiersAttacking - 1;//territoryOrigin.getSoldiers() - 1;
        // System.out.println("attack troops: " + attackTroops);
        System.out.println("territorydestiny: " + territoryDestiny);
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

        System.out.println(territoryDestiny.getPlayer());
        grid.showNumberSoldiers();
    }


    @Override
    //verify victory condition
    public boolean victory(Player player1, Player player2) {
        int countP1 = 0;
        int countP2 = 0;
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                if (territoriesArray[i][j].getPlayer() != null) {

                    if (territoriesArray[i][j].getPlayer().getColor().equals("Red")) {
                        countP1++;
                    } else {
                        countP2++;
                    }
                    if (countP1 > countP2) {
                        System.out.println("P1 wins (Por imagem)");
                    }
                    System.out.println("P2 wins (Por imagem");
                }
            }
        }
        return true;
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
                    game.getActivePlayer().moveLeft();
                    if (territoriesArray[territory.getColumn() - 1][territory.getRow()].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn() - 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                        System.out.println("Player: " + territory.getPlayer() + territory.getColumn());
                    }
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() - 1][territory.getRow()];
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].select();
                    territory.unselect();

                    //System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                    return;
                }
                territory.select();
                System.out.println("Cant move left---------------------------");
                return;

            case UP:
                if (territory.getRow() > 0) {
                    game.getActivePlayer().moveUp();
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
                System.out.println("Cant move up---------------------------");
                return;

            case RIGHT:
                if (territory.getColumn() < numCols - 1) {
                    game.getActivePlayer().moveRight();
                    if (territoriesArray[territory.getColumn() + 1][territory.getRow()].getPlayer() == null) {//see if the territory doenst has a player
                        territoriesArray[territory.getColumn() + 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() + 1][territory.getRow()];
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].select();
                    territory.unselect();

                    //  System.out.println(territoriesArray[territory.getColumn()][territory.getRow()].getSoldiers());
                    return;
                }
                territory.select();
                System.out.println("Cant move right---------------------------");
                return;

            case DOWN:
                if (territory.getRow() < numRows - 1) {
                    game.getActivePlayer().moveDown();
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
                System.out.println("Cant move down---------------------------");
        }
    }

    public Territory getTerritoryOrigin() {
        return territoryOrigin;
    }

    public void addTerritoryToP1(Player player) {
        territoriesArray[numCols - 1][numRows - 1].select();

        territoriesArray[numCols - 1][numRows - 1].setPlayer(player);
        territoriesArray[numCols - 1][numRows - 1].setSoldiersIn(20);
        //territoryOrigin = territoriesArray[0][0];
    }

    public void addTerritoryToP2(Player player) {
        territoriesArray[0][0].setPlayer(player);
        territoriesArray[0][0].setSoldiersIn(20);
    }

    public void beginRoundP1() { //review this logic
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[0][0].select();
        //territoryOrigin = territoriesArray[0][0];
    }

    public void beginRoundP2() { //review this logic
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[numCols - 1][numRows - 1].select();
        //  territoryOrigin = territoriesArray[numCols-1][numRows-1];
    }

    public void setTerritoryOrigin(Territory origin) {
        territoryOrigin = origin;
    }

    public void setTerritoryDestiny(Territory destiny) {
        territoryDestiny = destiny;
    }

    public void setGame(GameLogic game) {
        this.game = game;
    }
}