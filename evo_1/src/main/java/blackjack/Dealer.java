package blackjack;

import java.util.ArrayList;

public class Dealer {

    Hand m_hand = new Hand();
    Deck m_deck;

    void play(Player a_player) {
        m_deck = new Deck();

        m_deck.shuffle();

        giveCardTo(a_player);
        giveCardToMyself();
        giveCardTo(a_player);
        giveCardToMyself();

        System.out.println(this);
        System.out.println(a_player);

        while(a_player.wantsToHit()) {
            giveCardTo(a_player);
            System.out.println("Player Hit!" + System.lineSeparator() + a_player);
        }

        if (a_player.getScore() > 21) {
            System.out.println("----------------------------");
            System.out.println("Player is bust!");
            System.out.println("Dealer is winner!");
        } else {

            while (m_hand.getScore() < 17 && m_hand.getScore() < 21) {
                giveCardToMyself();
                System.out.println("Dealer Hit!" + System.lineSeparator() + this);
            }

            if (m_hand.getScore()> 21) {
                System.out.println("----------------------------");
                System.out.println("Dealer is bust!");
                System.out.println("Player is winner!");
            } else {
                if (a_player.getScore() > m_hand.getScore()) {
                    System.out.println("Player is winner!");
                } else {
                    System.out.println("Dealer is winner!");
                }
            }
        }
    }

    private void giveCardTo(Player a_player) {
        Card c = m_deck.getTopCard();
        c.show();
        a_player.addCard(c);
    }

    private void giveCardToMyself() {
        Card c = m_deck.getTopCard();
        c.show();
        m_hand.addCard(c);
    }

    public String toString() {
        return "Dealer has: " + m_hand.getScore() + System.lineSeparator() + m_hand;
    }
}
