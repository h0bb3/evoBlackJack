package blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> m_cards = new ArrayList<>();

    public void addCard(Card a_card) {
        m_cards.add(a_card);
    }

    int getScore() {
        int cardScores[] = new int [Card.Value.values().length];
        cardScores[Card.Value.Ace.ordinal()] = 11;
        cardScores[Card.Value.King.ordinal()] = cardScores[Card.Value.Queen.ordinal()] = cardScores[Card.Value.Knight.ordinal()] = 10;
        cardScores[Card.Value.Ten.ordinal()] = 10;
        cardScores[Card.Value.Nine.ordinal()] = 9;
        cardScores[Card.Value.Eight.ordinal()] = 8;
        cardScores[Card.Value.Seven.ordinal()] = 7;
        cardScores[Card.Value.Six.ordinal()] = 6;
        cardScores[Card.Value.Five.ordinal()] = 5;
        cardScores[Card.Value.Four.ordinal()] = 4;
        cardScores[Card.Value.Three.ordinal()] = 3;
        cardScores[Card.Value.Two.ordinal()] = 2;
        cardScores[Card.Value.Hidden.ordinal()] = 0;

        int score = 0;

        for(Card c : m_cards) {
            score += cardScores[c.getValue().ordinal()];
        }

        if (score > 21) {
            for(Card c : m_cards) {
                if (c.getValue() == Card.Value.Ace && score > 21) {
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
