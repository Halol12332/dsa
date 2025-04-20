/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Asus
 */
public class JobSeeker {
    String name;
    String email;
    String phone;
    String location;
    String desiredJob;
    String skills;

    public JobSeeker(String name, String email, String phone, String location, String desiredJob, String skills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.desiredJob = desiredJob;
        this.skills = skills;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesiredJob() {
        return desiredJob;
    }

    public void setDesiredJob(String desiredJob) {
        this.desiredJob = desiredJob;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return (name + " " + email + " " + phone + " " + location + " " + desiredJob + " " + skills); 
    }

   
}

