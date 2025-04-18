/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Match {
    private Applicant applicant;
    private JobPosting jobPosting;
    private double matchScore;

    public Match(Applicant applicant, JobPosting jobPosting, double matchScore) {
        this.applicant = applicant;
        this.jobPosting = jobPosting;
        this.matchScore = matchScore;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public JobPosting getJobPosting() {
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
