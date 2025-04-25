package boundary;

/**
 * Author : Jaya Hakim Prajna
 */

import control.MatchingEngineController;
import control.SearchController;
import entities.Applicant;
import entities.JobPostings;
import adt.*;

import java.util.Scanner;

public class MatchingEngineUI {
    private MatchingEngineController matchingEngineController;
    private Scanner scanner;
    private SearchController searchController;

    public MatchingEngineUI(MatchingEngineController matchingEngineController, SearchController searchController) {
        this.matchingEngineController = matchingEngineController;
        this.searchController = searchController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("---------------------------------------------\nMatching Engine Menu:");
            System.out.println("1. Perform Matching as Applicant");
            System.out.println("2. Perform Matching as Job Posting");
            System.out.println("3. View All Matches");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    performMatchingAsApplicant();
                    break;
                case 2:
                    performMatchingAsJobPosting();
                    break;
                case 3:
                    matchingEngineController.viewAllMatches();
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

    private void performMatchingAsApplicant() {
        System.out.print("Enter applicant keyword: ");
        String keyword = scanner.nextLine();

        ListInterface<String> results = matchingEngineController.getApplicantSearchResults(keyword, searchController);
        if (results.isEmpty()) {
            System.out.println("No applicants found.");
            return;
        }

        System.out.println("Applicants found:");
        for (int i = 1; i <= results.getNumberOfEntries(); i++) {
            System.out.println(results.getEntry(i));
        }

        System.out.print("Select applicant by number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Applicant selected = matchingEngineController.getApplicantBySearchIndex(keyword, choice, searchController);
        if (selected != null) {
            matchingEngineController.performMatchingAsApplicant(selected.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void performMatchingAsJobPosting() {
        System.out.print("Enter job keyword: ");
        String keyword = scanner.nextLine();

        LinkedList<String> results = matchingEngineController.getJobSearchResults(keyword, searchController);
        if (results.isEmpty()) {
            System.out.println("No job postings found.");
            return;
        }

        System.out.println("Job postings found:");
        for (int i = 1; i <= results.getNumberOfEntries(); i++) {
            System.out.println(results.getEntry(i));
        }

        System.out.print("Select job by number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        JobPostings selected = matchingEngineController.getJobPostingBySearchIndex(keyword, choice, searchController);
        if (selected != null) {
            matchingEngineController.performMatchingAsJobPosting(selected.getJobID());
        } else {
            System.out.println("Invalid selection.");
        }
    }
}