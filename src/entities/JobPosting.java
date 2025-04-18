package entities;

/**
 * Represents a job posting in the Internship Application Program.
 */
public class JobPosting {
    private String id;
    private Company company;
    private String title;
    private String description;
    private String jobType; // Added jobType field
    private String requiredSkills;
    private String location;
    private double salary;

    // Updated Constructor to include jobType
    public JobPosting(String id, Company company, String title, String description, String jobType, String requiredSkills, String location, double salary) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.description = description;
        this.jobType = jobType; // Initialize jobType
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.salary = salary;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Added getter and setter for jobType
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                "id='" + id + '\'' +
                ", company=" + company +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", jobType='" + jobType + '\'' + // Included jobType in toString
                ", requiredSkills='" + requiredSkills + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }
}