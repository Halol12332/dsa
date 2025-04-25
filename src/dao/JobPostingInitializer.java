package dao;

import adt.*;
import entities.JobPostings;
/**
 *
 * @author Chak Jia Min
 */

public class JobPostingInitializer{
  private final JobPostingsDAO jobPostingsDAO;
  
  public JobPostingInitializer(){
      this.jobPostingsDAO = new JobPostingsDAO();   
  }


//  Method to return a collection of with hard-coded entity values
  public void initializeJobPosts() {
    ListInterface<JobPostings> jList = new LinkedList<>();
    jList.add(new JobPostings("J001", "Accountant",         "Perform account analysis and E-invoice",       "Kuala Lumpur",         "Acclime",              "Financial Reporting, Excel, Accounting Standards",              "Full-Time", 4500.00));
    jList.add(new JobPostings("J002", "Data Scientist",     "Data interpretation and visualization",        "Selangor",             "IBM",                  "Python, Machine Learning, SQL, Tableau",                            "Part-Time", 6500.00));
    jList.add(new JobPostings("J003", "Web Designer",       "Create custom E-commerce websites",            "Kuala Lumpur",         "WD",                   "HTML, CSS, Adobe XD, JavaScript",                                          "Full-Time", 4800.00));
    jList.add(new JobPostings("J004", "Software Engineer",  "Develop software applications",                "Johor Bahru",          "SE",                   "Java, Spring Boot, Git, Problem Solving",                                  "Full-Time", 5500.00));
    jList.add(new JobPostings("J005", "Product Designer",   "Designs new products based on requirements",   "Melaka",               "Expedio",              "Figma, UX Research, Prototyping",                                          "Part-Time", 4300.00));
    jList.add(new JobPostings("J006", "Library Assistant",  "Maintain library system and materials",        "Negeri Sembilan",      "UCCI University",      "Library Management System, Cataloging, Communication",  "Part-Time", 3600.00));
    jList.add(new JobPostings("J007", "Game Developer",     "Create pixel art games",                       "Selangor",             "Nexx Studio",          "Unity, C#, Pixel Art, Game Design",                                        "Full-Time", 6300.00));

    jobPostingsDAO.saveToFile(jList);
  }

  public static void main(String[] args) {
    // To illustrate how to use the initializeProducts() method
    JobPostingInitializer j = new JobPostingInitializer();
    j.initializeJobPosts();
    ListInterface<JobPostings> loadedJobs = j.jobPostingsDAO.retrieveFromFile();
    System.out.println(JobPostings.getTableHeader());
    for(int i = 1; i<=loadedJobs.getNumberOfEntries(); i++){
        System.out.println(loadedJobs.getEntry(i));
    }
    System.out.println(JobPostings.getTableFooter());
    
  }
  
  public JobPostingsDAO getJobPostingDao() {
        return jobPostingsDAO;
   }

}
