package blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> m_cards = new ArrayList<>();

    public void addCard(Card a_card) {
        m_cards.add(a_card);
    }

    public void showAllCards() {
        for (Card c : m_cards) {
            c.show();
        }
    }

    int getScore() {
        int cardScores[] = {
                2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
        };

        int score = 0;

        for(Card c : m_cards) {
            if (c.getValue() != Card.Value.Hidden)
            {
                score += cardScores[c.getValue().ordinal()];
            }
        }

        if (score > 21) {
            for(Card c : m_cards)
            {
                if (c.getValue() == Card.Value.Ace && score > 21)
                {
                    score -= 10;
                }
            }
        }

        return score;
    }

    public String toString() {
        String ret = "";

        for (Card c : m_cards) {
            ret += c.toString() + System.lineSeparator();
        }

        return ret;
    }
}
