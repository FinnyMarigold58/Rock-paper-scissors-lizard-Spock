import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;


public class MyProgram
{
    private static final String[] choices = {"r", "p", "c", "s", "l"};
    private static final HashMap<String, String[]> winMoves = new HashMap<>();
    private static final Random ran = new Random(); 
    private static final Scanner sc = new Scanner(System.in);
    private static boolean running = true;

    private enum Result {
        WIN,
        LOSE,
        TIE
    }

    static {
        winMoves.put("r", new String[] {"s","l"});
        winMoves.put("p", new String[] {"r","s"});
        winMoves.put("c", new String[] {"p","l"});
        winMoves.put("l", new String[] {"p","s"});
        winMoves.put("s", new String[] {"r","c"});
    }
    
    public static void main(String[] args)
    {
        while(running) {
            clearScreen();
            displayInfo();
            String userInput = getInput();
            System.out.println(userInput);
            String oppChoice = getRandom();

            System.out.println("\nResult\n-------");
            System.out.println("Your opponent chose: " + oppChoice);
            switch (getResult(userInput, oppChoice)) {
                case WIN:
                    System.out.println("You win!");
                    break;
                case LOSE:
                    System.out.println("You lose.");
                    break;
                case TIE:
                    System.out.println("You have tied!");
                    break;
            }
            
            System.out.println("Would you like to play again? y or n");
            String playAgain = sc.nextLine();
            if (playAgain.equals("n") || playAgain.equals("no")) {
                running = false;
                clearScreen();
                System.out.println("Goodbye!");
            }
        }
    }

    private static void displayInfo() {
        System.out.println("================================");
        System.out.println("Rock-Paper-Scissors-Spock-Lizard");
        System.out.println("================================\n");

        System.out.println("\tRules\n   --------------\n");
        printRule("Rock beats scissors and lizard");
        printRule("Paper beats rock and spock");
        printRule("Scissors beats paper and lizard");
        printRule("Lizard beats paper and spock");
        printRule("Spock beats rock and scissors");
        printRule("If both players choose same item, it is a tie");
        System.out.println("");
    }

    private static void printRule(String message) {
        System.out.println("[-] " + message);
    }

    private static String getInput(){
        System.out.println("What would you like to pick? (R)ock, (P)aper, S(c)issors, (S)pock, (L)izard");
        System.out.print("Choice: ");
        return sc.nextLine().toLowerCase();
    }

    private static String getRandom(){
        return choices[ran.nextInt(choices.length)];
    }

    private static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static Result getResult(String user, String opp) {
        if (user.equals(opp)) return Result.TIE;
        if (Arrays.asList(winMoves.get(opp)).contains(user)) return Result.LOSE;
        return Result.WIN;
    }
}
