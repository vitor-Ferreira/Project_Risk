package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class GameKeyboard implements KeyboardHandler {

    private GameLogic game;
    private Board board;

    public void setGame(GameLogic game) {
        this.game = game;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void runKeyboard() {

        Keyboard k = new Keyboard(this);

        KeyboardEvent select = new KeyboardEvent();
        select.setKey(KeyboardEvent.KEY_S);
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

            case KeyboardEvent.KEY_S:
                board.setTerritoryOrigin(board.verifyTerritorySelected());
                System.out.println("S PRESSED");
                break;

            case KeyboardEvent.KEY_A:
                board.setTerritoryDestiny(board.verifyTerritorySelected());
                game.round();
                System.out.println("A PRESSED");
                break;

            case KeyboardEvent.KEY_LEFT:
                game.moveActivePlayer(Movement.LEFT);
                System.out.println("LEFT KEY PRESSED");
                break;

            case KeyboardEvent.KEY_UP:
                game.moveActivePlayer(Movement.UP);
                System.out.println("UP KEY PRESSED");
                break;

            case KeyboardEvent.KEY_RIGHT:
                game.moveActivePlayer(Movement.RIGHT);
                System.out.println("RIGHT KEY PRESSED");
                break;

            case KeyboardEvent.KEY_DOWN:
                game.moveActivePlayer(Movement.DOWN);
                System.out.println("DOWN KEY PRESSED");
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}