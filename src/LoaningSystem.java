package src;

import java.util.ArrayList;

public class LoaningSystem {

    public static final String CREATE_STAFF     = "CREATE_STAFF";
    public static final String CREATE_APPLICANT = "CREATE_APPLICANT";
    public static final String CREATE_CONTRACT  = "CREATE_CONTRACT";
    public static final String APPROVE_LOAN     = "APPROVE_LOAN";
    public static final String REJECT_LOAN      = "REJECT_LOAN";
    public static final String ADD_COSIGNER     = "ADD_COSIGNER";

    private String bankName;
    private static int indexBankId = 1;
    private int bankId;
    private static double currentInterestRate = 0.05; 

    private ArrayList<Staff> staffs;
    private ArrayList<Applicant> applicants;
    private ArrayList<Contract> contracts;

    private Staff loggedInStaff;
    private String lastMessage;

    public LoaningSystem(String bankName, double currentInterestRate) {
        this.bankId = indexBankId++;
        setBankName(bankName);
        setCurrentInterestRate(currentInterestRate);

        this.staffs     = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.contracts  = new ArrayList<>();

        this.loggedInStaff = null;
        this.lastMessage   = "";

        seedDefaultAdmin();
    }

    public String getBankName()                  { return bankName; }
    public int getBankId()                       { return bankId; }
    public static double getCurrentInterestRate(){ return currentInterestRate; }
    public String getLastMessage()               { return lastMessage; }
    public boolean isStaffLoggedIn()             { return loggedInStaff != null; }
    public IStaff getLoggedInStaff()             { return loggedInStaff; }

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
        this.currentInterestRate = rate;
    }

    private void setLastMessage(String msg) {
        this.lastMessage = msg;
        System.out.println(msg);
    }

    // ===== Seed Default Admin =====
    private void seedDefaultAdmin() {
        Staff base = new Staff("Admin",18);
        Manager admin = new Manager(base,"1234", 5000);
        staffs.add(admin);
        setLastMessage("System ready. Default admin seeded: Admin / 1234");
    }

    public void staffLogin(String name, String password) {
        if (isBlank(name) || isBlank(password)) {
            setLastMessage("Error: Name or password cannot be empty.");
            return;
        }

        for (int i = 0; i < staffs.size(); i++) {
            Staff s = staffs.get(i);
            if (s.getName().equalsIgnoreCase(name.trim())) {
                if (!s.isActive()) {
                    setLastMessage("Error: Staff account is inactive.");
                    return;
                }
                if (!s.checkPassword(password)) {
                    setLastMessage("Error: Wrong password.");
                    return;
                }
                loggedInStaff = s;
                setLastMessage("Login success. Welcome " + s.getName() + "!");
                return;
            }
        }
        setLastMessage("Error: Staff not found.");
    }

    public void staffLogout() {
        if (loggedInStaff == null) {
            setLastMessage("No staff is currently logged in.");
            return;
        }
        setLastMessage("Goodbye " + loggedInStaff.getName() + ". Logged out successfully.");
        loggedInStaff = null;
    }

    public void createStaff(String name, int age, String password, double salary, String position) {
        if (!requireStaffLogin()) return;
        if (!requirePermission(CREATE_STAFF)) return;

        if (isBlank(name) || isBlank(password)) {
            setLastMessage("Error: Name or password cannot be empty.");
            return;
        }

        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getName().equalsIgnoreCase(name.trim())) {
                setLastMessage("Error: Staff with this name already exists.");
                return;
            }
        }

        Staff base = new Staff(name, age);

        Staff newStaff;
        if (position.equals("Manager")) {
            newStaff = new Manager(base,password, salary);
        } else if (position.equals("LoanOfficer")) {
            newStaff = new LoanOfficer(base,password, salary, 50000); 
        } else if (position.equals("CreditCommittee")) {
            newStaff = new CreditCommittee(base,password, salary, 3);
        } else {
            setLastMessage("Error: Unknown position '" + position + "'. Use Manager, LoanOfficer, or CreditCommittee.");
            return;
        }

        staffs.add(newStaff);
        setLastMessage("Staff created successfully: " + newStaff.getName() + " | Role: " + position);
    }

    public void createApplicant(String name, int age, int income, String gender) {
        if (!requireStaffLogin()) return;

        if (isBlank(name)) {
            setLastMessage("Error: Applicant name cannot be empty.");
            return;
        }

        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getName().equalsIgnoreCase(name.trim())) {
                setLastMessage("Error: Applicant already exists.");
                return;
            }
        }

        applicants.add(new Applicant(name, gender, income, age));
        setLastMessage("Applicant created successfully: " + name);
    }

    public void createContract(int applicantId, double amount, int duration) {
        if (!requireStaffLogin()) return;
        if (!requirePermission(CREATE_CONTRACT)) return;

        Applicant applicant = findApplicantById(applicantId);
        if (applicant == null) {
            setLastMessage("Error: Applicant not found.");
            return;
        }

        for (int i = 0; i < contracts.size(); i++) {
            Contract c = contracts.get(i);
            if (c.getApplicant().getApplicantId() == applicantId
                    && c.getPrincipalAmount() == amount
                    && c.getDuration() == duration) {
                setLastMessage("Error: Identical contract already exists for this applicant.");
                return;
            }
        }

        Contract contract = new Contract(applicant, amount, duration);
        contract.setDraftingOfficer(loggedInStaff);
        contracts.add(contract);
        setLastMessage("Contract created successfully. ID: " + contract.getContractId());
    }

    public void approveContract(int contractId) {
        if (!requireStaffLogin()) return;
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

        if (loggedInStaff instanceof LoanOfficer) {
            LoanOfficer officer = (LoanOfficer) loggedInStaff;
            if (!officer.canApprove(contract.getPrincipalAmount())) {
                contract.setStatus("FORWARDED");
                setLastMessage("Loan amount exceeds officer limit. Forwarded to Credit Committee.");
                return;
            }
            contract.setStatus("APPROVED");
            contract.setApprovingOfficer(loggedInStaff);
            setLastMessage("Contract #" + contractId + " approved by Loan Officer: " + loggedInStaff.getName());
            return;
        }

        if (loggedInStaff instanceof CreditCommittee) {
            CreditCommittee committee = (CreditCommittee) loggedInStaff;
            committee.castVote();
            if (!committee.hasEnoughVotes()) {
                setLastMessage("Vote cast. Current votes: " + committee.getCurrentVotes()
                        + "/" + committee.getRequiredVotes());
                return;
            }
            committee.resetVotes();
            contract.setStatus("APPROVED");
            contract.setApprovingOfficer(loggedInStaff);
            setLastMessage("Contract #" + contractId + " approved by Credit Committee.");
            return;
        }
    }

    public void rejectContract(int contractId) {
        if (!requireStaffLogin()) return;
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
        setLastMessage("Contract #" + contractId + " rejected by: " + loggedInStaff.getName());
    }

    // ===== Add CoSigner =====
    public void addCoSigner(int contractId, int staffId) {
        if (!requireStaffLogin()) return;
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
        if (!requireStaffLogin()) return;
        if (!requirePermission(CREATE_STAFF)) return;

        Staff staff = findStaffById(staffId);
        if (staff == null) {
            setLastMessage("Error: Staff not found.");
            return;
        }

        staff.setActive(false);
        setLastMessage("Staff deactivated: " + staff.getName());
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

    private boolean requireStaffLogin() {
        if (loggedInStaff == null) {
            setLastMessage("Action denied: please login first.");
            return false;
        }
        if (!loggedInStaff.isActive()) {
            loggedInStaff = null;
            setLastMessage("Action denied: staff is inactive. Auto logout.");
            return false;
        }
        return true;
    }

    private boolean requirePermission(String action) {
        if (!loggedInStaff.can(action)) {
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