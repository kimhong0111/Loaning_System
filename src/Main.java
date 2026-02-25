package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoaningSystem ABA= new LoaningSystem("ABA", 0.18);
        int choice=-1;
        do{
            if(!ABA.isStaffLogin()){
                printMainMenu();
                System.out.println("Choose :");
                choice=sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Username: ");
                        String username=sc.nextLine();
                        
                        System.out.println("Password: ");
                        String password=sc.nextLine();
                        ABA.staffLogin(username, password);
                        break;
                
                    default:
                        break;
                }
            }
        }while(choice !=0);

        sc.close();

    }



     private static void printMainMenu() {
        System.out.println("\n=== MAIN MENU (Not Logged In) ===");
        System.out.println("1) Staff Login");
        System.out.println("2) View Menu Items");
        System.out.println("0) Exit");
    }

    private static void printStaffMenu(LoaningSystem ABA) {
        System.out.println("\n=== STAFF MENU (Logged In) ===");
        System.out.println("Logged in staff: " + ABA.getLoggedInStaff());
        System.out.println("1) Create Staff");
        System.out.println("2) Create Customer");
        System.out.println("3) Create Menu Item");
        System.out.println("4) Set Menu Item Availability");
        System.out.println("5) Create Order");
        System.out.println("6) List Customers");
        System.out.println("7) List Menu Items");
        System.out.println("8) List Orders");
        System.out.println("9) Logout");
        System.out.println("0) Exit");
    }
}