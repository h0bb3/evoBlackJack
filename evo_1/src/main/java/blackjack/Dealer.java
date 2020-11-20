package blackjack;

public class Dealer {

    Hand m_hand = new Hand();
    Deck m_deck;

    void play(Player a_player) {
        startGame(a_player);

        printStatus(a_player);

        playGame(a_player);

        endGame(a_player);
    }

    private void printStatus(Player a_player) {
        System.out.println(this);
        System.out.println(a_player);
    }

    private void endGame(Player a_player) {
        if (a_player.isBust()) {
            printPlayerBust();
        } else if (isBust()) {
            printDealerIsBust();
        } else {
            printResult(a_player);
        }
    }

    private boolean isBust() {
        return m_hand.getScore() < 21;
    }


    private void playGame(Player a_player) {
        playersTurn(a_player);
        if (!a_player.isBust()) {
            myTurn();
        }
    }

    private void startGame(Player a_player) {
        m_deck = new Deck();
        m_deck.shuffle();

        giveCardTo(a_player);
        giveCardToMyself();
        giveCardTo(a_player);
        giveCardToMyself();
    }


    private void myTurn() {
        while (wantsToHit()) {
            giveCardToMyself();
            printDealerHit();
        }
    }

    private boolean wantsToHit() {
        return m_hand.getScore() < 17 && m_hand.getScore() < 21;
    }

    private void playersTurn(Player a_player) {
        while(a_player.wantsToHit()) {
            giveCardTo(a_player);
            printPlayerHit(a_player);
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

    // Printing operations below

    private void printResult(Player a_player) {
        printResultSeparator();
        if (a_player.getScore() > m_hand.getScore()) {
            printPlayerWinner();
        } else {
            printDealerIsWinner();
        }
    }

    private void printDealerIsWinner() {
        System.out.println("Dealer is winner!");
    }

    private void printPlayerWinner() {
        System.out.println("Player is winner!");
    }

    private void printResultSeparator() {
        System.out.println("----------------------------");
    }

    private void printDealerHit() {
        System.out.println("Dealer Hit!" + System.lineSeparator() + this);
    }

    private void printPlayerHit(Player a_player) {
        System.out.println("Player Hit!" + System.lineSeparator() + a_player);
    }

    private void printDealerIsBust() {
        printResultSeparator();
        System.out.println("Dealer is bust!");
        printPlayerWinner();
    }

    private void printPlayerBust() {
        printResultSeparator();
        System.out.println("Player is bust!");
        printDealerIsWinner();
    }

    public String toString() {
        return "Dealer has: " + m_hand.getScore() + System.lineSeparator() + m_hand;
    }
}
