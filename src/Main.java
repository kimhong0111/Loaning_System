package src;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  LOAN MANAGEMENT SYSTEM - CONTRACT EXECUTION WORKFLOW");
        System.out.println("=".repeat(60));
        System.out.println();

        // ===== SETUP: Initialize Bank and Hire Staff =====
        System.out.println("INITIALIZATION: Setting up Bank and Staff");
        System.out.println("-".repeat(60));
        LoaningSystem bank = new LoaningSystem("ABA", 0.05);
        System.out.println("Bank created: " + bank.getName());
        System.out.println();

        // Hire staff members
        LoanOfficer loanOfficer = new LoanOfficer("John Smith", bank, "Loan Officer", 35);
        CreditCommittee creditCommittee = new CreditCommittee("Sarah Johnson", bank, "Credit Committee", 42);
        LegalOfficer legalOfficer = new LegalOfficer("Michael Brown", bank, "Legal Officer", 38);

        // bank.createStaff("John Smith", bank, "Loan Officer", 35);
        // bank.createStaff("Sarah Johnson", bank, "Credit Committee", 42);
        // bank.createStaff("Michael Brown", bank, "Legal Officer", 38 );
        // System.out.println();

        // ===== STEP 1: LoanOfficer Surveys Applicant Information =====
        System.out.println("STEP 1: LoanOfficer Surveys Applicant Information");
        System.out.println("-".repeat(60));
        Applicant applicant = new Applicant("Keo Kimhong", "M", 2000, 30);
        double requestedAmount = 600.0;
        int loanDuration = 2;
        
        loanOfficer.job(applicant, LoaningSystem.SUBMIT_APPLICATION);
        System.out.println();

        // ===== STEP 2: CreditCommittee Validates and Approves/Rejects =====
        System.out.println("STEP 2: CreditCommittee Validates Application");
        System.out.println("-".repeat(60));
        
        // Check if applicant meets criteria
        boolean isApproved = creditCommittee.validateApplicant(applicant, requestedAmount);
        
        if (isApproved) {
            creditCommittee.job(applicant, LoaningSystem.APPROVE_LOAN);
        } else {
            creditCommittee.job(applicant, LoaningSystem.REJECT_LOAN);
            System.out.println("\nProcess terminated - Application rejected.");
            return; // Exit if rejected
        }
        System.out.println();

        // ===== STEP 3: Create Contract (approved by CreditCommittee) =====
        System.out.println("STEP 3: Creating Contract");
        System.out.println("-".repeat(60));
        Contract contract = new Contract(applicant, requestedAmount, loanDuration);
        contract.setApprovingOfficer(creditCommittee);
        System.out.println("Contract #" + contract.getContractId() + " created for " + applicant.getName());
        System.out.println("Loan Amount: $" + requestedAmount);
        System.out.println("Duration: " + loanDuration + " years");
        System.out.println();

        // ===== STEP 4: LegalOfficer Drafts Contract and Stores in System =====
        System.out.println("STEP 4: LegalOfficer Drafts Contract & Stores in System");
        System.out.println("-".repeat(60));
        contract.setDraftingOfficer(legalOfficer);
        legalOfficer.draftAndStoreContract(applicant, contract);
        System.out.println();

        // ===== STEP 5: Calculate Interest and Finalize =====
        System.out.println("STEP 5: Calculate Interest and Finalize Contract");
        System.out.println("-".repeat(60));
        contract.calculateTotal();
        System.out.println("Original Amount: $" + requestedAmount);
        System.out.println("Total Amount (with " + (contract.getAmount() / requestedAmount * 100 - 100) + "% interest over " + loanDuration + " years): $" + String.format("%.2f", contract.getAmount()));
        System.out.println();

        // ===== STEP 6: Display System Records =====
        System.out.println("STEP 6: Final System Records");
        System.out.println("-".repeat(60));
        System.out.println(bank.toString());
        System.out.println();

        System.out.println("APPLICANTS IN SYSTEM (" + bank.getApplicantCount() + " total):");
        for (Applicant app : bank.getApplicantList()) {
            System.out.println("  - " + app.getName() + " (ID: " + app.getApplicantId() + 
                             ", Salary: $" + app.getSalary() + ", Age: " + app.getAge() + ")");
        }
        System.out.println();

        System.out.println("STAFF IN SYSTEM (" + bank.getStaffCount() + " total):");
        for (IStaff staff : bank.getStaffList()) {
            System.out.println("  - " + staff.getName() + " (ID: " + staff.getStaffId() + 
                             ", Role: " + staff.getRole() + ")");
        }
        System.out.println();

        System.out.println("CONTRACTS IN SYSTEM (" + bank.getContractCount() + " total):");
        for (Contract c : bank.getContractList()) {
            System.out.println("  - Contract #" + c.getContractId() + 
                             " | Applicant: " + c.getApplicant().getName() + 
                             " | Amount: $" + String.format("%.2f", c.getAmount()) +
                             " | Duration: " + c.getDuration() + " years");
        }
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("  WORKFLOW COMPLETE - CONTRACT SUCCESSFULLY EXECUTED");
        System.out.println("=".repeat(60));
    }
}