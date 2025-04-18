package javaapplication3;

import adt.LinkedList;
import control.MatchingEngineController;
import boundary.MatchingEngineUI;
import entities.Applicant;
import entities.Company;
import entities.JobPosting;
import entities.Match;
import utility.FileUtil;

public class JavaApplication3 {

    public static void main(String[] args) {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));


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

        // Load matches from file
        LinkedList<Match> loadedMatches = FileUtil.loadMatchesFromFile("matches.txt", applicants, jobPostings);

        // Display matches
        MatchingEngineUI ui = new MatchingEngineUI(matchingEngine);
        ui.displayMatches();
    }
}