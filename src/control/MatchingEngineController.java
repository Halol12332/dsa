package control;

import adt.LinkedList;
import entities.Applicant;
import entities.JobPosting;
import entities.Match;

public class MatchingEngineController {
    private LinkedList<Applicant> applicants;
    private LinkedList<JobPosting> jobPostings;
    private LinkedList<Match> matches;
    
    public MatchingEngineController(){
        
    }

    public MatchingEngineController(LinkedList<Applicant> applicants, 
            LinkedList<JobPosting> jobPostings) {
        this.applicants = applicants;
        this.jobPostings = jobPostings;
        this.matches = new LinkedList<>();
    }

    public void performMatching(String applicantName) {
        // Clear previous matches
        matches.clear();

        // Find the applicant by name
        Applicant targetApplicant = null;
        for (int i = 0; i < applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            if (applicant.getName().equalsIgnoreCase(applicantName)) {
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
            JobPosting job = jobPostings.getEntry(i);
            double matchScore = calculateMatchScore(targetApplicant, job);
            if (matchScore > 0) {
                matches.add(new Match(targetApplicant, job, matchScore));
            }
        }
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

    public LinkedList<Match> filterMatches(String location, String jobType) {
        LinkedList<Match> filteredMatches = new LinkedList<>();
        for (int i = 0; i < matches.getNumberOfEntries(); i++) {
            Match match = matches.getEntry(i);
            boolean locationMatch = (location == null || match.getJobPosting().getLocation().equalsIgnoreCase(location));
            boolean jobTypeMatch = (jobType == null || match.getJobPosting().getJobType().equalsIgnoreCase(jobType));
            if (locationMatch && jobTypeMatch) {
                filteredMatches.add(match);
            }
        }
        return filteredMatches;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}
