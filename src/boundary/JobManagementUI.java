/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entities.JobPostings;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Chak Jia Min
 * Use Linked List ADT
 * 
 */
public class JobManagementUI{

  Scanner scanner = new Scanner(System.in);

  public int getMenuChoice() {
    System.out.println("\nJob Posting");
    System.out.println("1. View ");
    System.out.println("2. Add");
    System.out.println("3. Remove");
    System.out.println("4. Update");
    System.out.println("5. Filter");
    System.out.println("6. Sort");
    System.out.println("7. Search keyword");
    System.out.println("8. Generate Report");
    System.out.println("0. Quit");
    System.out.print("Enter choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    System.out.println();
    return choice;
  }
  
  //Allows filtering jobs based on various criteria (e.g., location, company, job type).
  public int getFilterMenuChoice(){
      System.out.println("\nFilter Job Postings based on: ");
      System.out.println("1. Location");
      System.out.println("2. Company");
      System.out.println("3. Job Type");
      System.out.println("0. Back to Main Menu");
      System.out.print("Enter choice: ");
      int filterChoice = scanner.nextInt();
      scanner.nextLine();
      System.out.println();
      return filterChoice;
  }
  
  //Sorting menu for salary
  public int getSortMenuChoice(){
      System.out.println("\nSort Job Postings based on Salary of: ");
      System.out.println("1. Ascending (Low to High)");
      System.out.println("2. Descending (High to Low)");
      System.out.println("0. Back to Main Menu");
      System.out.print("Enter choice: ");
      int sortChoice = scanner.nextInt();
      scanner.nextLine();
      System.out.println();
      return sortChoice;
  }
  
  public int getReportMenuChoice(){
      System.out.println("\n--------------------Job Postings Report--------------------");
      System.out.println("1. View All Job Postings");
      System.out.println("2. By Location");
      System.out.println("3. By Company");
      System.out.println("4. By Job Type");
      System.out.println("0. Back to Main Menu");
      System.out.print("Enter choice: ");
      int reportChoice = scanner.nextInt();
      scanner.nextLine();
      System.out.println();
      return reportChoice;   
  }

  public void listAllJobPosts(String outputStr) {
    System.out.println(outputStr);
  }
  
  public void printJobPostingsDetails(JobPostings jp) {
    System.out.println("Job Postings Details");
    System.out.println("Job ID: "+ jp.getJobID());
    System.out.println("Job Title: "+ jp.getTitle());
    System.out.println("Description: "+ jp.getDesc());
    System.out.println("Location: "+ jp.getLocation()); 
    System.out.println("Company Name: "+ jp.getCompName());
    System.out.println("Required Skills: "+ jp.getRequiredSkills());
    System.out.println("Job Type: "+ jp.getJobType());
    System.out.println("Salary: "+ jp.getSalary());
  }
  
  public void displayReport(String report){
      System.out.println(report);
  }
  
  public void formatReportViewAllHeader(){
      System.out.println("\n================= ALL JOB POSTINGS REPORT =================");
  }
  
  public void formatReportTimeHeader(){
      System.out.println("Report generated on: " + 
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      System.out.println("==========================================================");
  }
  
  public String inputJobID(){
      System.out.println("Enter Job ID: ");
      String jobID = scanner.nextLine();
      return jobID;
  }
  
  public String inputJobTitle(){
      System.out.println("Enter Job Title: ");
      String title = scanner.nextLine();
      return title;
  }
  
  public String inputJobDescription(){
      System.out.println("Enter Job Description: ");
      String description = scanner.nextLine();
      return description;
  }
  
  public String inputJobLocation(){
      System.out.println("Enter Job Location: ");
      String location = scanner.nextLine();
      return location;
  }
  
  public String inputCompanyName(){
      System.out.println("Enter Company Name: ");
      String companyName = scanner.nextLine();
      return companyName;
  }
  
  public String inputRequiredSkills(){
      System.out.println("Enter Required Skills: ");
      String requiredSkills = scanner.nextLine();
      return requiredSkills;
  }
  
  public String inputJobType(){
      System.out.println("Enter Job Type(Full-time/Part-time): ");
      String jobType = scanner.nextLine();
      return jobType;
  }
  
  public double inputJobSalary(){
      System.out.println("Enter Job Salary: ");
      double salary = scanner.nextDouble();
      return salary;
  }
  
  public String inputSearchKeyword(){
      System.out.println("Enter Search Keyword: ");
      String keyword = scanner.nextLine();
      return keyword;
  }
  
  public void close(){
      scanner.close();
  }
  
  public JobPostings inputJobDetails() {
    String jobID = inputJobID();
    String title = inputJobTitle();
    String description = inputJobDescription();
    String location = inputJobLocation();
    String companyName = inputCompanyName();
    String requiredSkills = inputRequiredSkills();
    String jobType = inputJobType();
    double salary = inputJobSalary();
    System.out.println();
    return new JobPostings(jobID, title, description, location, companyName, requiredSkills, jobType, salary);
  }
}



