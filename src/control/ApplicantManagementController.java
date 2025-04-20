/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entities.JobSeeker;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natali
 */
public class ApplicantManagementController {

    private List<JobSeeker> jobSeekerList;

    public ApplicantManagementController() {
        jobSeekerList = new ArrayList<>();
    }

    public void addJobSeeker(JobSeeker newJS) {
        jobSeekerList.add(newJS);
    }

    public boolean removeJobSeeker(String email) {
        for (int i = 0; i < jobSeekerList.size(); i++) {
            JobSeeker js = jobSeekerList.get(i);
            if (js.getEmail().equalsIgnoreCase(email)) {
                jobSeekerList.remove(i);
                return true;
            }
        }
        return false;
    }

    public JobSeeker updateJobSeeker(String email, int updateOption, String newValue) {
        for (JobSeeker js : jobSeekerList) {
            if (js.getEmail().equalsIgnoreCase(email)) {
                switch (updateOption) {
                    case 1:
                        js.setName(newValue);
                        break;
                    case 2:
                        js.setEmail(newValue);
                        break;
                    case 3:
                        js.setPhone(newValue);
                        break;
                    case 4:
                        js.setLocation(newValue);
                        break;
                    case 5:
                        js.setDesiredJob(newValue);
                        break;
                    case 6:
                        js.setSkills(newValue);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return null;
                }
                return js;
            }
        }
        return null;
    }

    public void displayAllJobSeekers() {
        printHeader();
        for (JobSeeker js : jobSeekerList) {
            printJobSeeker(js);
        }
        printCount();
    }

    public void filterByLocation(String location) {
        boolean found = false;
        System.out.println("\nFiltered by Location: " + location);
        printHeader();
        int count = 0;
        for (JobSeeker js : jobSeekerList) {
            if (js.getLocation().toLowerCase().contains(location.toLowerCase())) {
                printJobSeeker(js);
                found = true;
                count++;
            }
        }
        if (!found) {
            System.out.println("No results found for location: " + location);
        } else {
            System.out.println("Number of Job Seeker find job in " + location.toUpperCase() + " : " + count);
        }
    }

    public void filterByDesiredJob(String desiredJob) {
        boolean found = false;
        System.out.println("\nFiltered by Desired Job: " + desiredJob);
        printHeader();
        int count = 0;
        for (JobSeeker js : jobSeekerList) {
            if (js.getDesiredJob().toLowerCase().contains(desiredJob.toLowerCase())) {
                printJobSeeker(js);
                found = true;
                count++;
            }
        }
        if (!found) {
            System.out.println("No results found for desired job: " + desiredJob);
        } else {
            System.out.println("Number of Job Seeker find job as " + desiredJob.toUpperCase() + " : " + count);
        }
    }

    private void printHeader() {
        System.out.println("                                                    Job Seeker List\n");
        System.out.printf("%-15s %-25s %-15s %-25s %-20s %-20s\n",
            "Name", "Email", "Phone", "Location", "Desired Job", "Skills");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }

    private void printJobSeeker(JobSeeker js) {
        System.out.printf("%-15s %-25s %-15s %-25s %-20s %-20s\n",js.getName(),
            js.getEmail(),js.getPhone(),js.getLocation(),js.getDesiredJob(),js.getSkills());
    }

    private void printCount() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total number of job seekers: " + jobSeekerList.size());
    }
}