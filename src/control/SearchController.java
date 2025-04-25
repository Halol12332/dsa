package control;

/**
 * Author : Jaya Hakim Prajna
 */

import adt.ListInterface;
import adt.LinkedList;
import entities.Applicant;
import entities.JobPostings;
import org.apache.commons.text.similarity.FuzzyScore;

import java.util.Locale;

public class SearchController {
    private ListInterface<JobPostings> jobPostings;
    private ListInterface<Applicant> applicants;
    private FuzzyScore fuzzyScore;

    // ✅ Constructor now accepts ListInterface
    public SearchController(ListInterface<JobPostings> jobPostings, ListInterface<Applicant> applicants) {
        this.jobPostings = jobPostings;
        this.applicants = applicants;
        this.fuzzyScore = new FuzzyScore(Locale.ENGLISH);
    }

    // 🔍 Search jobs by fuzzy matching
    public ListInterface<JobPostings> searchJobs(String keyword) {
        ListInterface<JobPostings> results = new LinkedList<>();
        for (int i = 1; i <= jobPostings.getNumberOfEntries(); i++) {
            JobPostings job = jobPostings.getEntry(i);
            if (matchesKeyword(keyword, job.getTitle()) ||
                matchesKeyword(keyword, job.getDesc()) ||
                matchesKeyword(keyword, job.getRequiredSkills())) {
                results.add(job);
            }
        }
        return results;
    }

    // 🔍 Search applicants by fuzzy matching
    public ListInterface<Applicant> searchApplicants(String keyword) {
        ListInterface<Applicant> results = new LinkedList<>();
        for (int i = 1; i <= applicants.getNumberOfEntries(); i++) {
            Applicant applicant = applicants.getEntry(i);
            if (matchesKeyword(keyword, applicant.getName()) ||
                matchesKeyword(keyword, applicant.getMajorAsString()) ||
                matchesKeyword(keyword, applicant.getLocationPreferenceAsString())) {
                results.add(applicant);
            }
        }
        return results;
    }

    private boolean matchesKeyword(String keyword, String field) {
        if (field == null) {
            return false;
        }
        return fuzzyScore.fuzzyScore(field, keyword) > 0;
    }
}
