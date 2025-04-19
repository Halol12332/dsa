/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
/**
 * Represents a company in the Internship Application Program.
 */
public class Company {
    private String id;
    private String name;
    private String industry;
    private String location;

    // Constructor
    public Company(String id, String name, String industry, String location) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.location = location;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}