package blackjack;

public class Main {

    public static void main(String [] a_args) {
        Dealer d = new Dealer();
        Player p = new Player();

        System.out.println("Hello Black Jack World!");
        d.play(p);
    }
}
