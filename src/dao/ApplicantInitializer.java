package dao;

import adt.*;
import entities.Applicant;

public class ApplicantInitializer {
    private final ApplicantDAO applicantDAO;

    public ApplicantInitializer() {
        this.applicantDAO = new ApplicantDAO();
    }

    public void initializeApplicants() {
        ListInterface<Applicant> aList = new LinkedList<>();
        //aList.add(new Applicant("A001", "John Doe", "Computer Science", "Kuala Lumpur", "Java, Python, SQL", "Full-Time", 5000.00));
        //aList.add(new Applicant("A002", "Jane Smith", "Data Science", "Selangor", "Python, R, Machine Learning", "Full-Time", 6000.00));
        //aList.add(new Applicant("A003", "Alice Johnson", "Web Design", "Johor Bahru", "HTML, CSS, JavaScript", "Part-Time", 4000.00));
        //aList.add(new Applicant("A004", "Bob Lee", "Accounting", "Melaka", "Financial Reporting, Excel", "Full-Time", 4500.00));
        //aList.add(new Applicant("A005", "Charlie Brown", "Game Development", "Negeri Sembilan", "Unity, C#", "Full-Time", 6300.00));

        applicantDAO.saveToFile(aList);
    }

    public static void main(String[] args) {
        ApplicantInitializer ai = new ApplicantInitializer();
        ai.initializeApplicants();
        ListInterface<Applicant> loadedApplicants = ai.applicantDAO.retrieveFromFile();
        System.out.println("Applicants:");
        for (int i = 1; i <= loadedApplicants.getNumberOfEntries(); i++) {
            System.out.println(loadedApplicants.getEntry(i));
        }
    }
}