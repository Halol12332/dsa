package entities;

import java.io.Serializable;

/**
 *
 * @author Chak Jia Min
 */
public class JobPostings implements Serializable {
  private static final long serialVersionUID = 1L;
  private String jobID;
  private String title;
  private String description;
  private String location;
  private String companyName;
  private String requiredSkills;
  private String jobType;
  private double salary;

  public JobPostings() {
  }

  public JobPostings(String jobID, String title, String description, String location, String companyName, 
          String requiredSkills,String jobType, double salary) {
    this.jobID = jobID;
    this.title = title;
    this.description = description;
    this.location = location;
    this.companyName = companyName;
    this.requiredSkills = requiredSkills;
    this.jobType = jobType;
    this.salary = salary;
  }
  
  //Setter and Getter
  public String getJobID() {
    return jobID;
  }

  public void setJobID(String jobID) {
    this.jobID = jobID;
  }

  public String getTitle(){
      return title;
  }
  
  public void setTitle(String title){
      this.title = title;
  }
  
  public String getDesc(){
      return description;
  }
  
  public void setDesc(String description){
      this.description = description;
  }
  
  public String getLocation(){
      return location;
  } 
  
  public void setLocation(String location){
      this.location = location;
  }
  
  public String getCompName(){
      return companyName;
  }
  
  public void setCompName(String companyName){
      this.companyName = companyName;
  }
  
  public String getRequiredSkills(){
      return requiredSkills;
  }
  
  public void setRequiredSkills(String requiredSkills){
      this.requiredSkills = requiredSkills;
  }
  
  public String getJobType() {
    return jobType;
  }

  public void setJobType(String jobType) {
    this.jobType = jobType;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  private String truncate(String value, int maxLength) {
  if (value == null) return "";
  return value.length() <= maxLength ? value : value.substring(0, maxLength - 3) + "...";
}

  @Override
public String toString() {
  return String.format("| %-6s | %-18s | %-35s | %-15s | %-18s | %-28s | %-9s | %10.2f |", 
      truncate(jobID, 6),
      truncate(title, 18),
      truncate(description, 35),
      truncate(location, 15),
      truncate(companyName, 18),
      truncate(requiredSkills, 28),
      truncate(jobType, 9),
      salary
  );
}

  public static String getTableHeader(){
  String line = "+--------+--------------------+-------------------------------------+-----------------+--------------------+------------------------------+-----------+------------+";
  String header = String.format("| %-6s | %-18s | %-35s | %-15s | %-18s | %-28s | %-9s | %-10s |", 
      "Job ID", "Title", "Description", "Location", "Company Name", "Required Skills", "Job Type", "Salary");
  return line + "\n" + header + "\n" + line;
}


  
  public static String getTableFooter(){
      return "------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
  }
}


