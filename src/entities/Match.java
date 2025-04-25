package entities;

import java.io.Serializable;

public class Match implements Serializable {
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

    // Method to generate a detailed report format
    public String generateReport() {
        StringBuilder report = new StringBuilder();

        // Header
        report.append("Match Report\n");
        report.append("-----------\n");

        // Applicant Details
        report.append("Applicant Details:\n");
        report.append("Name: ").append(applicant.getName()).append("\n");
        report.append("Email: ").append(applicant.getEmail()).append("\n");
        report.append("Phone Number: ").append(applicant.getPhoneNumber()).append("\n");
        report.append("Major: ").append(applicant.getMajor()).append("\n");
        report.append("Skills: ").append(applicant.getSkills()).append("\n");
        report.append("Location Preference: ").append(applicant.getLocationPreference()).append("\n");
        report.append("Desired Job: ").append(applicant.getDesiredJob()).append("\n\n");

        // Job Posting Details
        report.append("Job Posting Details:\n");
        report.append("Job ID: ").append(jobPosting.getJobID()).append("\n");
        report.append("Title: ").append(jobPosting.getTitle()).append("\n");
        report.append("Description: ").append(jobPosting.getDesc()).append("\n");
        report.append("Location: ").append(jobPosting.getLocation()).append("\n");
        report.append("Company Name: ").append(jobPosting.getCompName()).append("\n");
        report.append("Required Skills: ").append(jobPosting.getRequiredSkills()).append("\n");
        report.append("Job Type: ").append(jobPosting.getJobType()).append("\n");
        report.append("Salary: ").append(jobPosting.getSalary()).append("\n\n");

        // Match Score
        report.append("Match Score: ").append(matchScore).append("%\n");

        return report.toString();
    }
}