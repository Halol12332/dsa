/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import entities.Applicant;
import dao.ApplicantDAO;

/**
 *
 * @author natalie
 */
public class ApplicantManagementController {
    private final ApplicantDAO applicantDAO;

    private final LinkedList<Applicant> applicantList;

    public ApplicantManagementController() {
        applicantDAO = new ApplicantDAO();
        applicantList = (LinkedList<Applicant>) applicantDAO.retrieveFromFile();
    }

    public void addApplicant(Applicant newApplicant) {
        applicantList.add(newApplicant);
        saveApplicants();
    }
    
    public boolean removeApplicant(String email) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getEmail().equalsIgnoreCase(email)) {
                applicantList.remove(i);
                saveApplicants();
                return true;
            }
        }
        return false;
    }
    
    public Applicant updateApplicant(String email, int updateOption, String newValue) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getEmail().equalsIgnoreCase(email)) {
                switch (updateOption) {
                    case 1:
                        applicant.setName(newValue);
                        break;
                    case 2:
                        applicant.setEmail(newValue);
                        break;
                    case 3:
                        applicant.setPhoneNumber(newValue);
                        break;
                    case 4:
                        applicant.setMajor(newValue);
                        break;
                    case 5:
                        updateSkills(applicant, newValue);
                        break;
                    case 6:
                        updateLocationPreferences(applicant,newValue);
                        break;
                    case 7:
                        applicant.setDesiredJob(newValue);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return null;
                }
                applicantList.replace(i, applicant);
                saveApplicants();
                return applicant;
            }
        }
        return null;
    } 
   
    private void updateSkills(Applicant applicant, String newValue) {
        ListInterface<String> skillsList = applicant.getSkills();
        skillsList.clear(); // Clear existing skills
        String[] skillsArray = newValue.split(",");
        for (String skill : skillsArray) {
            skillsList.add(skill.trim());
        }
    }
    
    private void updateLocationPreferences(Applicant applicant, String newValue) {
        ListInterface<String> locationPreferencesList = applicant.getLocationPreference();
        locationPreferencesList.clear(); // Clear existing location preferences
        String[] locationPreferencesArray = newValue.split(",");
        for (String location : locationPreferencesArray) {
            locationPreferencesList.add(location.trim());
        }
    }
    
    public void displayAllApplicants() {
        printHeader();
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            printApplicant(applicantList.getEntry(i));
        }
        printCount(applicantList.getNumberOfEntries());
    }
    private void saveApplicants() {
        applicantDAO.saveToFile(applicantList);
    }

    public void filterByLocation(String location) {
        boolean found = false;
        System.out.println("\nFiltered by Location: " + location);
        printHeader();
        int count = 0;
        
        
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            ListInterface<String> locationPreferences = applicant.getLocationPreference();

            for (int j = 1; j <= locationPreferences.getNumberOfEntries(); j++) {
                String loc = locationPreferences.getEntry(j);
                if (loc.equalsIgnoreCase(location)) {
                    printApplicant(applicant);
                    found = true;
                    count++;
                    break; // No need to check other locations once we found a match
                }
            }
        }

        if (!found) {
            System.out.println("No results found for location: " + location);
        } else {
            printCount(count);
        }
    }
        
    public void filterByDesiredJob(String desiredJob) {
        boolean found = false;
        System.out.println("\nFiltered by Desired Job: " + desiredJob);
        printHeader();
        int count = 0;
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getDesiredJob().toLowerCase().contains(desiredJob.toLowerCase())) {
                printApplicant(applicant);
                found = true;
                count++;
            }
        }
        if (!found) {
            System.out.println("No results found for desired job: " + desiredJob);
        } else {
            printCount(count);
        }
    }    
        
    public void filterByMajor(String major) {
        boolean found = false;
        System.out.println("\nFiltered by Desired Job: " + major);
        printHeader();
        int count = 0;
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant applicant = applicantList.getEntry(i);
            if (applicant.getMajor().toLowerCase().contains(major.toLowerCase())) {
                printApplicant(applicant);
                found = true;
                count++;
            }
        }
        if (!found) {
            System.out.println("No results found for major: " + major);
        } else {
            printCount(count);
        }
    } 

    private void printHeader() {
        System.out.println("                                                    Job Seeker List\n");
        System.out.printf("%-15s %-25s %-15s %-20s %-30s %-30s %-25s \n",
            "Name", "Email", "Phone", "Major","Skills","Location", "Desired Job");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void printApplicant(Applicant applicant) {
        String skillsStr = listToString(applicant.getSkills());
        String locationsStr = listToString(applicant.getLocationPreference());
        
        System.out.printf("%-15s %-25s %-15s %-20s %-30s %-30s %-25s \n",
                applicant.getName(),applicant.getEmail(),applicant.getPhoneNumber(),applicant.getMajor(),
                skillsStr,locationsStr,applicant.getDesiredJob());
    }

    private void printCount(int count) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total number of job seekers: " + count);
    }
    
    private String listToString(ListInterface<String> list) {
        if (list == null || list.isEmpty()) {
            return "None";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= list.getNumberOfEntries(); i++) {
            if (i > 1) {
                sb.append(", ");
            }
            sb.append(list.getEntry(i));
        
            // Limit the display length if needed
            if (sb.length() > 20 && i < list.getNumberOfEntries()) {
                sb.append("...");
                break;
            }
        }return sb.toString();
    }
}