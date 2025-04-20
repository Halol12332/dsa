/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package control;

import adt.*;
import boundary.JobManagementUI;
import dao.JobPostingsDAO;
import entities.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import utility.MessageUI;

/**
 *
 * @author Chak Jia Min
 * Interact with boundary
 */
public class JobManagementController implements Serializable {

  private ListInterface<JobPostings> jobLists = new LinkedList<>();
  private JobPostingsDAO jobPostsDAO = new JobPostingsDAO();
  private JobManagementUI jobPostsUI = new JobManagementUI();

  public JobManagementController() {
    jobLists = jobPostsDAO.retrieveFromFile();
  }
  
   public void runJobPostingsMaintenance() {
    int choice;
    do {
      choice = jobPostsUI.getMenuChoice();
      switch(choice) {
        case 0: //Exit the system
          exitSystem();
          break;
        case 1: //View the job postings
          jobPostsUI.listAllJobPosts(getAllJobPosts());
          break;
        case 2: //Add new job postings
          addNewJobPosts();
          break;
        case 3: //Remove certain job postings
          removeJobPosts();
          break;
        case 4: //Update job postings
          updateJobPosts();
          break;
        case 5: //Filter job postings by certain criteria
          runFilterMenu();
          break;
        case 6: //Sort job postings
          runSortSalaryMenu();
          break;
        case 7: // Search the job postings by keyword
          searchJobPostings();
          break;
        case 8: //Generate report 
          generateReports();
          break;
        default:
          MessageUI.displayInvalidChoiceMessage();
      } 
    } while (choice != 0);
  }
   
    public String getAllJobPosts() {
    System.out.println(JobPostings.getTableHeader());
    String outputStr = "";
    for (int i = 1; i <= jobLists.getNumberOfEntries(); i++) {
      outputStr += jobLists.getEntry(i) + "\n";
    }
    return outputStr;
  }
    
  public void displayJobPosts() {
    jobPostsUI.listAllJobPosts(getAllJobPosts());
  }

  //Add New Job Postings
  public void addNewJobPosts() {
    JobPostings jobPosts = jobPostsUI.inputJobDetails();
    if(jobLists.add(jobPosts)){
        jobPostsDAO.saveToFile(jobLists);
        MessageUI.displayValidAddMessage();
        displayJobPosts();
    }else{
        MessageUI.displayInvalidAddMessage();
    }
    
  }

  //Remove Job Postings 
  public void removeJobPosts(){
      String jobIDToRemove = jobPostsUI.inputJobID();
      boolean found = false;
      for(int i=1; i<=jobLists.getNumberOfEntries(); i++){
          JobPostings jobPosts = jobLists.getEntry(i);
          if(jobPosts.getJobID().equals(jobIDToRemove)){
              jobLists.remove(i);
              jobPostsDAO.saveToFile(jobLists);
              MessageUI.displayValidRemoveMessage();
              displayJobPosts();
              break;
          }
      }
      if(!found){
          MessageUI.displayInvalidJobIDMessage();
      }
  }
  
  //Update Job Postings
  public void updateJobPosts(){
      String jobID = jobPostsUI.inputJobID();
      boolean found = false;
      for(int i=1; i<=jobLists.getNumberOfEntries(); i++){
          JobPostings jobPosts = jobLists.getEntry(i);
          if (jobPosts.getJobID().equals(jobID)){
              System.out.println("Current Job Posting Details: ");
              jobPostsUI.listAllJobPosts(jobPosts.toString());
              
              System.out.println("\n Enter New Job Postings Details: \n");
              JobPostings updatedJob = jobPostsUI.inputJobDetails();
              
              jobLists.replace(i, updatedJob);
              jobPostsDAO.saveToFile(jobLists);
              
              MessageUI.displayValidUpdateMessage();
              displayJobPosts();
              found = true;
              
              break; 
          }   
      }
      
      if (!found){
          MessageUI.displayInvalidJobIDMessage();
      }
  }
  
  public void runFilterMenu(){
      int filterChoice;
      do{
          filterChoice = jobPostsUI.getFilterMenuChoice();
          switch(filterChoice){
              case 1:
                  filterByLocation();
                  break;
              case 2:
                  filterByCompanyName();
                  break;
              case 3:
                  filterByJobType();
                  break;
              case 0:
                  break;
              default:
                  MessageUI.displayInvalidChoiceMessage();         
          }
                  
      }while(filterChoice != 0);
  }
  
  public String formatJobReportbyCriteria(String filterType, String filterValue, ListInterface<JobPostings> jobList){
    StringBuilder sb = new StringBuilder();
    sb.append("\n================= JOB REPORT BY ").append(filterType.toUpperCase()).append(" =================\n");
    sb.append(filterType).append(" Filter: ").append(filterValue).append("\n");
    sb.append("Report generated on: ")
      .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
      .append("\n");
    sb.append(JobPostings.getTableHeader()).append("\n");
    for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
        sb.append(jobList.getEntry(i).toString()).append("\n");
    }
    sb.append(JobPostings.getTableFooter()).append("\n");
    sb.append("Total Results: ").append(jobList.getNumberOfEntries()).append("\n");

    return sb.toString();
}
  
   
  //Filter Job Posts based on Location
  public void filterByLocation(){
      String location = jobPostsUI.inputJobLocation();
      ListInterface<JobPostings> filteredList = jobLists.filter(jobPosts -> jobPosts.getLocation().equalsIgnoreCase(location));
      String report = formatJobReportbyCriteria("Location", location,filteredList);
      jobPostsUI.displayReport(report);
      
  }
  
  public void filterByCompanyName(){
      String compName = jobPostsUI.inputCompanyName();
      ListInterface<JobPostings> filteredList = jobLists.filter(jobPosts -> jobPosts.getCompName().equalsIgnoreCase(compName));
      String report = formatJobReportbyCriteria("Company Name", compName,filteredList);
      jobPostsUI.displayReport(report);
  }
  
  public void filterByJobType(){
      String jobType = jobPostsUI.inputJobType();
      ListInterface<JobPostings> filteredList = jobLists.filter(jobPosts -> jobPosts.getJobType().equalsIgnoreCase(jobType));
      String report = formatJobReportbyCriteria("Job Type", jobType,filteredList);
      jobPostsUI.displayReport(report);
  }
  
  public void runSortSalaryMenu(){
      int sortChoice;
      do{
          sortChoice = jobPostsUI.getSortMenuChoice();
          switch(sortChoice){
              case 1:
                  System.out.println(JobPostings.getTableHeader());
                  sortSalaryByAsc();
                  System.out.println(JobPostings.getTableFooter());
                  break;
              case 2:
                  System.out.println(JobPostings.getTableHeader());
                  sortSalaryByDesc();
                  System.out.println(JobPostings.getTableFooter());
                  break;
              case 0:
                  break;
              default:
                  MessageUI.displayInvalidChoiceMessage();
          }
      }while(sortChoice != 0);
  }
  
  //Sort Job Posts by Salary
  public void sortSalaryByAsc(){
      jobLists.sort(Comparator.comparingDouble(JobPostings::getSalary));
      jobPostsUI.listAllJobPosts(jobLists.toString());
  }
  
  public void sortSalaryByDesc(){
      jobLists.sort(Comparator.comparingDouble(JobPostings::getSalary).reversed());
      jobPostsUI.listAllJobPosts(jobLists.toString());
  }
  
  //Search keyword of Job Postings
  private void searchJobPostings(){
      String keyword = jobPostsUI.inputSearchKeyword().toLowerCase();
      ListInterface<JobPostings> searchResults = new LinkedList<>();
      
      for (int i=1; i<=jobLists.getNumberOfEntries(); i++){
          JobPostings jobPosts = jobLists.getEntry(i);
          if(jobPosts.getJobID().toLowerCase().contains(keyword) ||
             jobPosts.getTitle().toLowerCase().contains(keyword) ||
             jobPosts.getDesc().toLowerCase().contains(keyword) ||
             jobPosts.getLocation().toLowerCase().contains(keyword) ||
             jobPosts.getCompName().toLowerCase().contains(keyword)||
             jobPosts.getJobType().toLowerCase().contains(keyword)){
             searchResults.add(jobPosts);
          }          
      }
      
      if (searchResults.isEmpty()){
          MessageUI.displayInvalidSearchMessage();
      }else{
          jobPostsUI.listAllJobPosts(searchResults.toString());
      }
  }
  
  //Generate reports
  private void generateReports(){
      int reportChoice;
      do{
          reportChoice = jobPostsUI.getReportMenuChoice();
          switch(reportChoice){
              case 1:
                  jobPostsUI.formatReportViewAllHeader();
                  jobPostsUI.formatReportTimeHeader();
                  jobPostsUI.listAllJobPosts(getAllJobPosts());
                  break;
              case 2:
                  filterByLocation();
                  break;
              case 3:
                  filterByCompanyName();
                  break;
              case 4:
                  filterByJobType();
                  break;
              case 0:
                  break;
              default:
                  MessageUI.displayInvalidChoiceMessage();         
          }
                  
      }while(reportChoice != 0);
   
  }

  private void exitSystem(){
      jobPostsDAO.saveToFile(jobLists);
      MessageUI.displayExitMessage();
      jobPostsUI.close();
  }
  
  public static void main(String[] args) {
    JobManagementController jobPostingsMaintenance = new JobManagementController();
    jobPostingsMaintenance.runJobPostingsMaintenance();
  }
}

