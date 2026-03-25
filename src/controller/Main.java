package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.controller.LoaningSystem;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static    LoaningSystem system = new LoaningSystem("KH Bank", 0.05);
    static boolean loggin = false;


    public static void main(String[] args) {

         system.staffLogin("admin","1234");
         system.createStaff("Alice", 30, "pass1", 50000, LoaningSystem.MANAGER);
         system.createStaff("Bob", 28, "pass2", 40000, LoaningSystem.LOAN_OFFICER);
         system.createStaff("Charlie", 35, "pass3", 60000, LoaningSystem.CREDIT_COMMITTEE);
         
         system.createApplicant("David", 25, 2000, "M");
         system.staffLogin("bob","pass2");
        


        System.out.println("==========================================");
        System.out.println("       WELCOME TO KH BANK LOANING SYSTEM ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
             try {
            validateChoice(choice, loggin);
            switch (choice) {
                case 1:  handleLogin();         break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                case 2:  handleLogout();        break;
                case 3:  handleCreateStaff();   break;
                case 4:  handleCreateApplicant();break;
                case 5:  handleCreateContract();break;
                case 6:  handleApproveContract();break;
                case 7:  handleRejectContract(); break;
                case 8:  handleAddCoSigner();   break;
                case 9:  handleDeactivateStaff();break;
                case 10: system.printStaffs();  break;
                case 11: system.printApplicants();break;
                case 12: system.printContracts();break;
                   default :
                     throw new InputMismatchException("Error : Invaild Choice");
                            }
                } catch(InputMismatchException e){
                 System.out.println(e.getMessage());
              }
            }
            scanner.close();
    }

    // ===== Main Menu =====
   private static void printMainMenu() {
    System.out.println("\n==========================================");
    System.out.println("                 MAIN MENU               ");
    System.out.println("==========================================");
    System.out.println("  AUTH");

    if (!loggin) {
        System.out.println("  [1] Login");
    } else {
        System.out.println("  [2] Logout");
        System.out.println("------------------------------------------");
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
        System.out.println("  [9]  Deactivate Staff");
        System.out.println("------------------------------------------");
        System.out.println("  VIEW");
        System.out.println("  [10] Print All Staffs");
        System.out.println("  [11] Print All Applicants");
        System.out.println("  [12] Print All Contracts");
    }

    System.out.println("------------------------------------------");
    System.out.println("  [0] Exit");
    System.out.println("==========================================");
}
    
    private static void validateChoice(int choice, boolean loggin) {
        if(!loggin){
            if(choice != 1 && choice != 0){
                throw new InputMismatchException("Error : Invaild choice");
            }
        } else {
            if (choice == 1){
                throw new InputMismatchException("Error : You are already logged in");
            }
        }
    }
    // ===== Handlers =====
    private static void handleLogin() {
        while(!loggin){

        try {
        System.out.println("\n--- LOGIN ---");
        String name     = readUserName("Enter name: ");
        String password = readPassword("Enter password: ");
        system.staffLogin(name, password);
        loggin=true;
        } catch (LogginException e) {
            System.out.println(e.getMessage());
            
        }
        
        }
    }

    private static void handleLogout() {
        System.out.println("\n--- LOGOUT ---");
        system.staffLogout();
        loggin=false;
    }

    private static void handleCreateStaff() {
        boolean validInput = false;
        while(!validInput) {
            try {
        System.out.println("\n--- CREATE STAFF ---");
        String name     = readUserName("Enter name: ");
        int    age      = readAge("Enter age: ");
        String password = readNewPassword("Enter password: ");
        double salary   = readDouble("Enter salary: ");
        String position = readPosition("Select Position");
        system.createStaff(name, age, password, salary, position);
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
         String name   = readUserName("Enter name: ");
        int    age    = readAge("Enter age: ");
        int    income = readInt("Enter income: ");
        String gender = readGender("Select Gender");
        system.createApplicant(name, age, income, gender);
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

   private static String readUserName(String prompt) {
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
    private static String readPassword(String prompt) {
        String input;
        while(true) {
            System.out.print(prompt);
            try {
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new InputMismatchException("Input cannot be empty.");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readNewPassword(String prompt) {
        String input;
        while(true) {
            System.out.print(prompt);
            try {
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new InputMismatchException("Input cannot be empty.");
                }
                if (input.length() < 4){
                    throw new InputMismatchException("Password must be at least 4 characters long.");
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static int readAge(String prompt){
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()){
                    throw new InputMismatchException("Input cannot be empty.");
                }
                int value = Integer.parseInt(input);
                if (value < 18 || value > 65) {
                    throw new InputMismatchException("Age must be between 18 and 65.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()){
                    throw new InputMismatchException("Input cannot be empty.");
                }
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()){
                    throw new InputMismatchException("Input cannot be empty.");
                }
                double value = Double.parseDouble(input);
                if (value < 0){
                    throw new InputMismatchException("Input cannot be negative.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
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