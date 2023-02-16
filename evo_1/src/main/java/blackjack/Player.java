package blackjack;

public class Player {
    private Hand m_hand = new Hand();

    public void addCard(Card a_card) {
        m_hand.addCard(a_card);
    }

    public String toString() {
        return "Player has: " + m_hand.getScore() + System.lineSeparator() + m_hand;
    }

    public boolean wantsToHit() {
        return m_hand.getScore() < 19;
    }

    public int getScore() {
        return m_hand.getScore();
    }

    public boolean isBust() {
        return getScore() > 21;
    }
}
