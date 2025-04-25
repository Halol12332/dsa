package dao;

import adt.*;
import entities.*;
import dao.MatchDAO;

import java.time.LocalDateTime;

/**
 * Initializes sample interview data
 * @author Your Name
 */
public class InterviewInitializer {
    private final InterviewDAO interviewDAO;
    private final MatchDAO matchDAO;
    
    public InterviewInitializer() {
        this.interviewDAO = new InterviewDAO();
        this.matchDAO = new MatchDAO();
    }
    
    public InterviewDAO getInterviewDAO() {
        return interviewDAO;
    }
    
    public void initializeInterviews() {
        ListInterface<Match> matches = matchDAO.retrieveFromFile();
        ListInterface<Interview> interviews = new LinkedList<>();
        
        if (matches.isEmpty()) {
            System.out.println("No matches available to create interviews");
            return;
        }
        
        // Create sample interviews from existing matches
        LocalDateTime now = LocalDateTime.now();
        
        // Create interviews with different statuses for demonstration
        if (matches.getNumberOfEntries() >= 1) {
            Match match1 = matches.getEntry(1);
            Interview interview1 = new Interview(
                match1, 
                now.plusDays(1).withHour(10).withMinute(0), 
                "Conference Room A", 
                "Sarah Johnson", 
                "Scheduled"
            );
            interviews.add(interview1);
        }
        
        if (matches.getNumberOfEntries() >= 2) {
            Match match2 = matches.getEntry(2);
            Interview interview2 = new Interview(
                match2, 
                now.plusDays(2).withHour(14).withMinute(30), 
                "Online (Zoom)", 
                "Michael Chen", 
                "Scheduled"
            );
            interviews.add(interview2);
        }
        
        if (matches.getNumberOfEntries() >= 3) {
            Match match3 = matches.getEntry(3);
            Interview interview3 = new Interview(
                match3, 
                now.minusDays(1).withHour(11).withMinute(0), 
                "Conference Room B", 
                "Emma Rodriguez", 
                "Completed"
            );
            interview3.setRating(8.5);
            interview3.setFeedback("Excellent communication skills. Strong technical knowledge. Good cultural fit.");
            interviews.add(interview3);
        }
        
        if (matches.getNumberOfEntries() >= 4) {
            Match match4 = matches.getEntry(4);
            Interview interview4 = new Interview(
                match4, 
                now.minusDays(2).withHour(15).withMinute(0), 
                "Conference Room C", 
                "James Wilson", 
                "Successful"
            );
            interview4.setRating(9.0);
            interview4.setFeedback("Outstanding candidate. Strong leadership potential. Ready for immediate placement.");
            interviews.add(interview4);
        }
        
        if (matches.getNumberOfEntries() >= 5) {
            Match match5 = matches.getEntry(5);
            Interview interview5 = new Interview(
                match5, 
                now.minusDays(3).withHour(9).withMinute(0), 
                "Online (Microsoft Teams)", 
                "David Lee", 
                "Rejected"
            );
            interview5.setRating(4.0);
            interview5.setFeedback("Lacks required technical skills. Not a good fit for this position.");
            interviews.add(interview5);
        }
        
        interviewDAO.saveToFile(interviews);
        System.out.println("Sample interviews initialized successfully");
    }
    
    public static void main(String[] args) {
        InterviewInitializer initializer = new InterviewInitializer();
        initializer.initializeInterviews();
        
        ListInterface<Interview> loaded = initializer.interviewDAO.retrieveFromFile();
        System.out.println("Loaded interviews:");
        System.out.println(Interview.getTableHeader());
        for (int i = 1; i <= loaded.getNumberOfEntries(); i++) {
            System.out.println(loaded.getEntry(i));
        }
        System.out.println(Interview.getTableFooter());
    }
}