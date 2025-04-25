package dao;

import adt.*;
import entities.Interview;

import java.io.*;

/**
 * Data Access Object for Interview entities
 * @author Your Name
 */
public class InterviewDAO {
    private static final String FILE_NAME = "interviews.dat";
    
    public void saveToFile(ListInterface<Interview> interviews) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(interviews);
            System.out.println("Interviews saved to file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving interviews: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public ListInterface<Interview> retrieveFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ListInterface<Interview> interviews = (ListInterface<Interview>) ois.readObject();
            System.out.println("Interviews retrieved from file: " + FILE_NAME);
            return interviews;
        } catch (FileNotFoundException e) {
            System.err.println("Interview file not found, creating new list");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error retrieving interviews: " + e.getMessage());
        }
        return new LinkedList<>();
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