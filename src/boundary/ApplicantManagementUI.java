/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author natalie
 */

import adt.LinkedList;
import control.ApplicantManagementController;
import entities.Applicant;
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
                    addApplicant();
                    break;
                case 2:
                    removeApplicant();
                    break;
                case 3:
                    updateApplicant();
                    break;
                case 4:
                    displayAllApplicants();
                    break;
                case 5:
                    filterApplicants();
                    break;
                case 6:
                    System.out.println("Exited.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 6);
    }

    private void addApplicant() {
        System.out.println("\nPlease enter details of the new applicant.");
        Applicant newApplicant = inputApplicant();
        controller.addApplicant(newApplicant);
        System.out.println("Applicant added successfully.");
    }

    private void removeApplicant() {
        System.out.print("Please enter the email of the applicant to remove: ");
        String email = scanner.nextLine();
        boolean removed = controller.removeApplicant(email);
        if (removed) {
            System.out.println("The applicant has been removed successfully.");
        } else {
            System.out.println("No applicant found with email " + email);
        }
    }

    private void updateApplicant() {
        System.out.print("\nPlease enter the email of the applicant to update: ");
        String email = scanner.nextLine();

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone Number");
        System.out.println("4. Major");
        System.out.println("5. Skills");
        System.out.println("6. Location Preference");
        System.out.println("7. Desired Job");
        System.out.print("Enter your choice: ");
        int updateOption = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        Applicant updatedApplicant = controller.updateApplicant(email, updateOption, newValue);
        if (updatedApplicant != null) {
            System.out.println("Information of the applicant has been updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void displayAllApplicants() {
        controller.displayAllApplicants();
    }

    private void filterApplicants() {
        System.out.println("\nFilter options:");
        System.out.println("1. By Location Preference");
        System.out.println("2. By Desired Job");
        System.out.println("3. By Applicant's Major");
        
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
            case 3:
                System.out.print("Enter major: ");
                String major = scanner.nextLine();
                controller.filterByMajor(major);
                break;
            default:
                System.out.println("Invalid filter.");
        }
    }

    private Applicant inputApplicant() {
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
        
        System.out.print("Major: ");
        String major = scanner.nextLine();
        
        LinkedList<String> skills = new LinkedList<>();
        System.out.print("Skills (Please use comma as separator): ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            String[] skillsArray = input.split("\\s*,\\s*");
            for (String skill : skillsArray) {
                if(!skill.trim().isEmpty()){
                skills.add(skill.trim());
                }
            }
        }
        
        LinkedList<String> locationPreferences = new LinkedList<>();
        System.out.print("Location preferences (Please use comma as separator): ");
        String locInput = scanner.nextLine().trim();
        if (!locInput.isEmpty()) {
            String[] locations = locInput.split("\\s*,\\s*");
            for (String location : locations) {
                if (!location.trim().isEmpty()) {
                    locationPreferences.add(location.trim());
                }
            }
        }

        System.out.print("Desired Job Type: ");
        String desiredJob = scanner.nextLine();

        return new Applicant(name, email, phone, major, skills ,locationPreferences,desiredJob);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10,11}");
    }
}
