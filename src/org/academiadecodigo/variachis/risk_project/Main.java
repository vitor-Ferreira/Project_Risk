package org.academiadecodigo.variachis.risk_project;

public class Main {
    public static void main(String[] args) {


        Territory t1 = new Territory(0, "cona");
        Territory t2 = new Territory(1, "fodase");
        Territory t3 = new Territory(2, "pissa");

        GameLogic game = new GameLogic();
game.setTerritoriesArray(t1,t2,t3);
        //Player p1 = new Player("Red", 20, territory0);

        game.moveSoldiers(9, Movement.UP);
        //Player p2 = new Player("Red", 20);

        //System.out.println(p1.getInicialTerritory());
        //game.moveToTerritory(p1,Movement.UP);
        //System.out.println(p1.getCurrentTerritory());
        //System.out.println(territory0.getSoldiers());
        //System.out.println(territory1.getSoldiers());

    }
}
