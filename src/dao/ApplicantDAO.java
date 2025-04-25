package dao;

import adt.LinkedList;
import adt.ListInterface;
import entities.Applicant;
import java.io.*;

public class ApplicantDAO {
    private static final String FILE_NAME = "applicants.dat";

    public void saveToFile(ListInterface<Applicant> applicants) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(applicants);
        } catch (IOException e) {
            System.err.println("Error saving applicants: " + e.getMessage());
        }
    }

    public ListInterface<Applicant> retrieveFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ListInterface<Applicant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error retrieving applicants: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}
