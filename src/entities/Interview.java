/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 * Represents an interview in the Internship Application Program.
 */
public class Interview {
    private String id;
    private Applicant applicant;
    private JobPosting jobPosting;
    private String dateTime;
    private String location;

    // Constructor
    public Interview(String id, Applicant applicant, 
            JobPosting jobPosting, String dateTime,
            String location) {
        this.id = id;
        this.applicant = applicant;
        this.jobPosting = jobPosting;
        this.dateTime = dateTime;
        this.location = location;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }
    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDate(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
