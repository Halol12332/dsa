/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import adt.*;
import entities.JobPostings;
import java.io.*;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;

/**
 *
 * @author Chak Jia Min
 * Data Access Object (DAO) - abstract and encapsulate all access to a data source
 */
public class JobPostingsDAO{
    private static final String FILE_NAME = "job_postings.dat";
    private static final String REPORT_FILE_NAME = "jobs_reports_";
    private static final DateTimeFormatter REPORT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private ListInterface<JobPostings> jobLists = new LinkedList<>();
    /*
    * Retrieves all job postings from file
     */
    public ListInterface<JobPostings> retrieveFromFile() {
        ListInterface<JobPostings> jobList = new LinkedList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            jobList = (ListInterface<JobPostings>) ois.readObject();
            MessageUI.displayValidRetrieveMessage();
        }catch (FileNotFoundException e) {
            MessageUI.displayFileNotFoundMessage();
        } catch (IOException | ClassNotFoundException e) {
            MessageUI.displayInvalidFileMessage();
        } 
        return jobList;
    }

    public void saveToFile(ListInterface<JobPostings> jobList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(jobList);
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
