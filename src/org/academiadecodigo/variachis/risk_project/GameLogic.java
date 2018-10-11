package org.academiadecodigo.variachis.risk_project;

public class GameLogic {

    private Grid grid;
    private Board board;
    private Territory territory;
    private Territory[][] territoryArray;

    private Player p1 = new Player("Blue");;
    private Player p2 = new Player("Red");;
    private Player activePlayer = p1;
    //private Movement moveChoice;

    private int rounds = 1; //if we want to keep track of the number of rounds.
    private RoundStage roundStage = RoundStage.ATTACK; //começamos no ataque

    private boolean attackDone = false;
    private boolean reinforceDone = false;

    public void init() {

        grid = new Grid(3, 3);
        board = new Board(grid, 3, 3);
        territoryArray = board.getTerritories();
        grid.init(territoryArray); //corrigir: ao fecharmos esta janela, o processo não encerra logo.

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setBoard(board);
        keyboard.runKeyboard();

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        grid.showNumberSoldiers();
        grid.movementImage();
    }

    public void start() {

        System.out.println("Current Round: " + rounds);

        //for (int i = 0; i < 11; i++) { //why 11? because if it's not 11, the for doesn't stop. this is just a quick fix.

            //round();
            //System.out.println("round: " + rounds);

            //attackDone = false;

            /* if (rounds % 2 == 0) {
                System.out.println("Player 2's turn");
                board.beginRoundP2();
                round();
                rounds++;
            }

            board.beginRoundP1();
            System.out.println("Player 1's turn");
            round(); */


            rounds++;
        //}

        /** plano de acção: em vez de fazer a estratégia em cima descrita, fazer algo com activePlayer. a cada vez que activePlayer faz uma jogada, muda
         * de P1 para P2 e vice-versa. Só há um activePlayer de cada vez. as jogadas (i.e movements, etc, aplicam-se em nome do activePlayer. **/
    }

    public void round() { //"o round é um jogo automático"; "premir uma tecla -> acção. dependendo da roundStage, significados diferentes."

        if (roundStage == RoundStage.INCREMENT) {
            board.increment(); //para os 2 players
            roundStage = RoundStage.ATTACK;
        }

        if (roundStage == RoundStage.ATTACK) {
            attack();
            roundStage = RoundStage.REINFORCEMENT;
        }

        if (roundStage == RoundStage.REINFORCEMENT) {
            reinforce();
            roundStage = RoundStage.INCREMENT;
        }

        activePlayer = activePlayer == p1 ? p2 : p1;
    }

    public void attack() {

        while (!attackDone) {

            //board.moveToTerritory(moveChoice);
            //moveActivePlayer(move);

            //check if territoryArray that we are attacking from has more than 1 soldier
            //if (board.verifyTerritorySelected().getSoldiers() <= 1) {
            //System.out.println(board.verifyTerritorySelected());
            if (board.getTerritoryOrigin().getSoldiers() <= 1) {
                //choose another territoryArray to attack from
                //  System.out.println("verify if territory only 1");
                return;
            }

            /* //gets the territoryArray (movement) that the player wants to attack (defined in Player).
            //check if the movement is allowed
            //board.moveToTerritory(movement);
            //if not allowed return choose new movement
            //needs to keep checking if new move is allowed...
            //return; */

            //check if territories have different owners
            Territory territoryAttack = board.verifyTerritorySelected();

            if (territoryAttack.getPlayer() == activePlayer) {
                return;
            }

            board.battle(); // Changes in the board.battle method
            attackDone = true;
        }
    }

    public void reinforce() {

        while (!reinforceDone) {

            //board.moveToTerritory(moveChoice);
            //moveActivePlayer(move);

            if (board.getTerritoryOrigin().getSoldiers() <= 1) {
                return;
            }

            //gets the territoryArray (movement) that the player wants to reinforce (defined in Player).

            Territory territoryReinforce = board.verifyTerritorySelected();

            if (territoryReinforce.getPlayer() != activePlayer) {
                return;
            }

            board.reinforce();
            reinforceDone = true;
        }
    }

    /* public void movePlayer(Movement movement) {
        moveChoice = movement;
    } */

    public void moveActivePlayer(Movement movement) {

        Movement move = movement;

        switch (move) {

            case LEFT:
                if (!attackDone) {
                    board.moveToTerritory(Movement.LEFT);
                }
                return;

            case UP:
                if (!attackDone) {
                    board.moveToTerritory(Movement.UP);
                }
                return;

            case RIGHT:
                if (!attackDone) {
                    board.moveToTerritory(Movement.RIGHT);
                }
                return;

            case DOWN:
                if (!attackDone) {
                    board.moveToTerritory(Movement.DOWN);
                }
                return;
        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public enum RoundStage {
        INCREMENT,
        ATTACK,
        REINFORCEMENT

    }
}