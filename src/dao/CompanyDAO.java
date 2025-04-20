package dao;

import adt.*;
import entities.Company;
import utility.MessageUI;

import java.io.*;

public class CompanyDAO {
    private static final String FILE_NAME = "companies.dat";
    private static final String REPORT_FILE_NAME = "companies_reports_";
    //private static final DateTimeFormatter REPORT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private ListInterface<Company> companyLists = new LinkedList<>();

    public ListInterface<Company> retrieveFromFile() {
        ListInterface<Company> companyList = new LinkedList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            companyList = (ListInterface<Company>) ois.readObject();
            MessageUI.displayValidRetrieveMessage();
        } catch (FileNotFoundException e) {
            MessageUI.displayFileNotFoundMessage();
        } catch (IOException | ClassNotFoundException e) {
            MessageUI.displayInvalidFileMessage();
        }
        return companyList;
    }

    public void saveToFile(ListInterface<Company> companyList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(companyList);
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