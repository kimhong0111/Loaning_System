package src;


public class Co {

    // ===== Fields =====
    private String name;
    private String role;
    private int age;
    private LoaningSystem bank;
    private static int indexID = 1;
    private int coId = indexID;

    // ===== Constructor =====
    public Co(String name, LoaningSystem bank, String role, int age) {
        setName(name);
        this.role = role;
        setBank(bank);
        setAge(age);
        this.coId = indexID++;
    }

    // ===== Getters =====
    public String getRole() {
        return role;
    }

    public int getId() {
        return coId;
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

    public void setAge(int age) {
        if (age >= 18 && age <= 65) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Age should be between 18 and 65.");
        }
    }

    // ===== Business Logic Methods =====
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
            approvedContract.setApprovingOfficer(this);
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
        return "Co ID: " + coId + ",Name: " + name + ",Bank ID: " + bank.getBankId() + ",Bank Name: " + bank.getName() + ",Role: " + role + ",Age: " + age + "id: " + coId;
    }

    public boolean equals(Co co2) {
        if (co2 == null) {
            return false;
        }

        if (this.name.equals(co2.name) && this.bank.getName().equals(co2.bank.getName())) {
            return true;
        }
        return false;
    }

}