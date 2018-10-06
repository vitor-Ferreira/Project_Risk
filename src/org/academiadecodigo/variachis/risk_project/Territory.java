package org.academiadecodigo.variachis.risk_project;

import javax.swing.*;

public class Territory {
    private boolean conquered = false;
    private int soldiersTurn = 1;
    private int position, row, column, soldiers;
    private boolean selected;
    private Player player;

    
    public Territory(int row, int column) {
        this.row = row;
        this.column = column;

        this.position = row + column;
    }


    /* public Territory(int position, String name) {
        this.position = position;
        this.name = name;
    } */


    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiersIn(int s) {
        soldiers = soldiers + s;
    }

    public void setSoldiersOut(int s) {
        soldiers = soldiers - s;
    }


    public int getRow() {
        return this.row;
    }


    public int getColumn() {
        return this.column;
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

    public void select() {
        // square.draw(); // this draws a square when the territory is selected
        selected=true;
    }


    public void unselect() {
        //square.delete(); // this deletes a square when the territory is unselected
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }


    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public int guardianSoldier() {
        return soldiers = 1;
    }


    // --------------territory knows its neighbours?----------


   /* public Territory getDown() {
        return down;
    }

    public Territory getNext() {
        return next;
    }*/
}
