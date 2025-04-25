/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import adt.*;
import java.io.Serializable;
/**
 * Represents an applicant in the Internship Application Program.
 */
public class Applicant implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;
    private String major;
    private ListInterface<String> skills;
    private ListInterface<String> locationPreference;
    private String desiredJob;

    // Constructor

    public Applicant(String name, String email, String phone, String major,
                 ListInterface<String> skills, ListInterface<String> preferredLocations, String jobType) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phone; // ✅ fix
        this.major = major;
        this.skills = skills;
        this.locationPreference = preferredLocations; // ✅ fix
        this.desiredJob = jobType; // ✅ fix
    }

   

    // Getters and Setters
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

    public ListInterface<String> getSkills() {
        return skills;
    }

    public void setSkills(LinkedList<String> skills) {
        this.skills = skills;
    }

    public ListInterface<String> getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(LinkedList<String> locationPreference) {
        this.locationPreference = locationPreference;
    }

    public String getDesiredJob() {
        return desiredJob;
    }

    public void setDesiredJob(String desiredJob) {
        this.desiredJob = desiredJob;
    }
    
    public String getMajorAsString() {
        return major;
    }

    public String getLocationPreferenceAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= locationPreference.getNumberOfEntries(); i++) {
            sb.append(locationPreference.getEntry(i));
            if (i < locationPreference.getNumberOfEntries()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    
    @Override
    public String toString() {
        return "Applicant{" +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", major = '" + major + '\'' +
                ", skills = " + skills +
                ", locationPreference = '" + locationPreference + '\'' +
                ", desiredJob = '" + desiredJob + '\'' +
                '}';
    }
}