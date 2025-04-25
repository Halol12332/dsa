package dao;

import adt.ListInterface;
import adt.LinkedList;
import entities.Applicant;

public class ApplicantInitializer {
    private final ApplicantDAO applicantDAO = new ApplicantDAO();
    private ListInterface<Applicant> applicants = new LinkedList<>();

    public ApplicantDAO getApplicantDAO() {
        return applicantDAO;
    }

    public ListInterface<Applicant> initializeApplicants() {
        applicants = new LinkedList<>();

        // Applicant 1
        ListInterface<String> skills1 = new LinkedList<>();
        skills1.add("HTML");
        skills1.add("CSS");
        skills1.add("Adobe XD");
        skills1.add("JavaScript");
        skills1.add("Financial Reporting");
        skills1.add("Excel");
        skills1.add("Accounting Standards");

        ListInterface<String> locationPreference1 = new LinkedList<>();
        locationPreference1.add("Kuala Lumpur");

        applicants.add(new Applicant("John Doe", "john@example.com", "0123456789", "Computer Science", skills1, locationPreference1, "Part-Time"));

        // Applicant 2
        ListInterface<String> skills2 = new LinkedList<>();
        skills2.add("Python");
        skills2.add("SQL");
        skills2.add("Machine Learning");

        ListInterface<String> locationPreference2 = new LinkedList<>();
        locationPreference2.add("Selangor");

        applicants.add(new Applicant("Jane Smith", "jane@example.com", "0198765432", "Data Science", skills2, locationPreference2, "Part-Time"));

        // Applicant 3
        ListInterface<String> skills3 = new LinkedList<>();
        skills3.add("Python");
        skills3.add("SQL");
        skills3.add("JavaScript");

        ListInterface<String> locationPreference3 = new LinkedList<>();
        locationPreference3.add("Selangor");

        applicants.add(new Applicant("Alice Johnson", "alice@example.com", "0177777777", "Web Design", skills3, locationPreference3, "Part-Time"));

        // Applicant 4
        ListInterface<String> skills4 = new LinkedList<>();
        skills4.add("Financial Reporting");
        skills4.add("Excel");

        ListInterface<String> locationPreference4 = new LinkedList<>();
        locationPreference4.add("Melaka");

        applicants.add(new Applicant("Bob Lee", "bob@example.com", "0168888888", "Accounting", skills4, locationPreference4, "Full-Time"));

        // Applicant 5
        ListInterface<String> skills5 = new LinkedList<>();
        skills5.add("Unity");
        skills5.add("C#");
        skills5.add("Pixel Art");

        ListInterface<String> locationPreference5 = new LinkedList<>();
        locationPreference5.add("Negeri Sembilan");

        applicants.add(new Applicant("Charlie Brown", "charlie@example.com", "0139999999", "Game Development", skills5, locationPreference5, "Full-Time"));

        applicantDAO.saveToFile(applicants);
        return applicants;
    }

    public ListInterface<Applicant> getApplicants() {
        return applicants;
    }

    public static void main(String[] args) {
        ApplicantInitializer initializer = new ApplicantInitializer();
        initializer.initializeApplicants();

        ListInterface<Applicant> loadedApplicants = initializer.applicantDAO.retrieveFromFile();
        for (int i = 1; i <= loadedApplicants.getNumberOfEntries(); i++) {
            System.out.println(loadedApplicants.getEntry(i));
        }
    }
}
