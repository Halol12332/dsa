/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Match {
    private Applicant applicant;
    private JobPostings jobPosting;
    private double matchScore;

    public Match(Applicant applicant, JobPostings jobPosting, double matchScore) {
        this.applicant = applicant;
        this.jobPosting = jobPosting;
        this.matchScore = matchScore;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public JobPostings getJobPosting() {
        return jobPosting;
    }

    public double getMatchScore() {
        return matchScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "applicant=" + applicant.getName() +
                ", jobPosting=" + jobPosting.getTitle() +
                ", matchScore=" + matchScore +
                '}';
    }
}
