package control;

import adt.HashTable;
import adt.LinkedList;
import entities.Applicant;
import entities.JobPosting;

public class SearchController {
    private HashTable<JobPosting> jobPostings;
    private HashTable<Applicant> applicants;

    public SearchController(HashTable<JobPosting> jobPostings, HashTable<Applicant> applicants) {
        this.jobPostings = jobPostings;
        this.applicants = applicants;
    }

    public LinkedList<JobPosting> searchJobs(String keyword) {
        LinkedList<JobPosting> results = new LinkedList<>();
        LinkedList<JobPosting> allJobPostings = jobPostings.getAllEntries();
        for (int i = 0; i < allJobPostings.getNumberOfEntries(); i++) {
            JobPosting job = allJobPostings.getEntry(i);
            if (job.getTitle().contains(keyword) || job.getDescription().contains(keyword) || job.getRequiredSkills().contains(keyword)) {
                results.add(job);
            }
        }
        return results;
    }

    public LinkedList<Applicant> searchApplicants(String keyword) {
        LinkedList<Applicant> results = new LinkedList<>();
        LinkedList<Applicant> allApplicants = applicants.getAllEntries();
        for (int i = 0; i < allApplicants.getNumberOfEntries(); i++) {
            Applicant applicant = allApplicants.getEntry(i);
            if (applicant.getName().contains(keyword) || applicant.getMajor().contains(keyword) || applicant.getLocationPreference().contains(keyword)) {
                results.add(applicant);
            }
        }
        return results;
    }
}