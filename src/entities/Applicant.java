/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import adt.LinkedList;
/**
 * Represents an applicant in the Internship Application Program.
 */
public class Applicant {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String major;
    private LinkedList<String> skills;
    private String locationPreference;

    // Constructor
    public Applicant(String id, String name, String email, 
            String phoneNumber, String major, 
            LinkedList<String> skills, String locationPreference) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.skills = skills;
        this.locationPreference = locationPreference;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public LinkedList<String> getSkills() {
        return skills;
    }

    public void setSkills(LinkedList<String> skills) {
        this.skills = skills;
    }

    public String getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(String locationPreference) {
        this.locationPreference = locationPreference;
    }
    
    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", major='" + major + '\'' +
                ", skills=" + skills +
                ", locationPreference='" + locationPreference + '\'' +
                '}';
    }
}