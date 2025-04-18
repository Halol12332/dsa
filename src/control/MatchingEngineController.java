/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import entities.Applicant;
import entities.JobPosting;
import entities.Match;



public class MatchingEngineController {
    private LinkedList<Applicant> applicants;
    private LinkedList<JobPosting> jobPostings;
    private LinkedList<Match> matches;

    public MatchingEngineController(LinkedList<Applicant> applicants, LinkedList<JobPosting> jobPostings) {
        this.applicants = applicants;
        this.jobPostings = jobPostings;
        this.matches = new LinkedList<>();
    }

    public void performMatching() {
        for (Applicant applicant : applicants) {
            for (JobPosting job : jobPostings) {
                double matchScore = calculateMatchScore(applicant, job);
                if (matchScore > 0) {
                    matches.add(new Match(applicant, job, matchScore));
                }
            }
        }
    }

    private double calculateMatchScore(Applicant applicant, JobPosting job) {
        double score = 0.0;

        // Skill Matching
        if (applicant.getSkills().equalsIgnoreCase(job.getRequiredSkills())) {
            score += 30; // Example weight for skill match
        }

        // Location Matching
        if (applicant.getLocationPreference().equalsIgnoreCase(job.getLocation())) {
            score += 20; // Example weight for location match
        }

        // Major Matching
        if (applicant.getMajor().equalsIgnoreCase(job.getCompany().getIndustry())) {
            score += 10; // Example weight for major match
        }

        // Additional criteria can be added here
        return score;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}
