/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author natalie
 */

import control.ApplicantManagementController;
import entities.JobSeeker;
import java.util.Scanner;

public class ApplicantManagementUI {
    private ApplicantManagementController controller;
    private Scanner scanner;

    public ApplicantManagementUI(ApplicantManagementController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n\n---------- Job Seeker ----------");
        System.out.println("1. Add new job seeker");
        System.out.println("2. Remove job seeker");
        System.out.println("3. Update job seeker");
        System.out.println("4. Display all job seekers");
        System.out.println("5. Filter job seekers");
        System.out.println("6. Back");
        System.out.print("\nPlease enter your choice: ");
    }

    public void start() {
        int option;
        do {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addJobSeeker();
                    break;
                case 2:
                    removeJobSeeker();
                    break;
                case 3:
                    updateJobSeeker();
                    break;
                case 4:
                    displayAllJobSeekers();
                    break;
                case 5:
                    filterJobSeekers();
                    break;
                case 6:
                    System.out.println("Exited.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 6);
        scanner.close();
    }

    private void addJobSeeker() {
        System.out.println("\nPlease enter details of the new job seeker.");
        JobSeeker newJS = inputJobSeeker();
        controller.addJobSeeker(newJS);
        System.out.println("Job seeker added successfully.");
    }

    private void removeJobSeeker() {
        System.out.print("Please enter the email of the job seeker to remove: ");
        String email = scanner.nextLine();
        boolean removed = controller.removeJobSeeker(email);
        if (removed) {
            System.out.println("The job seeker has been removed successfully.");
        } else {
            System.out.println("No job seeker found with email " + email);
        }
    }

    private void updateJobSeeker() {
        System.out.print("\nPlease enter the email of the job seeker to update: ");
        String email = scanner.nextLine();

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone Number");
        System.out.println("4. Location");
        System.out.println("5. Desired Job");
        System.out.println("6. Skills");
        System.out.print("Enter your choice: ");
        int updateOption = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        JobSeeker updatedJS = controller.updateJobSeeker(email, updateOption, newValue);
        if (updatedJS != null) {
            System.out.println("Information of Job Seeker updated successfully");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void displayAllJobSeekers() {
        controller.displayAllJobSeekers();
    }

    private void filterJobSeekers() {
        System.out.println("\nFilter options:");
        System.out.println("1. By Location");
        System.out.println("2. By Desired Job");
        System.out.print("Please choose the filter option: ");
        int filterOption = scanner.nextInt();
        scanner.nextLine();

        switch (filterOption) {
            case 1:
                System.out.print("Enter location: ");
                String location = scanner.nextLine();
                controller.filterByLocation(location);
                break;
            case 2:
                System.out.print("Enter desired job: ");
                String desiredJob = scanner.nextLine();
                controller.filterByDesiredJob(desiredJob);
                break;
            default:
                System.out.println("Invalid filter.");
        }
    }

    private JobSeeker inputJobSeeker() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email. Must contain @.");
            }
        } while (!isValidEmail(email));

        String phone;
        do {
            System.out.print("Phone Number: ");
            phone = scanner.nextLine();
            if (!isValidPhone(phone)) {
                System.out.println("Invalid phone number. Must be 10 or 11 digits.");
            }
        } while (!isValidPhone(phone));

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Desired Job Type: ");
        String desiredJob = scanner.nextLine();

        System.out.print("Skills: ");
        String skills = scanner.nextLine();

        return new JobSeeker(name, email, phone, location, desiredJob, skills);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10,11}");
    }
}
