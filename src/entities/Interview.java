package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an interview in the Internship Application Program.
 * @author Your Name
 */
public class Interview implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    private Match match;
    private LocalDateTime scheduledDateTime;
    private String location;
    private String interviewerName;
    private String interviewStatus; // "Scheduled", "Completed", "Cancelled", "Rejected", "Successful"
    private String feedback;
    private double rating; // 0.0 to 10.0 scale
    
    public Interview() {
    }
    
    public Interview(Match match, LocalDateTime scheduledDateTime, String location, 
                     String interviewerName, String interviewStatus) {
        this.match = match;
        this.scheduledDateTime = scheduledDateTime;
        this.location = location;
        this.interviewerName = interviewerName;
        this.interviewStatus = interviewStatus;
        this.feedback = "";
        this.rating = 0.0;
    }
    
    // Getters and Setters
    public Match getMatch() {
        return match;
    }
    
    public void setMatch(Match match) {
        this.match = match;
    }
    
    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }
    
    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getInterviewerName() {
        return interviewerName;
    }
    
    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }
    
    public String getInterviewStatus() {
        return interviewStatus;
    }
    
    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }
    
    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        if (rating >= 0.0 && rating <= 10.0) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
        }
    }
    
    public String getFormattedDateTime() {
        return scheduledDateTime.format(DATE_FORMAT);
    }
    
    @Override
    public String toString() {
        return String.format("| %-20s | %-20s | %-15s | %-18s | %-12s | %5.1f |",
                match.getApplicant().getName(),
                match.getJobPosting().getTitle(),
                getFormattedDateTime(),
                interviewerName,
                interviewStatus,
                rating
        );
    }
    
    public static String getTableHeader() {
        String line = "+----------------------+----------------------+-----------------+--------------------+--------------+-------+";
        String header = String.format("| %-20s | %-20s | %-15s | %-18s | %-12s | %-5s |",
                "Applicant Name", "Job Title", "Date & Time", "Interviewer", "Status", "Rating");
        return line + "\n" + header + "\n" + line;
    }
    
    public static String getTableFooter() {
        return "+----------------------+----------------------+-----------------+--------------------+--------------+-------+";
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        
        // Header
        report.append("Interview Report\n");
        report.append("-----------------\n\n");
        
        // Interview Details
        report.append("Interview Details:\n");
        report.append("Date & Time: ").append(getFormattedDateTime()).append("\n");
        report.append("Location: ").append(location).append("\n");
        report.append("Interviewer: ").append(interviewerName).append("\n");
        report.append("Status: ").append(interviewStatus).append("\n");
        if (rating > 0) {
            report.append("Rating: ").append(rating).append(" / 10\n");
        }
        if (!feedback.isEmpty()) {
            report.append("Feedback: ").append(feedback).append("\n");
        }
        report.append("\n");
        
        // Applicant and Job Details
        report.append(match.generateReport());
        
        return report.toString();
    }
}