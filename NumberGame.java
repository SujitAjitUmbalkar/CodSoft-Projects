import java.util.Random;
import java.util.Scanner;

public class NumberGame 
   {

    public static void main(String[] args) 
{
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("=======================================");
        System.out.println("            NUMBER GUESS GAME      ");
        System.out.println("=======================================\n");

        while (playAgain) 
{
            int numberToGuess = rand.nextInt(100) + 1;  // random between 1-100
            int attemptsLeft = 7;  // limit attempts
            boolean guessedCorrect = false;

            System.out.println("I have picked a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts. Good luck!\n");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed it!");
                    guessedCorrect = true;
                    totalScore++;
                    break;
                } else if (guess > numberToGuess) {
                    System.out.println(" Too high! Try again.");
                } else {
                    System.out.println(" Too low! Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft + "\n");
            }

            if (!guessedCorrect) {
                System.out.println(" Out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("\nYour current score (Rounds Won): " + totalScore);


            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();

            if (!choice.equals("yes")) {
                playAgain = false;
                System.out.println("\nThanks for playing! Final Score: " + totalScore);
                System.out.println("=======================================");
            }
            System.out.println();
        }

        sc.close();
    }
}
