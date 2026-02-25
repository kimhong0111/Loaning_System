package src;

public class LoanOfficer implements IStaff {
    
    // ===== Fields =====
    private String name;
    private String role;
    private LoaningSystem bank;
    private static int indexID = 1;
    private int staffId = indexID;

    // ===== Constructor =====
    public LoanOfficer(String name, LoaningSystem bank, String role, int age) {
        setName(name);
        this.role = role;
        setBank(bank);
        this.staffId = indexID++;
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
        return (action.equals(LoaningSystem.SUBMIT_APPLICATION));
    }

    @Override
    public void job(Applicant applicant, String action) {
        if (!can(action)) {
            System.out.println("Error: " + getName() + " cannot perform action: " + action);
            return;
        }

        if (action.equals(LoaningSystem.SUBMIT_APPLICATION)) {
            System.out.println("[LoanOfficer " + getName() + "] Surveying applicant information:");
            System.out.println("    - Name: " + applicant.getName());
            System.out.println("    - Applicant ID: " + applicant.getApplicantId());
            System.out.println("    - Salary: $" + applicant.getSalary());
            System.out.println("    - Age: " + applicant.getAge());
            System.out.println("    - Gender: " + applicant.getGender());
            System.out.println("    Status: Information collected. Sending to Credit Committee for validation.");
        }
    }
}
