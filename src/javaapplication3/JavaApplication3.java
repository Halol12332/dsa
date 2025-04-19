package javaapplication3;

import adt.LinkedList;
import boundary.ApplicantManagementUI;
import boundary.InterviewSchedulingUI;
import boundary.JobManagementUI;
import boundary.MatchingEngineUI;
import control.ApplicantManagementController;
import control.InterviewSchedulingController;
import control.JobManagementController;
import control.MatchingEngineController;
import entities.Applicant;
import entities.JobPosting;
import entities.Company;
import entities.Interview;

import java.util.Scanner;

public class JavaApplication3 {
    public static void main(String[] args) {
        // Initialize controllers
        //ApplicantManagementController applicantManagementController = new ApplicantManagementController();
        //JobManagementController jobManagementController = new JobManagementController();
        MatchingEngineController matchingEngineController = new MatchingEngineController();
        //InterviewSchedulingController interviewSchedulingController = new InterviewSchedulingController();

        // Initialize UIs
        //ApplicantManagementUI applicantManagementUI = new ApplicantManagementUI(applicantManagementController);
        //JobManagementUI jobManagementUI = new JobManagementUI(jobManagementController);
        MatchingEngineUI matchingEngineUI = new MatchingEngineUI(matchingEngineController);
        //InterviewSchedulingUI interviewSchedulingUI = new InterviewSchedulingUI(interviewSchedulingController);

        
        // Main menu
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Applicant Management");
            System.out.println("2. Job Management");
            System.out.println("3. Matching Engine");
            System.out.println("4. Interview Scheduling");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    //applicantManagementUI.displayMenu();
                    break;
                case 2:
                    //jobManagementUI.displayMenu();
                    break;
                case 3:
                    matchingEngineUI.displayMenu();
                    break;
                case 4:
                    //interviewSchedulingUI.displayMenu();
                    break;
                case 5:
                    done = true;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}