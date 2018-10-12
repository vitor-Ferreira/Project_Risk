package org.academiadecodigo.variachis.risk_project;

public class Board implements Interface_Board {

    private GameLogic game;
    private Grid grid;

    private int numCols;
    private int numRows;

    private Territory[][] territoriesArray;
    private Territory territoryOrigin;
    private Territory territoryDestiny;

    private int numberSoldiersAttacking;

    public Board(Grid grid, int numCols, int numRows) {
        this.grid = grid;
        this.numCols = numCols;
        this.numRows = numRows;
        territoriesArray = new Territory[numCols][numRows];
        territoryMaker();
        territoryOrigin = territoriesArray[2][2];
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

    @Override
    public void increment() {
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                int rng = (int) (Math.random() * 10);

                if (territoriesArray[i][j].getPlayer() == game.getActivePlayer()) {

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
    }

    @Override
    public void reinforce() {
        territoryDestiny.setSoldiersIn(territoryDestiny.getSoldiers() + territoryOrigin.getSoldiers() - 1);
        territoryOrigin.guardianSoldier();
    }

    @Override
    public void battle() {

        int attackTroops = numberSoldiersAttacking - 1;
        System.out.println("territorydestiny: " + territoryDestiny);
        int defendTroops = territoryDestiny.getSoldiers() - attackTroops;
        if (attackTroops == defendTroops) {
            territoryDestiny.afterBattle(1);
            territoryOrigin.setSoldiersOut(attackTroops);
            return;
        }
        if (attackTroops > defendTroops) {
            territoryOrigin.setSoldiersOut(attackTroops);
            int newAmount = attackTroops - defendTroops;
            territoryDestiny.afterBattle(newAmount);
            territoryDestiny.setPlayer(territoryOrigin.getPlayer());
            return;
        }
        if (attackTroops < defendTroops) {
            territoryOrigin.setSoldiersOut(attackTroops);
            int newAmount = defendTroops - attackTroops;
            territoryDestiny.afterBattle(newAmount);
        }

    }


    @Override
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
                        System.out.println("P1 wins!");
                    }
                    System.out.println("P2 wins!");
                }
            }
        }
        return true;
    }


    @Override
    public void changePlayerTerritory(Player player, Territory territory) {
        territory.setPlayer(player);
    }

    public void territoryMaker() {

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {

                territoriesArray[i][j] = new Territory(i, j);
            }
        }
    }

    public Territory[][] getTerritories() {
        return this.territoriesArray;
    }

    @Override
    public boolean allowsMovement(Movement movement) {
        return true;
    }

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

    public void moveToTerritory(Movement movement) {

        Territory territory = verifyTerritorySelected();
        territoryOrigin = territory;
        numberSoldiersAttacking = territory.getSoldiers();
        switch (movement) {

            case LEFT:
                if (territory.getColumn() > 0) {
                    game.getActivePlayer().moveLeft();
                    if (territoriesArray[territory.getColumn() - 1][territory.getRow()].getPlayer() == null) {
                        territoriesArray[territory.getColumn() - 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                        System.out.println("Player: " + territory.getPlayer() + territory.getColumn());
                    }
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() - 1][territory.getRow()];
                    territoriesArray[territory.getColumn() - 1][territory.getRow()].select();
                    territory.unselect();

                    return;
                }
                territory.select();
                System.out.println("Cant move left---------------------------");
                return;

            case UP:
                if (territory.getRow() > 0) {
                    game.getActivePlayer().moveUp();
                    if (territoriesArray[territory.getColumn()][territory.getRow() - 1].getPlayer() == null) {
                        territoriesArray[territory.getColumn()][territory.getRow() - 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();

                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() - 1];
                    territoriesArray[territory.getColumn()][territory.getRow() - 1].select();
                    territory.unselect();

                    return;
                }
                territory.select();
                System.out.println("Cant move up---------------------------");
                return;

            case RIGHT:
                if (territory.getColumn() < numCols - 1) {
                    game.getActivePlayer().moveRight();
                    if (territoriesArray[territory.getColumn() + 1][territory.getRow()].getPlayer() == null) {
                        territoriesArray[territory.getColumn() + 1][territory.getRow()].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].setSoldiersIn(territory.getSoldiers() - 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn() + 1][territory.getRow()];
                    territoriesArray[territory.getColumn() + 1][territory.getRow()].select();
                    territory.unselect();

                    return;
                }
                territory.select();
                System.out.println("Cant move right---------------------------");
                return;

            case DOWN:
                if (territory.getRow() < numRows - 1) {
                    game.getActivePlayer().moveDown();
                    if (territoriesArray[territory.getColumn()][territory.getRow() + 1].getPlayer() == null) {
                        territoriesArray[territory.getColumn()][territory.getRow() + 1].setPlayer(territoriesArray[territory.getColumn()][territory.getRow()].getPlayer());
                    }
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].setSoldiersIn(territory.getSoldiers() + 1);
                    territoriesArray[territory.getColumn()][territory.getRow()].guardianSoldier();
                    territoryDestiny = territoriesArray[territory.getColumn()][territory.getRow() + 1];
                    territoriesArray[territory.getColumn()][territory.getRow() + 1].select();
                    territory.unselect();

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
    }

    public void addTerritoryToP2(Player player) {
        territoriesArray[0][0].setPlayer(player);
        territoriesArray[0][0].setSoldiersIn(20);
    }

    public void beginRoundP1() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[0][0].select();
    }

    public void beginRoundP2() {
        Territory t = verifyTerritorySelected();
        t.unselect();
        territoriesArray[numCols - 1][numRows - 1].select();
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