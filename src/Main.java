import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short choice = 100;

        while (choice != 9) {
            System.out.print("""
                    Hello and Welcome to the main menu!
                    Please select from the following options (or 9 to exit):
                        0: Go Fish
                        1: Black Jack
                        2: Poker
                        3: Rummy
                        4: Solitaire
                        5: Uno
                        6: War
                        9: Exit
                    Enter the number of your selection:
                    """);

            choice = Short.parseShort(scan.nextLine());
            switch (choice) {
                case 0:
                    Fish fish = new Fish();
                    while (fish.keepPlaying) {
                        fish.run();
                        fish.checkExit();
                    }
                    break;
                case 1:
                    BlackJack blackJack = new BlackJack();
                    while (blackJack.keepPlaying) {
                        blackJack.run();
                        blackJack.checkExit();
                    }
                    break;
                case 2:
                    Poker poker = new Poker();
                    while (poker.keepPlaying) {
                        poker.run();
                        poker.checkExit();
                    }
                    break;
                case 3:
                    Rummy rummy = new Rummy();
                    while (rummy.keepPlaying) {
                        rummy.run();
                        rummy.checkExit();
                    }
                    break;
                case 4:
                    Solitaire sol = new Solitaire();
                    while (sol.keepPlaying) {
                        sol.run();
                        sol.checkExit();
                    }
                    break;
                case 5:
                    Uno uno = new Uno();
                    while (uno.keepPlaying) {
                        uno.run();
                        uno.checkExit();
                    }
                    break;
                case 6:
                    War war = new War();
                    while (war.keepPlaying) {
                        war.run();
                        war.checkExit();
                    }
                    break;
                default:
                    System.out.println("Thanks for playing!\nGoodbye!");
                    choice = 9; // set to exit
            }
        }
    }
}