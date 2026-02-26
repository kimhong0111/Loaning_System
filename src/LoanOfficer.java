package src;

public class LoanOfficer implements IStaff {
    
    // ===== Fields =====
     private String name;
    private String password;
    private String role;
    private int StaffId;
    public boolean active;

    // ===== Constructor =====
    public LoanOfficer(String name, String role, int age, String password) {
        setName(name);
        this.role = role;
        this.StaffId =Staff.getNextIndexID();
        this.active=true;
        setPassword(password);

    }


    public boolean checkPassword(String password){
        if(this.password.equals(password)){
            return true;
        }

        return false;

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
        return (action.equals(LoaningSystem.SUBMIT_APPLICATION)|| action.equals(LoaningSystem.VIEW_APPLICATION)|| action.equals(LoaningSystem.VIEW_APPLICANT));
    }
/* 
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
        */

        public boolean isActive(){
        return active;
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
