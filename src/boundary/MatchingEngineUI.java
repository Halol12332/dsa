package boundary;

import control.MatchingEngineController;
import entities.Match;
import adt.LinkedList;

import java.util.Scanner;

public class MatchingEngineUI {
    private MatchingEngineController matchingEngineController;
    private Scanner scanner;

    public MatchingEngineUI(MatchingEngineController matchingEngineController) {
        this.matchingEngineController = matchingEngineController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("\nMatching Engine Menu:");
            System.out.println("1. Perform Matching");
            System.out.println("2. View All Matches");
            System.out.println("3. Filter Matches");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    performMatching();
                    break;
                case 2:
                    viewAllMatches();
                    break;
                case 3:
                    filterMatches();
                    break;
                case 4:
                    done = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void performMatching() {
        System.out.print("Enter Applicant Name: ");
        String applicantName = scanner.nextLine();
        System.out.println("Performing matching of applicants with job postings...");
        matchingEngineController.performMatching(applicantName);
        System.out.println("Matching completed.");
    }

    private void viewAllMatches() {
        LinkedList<Match> matches = matchingEngineController.getMatches();
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Matches found:");
            for (Match match : matches) {
                System.out.println(match);
            }
        }
    }

    private void filterMatches() {
        System.out.println("Filtering matches...");
        System.out.print("Enter location (or press Enter to skip): ");
        String location = scanner.nextLine();
        System.out.print("Enter job type (or press Enter to skip): ");
        String jobType = scanner.nextLine();

        LinkedList<Match> filteredMatches = matchingEngineController.filterMatches(location, jobType);
        if (filteredMatches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Filtered Matches found:");
            for (Match match : filteredMatches) {
                System.out.println(match);
            }
        }
    }
}