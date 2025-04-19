package adt;

import entities.JobPosting;
import entities.Applicant;

public class HashTable<T> {
    private static final int TABLE_SIZE = 100; // Initial size of the hash table
    private LinkedList<T>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    public void insert(T entry) {
        int index = hash(entry.toString());
        table[index].add(entry);
    }

    public T search(String key) {
        int index = hash(key);
        LinkedList<T> list = table[index];
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            T entry = list.getEntry(i);
            if (entry.toString().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    public void remove(String key) {
        int index = hash(key);
        LinkedList<T> list = table[index];
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            T entry = list.getEntry(i);
            if (entry.toString().equals(key)) {
                list.remove(i);
                return;
            }
        }
    }

    // Method to get all entries from the hash table
    public LinkedList<T> getAllEntries() {
        LinkedList<T> allEntries = new LinkedList<>();
        for (int i = 0; i < TABLE_SIZE; i++) {
            LinkedList<T> list = table[i];
            for (int j = 0; j < list.getNumberOfEntries(); j++) {
                allEntries.add(list.getEntry(j));
            }
        }
        return allEntries;
    }
}