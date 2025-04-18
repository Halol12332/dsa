package utility;

import adt.LinkedList;
import entities.Applicant;
import entities.Company;
import entities.JobPosting;
import entities.Match;

import java.io.*;
import java.util.StringJoiner;

public class FileUtil {

    public static void saveApplicantsToFile(LinkedList<Applicant> applicants, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Applicant applicant : applicants) {
                StringJoiner sj = new StringJoiner(",");
                sj.add(applicant.getId());
                sj.add(applicant.getName());
                sj.add(applicant.getEmail());
                sj.add(applicant.getPhoneNumber());
                sj.add(applicant.getMajor());
                sj.add(applicant.getSkills());
                sj.add(applicant.getLocationPreference());
                writer.write(sj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Applicant> loadApplicantsFromFile(String filePath) {
        LinkedList<Applicant> applicants = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                applicants.add(new Applicant(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    public static void saveCompaniesToFile(LinkedList<Company> companies, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Company company : companies) {
                StringJoiner sj = new StringJoiner(",");
                sj.add(company.getId());
                sj.add(company.getName());
                sj.add(company.getIndustry());
                sj.add(company.getLocation());
                writer.write(sj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Company> loadCompaniesFromFile(String filePath) {
        LinkedList<Company> companies = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                companies.add(new Company(fields[0], fields[1], fields[2], fields[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public static void saveJobPostingsToFile(LinkedList<JobPosting> jobPostings, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (JobPosting jobPosting : jobPostings) {
                StringJoiner sj = new StringJoiner(",");
                sj.add(jobPosting.getId());
                sj.add(jobPosting.getCompany().getId());
                sj.add(jobPosting.getTitle());
                sj.add(jobPosting.getDescription());
                sj.add(jobPosting.getRequiredSkills());
                sj.add(jobPosting.getLocation());
                sj.add(String.valueOf(jobPosting.getSalary()));
                writer.write(sj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<JobPosting> loadJobPostingsFromFile(String filePath, LinkedList<Company> companies) {
        LinkedList<JobPosting> jobPostings = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Company company = findCompanyById(companies, fields[1]);
                jobPostings.add(new JobPosting(fields[0], company, fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobPostings;
    }

    private static Company findCompanyById(LinkedList<Company> companies, String companyId) {
        for (Company company : companies) {
            if (company.getId().equals(companyId)) {
                return company;
            }
        }
        return null;
    }

    public static void saveMatchesToFile(LinkedList<Match> matches, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Match match : matches) {
                StringJoiner sj = new StringJoiner(",");
                sj.add(match.getApplicant().getId());
                sj.add(match.getJobPosting().getId());
                sj.add(String.valueOf(match.getMatchScore()));
                writer.write(sj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Match> loadMatchesFromFile(String filePath, LinkedList<Applicant> applicants, LinkedList<JobPosting> jobPostings) {
        LinkedList<Match> matches = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Applicant applicant = findApplicantById(applicants, fields[0]);
                JobPosting jobPosting = findJobPostingById(jobPostings, fields[1]);
                matches.add(new Match(applicant, jobPosting, Double.parseDouble(fields[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static Applicant findApplicantById(LinkedList<Applicant> applicants, String applicantId) {
        for (Applicant applicant : applicants) {
            if (applicant.getId().equals(applicantId)) {
                return applicant;
            }
        }
        return null;
    }

    private static JobPosting findJobPostingById(LinkedList<JobPosting> jobPostings, String jobPostingId) {
        for (JobPosting jobPosting : jobPostings) {
            if (jobPosting.getId().equals(jobPostingId)) {
                return jobPosting;
            }
        }
        return null;
    }
}