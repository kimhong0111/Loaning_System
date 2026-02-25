package src;


public class Staff {

    // ===== Fields =====
    private String name;
    private String role;
    private LoaningSystem bank;
    private static int indexID = 1;
    private int StaffId = indexID;

    // ===== Constructor =====
    public Staff(String name, LoaningSystem bank, String role, int age) {
        setName(name);
        this.role = role;
        setBank(bank);
        this.StaffId = indexID++;
    }

    // ===== Getters =====
    public String getRole() {
        return role;
    }

    public int getId() {
        return StaffId;
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


    // ==== Business Logic Methods =====
    public void addApplication(Applicant applicant, double loanAmount, int duration) {
        // null safety
        if (applicant == null) {
            System.out.println("Cannot add: application is null");
            return;
        }
        if (bank == null) {
            System.out.println("Cannot add: bank is null");
            return;
        }
        if (ValidateRequest(applicant, loanAmount)) {
            Contract approvedContract = new Contract(applicant, loanAmount, duration);
            // approvedContract.setApprovingOfficer(this);
            approvedContract.calculateTotal();
            // addApprovedContract(approvedContract); // need to change this
            bank.addApplicantAndContract(approvedContract, applicant);
            System.out.println("Loan approved for " + applicant.getName());
        } else {
            System.out.println(applicant.getName() + "'s request was not accepted");
        }
    }

    public boolean ValidateRequest(Applicant applicant, double loanAmount) {
        int salary = applicant.getSalary();
        if (salary / 2 > loanAmount) {
            return true;
        }
        return false;
    }

    // ===== Comparison and toString =====
    @Override
    public String toString() {
        return "Staff ID: " + StaffId + ",Name: " + name + ",Bank ID: " + bank.getBankId() + ",Bank Name: " + bank.getName() + ",Role: " + role + "id: " + StaffId;
    }

    public boolean equals(Staff staff2) {
        if (staff2 == null) {
            return false;
        }

        if (this.name.equals(staff2.name) && this.bank.getName().equals(staff2  .bank.getName())) {
            return true;
        }
        return false;
    }

}