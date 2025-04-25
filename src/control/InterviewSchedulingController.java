package control;

import adt.*;
import dao.InterviewDAO;
import dao.MatchDAO;
import entities.Interview;
import entities.Match;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

/**
 * Controller for the Interview Schedule Module
 * @author Tan Jin Yan
 */
public class InterviewSchedulingController {
    private ListInterface<Interview> interviews;
    private final InterviewDAO interviewDAO;
    private final MatchDAO matchDAO;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public InterviewSchedulingController() {
        this.interviewDAO = new InterviewDAO();
        this.matchDAO = new MatchDAO();
        this.interviews = interviewDAO.retrieveFromFile();
    }
    
    public boolean scheduleInterview(Match match, String dateTimeStr, String location, String interviewer) {
        try {
            LocalDateTime scheduledDateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMAT);
            
            // Check if the match already has an interview scheduled
            for (int i = 1; i <= interviews.getNumberOfEntries(); i++) {
                Interview existingInterview = interviews.getEntry(i);
                if (existingInterview.getMatch().getApplicant().getEmail().equals(match.getApplicant().getEmail()) &&
                    existingInterview.getMatch().getJobPosting().getJobID().equals(match.getJobPosting().getJobID()) &&
                    !existingInterview.getInterviewStatus().equals("Cancelled") &&
                    !existingInterview.getInterviewStatus().equals("Rejected")) {
                    System.out.println("This match already has an interview scheduled.");
                    return false;
                }
            }
            
            // Create a new interview
            Interview newInterview = new Interview(match, scheduledDateTime, location, interviewer, "Scheduled");
            interviews.add(newInterview);
            interviewDAO.saveToFile(interviews);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm format.");
            return false;
        }
    }
    
    public boolean updateInterviewStatus(int interviewIndex, String newStatus) {
        if (interviewIndex < 1 || interviewIndex > interviews.getNumberOfEntries()) {
            System.out.println("Invalid interview selection.");
            return false;
        }
        
        Interview interview = interviews.getEntry(interviewIndex);
        interview.setInterviewStatus(newStatus);
        interviews.replace(interviewIndex, interview);
        interviewDAO.saveToFile(interviews);
        return true;
    }
    
    public boolean rateInterview(int interviewIndex, double rating, String feedback) {
        if (interviewIndex < 1 || interviewIndex > interviews.getNumberOfEntries()) {
            System.out.println("Invalid interview selection.");
            return false;
        }
        
        try {
            Interview interview = interviews.getEntry(interviewIndex);
            interview.setRating(rating);
            interview.setFeedback(feedback);
            interviews.replace(interviewIndex, interview);
            interviewDAO.saveToFile(interviews);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ListInterface<Interview> getAllInterviews() {
        return interviews;
    }
    
    public ListInterface<Interview> getScheduledInterviews() {
        return interviews.filter(interview -> interview.getInterviewStatus().equals("Scheduled"));
    }
    
    public ListInterface<Interview> getCompletedInterviews() {
        return interviews.filter(interview -> 
            interview.getInterviewStatus().equals("Completed") ||
            interview.getInterviewStatus().equals("Successful") ||
            interview.getInterviewStatus().equals("Rejected"));
    }
    
    public ListInterface<Interview> getSuccessfulInterviews() {
        return interviews.filter(interview -> interview.getInterviewStatus().equals("Successful"));
    }
    
    public ListInterface<Interview> filterInterviewsByJobTitle(String jobTitle) {
        return interviews.filter(interview -> 
            interview.getMatch().getJobPosting().getTitle().toLowerCase().contains(jobTitle.toLowerCase()));
    }
    
    public ListInterface<Interview> filterInterviewsByApplicantName(String applicantName) {
        return interviews.filter(interview -> 
            interview.getMatch().getApplicant().getName().toLowerCase().contains(applicantName.toLowerCase()));
    }
    
    public ListInterface<Interview> filterInterviewsByStatus(String status) {
        return interviews.filter(interview -> interview.getInterviewStatus().equals(status));
    }
    
    public ListInterface<Match> getAvailableMatches() {
        ListInterface<Match> allMatches = matchDAO.retrieveFromFile();
        ListInterface<Match> availableMatches = new LinkedList<>();
        
        // Find matches that don't have interviews scheduled yet
        for (int i = 1; i <= allMatches.getNumberOfEntries(); i++) {
            Match match = allMatches.getEntry(i);
            boolean hasInterview = false;
            
            for (int j = 1; j <= interviews.getNumberOfEntries(); j++) {
                Interview interview = interviews.getEntry(j);
                if (match.getApplicant().getEmail().equals(interview.getMatch().getApplicant().getEmail()) &&
                    match.getJobPosting().getJobID().equals(interview.getMatch().getJobPosting().getJobID()) &&
                    !interview.getInterviewStatus().equals("Cancelled") &&
                    !interview.getInterviewStatus().equals("Rejected")) {
                    hasInterview = true;
                    break;
                }
            }
            
            if (!hasInterview) {
                availableMatches.add(match);
            }
        }
        
        return availableMatches;
    }
    
    public void sortInterviewsByDateTime() {
        interviews.sort(Comparator.comparing(Interview::getScheduledDateTime));
    }
    
    public void sortInterviewsByRating() {
        interviews.sort(Comparator.comparing(Interview::getRating).reversed());
    }
    
    public String generateScheduleReport() {
        StringBuilder report = new StringBuilder();
        
        // Header
        report.append("INTERVIEW SCHEDULE REPORT\n");
        report.append("=======================\n\n");
        report.append("Generated on: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
        
        // Upcoming interviews
        ListInterface<Interview> scheduled = getScheduledInterviews();
        scheduled.sort(Comparator.comparing(Interview::getScheduledDateTime));
        
        report.append("UPCOMING INTERVIEWS\n");
        report.append("------------------\n");
        if (scheduled.isEmpty()) {
            report.append("No upcoming interviews scheduled.\n\n");
        } else {
            report.append(Interview.getTableHeader()).append("\n");
            for (int i = 1; i <= scheduled.getNumberOfEntries(); i++) {
                report.append(scheduled.getEntry(i)).append("\n");
            }
            report.append(Interview.getTableFooter()).append("\n\n");
        }
        
        // Completed interviews
        ListInterface<Interview> completed = getCompletedInterviews();
        completed.sort(Comparator.comparing(Interview::getRating).reversed());
        
        report.append("COMPLETED INTERVIEWS\n");
        report.append("-------------------\n");
        if (completed.isEmpty()) {
            report.append("No completed interviews found.\n\n");
        } else {
            report.append(Interview.getTableHeader()).append("\n");
            for (int i = 1; i <= completed.getNumberOfEntries(); i++) {
                report.append(completed.getEntry(i)).append("\n");
            }
            report.append(Interview.getTableFooter()).append("\n\n");
        }
        
        // Successful candidates
        ListInterface<Interview> successful = getSuccessfulInterviews();
        successful.sort(Comparator.comparing(Interview::getRating).reversed());
        
        report.append("SUCCESSFUL CANDIDATES\n");
        report.append("--------------------\n");
        if (successful.isEmpty()) {
            report.append("No successful candidates found.\n\n");
        } else {
            report.append(Interview.getTableHeader()).append("\n");
            for (int i = 1; i <= successful.getNumberOfEntries(); i++) {
                report.append(successful.getEntry(i)).append("\n");
            }
            report.append(Interview.getTableFooter()).append("\n\n");
        }
        
        // Summary
        report.append("SUMMARY\n");
        report.append("-------\n");
        report.append("Total interviews: ").append(interviews.getNumberOfEntries()).append("\n");
        report.append("Scheduled: ").append(scheduled.getNumberOfEntries()).append("\n");
        report.append("Completed: ").append(completed.getNumberOfEntries()).append("\n");
        report.append("Successful: ").append(successful.getNumberOfEntries()).append("\n");
        
        return report.toString();
    }
    
    public String generateDetailedInterviewReport(int interviewIndex) {
        if (interviewIndex < 1 || interviewIndex > interviews.getNumberOfEntries()) {
            return "Invalid interview selection.";
        }
        
        Interview interview = interviews.getEntry(interviewIndex);
        return interview.generateReport();
    }
}