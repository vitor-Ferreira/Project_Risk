package org.academiadecodigo.variachis.risk_project;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    public static final int PADDING = 10;

    private int cols;
    private int rows;
    private Territory[][] territory=;
    private int x;
    private int y;
    private String numSoldiers;
    private Board board;

    //private GridPosition gridPosition;


    private Rectangle[][] cellsArray = new Rectangle[6][6];
    private Picture moveableImage;


    private int cellSize = 200;

    public Grid(int cols, int rows, Board board) {
        this.cols = cols;
        this.rows = rows;
        this.board=board;
    }

    public void canvas() {

        Canvas.getInstance();
    }

    public void init() {


        Rectangle gridRect = new Rectangle(PADDING, PADDING, cellSize * cols, cellSize * rows);
        Picture backgroundPicture = new Picture(PADDING, PADDING, "Resources/terrain.jpg");
        backgroundPicture.draw();
        gridRect.setColor(Color.BLACK);
        gridRect.draw();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.println("i: " + i);
                System.out.println("j: " + j);
                Rectangle cell = new Rectangle(colsToX(i), rowToY(j), cellSize, cellSize);
                cell.setColor(Color.BLACK);
                cell.draw();
                cellsArray[i][j] = cell;

            }
        }

        movementImage();
        showNumberSoldiers();
    }

    public int colsToX(int cols) {
        x = cols * cellSize + PADDING;
        return x;

    }

    public int rowToY(int rows) {
        y = rows * cellSize + PADDING;
        return y;
    }

    public void movementImage(){ //Por argumentos para as posicoes pois vai ser ussado para fazer o set dos 2 players

        //if(territory.getPlayer().getColor().equals("red")) {
            moveableImage = new Picture(PADDING, PADDING, "Resources/tank1.png");
            moveableImage.draw();
        //}

        //if(territory.getPlayer().getColor().equals("blue")) {
            //moveableImage = new Picture(PADDING, PADDING + (territory.getRow() - 1) * cellSize, "Resources/tank1.png");
            //moveableImage.draw();
        //}

    }

    public void moveRight() {
        /* if(moveableImage.getX() < cols * cellSize + PADDING ) {
        } */
            moveableImage.translate(cellSize, 0);
    }

    public void moveLeft() {
        /* if(moveableImage.getX() > PADDING) {
        } */
            moveableImage.translate(-cellSize, 0);
    }

    public void moveUp() {
        /* if(moveableImage.getY() > PADDING) {
        } */
            moveableImage.translate(0, -cellSize);
    }

    public void moveDown() {
        /* if(moveableImage.getY() < rows * cellSize + PADDING) {

        } */
            moveableImage.translate(0, cellSize);
    }
    

    public void showNumberSoldiers(){
        territory.setSoldiersIn(3);
        System.out.println(territory.getSoldiers());

        //numSoldiers = Integer.toString(sdfsf);
        System.out.println(numSoldiers);

        for(int i = 0; i < cols; i++){
            for (int j = 0; j < rows; j++){
                System.out.println("i: " + i);
                System.out.println("j: " + j);
                //if (territory.getPlayer().getColor().equals("red")) {
                    Text text = new Text(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize , numSoldiers);
                    text.grow(25, 25);
                    text.draw();
                    text.setColor(Color.RED);
                //}

                /*if (territory.getPlayer().getColor().equals("blue")) {
                    Text text = new Text(i * cellSize + 0.5 * cellSize, j * cellSize + 0.5 * cellSize , "20");
                    text.grow(25, 25);
                    text.draw();
                    text.setColor(Color.BLUE);
                //} */


                //if (territory.getPlayer().getColor().equals("BLUE")) {
                /*text = new Text(i, j, getSoldiers);
                text.grow(25, 25);
                text.draw();
                text.setColor(Color.BLUE);
                //}*/
            }
        }



    }

        /* selectRect = new Rectangle(columnToX(0), rowToY(0), cellSize, cellSize);
        selectRect.setColor(Color.RED);
        selectRect.draw(); */
    /*}

    public void playerImagesShow() {
        Picture playerIcon = new Picture(columnToX(0) - 150, rowToY(0) - 150, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);

    }

    public void soldierNumber() {

        if (territory.getPlayer().getColor().equals("RED")) {
            Text text = new Text(columnToX(getCols()) + cellSize * 0.4, rowToY(getRows()) + cellSize * 0.5, "20");
            text.grow(25, 25);
            text.draw();
            text.setColor(Color.RED);
        }
        if (territory.getPlayer().getColor().equals("BLUE")) {
            Text text = new Text(columnToX(getCols()) + cellSize * 0.4, rowToY(getRows()) + cellSize * 0.5, "20");
            text.grow(25, 25);
            text.draw();
            text.setColor(Color.BLUE);
        }

    }

    /*public void player2ImagesShow(){
        Picture playerIcon = new Picture(columnToX(2) -550 , rowToY(2) -170 , "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        Text text = new Text(columnToX(2) - 320, rowToY(2) + 80, "20");
        text.grow(25,25);
        text.setColor(Color.BLUE);
        text.draw();
    }*/

    /*public void player1ImagesHide() {
        Picture playerIcon = new Picture(columnToX(0) - 150, rowToY(0) - 150, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        playerIcon.delete();
        Text text = new Text(columnToX(0) + cellSize * 0.4, rowToY(0) + cellSize * 0.5, "20");
        text.grow(25, 25);
        text.setColor(Color.RED);
        text.draw();
        text.delete();
    }

    public void player2ImagesHide() {
        Picture playerIcon = new Picture(columnToX(2) - 550, rowToY(2) - 170, "Resources/tank.png");
        playerIcon.draw();
        playerIcon.grow(-150, -150);
        playerIcon.delete();
        Text text = new Text(columnToX(2) - 320, rowToY(2) + 80, "20");
        text.grow(25, 25);
        text.setColor(Color.BLUE);
        text.draw();
        text.delete();

    }*/


}

