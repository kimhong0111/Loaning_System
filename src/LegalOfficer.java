package src;

public class LegalOfficer implements IStaff {
    
    // ===== Fields =====
     private String name;
    private String password;
    private String role;
    private int StaffId;
    public boolean active;

    // ===== Constructor =====
    public LegalOfficer(String name, String role, int age, String password) {
        setName(name);
        this.role = role;
        this.StaffId =Staff.getNextIndexID();
        this.active=true;
        setPassword(password);

    }


    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        // simple rule for teaching: >= 4 chars
        if (pw.length() < 4) this.password = "0000";
        else this.password = pw;
    }
    // ===== Getters =====
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public int getStaffId() {
        return StaffId;
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

    
   
    @Override
    public boolean can(String action) {
        return (action.equals(LoaningSystem.APPROVE_LOAN) || action.equals(LoaningSystem.REJECT_LOAN)
        || action.equals(LoaningSystem.VIEW_CONTRACT) || action.equals(LoaningSystem.VIEW_APPLICANT)    );
    }
  /*
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
        */
        
    
    public boolean isActive(){
        return active;
      }

    public boolean checkPassword(String password){
            if(this.password.equals(password)){
                return true;
            }

            return false;
        }
            

    @Override
    public String toString() {
        return "Staff ID: " + StaffId + ",Name: " + name +  ",Role: " + role + "id: " + StaffId;
    }

    public boolean equals(IStaff staff2) {
        if (staff2 == null) {
            return false;
        }

        if (this.name.equals(staff2.getName()) && this.StaffId==staff2.getStaffId()) {
            return true;
        }
        return false;
    }
}
