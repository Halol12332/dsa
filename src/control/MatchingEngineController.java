package control;

import adt.LinkedList;
import entities.Applicant;
import entities.JobPostings;
import entities.Match;

public class MatchingEngineController {
    private LinkedList<Applicant> applicants;
    private LinkedList<JobPostings> jobPostings;
    private LinkedList<Match> matches;

    public MatchingEngineController() {
        this.applicants = new LinkedList<>();
        this.jobPostings = new LinkedList<>();
        this.matches = new LinkedList<>();
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public void addJobPosting(JobPostings jobPosting) {
        jobPostings.add(jobPosting);
    }

    public void performMatching(String applicantName) {
        System.out.println("Searching for applicant: " + applicantName);

        // Clear previous matches
        matches.clear();

        // Find the applicant by name
        Applicant targetApplicant = null;
        for (int i = 0; i < applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            if (applicant.getName().equals(applicantName)) {
                targetApplicant = applicant;
                break;
            }
        }

        if (targetApplicant == null) {
            System.out.println("Applicant not found: " + applicantName);
            return;
        }

        // Perform matching for the target applicant
        for (int i = 0; i < jobPostings.getNumberOfEntries(); i++) {
            JobPostings job = jobPostings.getEntry(i);
            double matchScore = calculateMatchScore(targetApplicant, job);
            if (matchScore > 0) {
                matches.add(new Match(targetApplicant, job, matchScore));
            }
        }

        System.out.println("Matching completed. Found " + matches.getNumberOfEntries() + " matches.");
    }

    private double calculateMatchScore(Applicant applicant, JobPostings job) {
        double score = 0.0;

        // Skill Matching with proficiency levels
        String[] requiredSkills = job.getRequiredSkills().split(";");
        int matchCount = 0;
        for (int i = 0; i < applicant.getSkills().getNumberOfEntries(); i++) {
            String skill = applicant.getSkills().getEntry(i);
            for (int j = 0; j < requiredSkills.length; j++) {
                if (skill.equalsIgnoreCase(requiredSkills[j].trim())) {
                    matchCount++;
                    break;
                }
            }
        }
        if (matchCount > 0) {
            score += 30 * (matchCount / (double) requiredSkills.length);
        }

        // Location Matching
        if (applicant.getLocationPreference().equalsIgnoreCase(job.getLocation())) {
            score += 20;
        }

        // Major vs Industry Matching
        /*
        if (applicant.getMajor().equalsIgnoreCase(job.getCompany().getIndustry())) {
            score += 10;
        }
        */
        return score;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}