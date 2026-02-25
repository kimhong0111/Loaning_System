package src;
import java.util.ArrayList;

public class LoaningSystem {
    // ===== Action =====
    public static final String APPROVE_LOAN = "approve_loan";
    public static final String REJECT_LOAN = "reject_loan";
    public static final String ADD_STAFF = "add_staff";
    public static final String REMOVE_STAFF = "remove_staff";
    public static final String VIEW_CONTRACT = "view_contract";
    public static final String VIEW_APPLICANT = "view_applicant";
    public static final String VIEW_STAFF = "view_staff";
    public static final String SUBMIT_APPLICATION = "submit_application";
    public static final String DRAFT_CONTRACT = "draft_contract";

    // ===== Fields =====
    private String bankName;
    private ArrayList<Applicant> applicantLists;
    private ArrayList<Contract> contractLists;
    private ArrayList<IStaff> staffLists;
    private static int indexID = 1;
    private int bankId = indexID;
    private static double currentInterestsRate = 0.05; // Default interest rate
    private int contractCount;
    private int applicantCount;
    private int staffCount;

    private IStaff loggedInStaff;

    // ===== Constructor =====
    public LoaningSystem(String bankName, double currentInterestsRate) {
        setBankName(bankName);
        this.bankId = indexID++;
        setCurrentInterestsRate(currentInterestsRate);
        applicantLists = new ArrayList<Applicant>();
        staffLists = new ArrayList<IStaff>();
        contractLists = new ArrayList<Contract>();
        contractCount = 0;
        applicantCount = 0;
        staffCount = 0;
    }

    // ===== Getters =====
    public String getName() {
        return bankName;
    }

    public int getBankId() {
        return bankId;
    }
    public static double getCurrentInterestsRate() {
        return currentInterestsRate;
    }   

    // ===== Setters with validation =====
    public void setBankName(String bankName) {
        String regex = "[A-Za-z]{2,29}$";
        if (bankName.matches(regex)) {
            this.bankName = bankName;
        } else {
            System.out.println("Invalid bank name format. Bank name should start with an uppercase letter followed by lowercase letters, and be between 3 and 30 characters long.");
        }
    }

    public static void setCurrentInterestsRate(double rate) {
        if (rate >= 0 && rate <= 1) {
            currentInterestsRate = rate;
        } else {
            System.out.println("Invalid interest rate. Interest rate should be between 0 and 1.");
        }
    }

    private boolean requireStaffLogin() {
        if (loggedInStaff == null) {
            return false;
        }

        if (!loggedInStaff.isActive()) {
            loggedInStaff = null;
            return false;
        }

        return true;
    }
    public boolean requirePermission(String action){
        if(loggedInStaff==null){
            System.out.println("Please login first");
            return false;
        }


        if(!loggedInStaff.can(action)){
            return false;
        }


        return true;
    }

    public void staffLogin(String username, String password) {

        if (isBlank(username) || password == null) {
            System.out.println("Login failed: missing username/password.");
            return;
        }

        for (int i = 0; i < staffLists.size(); i++) {
            IStaff s = staffLists.get(i);

            if (s.getName().equalsIgnoreCase(username.trim())) {

                if (!s.isActive()) {
                    System.out.println("Login failed: staff is inactive.");
                    return;
                }

                if (!s.checkPassword(password)) {
                    System.out.println("Login failed: wrong password.");
                    return;
                }

                loggedInStaff = s;
            System.out.println("Login success. Welcome " + s.getName() + "!");
                return;
            }
        }

    System.out.println("Login failed: username not found.");
    }


    public boolean isBlank(String username){
          return username==null || username.trim().isEmpty();
        
    }
    // ===== Business Logic Methods =====
    public void addContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        if (contractCount >= contractLists.size()) {
            System.out.println("Cannot add: contract list is full");
            return;
        }
        contractLists.add(contract);
    }

    public void addApplicantAndContract(Contract approvedContract, Applicant applicant) {
        if (approvedContract == null) {
            System.out.println("Cannot add: applicant is null");
            return;
        }
        for (int i = 0; i < contractCount; i++) {
            if (approvedContract.getApplicant().applicantId == contractLists.get(i).getApplicant().applicantId) {
                contractLists.get(i).setAmount(approvedContract.getAmount()); // Update existing applicant
                return;
            }
        }
        applicantLists.add(applicant);
        contractLists.add(approvedContract);
    }

    public void createStaff(String name, String role, int age, String password) {

          
        if (!requireStaffLogin() || !requirePermission(ADD_STAFF)) return;

        if(isBlank(name) || isBlank(password)){
            System.out.println("Cannot create staff: name or password is empty");
        }



        if (role.equals("Manager")) {
            Manager newManager = new Manager(name,role,age,password);
            staffLists.add(newManager);
            staffCount++;
        } else if (role.equals("Loan Officer")) {
            LoanOfficer newLoanOfficer = new LoanOfficer(name,role, age, password);
            staffLists.add(newLoanOfficer);
            staffCount++;
        } else if (role.equals("Legal Officer")) {
            LegalOfficer newLegalOfficer = new LegalOfficer(name,role, age, password);
            staffLists.add(newLegalOfficer);
            staffCount++;
        } else if (role.equals("Credit Committee")) {
            CreditCommittee newCreditCommittee = new CreditCommittee(name,role, age, password);
            staffLists.add(newCreditCommittee);
            staffCount++;
        } else {
            System.out.println("Invalid role: " + role + ". Staff not created.");
        }
    }

    public void addApplicant(Applicant applicant) {
        if (applicant == null) {
            System.out.println("Cannot add: applicant is null");
            return;
        }
        for (int i = 0; i < applicantLists.size(); i++) {
            if (applicant.getApplicantId() == applicantLists.get(i).getApplicantId()) {
                System.out.println("Applicant already exists: " + applicant.getName());
                return;
            }
        }
        applicantLists.add(applicant);
        applicantCount++;
        System.out.println("Applicant stored: " + applicant.getName() + " (ID: " + applicant.getApplicantId() + ")");
    }

    public void storeContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        contractLists.add(contract);
        contractCount++;
        System.out.println("Contract stored: Contract #" + contract.getContractId() + " for " + contract.getApplicant().getName());
    }
    public void displayList() {
        if (contractCount == 0) {
            System.out.println("No contract in the system");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < contractCount; i++) {
            System.out.print(" " + contractLists.get(i).getApplicant().getName());
        }
        System.out.println();
    }

    // ===== Search Methods =====
    // null safety
    public Contract searchContractByName(String name) {
        if (name == null) {
            System.out.println("Name not found");
            return null;
        }
        for (int i = 0; i < contractCount; i++) {
            if (name.equals(contractLists.get(i).getApplicant().getName())) {
                return contractLists.get(i);
            }
        }
        throw new NullPointerException("Name not found");
    }
    public IStaff searchStaffById(int id) {
        for (int i = 0; i < staffLists.size(); i++) {
            if (staffLists.get(i).getStaffId() == id) {
                return staffLists.get(i);
            }
        }
        return null;
    }

    public Applicant searchApplicantById(int id) {
        for (int i = 0; i < applicantLists.size(); i++) {
            if (applicantLists.get(i).getApplicantId() == id) {
                return applicantLists.get(i);
            }
        }
        return null;
    }

    public ArrayList<Applicant> getApplicantList() {
        return applicantLists;
    }

    public ArrayList<Contract> getContractList() {
        return contractLists;
    }

    public ArrayList<IStaff> getStaffList() {
        return staffLists;
    }

    public int getApplicantCount() {
        return applicantCount;
    }

    public int getContractCount() {
        return contractCount;
    }

    public int getStaffCount() {
        return staffCount;
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "Bank Name: " + bankName + ",Bank ID: " + bankId + ",Current Interest Rate: " + currentInterestsRate + ",Number of Contracts: " + contractCount + ",Number of Applicants: " + applicantCount + ",Number of Staff: " + staffCount + ",ID: " + bankId;
    }

}