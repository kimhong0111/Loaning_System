package src.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;

import src.interfaces.ILoginable;
import src.interfaces.IStaff;
import src.model.Applicant;
import src.model.Contract;
import src.model.CreditCommittee;
import src.model.LoanOfficer;
import src.model.Manager;
import src.model.Staff;

public class LoaningSystem {

    public static final String CREATE_STAFF     = "CREATE_STAFF";
    public static final String CREATE_APPLICANT = "CREATE_APPLICANT";
    public static final String CREATE_CONTRACT  = "CREATE_CONTRACT";
    public static final String APPROVE_LOAN     = "APPROVE_LOAN";
    public static final String REJECT_LOAN      = "REJECT_LOAN";
    public static final String ADD_COSIGNER     = "ADD_COSIGNER";
    public static final String SET_NEW_APVL     = "SET_NEW_APVL";    
    public static final String SET_NEW_REQV     = "SET_NEW_REQV";

    public static final String MANAGER          = "MANAGER";
    public static final String LOAN_OFFICER     = "LOANOFFICER";
    public static final String CREDIT_COMMITTEE  = "CREDITCOMMITTEE";

    public static final String SET_NEW_NAME = "SET_NEW_NAME";
    public static final String SET_NEW_PASSWORD = "SET_NEW_PASSWORD";


    private String bankName;
    private static int indexBankId = 1;
    private int bankId;
    private static double currentInterestRate = 0.05; 

    private ArrayList<Staff> staffs;
    private ArrayList<Applicant> applicants;
    private ArrayList<Contract> contracts;

    private ILoginable loggedInUser;
    private  static String lastMessage;

    public LoaningSystem(String bankName, double currentInterestRate) {
        this.bankId = indexBankId++;
        setBankName(bankName);
        setCurrentInterestRate(currentInterestRate);

        this.staffs     = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.contracts  = new ArrayList<>();

        this.loggedInUser = null;
        LoaningSystem.lastMessage   = "";

        seedDefaultAdmin();
    }

    public String getBankName()                  { return bankName; }
    public int getBankId()                       { return bankId; }
    public static double getCurrentInterestRate(){ return currentInterestRate; }
    public String getLastMessage()               { return lastMessage; }
    public boolean isLoggedIn()             { return loggedInUser != null; }
    public ILoginable getLoggedInUser()             { return loggedInUser; }

    public void viewMyProfile(){
        System.out.println(loggedInUser.toString());
    }
   
    public boolean checkTypeArrayList(){ 
      for( Staff s : staffs){
        if(s instanceof Manager){
            return true;
        }
      }
        return false;

    }


    public void setBankName(String bankName) {
        if (isBlank(bankName)) {
            this.bankName = "Default Bank";
            return;
        }
        this.bankName = bankName.trim();
    }

    public void setCurrentInterestRate(double rate) {
        if (rate <= 0 || rate >= 1) {
            System.out.println("Error: Interest rate must be between 0 and 1 (e.g. 0.05 for 5%).");
            return;
        }
        LoaningSystem.currentInterestRate = rate;
    }

    public  static void setLastMessage(String msg) {
        LoaningSystem.lastMessage = msg;
        System.out.println(msg);
    }

    // ===== Seed Default Admin =====
    private void seedDefaultAdmin() {
      Staff admin = new Manager("Admin",18,"1234", 5000,2);
        staffs.add(admin);
        setLastMessage("System ready. Default admin seeded: Admin / 1234");
    }

    public void login(String name, String password) {
        if (isBlank(name) || isBlank(password)) {
            setLastMessage("Error: Name or password cannot be empty.");
            return;
        }

        for (int i = 0; i < staffs.size(); i++) {
            Staff s = staffs.get(i);
            if (s.getName().equalsIgnoreCase(name.trim())) {
                if (!s.isActive()) {
                    throw new LogginException("Error : User account is inactive");
                }
                if (!s.checkPassword(password)) {
                    throw new LogginException("Error : Wrong Password");
                }
                loggedInUser = s;
                setLastMessage("Login success. Welcome " + s.getName() + "!");
                return;
            }
        }

        for (int i = 0; i < applicants.size(); i++) {
            Applicant a = applicants.get(i);
            if (a.getName().equalsIgnoreCase(name.trim())) {
                if (!a.isActive()) {
                    throw new LogginException("Error : User account is inactive");
                }
                if (!a.checkPassword(password)) {
                    throw new LogginException("Error : Wrong Password");
                }
                loggedInUser = a;
                setLastMessage("Login success. Welcome " + a.getName() + "!");
                return;
            }
        }
        throw new LogginException("Error : User not found");
    }

    public void logout() {
        if (loggedInUser== null) {
            setLastMessage("No staff is currently logged in.");
            return;
        }
        setLastMessage("Goodbye " + loggedInUser.getName() + ". Logged out successfully.");
        loggedInUser = null;
    }

    public void createStaff(String name, int age, String password, double salary, String position) {
        if (!requireLogin()) return;
        if (!requirePermission(CREATE_STAFF)) return;

        if (isBlank(name) || isBlank(password)) {
            setLastMessage("Error: Name or password cannot be empty.");
            return;
        }

        Staff newStaff;
        if (position.equals(LoaningSystem.MANAGER)) {
            newStaff = new Manager(name ,age,password, salary,1);
        } else if (position.equals(LoaningSystem.LOAN_OFFICER)) {
            newStaff = new LoanOfficer(name ,age ,password, salary, 50000); 
        } else if (position.equals(LoaningSystem.CREDIT_COMMITTEE)) {
            newStaff = new CreditCommittee(name ,age,password, salary, 3);
        } else {

         throw new IllegalArgumentException("Error: Unknown position '" + position + "'. Use Manager, LoanOfficer, or CreditCommittee.");
        }

        staffs.add(newStaff);
        setLastMessage("Staff created successfully: " + newStaff.getName() + " | Role: " + position);

            }

    public void createApplicant(String name,String password, int age, int income, String gender) {
        if (!requireLogin()) return;

        if (isBlank(name)) {
            setLastMessage("Error: Applicant name cannot be empty.");
            return;
        }

        applicants.add(new Applicant(name,password, gender, income, age));
        setLastMessage("Applicant created successfully: " + name);
    }

    public void createContract(int applicantId, double amount, int duration) {
        if (!requireLogin()) return;
        if (!requirePermission(CREATE_CONTRACT)) return;

        Applicant applicant = findApplicantById(applicantId);
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant ID "+ applicantId + " doesn't exist");
        }
    
        double borrowedAmount=0;

        for (int i = 0; i < contracts.size(); i++) {

            Contract c = contracts.get(i);
            if(c.getApplicant().getApplicantId() == applicantId){
                borrowedAmount+=c.getPrincipalAmount();
                if(borrowedAmount + amount >= applicant.getSalary() / 2){
                    throw new IllegalArgumentException("Applicant cannot take more loan. Total would exceed 1/2 of salary." +
            "\n  Already borrowed: " + borrowedAmount +
            "\n  Requested: " + amount +
            "\n  Max allowed: " + (applicant.getSalary() / 2));
                }
            }


           /*  if (c.getApplicant().getApplicantId() == applicantId
            && c.getPrincipalAmount() == amount
                    && c.getDuration() == duration) {
                throw new IllegalArgumentException("Error: Identical contract already exists for this applicant.");
            }
            */
        }



        Contract contract = new Contract(applicant, amount, duration);
        Staff officer = (LoanOfficer) loggedInUser;
        contract.setDraftingOfficer(officer);
        contracts.add(contract);
        setLastMessage("Contract created successfully. ID: " + contract.getContractId());
    }

    public void approveContract(int contractId) {
        if (!requireLogin()) return;
        if (!requirePermission(APPROVE_LOAN)) return;

        Contract contract = findContractById(contractId);
        if (contract == null) {
            setLastMessage("Error: Contract not found.");
            return;
        }
        if (contract.isApproved()) {
            setLastMessage("Error: Contract is already approved.");
            return;
        }
        if (!contract.getStatus().equals("PENDING") && !contract.getStatus().equals("FORWARDED")) {
            setLastMessage("Error: Contract cannot be approved at status: " + contract.getStatus());
            return;
        }
        // probably have bug here will check later
        Staff officer = (Staff) loggedInUser;
        officer.canContractApprove(officer, contract);
            
        


    }

    public void rejectContract(int contractId) {
        if (!requireLogin()) return;
        if (!requirePermission(REJECT_LOAN)) return;

        Contract contract = findContractById(contractId);
        if (contract == null) {
            setLastMessage("Error: Contract not found.");
            return;
        }
        if (contract.isApproved()) {
            setLastMessage("Error: Cannot reject an already approved contract.");
            return;
        }

        contract.setStatus("REJECTED");
        setLastMessage("Contract #" + contractId + " rejected by: " + loggedInUser.getName());
    }

    // ===== Add CoSigner =====
    public void addCoSigner(int contractId, int staffId) {
        if (!requireLogin()) return;
        if (!requirePermission(ADD_COSIGNER)) return;

        Contract contract = findContractById(contractId);
        if (contract == null) {
            setLastMessage("Error: Contract not found.");
            return;
        }

        Staff signer = findStaffById(staffId);
        if (signer == null) {
            setLastMessage("Error: Staff not found.");
            return;
        }

        if (!(signer instanceof CreditCommittee)) {
            setLastMessage("Error: Only CreditCommittee  can co-sign a contract.");
            return;
        }

        boolean added = contract.addCoSigner(signer);
        if (added) {
            setLastMessage("Co-signer added successfully: " + signer.getName());
        } else {
            setLastMessage("Error: Could not add co-signer.");
        }
    }
    public void deactivateStaff(int staffId) {
        if (!requireLogin()) return;
        if (!requirePermission(CREATE_STAFF)) return;

        Staff staff = findStaffById(staffId);
        if (staff == null) {
            setLastMessage("Error: Staff not found.");
            return;
        }

        staff.setActive(false);
        setLastMessage("Staff deactivated: " + staff.getName());
        
    }


    public void setNewApprovalLimit(int loanOfficerId , double newAmount){
        if(!requireLogin()) return;
        if(!requirePermission(LoaningSystem.SET_NEW_APVL)) return;

        Staff staff=findStaffById(loanOfficerId);
        if(staff==null){
            setLastMessage("Error : No staff found");
        }

        if(!(staff instanceof LoanOfficer)){
            setLastMessage("Error : staff is not a Loan Officerr");
            return;
        }

        LoanOfficer officer = (LoanOfficer) staff;
        officer.setMaxApprovalLimit(newAmount);
        setLastMessage("Sucessfully set new approval limit for "+ officer.getName());
         
    }

    public void setNewName(String name ,String newName ,  String password){
        if(!requireLogin())  return;
        if(!requirePermission(LoaningSystem.SET_NEW_NAME)) return;

        if(loggedInUser.getName().equalsIgnoreCase(name) && loggedInUser.checkPassword(password)){
                loggedInUser.setName(newName);
                System.out.println("Successfully change user name ");
                return;
            }
            throw new InputMismatchException("Error : Authentication failed");
        }

    public void setNewPassword(String name , String password , String  newPassword){
        if(!requireLogin()) return;
        if(!requirePermission(LoaningSystem.SET_NEW_PASSWORD)) return ;

        if(loggedInUser.getName().equalsIgnoreCase(name) && loggedInUser.checkPassword(password)){
                loggedInUser.setPassword(newPassword);
                System.out.println("Successfully change your password");
                return;
        }
        throw new InputMismatchException("Error : Authentication failed");



    }
    



    public void printStaffs() {
        System.out.println("\n--- Staffs (" + staffs.size() + ") ---");
        if (staffs.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println((i + 1) + ") " + staffs.get(i));
        }
    }

    public void printApplicants() {
        System.out.println("\n--- Applicants (" + applicants.size() + ") ---");
        if (applicants.isEmpty()) {
            System.out.println("No applicants found.");
            return;
        }
        for (int i = 0; i < applicants.size(); i++) {
            System.out.println((i + 1) + ") " + applicants.get(i));
        }
    }

    public void printContracts() {
        System.out.println("\n--- Contracts (" + contracts.size() + ") ---");
        if (contracts.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }
        for (int i = 0; i < contracts.size(); i++) {
            System.out.println((i + 1) + ") " + contracts.get(i));
        }
    }

    // ===== Find Helpers =====
    private Contract findContractById(int contractId) {
        for (int i = 0; i < contracts.size(); i++) {
            if (contracts.get(i).getContractId() == contractId) {
                return contracts.get(i);
            }
        }
        return null;
    }

    private Applicant findApplicantById(int applicantId) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getApplicantId() == applicantId) {
                return applicants.get(i);
            }
        }
        return null;
    }

    private Staff findStaffById(int staffId) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getStaffId() == staffId) {
                return staffs.get(i);
            }
        }
        return null;
    }

    private boolean requireLogin() {
        if (loggedInUser == null) {
            setLastMessage("Action denied: please login first.");
            return false;
        }
        if (!loggedInUser.isActive()) {
            loggedInUser = null;
            setLastMessage("Action denied: User is inactive. Auto logout.");
            return false;
        }
        return true;
    }

    private boolean requirePermission(String action) {
        if (!loggedInUser.can(action)) {
            setLastMessage("Error: Permission denied for action: " + action);
            return false;
        }
        return true;
    }

    public boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Bank: " + bankName +
               " | ID: " + bankId +
               " | Interest Rate: " + (currentInterestRate * 100) + "%" +
               " | Staffs: " + staffs.size() +
               " | Applicants: " + applicants.size() +
               " | Contracts: " + contracts.size();
    }
}