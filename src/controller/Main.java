package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.controller.LoaningSystem;
import src.interfaces.ILoginable;
import src.model.Applicant;
import src.model.Staff;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static    LoaningSystem system = new LoaningSystem("KH Bank", 0.05);
    static boolean loggin;
    static int isUser=-1;


    public static void main(String[] args) {

         

        System.out.println("==========================================");
        System.out.println("       WELCOME TO KH BANK LOANING SYSTEM ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
    
             try {
                 validateChoice(choice,loggin);

                switch (choice) {
            case 0:  System.out.println("Exiting. Goodbye!"); running = false; break;
            case 1:  handleLogin();           break;
            case 2:  handleLogout();          break;
            case 3:  handleCreateStaff();     break;
            case 4:  handleCreateApplicant(); break;
            case 5:
                    handleCreateContract();   
             break; 
            case 6:  handleApproveContract();  break;
            case 7:  handleRejectContract();   break;
            case 8:  handleAddCoSigner();      break;
            case 9:  handleDeactivateStaff();  break;
            case 10: system.printStaffs();     break;
            case 11: system.printApplicants(); break;
            case 12:
           system.printContracts();    
            break;
            case 13: handleSetNewName();       break;
            case 14: handleSetNewPassword();   break;
            case 15:system.viewMyProfile();
}
            }
            
             
        catch(InputMismatchException e){
                 System.out.println(e.getMessage());
              }
            }

            scanner.close();
        
    
    }





    private  static void validateChoice(int choice , boolean loggin){
         if(!loggin){
           if(choice != 0 && choice !=1){
             throw new InputMismatchException("Error : Invalid Choice");
           }
        } else {
            if (choice == 1) {
                            throw new InputMismatchException("Error : Invalid Choice"); 
            }
        } 
            
        }
        
        
            
        
    

  

    // ===== Main Menu =====
  private static void printMainMenu() {
    System.out.println("\n==========================================");
    System.out.println("                 MAIN MENU               ");
    System.out.println("==========================================");

    if (!loggin) {
        // ===== NOT LOGGED IN =====
        System.out.println("  [1] Login");

    } else if (isUser == 1) {
        // ===== APPLICANT MENU =====
        System.out.println("  APPLICATIONS");
        System.out.println("  [5]  Submit Loan Application");
        System.out.println("  [12] View My Applications");
        System.out.println("------------------------------------------");
        System.out.println("  PROFILE");
        System.out.println("  [13] Change Name");
        System.out.println("  [14] Change Password");
        System.out.println("  [15] View My Profile");
        System.out.println("------------------------------------------");
        System.out.println("  [2] Logout");

    } else {
        // ===== STAFF MENU =====
        System.out.println("  MANAGER ACTIONS");
        System.out.println("  [3] Create Staff");
        System.out.println("  [4] Create Applicant");
        System.out.println("------------------------------------------");
        System.out.println("  LOAN OFFICER ACTIONS");
        System.out.println("  [5] Create Contract");
        System.out.println("  [6] Approve Contract");
        System.out.println("  [7] Reject Contract");
        System.out.println("  [8] Add Co-Signer");
        System.out.println("------------------------------------------");
        System.out.println("  ADMIN ACTIONS");
        System.out.println("  [9] Deactivate Staff");
        System.out.println("------------------------------------------");
        System.out.println("  VIEW");
        System.out.println("  [10] Print All Staffs");
        System.out.println("  [11] Print All Applicants");
        System.out.println("  [12] Print All Contracts");
        System.out.println("------------------------------------------");
        System.out.println("  PROFILE");
        System.out.println("  [13] Change Name");
        System.out.println("  [14] Change Password");
        System.out.println("  [15] View My Profile");
        System.out.println("------------------------------------------");
        System.out.println("  [2] Logout");
    }

    System.out.println("------------------------------------------");
    System.out.println("  [0] Exit");
    System.out.println("==========================================");
}

   
    // ===== Handlers =====
    private static void handleLogin() {
        while(!loggin){

        try {
        System.out.println("\n--- LOGIN ---");
        String username     = readUsername("Enter username: ");
        String password = readPassword("Enter password: ");
        system.login(username, password);
        loggin=true;
        validateUser();
        } catch (LogginException e) {
            System.out.println(e.getMessage());
            
        }
        
        }
    }

    private static void validateUser(){
      ILoginable user =  system.getLoggedInUser();
      if(user instanceof Applicant){
          isUser=1;
      }else if(user instanceof Staff){
         isUser=0;
      }

    }

    private static void handleSetNewName(){
        boolean validInput =false;
        while(!validInput){
            try {
        System.out.println("\n--- SET NEW NAME ---");
        String name = readName("Enter your name: ");
        String password = readPassword("Enter your password: ");
        String newName = readUsername("Enter your new name: ");
        system.setNewUserName(name, newName, password);
        validInput=true;
 
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void handleSetNewPassword(){
        boolean validInput =false;
        while(!validInput){
            try {
        System.out.println("\n--- SET NEW PASSWORD ---");
        String name = readName("Enter your name: ");
        String password = readPassword("Enter your password: ");
        String newPassword = readPassword("Enter your new password: ");
        String confirmNewPassword = readPassword("Enter your new password again: ");
        if(!newPassword.equals(confirmNewPassword)){
           throw new InputMismatchException("Error : mismatch confirmation password");
        }
    
        system.setNewPassword(name, password, newPassword);
        validInput=true;
 
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void handleLogout() {
        System.out.println("\n--- LOGOUT ---");
        system.logout();
        loggin=false;
    }

    private static void handleCreateStaff() {
        boolean validInput = false;
        while(!validInput) {
            try {
        System.out.println("\n--- CREATE STAFF ---");
        String name     = readName("Enter name: ");
        String userName     = readNewUsername("Enter username: ");
        String phoneNumber= readPhoneNumber("Enter your phone number: ");
        int    age      = readAge("Enter age: ");
        String password = readPassword("Enter password: ");
        double salary   = readDouble("Enter salary: ");
        String position = readPosition("Select Position");
        system.createStaff(name,userName,phoneNumber,age, password, salary, position);
        validInput=true;
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
    }
}

    private static void handleCreateApplicant() {
        System.out.println("\n--- CREATE APPLICANT ---");
        boolean validInput = false;
    while(!validInput) {
        try {
         String name   = readName("Enter name: ");
         String userName     = readNewUsername("Enter username: ");
         String phoneNumber= readPhoneNumber("Enter your phone number: ");
         String password = readPassword("Enter password: ");
        int    age    = readAge("Enter age: ");
        int   income = readInt("Enter income: ");
        String gender = readGender("Select Gender");
        system.createApplicant(name,userName,phoneNumber,password, age, income, gender);
        validInput=true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    }

    private static void handleCreateContract() {
        boolean validInput = false;
        while(!validInput){
            try {
         System.out.println("\n--- CREATE CONTRACT ---");
        system.printApplicants();
        int    applicantId = readInt("Enter applicant ID: ");
        double amount      = readDouble("Enter loan amount: ");
        int    duration    = readInt("Enter duration (years): ");
        system.createContract(applicantId, amount, duration);
        validInput=true;
            
    } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
            }
        }
        
    }

    private static void handleApproveContract() {
        System.out.println("\n--- APPROVE CONTRACT ---");
        system.printContracts();
        int contractId = readInt("Enter contract ID to approve: ");
        system.approveContract(contractId);
    }

    private static void handleRejectContract() {
        System.out.println("\n--- REJECT CONTRACT ---");
        system.printContracts();
        int contractId = readInt("Enter contract ID to reject: ");
        system.rejectContract(contractId);
    }

    private static void handleAddCoSigner() {
        System.out.println("\n--- ADD CO-SIGNER ---");
        system.printContracts();
        int contractId = readInt("Enter contract ID: ");
        system.printStaffs();
        int staffId = readInt("Enter staff ID to add as co-signer: ");
        system.addCoSigner(contractId, staffId);
    }

    private static void handleDeactivateStaff() {
        System.out.println("\n--- DEACTIVATE STAFF ---");
        system.printStaffs();
        int staffId = readInt("Enter staff ID to deactivate: ");
        system.deactivateStaff(staffId);
    }

    // ===== Input Helpers =====
private static String readUsername(String prompt) {
    String input;
    while (true) {
        System.out.print(prompt);
        try {
              input=scanner.nextLine();
             if (input.isEmpty()) {
              throw new InputMismatchException("Error :Input cannot be empty. Please try again.");
             }
                return input;
            
            }
            catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}

private static String readNewUsername(String prompt) {
    String input;
    while (true) {
        System.out.print(prompt);
        try {
              input=scanner.nextLine();
             if (input.isEmpty()) {
              throw new InputMismatchException("Error :Input cannot be empty. Please try again.");
                }
            if(!(system.checkIfUsernameAvailable(input))){
                throw new InputMismatchException("Error : Username already exist");
            }
                return input;
            
            }
            catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}

private static String readPhoneNumber(String prompt) {
    String input;
    while (true) {
        System.out.print(prompt);
        try {
              input=scanner.nextLine();
             if (input.isEmpty()) {
              throw new InputMismatchException("Error :Input cannot be empty. Please try again.");
                }
            if(!(system.checkIfPhoneNumerAvailable(input))){
                throw new InputMismatchException("Error : Phone number already exist");
            }
                return input;
            
            }
            catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
   private static String readName(String prompt) {
    String input;
    while (true) {
        System.out.print(prompt);
        //check if the input is empty + only contains letters
        try {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new InputMismatchException("Input cannot be empty.");
            }
            if (!input.matches("^[a-zA-Z]+$")) {
                throw new InputMismatchException("Input must contain only letters.");
            }
            return input;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if(value <0 || value > 10000){
                    throw new NumberFormatException("Range number 0 - 10000 ");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
    private static int readAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 18 || value > 65) {
                    throw new InputMismatchException("You must be at least 18 and at most 65 years old.");
                } 
                return value;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readPassword(String prompt){
         String password;
         while(true){
            System.out.println(prompt);
            try{
             password=scanner.nextLine().trim();
             if(password.length() < 4){
                throw new InputMismatchException("Password cannot be under 4");
             }
             return password;
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
         }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if(value < 0 || value > 10000){
                    throw new NumberFormatException("Range number from 0 - 10000");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readGender(String prompt) {
    System.out.println(prompt);
    System.out.println("  [1] Male");
    System.out.println("  [2] Female");
    while (true) {
        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1: return "M";
            case 2: return "F";
            default: System.out.println("Invalid choice. Please select 1-2.");
        }
    }
}

private static String readPosition(String prompt) {
    System.out.println(prompt);
    System.out.println("  [1] Manager");
    System.out.println("  [2] LoanOfficer");
    System.out.println("  [3] CreditCommittee");
    while (true) {
        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1: return LoaningSystem.MANAGER;
            case 2: return LoaningSystem.LOAN_OFFICER;
            case 3: return LoaningSystem.CREDIT_COMMITTEE;
            default: System.out.println("Invalid choice. Please select 1-3.");
        }
    }
}
    
}