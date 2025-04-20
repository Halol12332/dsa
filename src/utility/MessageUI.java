package utility;

/**
 *
 * @author Chak Jia Min
 */
public class MessageUI {
  
  
  public static void displayInvalidChoiceMessage() {
    System.out.println("\nInvalid choice!");
  }

  public static void displayExitMessage() {
    System.out.println("\nExiting system");
  }
  
  public static void displayValidAddMessage(){
      System.out.println("Job posting added successfully");
  }
  
  public static void displayInvalidAddMessage(){
      System.out.println("Failed to add job posting");
  }
  
  public static void displayValidRemoveMessage(){
      System.out.println("Job posting removed successfully");
  }
  
  public static void displayInvalidJobIDMessage(){
      System.out.println("Job ID not found!");
  }
  
  public static void displayValidUpdateMessage(){
      System.out.println("Job posting updated successfully");
  }
  
  public static void displayInvalidSearchMessage(){
      System.out.println("No matching job postings found");
  }
  
  public static void displayValidRetrieveMessage() {
      System.out.println("Valid retrieval of Job Postings");
  }
  
  public static void displayFileNotFoundMessage() {
      System.out.println("File not found!");
  }
  
  public static void displayInvalidFileMessage() {
      System.out.println("Invalid retrieval of Job Postings");
  }
  
  public static void displayValidSaveMessage(){
      System.out.println("File successfully saved!");
  }
  
  public static void displayInvalidSaveMessage(){
      System.out.println("Invalid file savings");
  }
       
  
  
}
