package boundary;

import adt.*;
import control.InterviewSchedulingController;
import dao.InterviewDAO;
import entities.Interview;
import entities.Match;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * UI for the Interview Schedule Module
 * @author Tan Jin Yan
 */
public class InterviewSchedulingUI {
    private final InterviewSchedulingController controller;
    private final Scanner scanner;
    private final InterviewDAO interviewDAO;
    
    public InterviewSchedulingUI(InterviewSchedulingController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.interviewDAO = new InterviewDAO();
    }
    
    public void start() {
        int option;
        do {
            displayMenu();
            option = getIntInput("Enter your choice: ");
            
            switch (option) {
                case 1:
                    scheduleInterview();
                    break;
                case 2:
                    updateInterviewStatus();
                    break;
                case 3:
                    rateInterview();
                    break;
                case 4:
                    displayAllInterviews();
                    break;
                case 5:
                    displayScheduledInterviews();
                    break;
                case 6:
                    displaySuccessfulCandidates();
                    break;
                case 7:
                    filterInterviews();
                    break;
                case 8:
                    sortInterviews();
                    break;
                case 9:
                    generateAndExportReport();
                    break;
                case 0:
                    System.out.println("Exiting Interview Schedule Module...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 0);
    }
    
    public void displayMenu() {
        System.out.println("\n===== Interview Schedule Module =====");
        System.out.println("1. Schedule New Interview");
        System.out.println("2. Update Interview Status");
        System.out.println("3. Rate and Provide Feedback");
        System.out.println("4. View All Interviews");
        System.out.println("5. View Scheduled Interviews");
        System.out.println("6. View Successful Candidates");
        System.out.println("7. Filter Interviews");
        System.out.println("8. Sort Interviews");
        System.out.println("9. Generate and Export Reports");
        System.out.println("0. Exit");
        System.out.print("Please enter your choice: ");
    }
    
    private void scheduleInterview() {
        ListInterface<Match> availableMatches = controller.getAvailableMatches();
        
        if (availableMatches.isEmpty()) {
            System.out.println("No available matches to schedule interviews for.");
            return;
        }
        
        System.out.println("\n===== Available Matches =====");
        for (int i = 1; i <= availableMatches.getNumberOfEntries(); i++) {
            Match match = availableMatches.getEntry(i);
            System.out.printf("%d. Applicant: %s, Job: %s, Match Score: %.2f%%\n", 
                i, match.getApplicant().getName(), match.getJobPosting().getTitle(), match.getMatchScore());
        }
        
        int matchIndex = getIntInput("Select a match to schedule an interview (0 to cancel): ");
        if (matchIndex == 0 || matchIndex > availableMatches.getNumberOfEntries()) {
            System.out.println("Operation cancelled or invalid selection.");
            return;
        }
        
        Match selectedMatch = availableMatches.getEntry(matchIndex);
        
        System.out.println("\nEnter interview details:");
        System.out.println("Date format: yyyy-MM-dd HH:mm (e.g., 2025-05-20 14:30)");
        String dateTime = scanner.nextLine();
        
        System.out.print("Location: ");
        String location = scanner.nextLine();
        
        System.out.print("Interviewer Name: ");
        String interviewer = scanner.nextLine();
        
        boolean success = controller.scheduleInterview(selectedMatch, dateTime, location, interviewer);
        if (success) {
            System.out.println("Interview scheduled successfully!");
        } else {
            System.out.println("Failed to schedule interview. Please check your input.");
        }
    }
    
    private void updateInterviewStatus() {
        ListInterface<Interview> interviews = controller.getAllInterviews();
        
        if (interviews.isEmpty()) {
            System.out.println("No interviews available to update.");
            return;
        }
        
        displayInterviewList(interviews);
        
        int interviewIndex = getIntInput("Select an interview to update (0 to cancel): ");
        if (interviewIndex == 0 || interviewIndex > interviews.getNumberOfEntries()) {
            System.out.println("Operation cancelled or invalid selection.");
            return;
        }
        
        System.out.println("\nSelect new status:");
        System.out.println("1. Scheduled");
        System.out.println("2. Completed");
        System.out.println("3. Cancelled");
        System.out.println("4. Rejected");
        System.out.println("5. Successful");
        
        int statusOption = getIntInput("Enter your choice: ");
        String newStatus;
        
        switch (statusOption) {
            case 1:
                newStatus = "Scheduled";
                break;
            case 2:
                newStatus = "Completed";
                break;
            case 3:
                newStatus = "Cancelled";
                break;
            case 4:
                newStatus = "Rejected";
                break;
            case 5:
                newStatus = "Successful";
                break;
            default:
                System.out.println("Invalid option. Operation cancelled.");
                return;
        }
        
        boolean success = controller.updateInterviewStatus(interviewIndex, newStatus);
        if (success) {
            System.out.println("Interview status updated successfully!");
        } else {
            System.out.println("Failed to update interview status.");
        }
    }
    
    private void rateInterview() {
        ListInterface<Interview> completedInterviews = controller.getCompletedInterviews();
        
        if (completedInterviews.isEmpty()) {
            System.out.println("No completed interviews available to rate.");
            return;
        }
        
        System.out.println("\n===== Completed Interviews =====");
        displayInterviewList(completedInterviews);
        
        int interviewIndex = getIntInput("Select an interview to rate (0 to cancel): ");
        if (interviewIndex == 0 || interviewIndex > controller.getAllInterviews().getNumberOfEntries()) {
            System.out.println("Operation cancelled or invalid selection.");
            return;
        }
        
        double rating = getDoubleInput("Enter rating (0.0 - 10.0): ");
        
        System.out.print("Enter feedback: ");
        String feedback = scanner.nextLine();
        
        boolean success = controller.rateInterview(interviewIndex, rating, feedback);
        if (success) {
            System.out.println("Interview rated successfully!");
        } else {
            System.out.println("Failed to rate interview.");
        }
    }
    
    private void displayAllInterviews() {
        ListInterface<Interview> interviews = controller.getAllInterviews();
        
        if (interviews.isEmpty()) {
            System.out.println("No interviews available.");
            return;
        }
        
        System.out.println("\n===== All Interviews =====");
        displayInterviewList(interviews);
    }
    
    private void displayScheduledInterviews() {
        ListInterface<Interview> scheduledInterviews = controller.getScheduledInterviews();
        
        if (scheduledInterviews.isEmpty()) {
            System.out.println("No scheduled interviews available.");
            return;
        }
        
        System.out.println("\n===== Scheduled Interviews =====");
        displayInterviewList(scheduledInterviews);
    }
    
    private void displaySuccessfulCandidates() {
        ListInterface<Interview> successfulInterviews = controller.getSuccessfulInterviews();
        
        if (successfulInterviews.isEmpty()) {
            System.out.println("No successful candidates available.");
            return;
        }
        
        System.out.println("\n===== Successful Candidates =====");
        controller.sortInterviewsByRating();
        displayInterviewList(successfulInterviews);
    }
    
    private void filterInterviews() {
        System.out.println("\n===== Filter Interviews =====");
        System.out.println("1. Filter by Job Title");
        System.out.println("2. Filter by Applicant Name");
        System.out.println("3. Filter by Status");
        System.out.println("0. Cancel");
        
        int option = getIntInput("Enter your choice: ");
        
        if (option == 0) {
            return;
        }
        
        ListInterface<Interview> filteredInterviews;
        
        switch (option) {
            case 1:
                System.out.print("Enter job title to filter: ");
                String jobTitle = scanner.nextLine();
                filteredInterviews = controller.filterInterviewsByJobTitle(jobTitle);
                break;
            case 2:
                System.out.print("Enter applicant name to filter: ");
                String applicantName = scanner.nextLine();
                filteredInterviews = controller.filterInterviewsByApplicantName(applicantName);
                break;
            case 3:
                System.out.println("Select status to filter:");
                System.out.println("1. Scheduled");
                System.out.println("2. Completed");
                System.out.println("3. Cancelled");
                System.out.println("4. Rejected");
                System.out.println("5. Successful");
                
                int statusOption = getIntInput("Enter your choice: ");
                String status;
                
                switch (statusOption) {
                    case 1: status = "Scheduled"; break;
                    case 2: status = "Completed"; break;
                    case 3: status = "Cancelled"; break;
                    case 4: status = "Rejected"; break;
                    case 5: status = "Successful"; break;
                    default:
                        System.out.println("Invalid option. Operation cancelled.");
                        return;
                }
                
                filteredInterviews = controller.filterInterviewsByStatus(status);
                break;
            default:
                System.out.println("Invalid option. Operation cancelled.");
                return;
        }
        
        if (filteredInterviews.isEmpty()) {
            System.out.println("No interviews match your filter criteria.");
            return;
        }
        
        System.out.println("\n===== Filtered Interviews =====");
        displayInterviewList(filteredInterviews);
    }
    
    private void sortInterviews() {
        System.out.println("\n===== Sort Interviews =====");
        System.out.println("1. Sort by Date and Time");
        System.out.println("2. Sort by Rating (highest first)");
        System.out.println("0. Cancel");
        
        int option = getIntInput("Enter your choice: ");
        
        if (option == 0) {
            return;
        }
        
        switch (option) {
            case 1:
                controller.sortInterviewsByDateTime();
                break;
            case 2:
                controller.sortInterviewsByRating();
                break;
            default:
                System.out.println("Invalid option. Operation cancelled.");
                return;
        }
        
        System.out.println("\n===== Sorted Interviews =====");
        displayInterviewList(controller.getAllInterviews());
    }
    
    private void generateAndExportReport() {
        System.out.println("\n===== Generate Reports =====");
        System.out.println("1. Generate Schedule Overview Report");
        System.out.println("2. Generate Detailed Interview Report");
        System.out.println("0. Cancel");
        
        int option = getIntInput("Enter your choice: ");
        
        if (option == 0) {
            return;
        }
        
        String report;
        String fileName;
        
        switch (option) {
            case 1:
                report = controller.generateScheduleReport();
                fileName = "interview_schedule_report_" + 
                          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
                interviewDAO.exportReportToTextFile(fileName, report);
                System.out.println("\n===== Schedule Report =====");
                System.out.println(report);
                break;
            case 2:
                displayInterviewList(controller.getAllInterviews());
                int interviewIndex = getIntInput("Select an interview for detailed report (0 to cancel): ");
                
                if (interviewIndex == 0 || interviewIndex > controller.getAllInterviews().getNumberOfEntries()) {
                    System.out.println("Operation cancelled or invalid selection.");
                    return;
                }
                
                report = controller.generateDetailedInterviewReport(interviewIndex);
                fileName = "interview_detailed_report_" + 
                          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
                interviewDAO.exportReportToTextFile(fileName, report);
                System.out.println("\n===== Detailed Interview Report =====");
                System.out.println(report);
                break;
            default:
                System.out.println("Invalid option. Operation cancelled.");
                return;
        }
    }
    
    private void displayInterviewList(ListInterface<Interview> interviews) {
        System.out.println(Interview.getTableHeader());
        for (int i = 1; i <= interviews.getNumberOfEntries(); i++) {
            System.out.println(interviews.getEntry(i));
        }
        System.out.println(Interview.getTableFooter());
    }
    
    private int getIntInput(String prompt) {
        int input = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        return input;
    }
    
    private double getDoubleInput(String prompt) {
        double input = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        return input;
    }
}