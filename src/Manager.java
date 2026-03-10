package src;

public class Manager extends Staff {

    public Manager(String name , int age , String password , double salary) {
        super(name , age , password);
        setSalary(salary);
        setPosition("Manager");
    }

    @Override
    public boolean can(String action) {
        switch (action) {
            case LoaningSystem.CREATE_STAFF:     return true;
            case LoaningSystem.CREATE_APPLICANT: return true;
            case LoaningSystem.SET_NEW_APVL: return true;
            case LoaningSystem.SET_NEW_REQV: return true;
            default: return false;
        }
    }

    public void Hello(){
        System.out.println("Manager");
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Position: Manager" +
               " | Salary: " + getSalary();
    }
}