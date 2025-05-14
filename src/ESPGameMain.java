/*
 * Class: CMSC203
 * Instructor:Professor Tanveer
 * Description: This class implements the ESP game by reading colors from a file, displaying a menu,
 *              handling user guesses in multiple rounds, and collecting student information to output results.)
 * Due: 04/3
 * Platform/compiler: eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Nitin Adhikari
 */
package espgame;

import java.io.*;
import java.util.*;

public class ESPGameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lastCorrectGuesses = 0; // holds the result of the last game session

        // Read colors from the file "colors.txt" once (without prompting for filename)
        String filename = "src/colors.txt";

        List<String> allColors = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allColors.add(line.trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + filename + ": " + e.getMessage());
            scanner.close();
            return;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Ignore closing error
                }
            }
        }
        
        // Ensure there are at least 16 colors in the file
        if (allColors.size() < 16) {
            System.out.println("Error: colors.txt must contain at least 16 colors.");
            scanner.close();
            return;
        }

        // Print header
        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");

        // Main game loop
        while (true) {
            // Display menu options
            System.out.println("\nWould you please choose one of the 4 options from the menu:\n");
            System.out.println("    1- read and display on the screen first 16 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("    2- read and display on the screen first 10 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("    3- read and display on the screen first 5 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("    4- Exit from a program");
            System.out.print("Enter the option: ");
            String option = scanner.nextLine().trim();

            if (option.equals("4")) {
                break;
            }

            List<String> displayColors;
            if (option.equals("1")) {
                displayColors = allColors.subList(0, 16);
            } else if (option.equals("2")) {
                displayColors = allColors.subList(0, 10);
            } else if (option.equals("3")) {
                displayColors = allColors.subList(0, 5);
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // Display the selected colors
            System.out.println("\nThere are sixteen colors from a file:");
            for (int i = 0; i < displayColors.size(); i++) {
                System.out.println((i + 1) + " " + displayColors.get(i));
            }
            System.out.println();

            // Play 3 rounds of the ESP game
            int correctCount = 0;
            Random random = new Random();
            for (int round = 1; round <= 3; round++) {
                System.out.println("Round " + round + "\n");
                System.out.println("I am thinking of a color.");
                System.out.println("Is it one of list of colors above?");
                System.out.print("Enter your guess: ");
                String guess = scanner.nextLine().trim().toLowerCase();

                // Generate a random index between 0 and 15
                int randomIndex = random.nextInt(16);
                String chosenColor = allColors.get(randomIndex);

                // Capitalize first letter for display
                String displayChosen = chosenColor.substring(0, 1).toUpperCase() + chosenColor.substring(1);
                System.out.println("\nI was thinking of " + displayChosen + ".\n");

                if (guess.equals(chosenColor)) {
                    correctCount++;
                }
            }
            
            System.out.println("Game Over\n");
            System.out.println("You guessed " + correctCount + " out of 3 colors correctly.\n");
            lastCorrectGuesses = correctCount;
            
            System.out.print("Would you like to continue a Game? Type Yes/No\n");
            String continueResponse = scanner.nextLine().trim().toLowerCase();
            if (!continueResponse.equals("yes")) {
                break;
            }
        }
        
        // After game sessions, collect student information
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine().trim();
        System.out.print("Describe yourself: ");
        String description = scanner.nextLine().trim();
        System.out.print("Due Date: ");
        String dueDate = scanner.nextLine().trim();
        
        // Display student information on screen
        System.out.println("Username: " + studentName);
        System.out.println("User Description: " + description);
        System.out.println("Date: " + dueDate);
        
        // Write final results to output file "EspGameResults.txt"
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("EspGameResults.txt"));
            pw.println("Game Over");
            pw.println("You guessed " + lastCorrectGuesses + " out of 3 colors correctly.");
            pw.println("Due Date: " + dueDate);
            pw.println("Username: " + studentName);
            pw.println("User Description: " + description);
            pw.println("Date: " + dueDate);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        
        scanner.close();
    }
}



