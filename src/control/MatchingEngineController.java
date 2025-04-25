package control;

/**
 * Author : Jaya Hakim Prajna
 */

import adt.*;
import entities.Applicant;
import entities.JobPostings;
import entities.Match;
import dao.ApplicantDAO;
import dao.JobPostingsDAO;
import dao.MatchDAO;

import java.io.*;
import java.util.Scanner;

public class MatchingEngineController {
    private final ListInterface<Applicant> applicants;
    private final ListInterface<JobPostings> jobPostings;
    private final ListInterface<Match> matches;
    private final MatchDAO matchDAO;
    private final ApplicantDAO applicantDAO;
    private final JobPostingsDAO jobPostingsDAO;
            Scanner scanner = new Scanner(System.in);

    

    public MatchingEngineController() {
        this.applicantDAO = new ApplicantDAO();
        this.jobPostingsDAO = new JobPostingsDAO();
        this.matchDAO = new MatchDAO();
        
        // Load applicants and job postings from files
        this.applicants = applicantDAO.retrieveFromFile();
        this.jobPostings = jobPostingsDAO.retrieveFromFile();
        this.matches = matchDAO.retrieveFromFile();
    }

    public void performMatchingAsApplicant(String applicantName) {
        System.out.println("Matching as Applicant: " + applicantName);

        Applicant targetApplicant = findApplicantByName(applicantName);
        if (targetApplicant == null) {
            System.out.println("Applicant not found: " + applicantName);
            return;
        }

        ListInterface<Match> potentialMatches = new LinkedList<>();
        boolean matchFound = false;
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPostings job = jobPostings.getEntry(i);
            ListInterface<String> matchedSkills = new LinkedList<>();
            double matchScore = calculateMatchScore(targetApplicant, job, matchedSkills);
            if (matchScore > 60.0) {
                potentialMatches.add(new Match(targetApplicant, job, matchScore));
                matchFound = true;
                System.out.println("------------------------\nMatch Found:");
                System.out.println("Applicant: " + targetApplicant.getName());
                System.out.println("Job Posting: " + job.getTitle() + " (ID: " + job.getJobID() + ")");
                System.out.print("Matched Skills: ");
                for (int j = 1; j <= matchedSkills.getNumberOfEntries(); j++) {
                    System.out.print(matchedSkills.getEntry(j));
                    if (j < matchedSkills.getNumberOfEntries()) System.out.print(", ");
                }
                System.out.println("\nMatch Score: " + matchScore + "%\n");
            }
        }

        if (matchFound) {
            System.out.println("Matching completed. Found matches for the following jobs:");
            for (int i = 1; i <= potentialMatches.getNumberOfEntries(); i++) {
                JobPostings job = potentialMatches.getEntry(i).getJobPosting();
                System.out.println(i + ". " + job.getTitle() + " (ID: " + job.getJobID() + ")");
            }

            System.out.print("Select the job posting you want to apply to by number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice >= 1 && choice <= potentialMatches.getNumberOfEntries()) {
                Match selectedMatch = potentialMatches.getEntry(choice);
                matches.add(selectedMatch);
                saveMatchesToFile();
                System.out.println("You have successfully applied to the job posting: " + selectedMatch.getJobPosting().getTitle());
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("No matches found for the applicant.");
        }
    }

    public void performMatchingAsJobPosting(String jobID) {
        System.out.println("Matching as Job Posting: " + jobID);

        JobPostings targetJobPosting = findJobPostingByID(jobID);
        if (targetJobPosting == null) {
            System.out.println("Job Posting not found: " + jobID);
            return;
        }

        ListInterface<Match> potentialMatches = new LinkedList<>();
        boolean matchFound = false;
        for (int i = 1; i <= applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            ListInterface<String> matchedSkills = new LinkedList<>();
            double matchScore = calculateMatchScore(applicant, targetJobPosting, matchedSkills);
            if (matchScore > 60.0) {
                potentialMatches.add(new Match(applicant, targetJobPosting, matchScore));
                matchFound = true;
                System.out.println("\nMatch Found:");
                System.out.println("Job Posting: " + targetJobPosting.getTitle() + " (ID: " + targetJobPosting.getJobID() + ")");
                System.out.println("Applicant: " + applicant.getName());
                System.out.print("Matched Skills: ");
                for (int j = 1; j <= matchedSkills.getNumberOfEntries(); j++) {
                    System.out.print(matchedSkills.getEntry(j));
                    if (j < matchedSkills.getNumberOfEntries()) System.out.print(", ");
                }
                System.out.println("\nMatch Score: " + matchScore + "%\n");
            }
        }

        if (matchFound) {
            System.out.println("Matching completed. Found matches for the following applicants:");
            for (int i = 1; i <= potentialMatches.getNumberOfEntries(); i++) {
                Applicant applicant = potentialMatches.getEntry(i).getApplicant();
                System.out.println(i + ". " + applicant.getName());
            }

            System.out.print("Select the applicants you want to match with by number (comma-separated): ");
            String choices = scanner.nextLine();
            String[] choiceArray = choices.split(",");

            for (String choice : choiceArray) {
                int choiceIndex = Integer.parseInt(choice.trim());
                if (choiceIndex >= 1 && choiceIndex <= potentialMatches.getNumberOfEntries()) {
                    Match selectedMatch = potentialMatches.getEntry(choiceIndex);
                    matches.add(selectedMatch);
                    System.out.println("You have successfully matched with the applicant: " + selectedMatch.getApplicant().getName());
                } else {
                    System.out.println("Invalid selection: " + choice);
                }
            }

            saveMatchesToFile();
        } else {
            System.out.println("No matches found for the job posting.");
        }
    }



    private double calculateMatchScore(Applicant applicant, JobPostings job, ListInterface<String> matchedSkills) {
        double score = 0.0;
        String[] requiredSkills = job.getRequiredSkills().split(",");
        int matchCount = 0;

        for (int i = 1; i <= applicant.getSkills().getNumberOfEntries(); i++) {
            String skill = applicant.getSkills().getEntry(i).trim();
            for (String req : requiredSkills) {
                String requiredSkill = req.trim();
                if (skill.equalsIgnoreCase(requiredSkill)) {
                    matchedSkills.add(skill);
                    matchCount++;
                    break;
                }
            }
        }

        if (matchCount > 0) {
            score += 30 * (matchCount / (double) requiredSkills.length);
        }

        if (applicant.getLocationPreference().getEntry(1).trim().equalsIgnoreCase(job.getLocation().trim())) {
            score += 25;
        }

        if (applicant.getDesiredJob().trim().equalsIgnoreCase(job.getJobType().trim())) {
            score += 15;
        }

        return (score / 70) * 100;
    }


    private Applicant findApplicantByName(String name) {
        for (int i = 1; i <= applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            if (applicant.getName().equalsIgnoreCase(name)) {
                return applicant;
            }
        }
        return null;
    }

    private JobPostings findJobPostingByID(String jobID) {
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPostings job = jobPostings.getEntry(i);
            if (job.getJobID().equalsIgnoreCase(jobID)) {
                return job;
            }
        }
        return null;
    }

    private void saveMatchesToFile() {
        if (!matches.isEmpty()) {
            matchDAO.saveToFile(matches);
        } else {
            System.out.println("No matches to save.");
        }
    }


    @SuppressWarnings("unchecked")
    public ListInterface<Match> loadMatchesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("matches.dat"))) {
            return (ListInterface<Match>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading matches from file: " + e.getMessage());
        }
        return new LinkedList<>();
    }

    public ListInterface<Match> getMatches() {
        return matches;
    }

    public void viewAllMatches() {
        ListInterface<Match> loadedMatches = loadMatchesFromFile();
        if (loadedMatches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Matches Report:");
            System.out.println(generateReport(loadedMatches)); // Pass loaded list
        }
    }

    // For displaying applicant search results
    public LinkedList<String> getApplicantSearchResults(String keyword, SearchController searchController) {
        ListInterface<Applicant> foundApplicants = searchController.searchApplicants(keyword);
        LinkedList<String> results = new LinkedList<>();
        for (int i = 1; i <= foundApplicants.getNumberOfEntries(); i++) {
            Applicant a = foundApplicants.getEntry(i);
            results.add(i + ". " + a.getName() + " - " + a.getEmail());
        }
        return results;
                
    }

    public Applicant getApplicantBySearchIndex(String keyword, int index, SearchController searchController) {
        ListInterface<Applicant> foundApplicants = searchController.searchApplicants(keyword);
        return (index >= 1 && index <= foundApplicants.getNumberOfEntries()) ? foundApplicants.getEntry(index) : null;
    }

    // For displaying job posting search results
    public LinkedList<String> getJobSearchResults(String keyword, SearchController searchController) {
        ListInterface<JobPostings> foundJobs = searchController.searchJobs(keyword);
        LinkedList<String> results = new LinkedList<>();
        for (int i = 1; i <= foundJobs.getNumberOfEntries(); i++) {
            JobPostings j = foundJobs.getEntry(i);
            results.add(i + ". " + j.getTitle() + " (ID: " + j.getJobID() + ") - " + j.getCompName());
        }
        return results;
    }

    public JobPostings getJobPostingBySearchIndex(String keyword, int index, SearchController searchController) {
        ListInterface<JobPostings> foundJobs = searchController.searchJobs(keyword);
        return (index >= 1 && index <= foundJobs.getNumberOfEntries()) ? foundJobs.getEntry(index) : null;
    }


    // Method to generate a detailed report using toString from Match class
    public String generateReport(ListInterface<Match> matchList) {
        StringBuilder report = new StringBuilder();

        report.append("Matching Engine Report\n");
        report.append("======================\n\n");
        if (matchList.isEmpty()) {
            report.append("No matches found.\n");
        } else {
            for (int i = 1; i <= matchList.getNumberOfEntries(); i++) {
                Match match = matchList.getEntry(i);
                report.append("Match ").append(i).append(":\n");
                report.append(match.toString()).append("\n\n");
            }
        }
        return report.toString();
    }
}