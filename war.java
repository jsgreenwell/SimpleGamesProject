import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * War Card Game
 * A simple console game where two players battle until one runs out of cards.
 */
public class war {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== WAR CARD GAME ===");
        System.out.println("Press ENTER to start...");
        sc.nextLine();

        // Let's create a standard 52-card deck
        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 2; value <= 14; value++) {
                deck.add(value); // 11=Jack, 12=Queen, 13=King, 14=Ace
            }
        }

        // Shuffle so the game isn't predictable
        Collections.shuffle(deck);

        // Split the deck in half for the two players
        ArrayList<Integer> handPlayer1 = new ArrayList<Integer>();
        ArrayList<Integer> handPlayer2 = new ArrayList<Integer>();

        for (int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) handPlayer1.add(deck.get(i));
            else handPlayer2.add(deck.get(i));
        }

        int turnNumber = 0;
        int maxTurns = 1000; // Just in case the game never ends lol

        // Keep playing as long as both players still have cards
        while (!handPlayer1.isEmpty() && !handPlayer2.isEmpty()) {
            turnNumber++;

            if (turnNumber > maxTurns) {
                System.out.println("Limit reached! It's a draw.");
                break;
            }

            System.out.println("\n--- TURN " + turnNumber + " ---");
            System.out.println("P1: " + handPlayer1.size() + " cards | P2: " + handPlayer2.size() + " cards");
            System.out.print("Press ENTER to flip...");
            sc.nextLine();

            // Pull the top card from each hand
            int cardP1 = handPlayer1.removeFirst();
            int cardP2 = handPlayer2.removeFirst();

            // Show what just happened on the table
            System.out.println("  [ " + getCardName(cardP1) + " ]  VS  [ " + getCardName(cardP2) + " ]");

            // Check who wins the round
            if (cardP1 > cardP2) {
                System.out.println("  >> P1 WINS THE ROUND");
                handPlayer1.add(cardP1);
                handPlayer1.add(cardP2);
            } else if (cardP2 > cardP1) {
                System.out.println("  >> P2 WINS THE ROUND");
                handPlayer2.add(cardP2);
                handPlayer2.add(cardP1);
            } else {
                // Oh boy, cards are equal, let's start a war!
                System.out.println("  >> TIE! WAR STARTED!");
                handleWar(handPlayer1, handPlayer2, cardP1, cardP2, sc);
            }
        }

        // The game is over, let's see who's the winner
        System.out.println("\n=== FINAL RESULTS ===");
        if (handPlayer1.size() > handPlayer2.size()) System.out.println("PLAYER 1 WINS THE GAME!");
        else if (handPlayer2.size() > handPlayer1.size()) System.out.println("PLAYER 2 WINS THE GAME!");
        else System.out.println("DRAW!");

        sc.close();
    }

    /**
     * Logic for when players tie. Each puts down 3 hidden cards and 1 face up.
     */
    static void handleWar(ArrayList<Integer> hand1, ArrayList<Integer> hand2, int card1, int card2, Scanner sc) {
        ArrayList<Integer> cardsInPlay = new ArrayList<Integer>();
        cardsInPlay.add(card1);
        cardsInPlay.add(card2);

        boolean keepGoing = true;
        while (keepGoing) {
            // Check if players have enough cards to actually do a war
            if (hand1.size() < 4 || hand2.size() < 4) {
                System.out.println("Not enough cards for war! Game ends.");
                return;
            }

            // Put the "sacrifice" cards face down
            System.out.println("  (3 cards placed face down)");
            for (int i = 0; i < 3; i++) {
                cardsInPlay.add(hand1.removeFirst());
                cardsInPlay.add(hand2.removeFirst());
            }

            System.out.print("  Reveal face-up card... ");
            sc.nextLine();

            // Flip the deciding cards
            int faceUpP1 = hand1.removeFirst();
            int faceUpP2 = hand2.removeFirst();
            cardsInPlay.add(faceUpP1);
            cardsInPlay.add(faceUpP2);

            System.out.println("  WAR DUEL: [ " + getCardName(faceUpP1) + " ] VS [ " + getCardName(faceUpP2) + " ]");

            if (faceUpP1 > faceUpP2) {
                System.out.println("  >> P1 WINS THE WAR (" + cardsInPlay.size() + " cards!)");
                hand1.addAll(cardsInPlay);
                keepGoing = false;
            } else if (faceUpP2 > faceUpP1) {
                System.out.println("  >> P2 WINS THE WAR (" + cardsInPlay.size() + " cards!)");
                hand2.addAll(cardsInPlay);
                keepGoing = false;
            } else {
                // If they tie again, we go for another round of war!
                System.out.println("  >> TIE AGAIN! The war goes on!");
            }
        }
    }

    /**
     * Just turns the numbers into nice names for people to read
     */
    static String getCardName(int value) {
        return switch (value) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> String.valueOf(value);
        };
    }
}