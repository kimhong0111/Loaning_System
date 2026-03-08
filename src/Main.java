package src;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static LoaningSystem system = new LoaningSystem("KH Bank", 0.05);

    public static void main(String[] args) {
       
        System.out.println("==========================================");
        System.out.println("       WELCOME TO KH BANK LOANING SYSTEM ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:  handleLogin();         break;
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
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
        System.out.println("  [1] Login");
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
        System.out.println("------------------------------------------");
        System.out.println("  [0] Exit");
        System.out.println("==========================================");
    }

    // ===== Handlers =====

    private static void handleLogin() {
        System.out.println("\n--- LOGIN ---");
        String name     = readString("Enter name: ");
        String password = readString("Enter password: ");
        system.staffLogin(name, password);
    }

    private static void handleLogout() {
        System.out.println("\n--- LOGOUT ---");
        system.staffLogout();
    }

    private static void handleCreateStaff() {
        System.out.println("\n--- CREATE STAFF ---");
        String name     = readString("Enter name: ");
        int    age      = readInt("Enter age: ");
        String password = readString("Enter password: ");
        double salary   = readDouble("Enter salary: ");
        System.out.println("Positions: Manager | LoanOfficer | CreditCommittee");
        String position = readString("Enter position: ");
        system.createStaff(name, age, password, salary, position);
    }

    private static void handleCreateApplicant() {
        System.out.println("\n--- CREATE APPLICANT ---");
        String name   = readString("Enter name: ");
        int    age    = readInt("Enter age: ");
        int    income = readInt("Enter income: ");
        System.out.println("Gender: Male | Female | Other");
        String gender = readString("Enter gender: ");
        system.createApplicant(name, age, income, gender);
    }

    private static void handleCreateContract() {
        System.out.println("\n--- CREATE CONTRACT ---");
        system.printApplicants();
        int    applicantId = readInt("Enter applicant ID: ");
        double amount      = readDouble("Enter loan amount: ");
        int    duration    = readInt("Enter duration (years): ");
        system.createContract(applicantId, amount, duration);
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

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}