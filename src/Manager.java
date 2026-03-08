package src;

public class Manager extends Staff {

    public Manager(Staff s , String password , double salary) {
        super(s.getName(), s.getAge());
        setSalary(salary);
        setPassword(password);
        setPosition("Manager");
    }

    @Override
    public boolean can(String action) {
        switch (action) {
            case LoaningSystem.CREATE_STAFF:     return true;
            case LoaningSystem.CREATE_APPLICANT: return true;
            default: return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Position: Manager" +
               " | Salary: " + getSalary();
    }
}