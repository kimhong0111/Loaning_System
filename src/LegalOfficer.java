package src;

public class LegalOfficer implements IStaff {
    
    // ===== Fields =====
    private String name;
    private String role;
    private LoaningSystem bank;
    private int staffId;

    // ===== Constructor =====
    public LegalOfficer(String name, LoaningSystem bank, String role, int age) {
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
    @Override
    public boolean can(String action) {
        return (action.equals(LoaningSystem.VIEW_CONTRACT) || action.equals(LoaningSystem.VIEW_APPLICANT)|| action.equals(LoaningSystem.DRAFT_CONTRACT));
    }

    @Override
    public void job(Applicant applicant, String action) {
        if (!can(action)) {
            System.out.println("Error: " + getName() + " cannot perform action: " + action);
            return;
        }

        if (action.equals(LoaningSystem.DRAFT_CONTRACT)) {
            System.out.println("[LegalOfficer " + getName() + "] Drafting contract for " + applicant.getName());
            System.out.println("    - Preparing legal documents...");
            System.out.println("    - Verifying applicant information...");
            System.out.println("    - Finalizing contract terms...");
            System.out.println("    Status: Contract drafted successfully.");
        } else if (action.equals(LoaningSystem.VIEW_APPLICANT)) {
            System.out.println("[LegalOfficer " + getName() + "] Viewing applicant: " + applicant);
        } else if (action.equals(LoaningSystem.VIEW_CONTRACT)) {
            System.out.println("[LegalOfficer " + getName() + "] Viewing contract for " + applicant.getName());
        }
    }

    // Method to draft and store contract in the system
    public void draftAndStoreContract(Applicant applicant, Contract contract) {
        if (applicant == null || contract == null) {
            System.out.println("Error: Cannot draft contract - applicant or contract is null");
            return;
        }
        
        // Perform the draft action
        job(applicant, LoaningSystem.DRAFT_CONTRACT);
        
        // Store applicant and contract in the bank system
        System.out.println("    - Storing applicant in bank system...");
        bank.addApplicant(applicant);
        
        System.out.println("    - Storing contract in bank system...");
        bank.storeContract(contract);
        
        System.out.println("    Contract #" + contract.getContractId() + " is now officially registered in the system.");
    }
}
