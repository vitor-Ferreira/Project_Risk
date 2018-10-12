package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {

    private String color;
    private Picture picture;
    //private int soldiersOnTerritory;
    private int totalSoldiers;
    private Territory initialTerritory;
    //private CurrentlyOwnedTerritories[] currentlyOwnedTerritories; //list of the player's owned territories. not needed for now.
    //private int numberOfCurrentlyOwnedTerritories;

    public Player(String color, Picture picture) {
        this.color = color;
        this.picture = picture;
    }

    /* public int checkSoldiersOnTerritory(Territory territory) {
        return
    } */

    public String getColor() {
        return color;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTotalSoldiers() {
        return totalSoldiers;
    }

    public void setTotalSoldiers(int addToTotalSoldiers) {
        this.totalSoldiers += addToTotalSoldiers;
    }

    public Territory getInitialTerritory() {
        return initialTerritory;
    }

    public void setInitialTerritory(Territory initialTerritory) {
        this.initialTerritory = initialTerritory;
    }

    public void move(Movement movement) {

        Movement move = movement;

        switch (move) {

            case LEFT:
                moveLeft();
                return;

            case UP:
                moveUp();
                return;

            case RIGHT:
                moveRight();
                return;

            case DOWN:
                moveDown();
                return;
        }
    }

    public void moveLeft() {

        if (picture.getX() > 10) {

            picture.translate(-200, 0);
        }
    }

    public void moveUp() {

        if (picture.getY() > 10) {

            picture.translate(0, -200);
        }
    }

    public void moveRight() {

        if (picture.getX() + 200 < 3 * 200 + 10) {

            picture.translate(200, 0);
        }
    }

    public void moveDown() {

        if (picture.getY() + 200 < 3 * 200 + 10) {

            picture.translate(0, 200);
        }
    }
}