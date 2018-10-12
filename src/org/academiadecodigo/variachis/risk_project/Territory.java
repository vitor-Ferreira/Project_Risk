package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Territory {

    private Player player;
    private int soldiers;

    private int column;
    private int row;
    private int position;

    private boolean selected;
    private Text text;


    public Territory(int column, int row) {
        this.column = column;
        this.row = row;
        this.position = row + column;

    }

    public int getSoldiers() {
        return this.soldiers;
    }

    public void setSoldiers(int s) {
        this.soldiers = s;

    }

    public void setSoldiersIn(int s) {
        System.out.println("previous ammount of soldiers is: " + this.soldiers);
        System.out.println("incrementing by: " + s);
        this.soldiers = this.soldiers + s;

    }

    public void setSoldiersOut(int s) {
        this.soldiers = this.soldiers - s;
    }

    public void afterBattle(int s) {
        this.soldiers = s;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }


    public int getPosition() {
        return position;
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
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

    public void setText(double x, double y, Color color) {
if (text!=null){
    text.delete();
}
        this.text = new Text(x,y,((Integer)soldiers).toString());
        text.grow(25, 25);
        text.draw();
        text.setColor(color);
    }

    @Override
    public String toString() {
        return "Territory{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}