package dao;

/**
 * Author : Jaya Hakim Prajna
 */

import adt.*;
import entities.Match;

import java.io.*;

public class MatchDAO {
    private static final String FILE_NAME = "matches.dat";

    // Method to save matches to the file by rewriting it
    public void saveToFile(ListInterface<Match> matches) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(matches);
            System.out.println("Matches saved to file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving matches: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ListInterface<Match> retrieveFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ListInterface<Match> matches = (ListInterface<Match>) ois.readObject();
            System.out.println("Matches retrieved from file: " + FILE_NAME);
            return matches;
        } catch (IOException e) {
            System.err.println("Error retrieving matches: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}
