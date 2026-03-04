package src;

import javax.security.auth.login.CredentialException;

public class CreditCommitee extends Staff {
    // Childs should have the same fields as other child  class and more
     private float salary;
    // ===== Constructor =====
    public CreditCommitee(Staff s , float salary) {
        super(s.getName(), s.getAge(), s.getPassword());
        this.setSalary(salary);
        System.out.println("Credit Commitee Constructor run Successfully");
        
    }


    //setter 
    public void setSalary(float salary){
        if(salary < 500){
            System.out.println("Incorrect Amount");
        }else {
            this.salary=salary;
        }
    }


    

    
    @Override
    public boolean can(String action) {
        return (action.equals(LoaningSystem.APPROVE_LOAN)|| action.equals(LoaningSystem.VIEW_APPLICANT)|| action.equals(LoaningSystem.REJECT_LOAN) || action.equals(LoaningSystem.VIEW_APPLICANT));
    }

        @Override
    public String toString(){
        return super.toString() + "salary : " + this.salary;
    }


}


