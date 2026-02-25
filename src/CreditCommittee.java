package src;

public class CreditCommittee implements IStaff {
    
    // ===== Fields =====
    private String name;
    private String role;
    private LoaningSystem bank;
    private int staffId;

    // ===== Constructor =====
    public CreditCommittee(String name, LoaningSystem bank, String role, int age) {
        setName(name);
        this.role = role;
        setBank(bank);
        this.staffId = Staff.getNextIndexID();
    }

    // ===== Getters =====
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public int getStaffId() {
        return staffId;
    }

    public LoaningSystem getBank() {
        return bank;
    }

    // ===== Setters with validation =====
    public void setName(String name) {
        String regex = "^[A-Z][a-z]{2,29}+ [A-Z][a-z]{2,29}$";
        if (name.matches(regex)) {
            this.name = name;
            return;
        }
        System.out.println("Invalid name format. Name should only contain letters");
    }

    public void setBank(LoaningSystem bank) {
        if (bank != null) {
            this.bank = bank;
        } else {
            System.out.println("Invalid bank. Bank cannot be null.");
        }
    }
    public boolean validateApplicant(Applicant applicant, double requestedAmount) {
        if (applicant == null) {
            System.out.println("Error: Applicant cannot be null");
            return false;
        }
        if (applicant.getSalary() < requestedAmount) {
            System.out.println("Validation failed: Salary too low");
            return false;
        }
        if (applicant.getAge() < 21 || applicant.getAge() > 65) {
            System.out.println("Validation failed: Age not within acceptable range");
            return false;
        }
        // Additional credit checks can be added here
        return true;
    }
    @Override
    public boolean can(String action) {
        return (action.equals(LoaningSystem.APPROVE_LOAN) || action.equals(LoaningSystem.REJECT_LOAN)
        || action.equals(LoaningSystem.VIEW_CONTRACT) || action.equals(LoaningSystem.VIEW_APPLICANT)    );
    }

    @Override
    public void job(Applicant applicant, String action) {
        if (!can(action)) {
            System.out.println("Error: " + getName() + " cannot perform action: " + action);
            return;
        }

        if (action.equals(LoaningSystem.APPROVE_LOAN)) {
            System.out.println("[CreditCommittee " + getName() + "] Validating application from " + applicant.getName());
            System.out.println("    - Checking salary: $" + applicant.getSalary());
            System.out.println("    - Checking age: " + applicant.getAge());
            System.out.println("    - Credit score evaluation...");
            System.out.println("    Status: APPROVED - Application meets all credit criteria.");
            System.out.println("    Next step: Sending to Legal Officer for contract drafting.");
        } else if (action.equals(LoaningSystem.REJECT_LOAN))    {
            System.out.println("[CreditCommittee " + getName() + "] Validating application from " + applicant.getName());
            System.out.println("    Status: REJECTED - Does not meet credit criteria.");
            System.out.println("    Application process terminated.");
        } else if (action.equals(LoaningSystem.VIEW_APPLICANT)) {
            System.out.println("[CreditCommittee " + getName() + "] Viewing applicant: " + applicant);
        } else if (action.equals(LoaningSystem.VIEW_CONTRACT)) {
            System.out.println("[CreditCommittee " + getName() + "] Viewing contract details for " + applicant.getName());
        }
    }
}
