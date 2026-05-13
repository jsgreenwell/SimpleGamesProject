import java.util.*;
// Imports utility classes like Scanner, Random, List, ArrayList, Map, and HashMap

// Main class of the program
public class RockPaperScissors {

    // Program starts here
    public static void main(String[] args) {

        // Create a GameEngine object to control the game
        GameEngine gameEngine = new GameEngine();

        // Start the game loop
        gameEngine.run();
    }
}

// Handles all game logic and user interaction
class GameEngine {

    // Scanner reads user input from the keyboard
    private final Scanner scanner = new Scanner(System.in);

    // Random object is used for computer move generation
    private final Random random = new Random();

    // Stores every round played in the game
    private final List<RoundResult> history = new ArrayList<>();

    // Stores statistics such as wins, losses, and ties
    private final Map<String, Integer> stats = new HashMap<>();

    // Constructor initializes statistics
    public GameEngine() {

        // Starting values for game stats
        stats.put("wins", 0);
        stats.put("losses", 0);
        stats.put("ties", 0);
    }

    // Main game loop
    public void run() {

        // Controls whether the program keeps running
        boolean running = true;

        // Loop continues until user chooses Quit
        while (running) {

            // Display menu options
            showMenu();

            // Read player's menu choice
            String choice = scanner.nextLine().trim();

            // Enhanced switch statement (Java 14+ syntax)
            switch (choice) {

                case "1" -> playMatch();   // Start a match
                case "2" -> showStats();   // Show statistics
                case "3" -> showHistory(); // Show previous rounds
                case "4" -> saveGame();    // Placeholder for save feature
                case "5" -> loadGame();    // Placeholder for load feature

                // Ends the loop and exits program
                case "6" -> running = false;

                // Handles invalid menu choices
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        // Message displayed when game exits
        System.out.println("Thanks for playing!");
    }

    // Displays menu options
    private void showMenu() {

        System.out.println("\n1. Play Match");
        System.out.println("2. View Stats");
        System.out.println("3. View History");
        System.out.println("4. Save Game");
        System.out.println("5. Load Game");
        System.out.println("6. Quit");

        System.out.print("Choose an option: ");
    }

    // Runs a Rock-Paper-Scissors match
    private void playMatch() {

        System.out.print("Enter best-of number (3 or 5): ");

        int targetWins;

        try {

            // Convert user input from String to int
            targetWins = Integer.parseInt(scanner.nextLine().trim());

            // Only allow best-of-3 or best-of-5
            if (targetWins != 3 && targetWins != 5) {

                System.out.println("Please enter 3 or 5.");
                return;
            }

        } catch (NumberFormatException e) {

            // Prevents program crash if user enters letters
            System.out.println("Invalid number.");
            return;
        }

        // Track wins during this match
        int playerWins = 0;
        int computerWins = 0;

        // Continue until either side wins majority
        while (playerWins < (targetWins / 2) + 1 &&
                computerWins < (targetWins / 2) + 1) {

            // Ask player for move
            System.out.print("Enter rock, paper, or scissors: ");

            // Convert input to lowercase for easier comparison
            String playerMove = scanner.nextLine().trim().toLowerCase();

            // Validate move input
            if (!isValidMove(playerMove)) {

                System.out.println("Invalid move.");
                continue;
            }

            // Computer randomly chooses move
            String computerMove = getRandomMove();

            // Determine who wins round
            String result = determineWinner(playerMove, computerMove);

            // Save round result to history list
            history.add(new RoundResult(playerMove, computerMove, result));

            // Update stats based on result
            switch (result) {

                case "win" -> {

                    playerWins++;

                    // Increase total wins stat
                    stats.put("wins", stats.get("wins") + 1);
                }

                case "loss" -> {

                    computerWins++;

                    // Increase total losses stat
                    stats.put("losses", stats.get("losses") + 1);
                }

                // If tie, update tie counter
                default -> stats.put("ties", stats.get("ties") + 1);
            }

            // Show round outcome
            System.out.println("Computer chose " + computerMove + ".");
            System.out.println("Result: " + result);

            // Show current score
            System.out.println("Score: Player " + playerWins +
                    " - " + computerWins + " Computer");
        }

        // Determine match winner
        if (playerWins > computerWins) {

            System.out.println("You won the match!");

        } else {

            System.out.println("Computer won the match!");
        }
    }

    // Checks if player entered a legal move
    private boolean isValidMove(String move) {

        return move.equals("rock") ||
                move.equals("paper") ||
                move.equals("scissors");
    }

    // Randomly selects computer move
    private String getRandomMove() {

        String[] moves = {"rock", "paper", "scissors"};

        // Random index selection
        return moves[random.nextInt(moves.length)];
    }

    // Determines round winner
    private String determineWinner(String playerMove, String computerMove) {

        // Tie condition
        if (playerMove.equals(computerMove)) {

            return "tie";
        }

        // Player winning conditions
        if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
                (playerMove.equals("paper") && computerMove.equals("rock")) ||
                (playerMove.equals("scissors") && computerMove.equals("paper"))) {

            return "win";
        }

        // Otherwise player loses
        return "loss";
    }

    // Displays total game statistics
    private void showStats() {

        System.out.println("\nStats:");

        System.out.println("Wins: " + stats.get("wins"));
        System.out.println("Losses: " + stats.get("losses"));
        System.out.println("Ties: " + stats.get("ties"));
    }

    // Shows all previous rounds
    private void showHistory() {

        // Check if no games have been played
        if (history.isEmpty()) {

            System.out.println("No rounds played yet.");
            return;
        }

        System.out.println("\nMatch History:");

        // Print every saved round result
        for (RoundResult result : history) {

            System.out.println(result);
        }
    }

    // Placeholder method for future save feature
    private void saveGame() {

        System.out.println("Save feature not implemented yet.");
    }

    // Placeholder method for future load feature
    private void loadGame() {

        System.out.println("Load feature not implemented yet.");
    }
}

// Represents one round of gameplay
class RoundResult {

    // Store player move
    private final String playerMove;

    // Store computer move
    private final String computerMove;

    // Store round result
    private final String result;

    // Constructor sets all values
    public RoundResult(String playerMove,
                       String computerMove,
                       String result) {

        this.playerMove = playerMove;
        this.computerMove = computerMove;
        this.result = result;
    }

    // Converts object into readable text
    @Override
    public String toString() {

        return "Player: " + playerMove +
                ", Computer: " + computerMove +
                ", Result: " + result;
    }
}