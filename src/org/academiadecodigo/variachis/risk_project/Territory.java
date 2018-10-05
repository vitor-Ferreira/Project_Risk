package org.academiadecodigo.variachis.risk_project;

import javax.swing.*;

public class Territory {
    private boolean conquered = false;
    private int soldiersTurn = 1;
    private int position;
    private int positionX;
    private int positionY;
    private int soldiers;
    private boolean selected = true;
    private String name;
    private Player player;


    public Territory(int positionX, int positionY, String name) {
        this.positionX = positionX;
        this.positionY = positionY;

        this.position = positionX + positionY;

        this.name = name;
    }


    /* public Territory(int position, String name) {
        this.position = position;
        this.name = name;
    } */


    public String getName() {
        return name;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiersIn(int s) {
        soldiers = soldiers + s;
    }

    public void setSoldiersOut(int s) {
        soldiers = soldiers - s;
    }


    public int getPosition() {
        return position;
    }

    public boolean isConquered() {
        return conquered;
    }

    public boolean conquer() {
        return conquered = true;
    }

    public boolean select() {
        return true;
    }

    public boolean unselected() {
        return false;
    }

    public boolean isSelected() {
        return selected;
    }


    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    // --------------territory knows its neighbours?----------


   /* public Territory getDown() {
        return down;
    }

    public Territory getNext() {
        return next;
    }*/


}
