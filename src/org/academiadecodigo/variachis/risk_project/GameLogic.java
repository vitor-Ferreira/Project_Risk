package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameLogic {

    private Grid grid;
    private Board board;
    private Territory[][] territoryArray;
    private Text textToDisplay;

    private Player p2 = new Player("Red", new Picture(10, 10, "Resources/tank1.png"));
    private Player p1 = new Player("Blue", new Picture(10 + 400, 10 + 400, "Resources/Sombreiro 02 transparente (200 X 200).png"));
    private Player activePlayer = p1;

    private int rounds = 1;
    private RoundStage roundStage = RoundStage.ATTACK;

    public void init() {

        grid = new Grid(3, 3);
        board = new Board(grid, 3, 3);
        board.setGame(this);
        territoryArray = board.getTerritories();
        grid.init(territoryArray);

        textToDisplay = new Text(290, 290, "Press Any Key");
        textToDisplay.draw();
        textToDisplay.grow(60, 60);

        GameKeyboard keyboard = new GameKeyboard();
        keyboard.setGame(this);
        keyboard.setBoard(board);
        keyboard.runKeyboard();

        board.addTerritoryToP1(p1);
        board.addTerritoryToP2(p2);
        grid.showNumberSoldiers();
        p1.getPicture().draw();
        p2.getPicture().draw();
    }

    public void round() {

        System.out.println("Current Round: " + rounds);

        textToDisplay.setText(roundStage.toString());

        if (roundStage == RoundStage.REINFORCE) {
            board.increment();
            roundStage = RoundStage.ATTACK;
            return;
        }

        if (roundStage == RoundStage.ATTACK) {
            attack();
            roundStage = RoundStage.REINFORCE;
        }

        if (activePlayer == p2) {
            rounds++;
        }

        activePlayer = activePlayer == p1 ? p2 : p1;
    }

    public void attack() {

        if (board.getTerritoryOrigin().getSoldiers() <= 1) {
            return;
        }

        Territory territoryAttack = board.verifyTerritorySelected();

        System.out.println("ter get P " + territoryAttack.getPlayer().getColor());
        if (territoryAttack.getPlayer() == activePlayer) {
            return;
        }

        board.battle();
        grid.showNumberSoldiers();
    }

    public void moveActivePlayer(Movement movement) {

        Movement move = movement;

        switch (move) {

            case LEFT:
                activePlayer.move(Movement.LEFT);
                return;

            case UP:
                activePlayer.move(Movement.UP);
                return;

            case RIGHT:
                activePlayer.move(Movement.RIGHT);
                return;

            case DOWN:
                activePlayer.move(Movement.DOWN);
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

    public int getRounds() {
        return rounds;
    }

    public enum RoundStage {
        REINFORCE,
        ATTACK

    }
}