package control;

import adt.HashTable;
import adt.LinkedList;
import entities.Applicant;
import entities.JobPosting;
import entities.Match;

public class MatchingEngineController {
    private HashTable<Applicant> applicants;
    private HashTable<JobPosting> jobPostings;
    private LinkedList<Match> matches;

    public MatchingEngineController() {
        this.applicants = new HashTable<>();
        this.jobPostings = new HashTable<>();
        this.matches = new LinkedList<>();
    }

    public void addApplicant(Applicant applicant) {
        applicants.insert(applicant);
    }

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.insert(jobPosting);
    }

    public void performMatching(String applicantName) {
        System.out.println("Searching for applicant: " + applicantName);

        // Clear previous matches
        matches.clear();

        // Find the applicant by name
        Applicant targetApplicant = applicants.search(applicantName);
        if (targetApplicant == null) {
            System.out.println("Applicant not found: " + applicantName);
            return;
        }

        // Perform matching for the target applicant
        LinkedList<JobPosting> allJobPostings = jobPostings.getAllEntries();
        for (int i = 0; i < allJobPostings.getNumberOfEntries(); i++) {
            JobPosting job = allJobPostings.getEntry(i);
            double matchScore = calculateMatchScore(targetApplicant, job);
            if (matchScore > 0) {
                matches.add(new Match(targetApplicant, job, matchScore));
            }
        }

        System.out.println("Matching completed. Found " + matches.getNumberOfEntries() + " matches.");
    }

    private double calculateMatchScore(Applicant applicant, JobPosting job) {
        double score = 0.0;

        // Skill Matching (compare each applicant skill with requiredSkills split by ';')
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
            score += 30;
        }

        // Location Matching
        if (applicant.getLocationPreference().equalsIgnoreCase(job.getLocation())) {
            score += 20;
        }

        // Major vs Industry Matching
        if (applicant.getMajor().equalsIgnoreCase(job.getCompany().getIndustry())) {
            score += 10;
        }

        return score;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}