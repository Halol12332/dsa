/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;
import adt.LinkedList;
import control.MatchingEngineController;
import boundary.MatchingEngineUI;
import entities.Applicant;
import entities.Company;
import entities.JobPosting;
import entities.Match;
import utility.FileUtil;
/**
 *
 * @author user
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Load data from files
        LinkedList<Applicant> applicants = FileUtil.loadApplicantsFromFile("applicants.txt");
        LinkedList<Company> companies = FileUtil.loadCompaniesFromFile("companies.txt");
        LinkedList<JobPosting> jobPostings = FileUtil.loadJobPostingsFromFile("jobPostings.txt", companies);

        // Perform matching
        MatchingEngineController matchingEngine = new MatchingEngineController(applicants, jobPostings);
        matchingEngine.performMatching();

        // Save matches to file
        LinkedList<Match> matches = matchingEngine.getMatches();
        FileUtil.saveMatchesToFile(matches, "matches.txt");

        // Display matches
        MatchingEngineUI ui = new MatchingEngineUI(matchingEngine);
        ui.displayMatches();
    }
}
    
