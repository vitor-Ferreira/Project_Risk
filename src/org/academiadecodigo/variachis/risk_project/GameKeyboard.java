package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class GameKeyboard implements KeyboardHandler {

    private GameLogic game;
    private Board board;
    private Grid grid;

    public void setGame(GameLogic game) {
        this.game = game;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void runKeyboard() {

        Keyboard k = new Keyboard(this);

        KeyboardEvent select = new KeyboardEvent();
        select.setKey(KeyboardEvent.KEY_S); //cada tecla que queremos mapear é um novo evento,
        // por isso temos que criar um novo evento a cada tecla nova à qual queremos atribuir cenas.
        select.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(select);

        KeyboardEvent attack = new KeyboardEvent();
        attack.setKey(KeyboardEvent.KEY_A);
        attack.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(attack);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(left);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(up);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(right);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(down);
    }

    @Override
    public void keyPressed(KeyboardEvent evt) {

        switch (evt.getKey()) {

            case KeyboardEvent.KEY_S: //selects current territory and sets it as territoryOrigin
                board.setTerritoryOrigin(board.verifyTerritorySelected());
                System.out.println("S PRESSED");
                break;

            case KeyboardEvent.KEY_A: //selects a territory to attack
                board.setTerritoryDestiny(board.verifyTerritorySelected());
                System.out.println("A PRESSED");
                break;

            case KeyboardEvent.KEY_LEFT:
                grid.moveLeft(); //moves selection rectangle in Grid
                System.out.println("LEFT KEY PRESSED");
                break;

            case KeyboardEvent.KEY_UP:
                grid.moveUp();
                System.out.println("UP KEY PRESSED");
                break;

            case KeyboardEvent.KEY_RIGHT:
                grid.moveRight();
                System.out.println("RIGHT KEY PRESSED");
                break;

            case KeyboardEvent.KEY_DOWN:
                grid.moveDown();
                System.out.println("DOWN KEY PRESSED");
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) { //not needed for now.
        //
    }
}