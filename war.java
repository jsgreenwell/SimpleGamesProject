import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * War Card Game - Console Version.
 * Updated to show only the cards played each turn.
 */
public class war {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== WAR CARD GAME ===");
        System.out.println("Press ENTER to start...");
        sc.nextLine();

        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 2; value <= 14; value++) {
                deck.add(value);
            }
        }

        Collections.shuffle(deck);

        ArrayList<Integer> handPlayer1 = new ArrayList<Integer>();
        ArrayList<Integer> handPlayer2 = new ArrayList<Integer>();

        for (int i = 0; i < deck.size(); i++) {
            if (i % 2 == 0) handPlayer1.add(deck.get(i));
            else handPlayer2.add(deck.get(i));
        }

        int turnNumber = 0;
        int maxTurns = 1000;

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

            int cardP1 = handPlayer1.removeFirst();
            int cardP2 = handPlayer2.removeFirst();

            // AFFICHAGE DU DUEL UNIQUEMENT
            System.out.println("  [ " + getCardName(cardP1) + " ]  VS  [ " + getCardName(cardP2) + " ]");

            if (cardP1 > cardP2) {
                System.out.println("  >> P1 WINS THE ROUND");
                handPlayer1.add(cardP1);
                handPlayer1.add(cardP2);
            } else if (cardP2 > cardP1) {
                System.out.println("  >> P2 WINS THE ROUND");
                handPlayer2.add(cardP2);
                handPlayer2.add(cardP1);
            } else {
                System.out.println("  >> TIE! WAR STARTED!");
                handleWar(handPlayer1, handPlayer2, cardP1, cardP2, sc);
            }
        }

        System.out.println("\n=== FINAL RESULTS ===");
        if (handPlayer1.size() > handPlayer2.size()) System.out.println("PLAYER 1 WINS THE GAME!");
        else if (handPlayer2.size() > handPlayer1.size()) System.out.println("PLAYER 2 WINS THE GAME!");
        else System.out.println("DRAW!");

        sc.close();
    }

    static void handleWar(ArrayList<Integer> hand1, ArrayList<Integer> hand2, int card1, int card2, Scanner sc) {
        ArrayList<Integer> cardsInPlay = new ArrayList<Integer>();
        cardsInPlay.add(card1);
        cardsInPlay.add(card2);

        boolean keepGoing = true;
        while (keepGoing) {
            if (hand1.size() < 4 || hand2.size() < 4) {
                System.out.println("Not enough cards for war! Game ends.");
                return;
            }

            System.out.println("  (3 cards placed face down)");
            for (int i = 0; i < 3; i++) {
                cardsInPlay.add(hand1.removeFirst());
                cardsInPlay.add(hand2.removeFirst());
            }

            System.out.print("  Reveal face-up card... ");
            sc.nextLine();

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
                System.out.println("  >> TIE AGAIN! The war goes on!");
            }
        }
    }

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