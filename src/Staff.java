package src;


public class Staff implements IStaff {

    // ===== Fields =====
    private String name;
    private int age;
    private String password;
    private static int staffIndexID = 1;
    private int staffId;
    public boolean active;

    // ===== Constructor =====
    public Staff(String name, int age, String password) {
        this.name = name;
        setAge(age);
        this.staffId =Staff.getNextIndexID();
        this.active=true;
        System.out.println("Staff Constructor run Successfully");


    }

    // ===== Getters =====

    public static int getNextIndexID() {
        return staffIndexID++;
    }
    public String getName(){
        return name;
    }

    public int getStaffId(){
        return staffId;
    }
    public int getAge(){
        return age;
    }

    public String getPassword(){
         return password;
    }

    public boolean isActive(){
        return  active;
    }

    // check password
    public boolean checkPassword(String password){
        if(this.password.equals(password)){
            return true;
        }

        return false;

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


    public void setAge(int age){
         if(age < 0 || age > 99){
            System.out.println("Invaild Age : write a correct age");
         }else {
            this.age=age;
         }
    }


   /*

    // ==== Business Logic Methods =====
    public void addApplication(Applicant applicant, double loanAmount, int duration) {
        // null safety
        if (applicant == null) {
            System.out.println("Cannot add: application is null");
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
    */

    // ===== Comparison and toString =====
    @Override
    public String toString() {
        return "Staff ID: " + staffId + ",Name: " + name  + "id: " + staffId; 
    }

    public boolean equals(Staff staff2) {
        if (staff2 == null) {
            return false;
        }

        if (this.name.equals(staff2.name) && this.staffId==staff2.staffId) {
            return true;
        }
        return false;
    }

    public boolean can(String action){
        return false;
    }


}