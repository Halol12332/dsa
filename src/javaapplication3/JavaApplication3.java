package javaapplication3;

import boundary.ApplicantManagementUI;
import boundary.InterviewSchedulingUI;
import boundary.JobManagementUI;
import boundary.MatchingEngineUI;
import control.ApplicantManagementController;
import control.InterviewSchedulingController;
import control.JobManagementController;
import control.MatchingEngineController;
import control.SearchController;
import dao.ApplicantInitializer;
import dao.JobPostingInitializer;
import java.util.Scanner;

public class JavaApplication3 {
    public static void main(String[] args) {
        // Initialize controllers
        ApplicantManagementController applicantController = new ApplicantManagementController();
        JobManagementController jobController = new JobManagementController();
        MatchingEngineController matchingController = new MatchingEngineController();
        InterviewSchedulingController interviewController = new InterviewSchedulingController();

        // Initialize data
        ApplicantInitializer applicantInitializer = new ApplicantInitializer();
        JobPostingInitializer jobPostingInitializer = new JobPostingInitializer();

        // Load initial data
        applicantInitializer.initializeApplicants();
        jobPostingInitializer.initializeJobPosts();

        // Initialize SearchController with applicants
        SearchController searchController = new SearchController(
            jobPostingInitializer.getJobPostingDao().retrieveFromFile(),
            applicantInitializer.getApplicantDAO().retrieveFromFile()
        );

        // Create UI instances
        ApplicantManagementUI applicantUI = new ApplicantManagementUI(applicantController);
        JobManagementUI jobUI = new JobManagementUI();
        MatchingEngineUI matchingUI = new MatchingEngineUI(matchingController, searchController);
        InterviewSchedulingUI interviewUI = new InterviewSchedulingUI(interviewController);

        // Main menu
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Applicant Management");
            System.out.println("2. Job Management");
            System.out.println("3. Matching Engine");
            System.out.println("4. Interview Scheduling");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    applicantUI.start();
                    break;
                case 2:
                    jobController.runJobPostingsMaintenance();
                    break;
                case 3:
                    matchingUI.displayMenu();
                    break;
                case 4:
                    interviewUI.start();
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
