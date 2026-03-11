package src.model;

import src.controller.LoaningSystem;

public class LoanOfficer extends Staff {
   private double maxApprovalLimit;

  public LoanOfficer(String name , int age ,String password, double salary, double maxApprovalLimit){
     super(name , age , password);
     setSalary(salary);
     setPosition(LoaningSystem.LOAN_OFFICER);
     setMaxApprovalLimit(maxApprovalLimit);
  }
public double getMaxApprovalLimit() {
        return maxApprovalLimit;
    }

public void setMaxApprovalLimit(double maxApprovalLimit) {
    if(maxApprovalLimit <= 0){
        System.out.println("Error: max approval limit must be greater than 0.");
        return;
    }
    this.maxApprovalLimit = maxApprovalLimit;
  }


public boolean canApprove(double amount) {
    return amount <= maxApprovalLimit;
}


@Override
    public boolean can(String action) {
        switch (action) {
            case LoaningSystem.CREATE_CONTRACT: return true;
            case LoaningSystem.APPROVE_LOAN:    return true;
            case LoaningSystem.REJECT_LOAN:     return true;
            case LoaningSystem.ADD_COSIGNER:    return true;
            default: return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Position: Loan Officer" +
               " | Salary: " + getSalary() +
               " | Max Approval: $" + maxApprovalLimit;
    }
}
