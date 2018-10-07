package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private int numRows;
    private int numCols;
    private Territory[] territoriesArray;
    private Territory territoryOrigin;// territory destiny when player moves
    private Territory territoryDestiny;
    public int numberSoldiersAttacking;


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
        for (int i = 0; i < territoriesArray.length; i++) {
            if (territoriesArray[i].getPlayer() == player) {
                System.out.println("Soldiers before increment: " + territoriesArray[i].getSoldiers());
                territoriesArray[i].setSoldiersIn(1);
                System.out.println("Soldiers after increment: " + territoriesArray[i].getSoldiers());
            }
        }
    }

    @Override
    public void reinforce() {
        System.out.println("destiny: " +territoryDestiny.getSoldiers());
        System.out.println("origin: " +territoryOrigin.getSoldiers());
       // territoryDestiny.setSoldiersIn(territoryDestiny.getSoldiers() + territoryOrigin.getSoldiers() - 1);
        System.out.println("territory destiny: "+territoryDestiny.getSoldiers());
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
        //System.out.println("atackTroops " + attackTroops);//--______________________sout here
        int defendTroops = territoryDestiny.getSoldiers() - attackTroops;
        //System.out.println("defenderTroops " + defendTroops);//--______________________sout here
        if (attackTroops == defendTroops) {
            territoryDestiny.afterBattle(1); //adds the result of battle on the territory
            territoryOrigin.setSoldiersOut(attackTroops);
            System.out.println(" Draw - Territory destiny troops: " + territoryDestiny.getSoldiers());
            return;
        }
        if (attackTroops > defendTroops) {
            int newAmount = attackTroops - defendTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            // System.out.println(territoryDestiny.getPlayer().getColor());
            territoryDestiny.setPlayer(territoryOrigin.getPlayer());
            // System.out.println(territoryOrigin.getPlayer().getColor());
            territoryOrigin.setSoldiersOut(attackTroops);
            //System.out.println("Attack wins - Territory destiny troops: " + territoryDestiny.getSoldiers());
            return;
        }
        if (attackTroops < defendTroops) { //Correct condition, error due to the fact we only have 3 territories.
            int newAmount = defendTroops - attackTroops;
            territoryDestiny.afterBattle(newAmount);//adds the result of battle on the territory
            territoryOrigin.setSoldiersOut(attackTroops);
           // System.out.println("new ammount: " + newAmount);//_______________________SOUT HERE
            //System.out.println("Defense wins - Territory destiny troops: " + territoryDestiny.getSoldiers());
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
            System.out.println("VICToRYYYY");//_______________________SOUT HERE
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

            //we will need to do a for inside a for to create a map like a 3x3
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

    //finds the selected territory
    public Territory verifyTerritorySelected() {
        Territory territory = null;
        for (int i = 0; i < territoriesArray.length; i++) {
            if (territoriesArray[i].isSelected()) {
                territory = territoriesArray[i];
            }
        }
        return territory;
    }

    //allows movement;
    public void moveToTerritory(Movement movement) {
        Territory territory = verifyTerritorySelected();
        numberSoldiersAttacking = territory.getSoldiers();
        territoryOrigin = territory;
        //numberSoldiersAttacking = territoryOrigin.getSoldiers();
        switch (movement) {
            case UP:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territoriesArray[i] == territory) {
                        territoriesArray[i].unselect();
                        territoriesArray[i - 1].select();
                        territoriesArray[i - 1].setSoldiersIn(territory.getSoldiers() - 1);
                        territoriesArray[i].guardianSoldier();
                        territoryDestiny = territoriesArray[i - 1];
                        if (territoriesArray[i - 1].getPlayer() == null) {
                            territoriesArray[i - 1].setPlayer(territoriesArray[i].getPlayer());
                        }
                        return;

                    }
                }
            case DOWN:
                for (int i = 0; i < territoriesArray.length; i++) {
                    if (territoriesArray[i] == territory) {
                        territoriesArray[i].unselect();
                        territoriesArray[i + 1].select();
                        territoriesArray[i + 1].setSoldiersIn(territory.getSoldiers() - 1);
                        territoriesArray[i].guardianSoldier();
                        territoryDestiny = territoriesArray[i + 1];
                        if (territoriesArray[i + 1].getPlayer() == null) {
                            territoriesArray[i + 1].setPlayer(territoriesArray[i].getPlayer());
                        }
                        // System.out.println("Troops to take with move " + territoriesArray[i + 1].getSoldiers());//_______________________SOUT HERE
                        //  System.out.println("troops remaining after moving out " + territory.getSoldiers());//_______________________SOUT HERE
                        return;

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
        //System.out.println(territoriesArray[0].getPlayer().getColor());

    }

    public void addTerritoryToP2(Player player) {

        territoriesArray[2].setPlayer(player);
        territoriesArray[2].setSoldiersIn(20);
        //System.out.println(territoriesArray[2].getPlayer().getColor());
    }

    public void beginRoundP1() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[0].select();
    }

    public void beginRoundP2() {
        Territory t = verifyTerritorySelected();
        t.unselect();
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
