package control;

import adt.LinkedList;
import entities.Applicant;
import entities.JobPostings;

public class SearchController {
    private LinkedList<JobPostings> jobPostings;
    private LinkedList<Applicant> applicants;

    public SearchController(LinkedList<JobPostings> jobPostings, LinkedList<Applicant> applicants) {
        this.jobPostings = jobPostings;
        this.applicants = applicants;
    }

    public LinkedList<JobPostings> searchJobs(String keyword) {
        LinkedList<JobPostings> results = new LinkedList<>();
        for (int i = 0; i < jobPostings.getNumberOfEntries(); i++) {
            JobPostings job = jobPostings.getEntry(i);
            if (job.getTitle().contains(keyword) || job.getDesc().contains(keyword) || job.getRequiredSkills().contains(keyword)) {
                results.add(job);
            }
        }
        return results;
    }

    public LinkedList<Applicant> searchApplicants(String keyword) {
        LinkedList<Applicant> results = new LinkedList<>();
        for (int i = 0; i < applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            if (applicant.getName().contains(keyword) || applicant.getMajor().contains(keyword) || applicant.getLocationPreference().contains(keyword)) {
                results.add(applicant);
            }
        }
        return results;
    }
}