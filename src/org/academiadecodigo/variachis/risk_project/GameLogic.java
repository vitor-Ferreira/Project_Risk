package org.academiadecodigo.variachis.risk_project;

public class GameLogic {
    private Player p1;


    private Territory[] territoriesArray;
    private Player p2;

    public GameLogic() {
        territoriesArray = new Territory[]{
               // new Territory(0, "cona"),
                //new Territory(1, "fodase"),
                //new Territory(2, "pissa"),
        };
     //   p2 = new Player("Blue", 20, territoriesArray[0]);
    }


    public Player getP2() {
        return p2;
    }

    public void setTerritoriesArray(Territory t, Territory q, Territory r) {

        territoriesArray[0]=t;//error i want to c
        territoriesArray[1]=q;
        territoriesArray[2]=r;
        p2 = new Player("Blue", 20, territoriesArray[0]);

    }

    /*
                private Player player1;
                private Player player2;
                private Territory head;

                public GameLogic(Player player1, Player player2) {
                    this.player1=player1;
                    this.player2=player2;
                    this.head.getNext();
                }

                public

            */
    public void moveToTerritory(Player p, Movement m) {//allows player to move / if the target territory belongs to him doesnt trigger the attack method
        /*switch (-----receive an input----) {
            case 0:
                getPos().subSetCol();
                break;
            case 1:
                getPos().addSetCol();
                break;
            case 2:
                getPos().addSetRow();
                break;
            case 3:
                getPos().subSetRow();
                break;
        }*/
        switch (m) {
            case UP:
                p.setCurrentTerritory(territoriesArray[1]);
            case DOWN:
                p.setCurrentTerritory(territoriesArray[0]);
            case LEFT:
                p.setCurrentTerritory(territoriesArray[2]);
        }

    }

    public void moveSoldiers(int amount, Movement m) {

        p2.getCurrentTerritory().setSoldiersOut(amount);

        moveToTerritory(p2, m);

        p2.getCurrentTerritory().setSoldiersIn(amount);

    }


}