package src.model;

import src.controller.LoaningSystem;

public class Manager extends Staff {
     private int accessLevel;

    public Manager(String name , int age , String password , double salary, int accessLevel) {
        super(name , age , password);
        setSalary(salary);
        setAccessLevel(accessLevel);
        setPosition(LoaningSystem.MANAGER);
    }

    public int getAccessLevel(){
        return accessLevel;
    }

    protected void setAccessLevel(int accessLevel){
        if(accessLevel < 1 || accessLevel > 3){
            System.out.println("Error : access level is not in range ");
            return;
        }
        this.accessLevel=accessLevel;

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

    @Override
    public void canContractApprove(Staff staff , Contract contract){
         System.out.println("Error : Manager cannot approve contract");
    }
}