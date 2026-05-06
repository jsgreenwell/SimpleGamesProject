import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char player = 'X';

        System.out.println("=== TIC TAC TOE ===");
        System.out.println("Choose mode: 1 = Player vs Player, 2 = Player vs AI");
        int mode = scanner.nextInt();

        System.out.println("\nTo place your piece, type the ROW letter (A/B/C) then the COLUMN number (1/2/3).");
        System.out.println("Example: A2 places your piece in row A, column 2.\n");

        while (true) {
            printBoard();

            if (mode == 2 && player == 'O') {
                // --- AI turn ---
                System.out.println("AI is thinking...");
                aiMove();
            } else {
                // --- Human turn ---
                System.out.println("Player " + player + ", enter position (e.g. A1, B3, C2): ");
                String input = scanner.next().trim().toUpperCase();

                if (input.length() != 2) {
                    System.out.println("Invalid input! Use a letter (A/B/C) and a number (1/2/3). Example: B2");
                    continue;
                }

                char rowChar = input.charAt(0);
                char colChar = input.charAt(1);

                // Validate row letter
                if (rowChar < 'A' || rowChar > 'C') {
                    System.out.println("Invalid row! Use A, B, or C.");
                    continue;
                }

                // Validate column number
                if (colChar < '1' || colChar > '3') {
                    System.out.println("Invalid column! Use 1, 2, or 3.");
                    continue;
                }

                int row = rowChar - 'A';      // A=0, B=1, C=2
                int col = colChar - '1';      // 1=0, 2=1, 3=2

                if (board[row][col] != ' ') {
                    System.out.println("That spot is already taken! Try again.");
                    continue;
                }

                board[row][col] = player;
            }

            // Check win
            if (checkWin(player)) {
                System.out.println("\nFinal board:");
                printBoard();
                if (mode == 2 && player == 'O') {
                    System.out.println("AI wins! Better luck next time.");
                } else {
                    System.out.println("Player " + player + " wins! Congratulations!");
                }
                break;
            }

            // Check draw
            if (isBoardFull()) {
                System.out.println("\nFinal board:");
                printBoard();
                System.out.println("It's a draw! Well played.");
                break;
            }

            // Switch player
            player = (player == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    // Prints the board with column numbers (1 2 3) on top and row letters (A B C) on the side
    // Empty cells show as blank so the board looks clean
    static void printBoard() {
        System.out.println("     1   2   3  ");
        System.out.println("   +---+---+---+");
        char[] rowLabels = {'A', 'B', 'C'};
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + rowLabels[i] + " |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("   +---+---+---+");
        }
        System.out.println();
    }

    // -------------------------------------------------------
    // AI using random moves — picks any empty spot at random
    // -------------------------------------------------------
    static void aiMove() {
        int row, col;

        // Keep picking random spots until we find one that's empty
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (board[row][col] != ' ');

        board[row][col] = 'O';

        // Report the AI's move using the same A/B/C + 1/2/3 format
        char rowLabel = (char) ('A' + row);
        int colLabel  = col + 1;
        System.out.println("AI played at: " + rowLabel + colLabel + "\n");
    }

    // Returns true if the given player has a winning line
    static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    // Returns true when no empty cells remain
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}