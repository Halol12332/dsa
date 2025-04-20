package dao;

import adt.*;
import entities.Applicant;
import utility.MessageUI;

import java.io.*;

public class ApplicantDAO {
    private static final String FILE_NAME = "applicants.dat";
    private static final String REPORT_FILE_NAME = "applicants_reports_";
    //private static final DateTimeFormatter REPORT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private ListInterface<Applicant> applicantLists = new LinkedList<>();

    public ListInterface<Applicant> retrieveFromFile() {
        ListInterface<Applicant> applicantList = new LinkedList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            applicantList = (ListInterface<Applicant>) ois.readObject();
            MessageUI.displayValidRetrieveMessage();
        } catch (FileNotFoundException e) {
            MessageUI.displayFileNotFoundMessage();
        } catch (IOException | ClassNotFoundException e) {
            MessageUI.displayInvalidFileMessage();
        }
        return applicantList;
    }

    public void saveToFile(ListInterface<Applicant> applicantList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(applicantList);
            MessageUI.displayValidSaveMessage();
        } catch (IOException e) {
            MessageUI.displayInvalidSaveMessage();
        }
    }

    public void exportReportToTextFile(String fileName, String reportContent) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(reportContent);
            System.out.println("Report successfully exported to: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to export report: " + e.getMessage());
        }
    }
}