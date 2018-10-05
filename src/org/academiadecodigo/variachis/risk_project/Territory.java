package org.academiadecodigo.variachis.risk_project;

import javax.swing.*;

public class Territory {
    private boolean conquered = false;
    private int soldiersTurn = 1;
    private int position;
    private int row;
    private int column;
    private int soldiers;
    private boolean selected = false;
    private String name;
    private Player player;


    public Territory(int row, int column, String name) {
        this.row = row;
        this.column = column;

        this.position = row + column;

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


    public int getRow(){
        return this.row:
    }


    public int getColumn(){
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

    public boolean select() {
        return this.selected;
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


    public int guardianSoldier(){
            soldiers = 1;
    }


    // --------------territory knows its neighbours?----------


   /* public Territory getDown() {
        return down;
    }

    public Territory getNext() {
        return next;
    }*/
}
