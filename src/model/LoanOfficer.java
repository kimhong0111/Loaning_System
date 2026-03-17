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



private boolean checkLoanOfficerApprovalLimit(double amount){
    return amount <= maxApprovalLimit;
}

private boolean checkApplicantSalary(double amount){
    int applicantSalary=Applicant.getSalary();
    return amount < applicantSalary /2 ;
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


    @Override
    public void canContractApprove(Staff staff, Contract contract){
        LoanOfficer officer = (LoanOfficer) staff;
        if(!officer.checkApplicantSalary(contract.getPrincipalAmount())){
            System.out.println("REJECTED : Applicant's Salary not high enough");
            contract.setStatus(Contract.REJECTED);
            return;
        }
        if(!officer.checkLoanOfficerApprovalLimit(contract.getPrincipalAmount())){
            System.out.println("LOAN OFFICER APPROVAL LIMIT IS NOT HIGH ENOUGH");
            contract.setStatus(Contract.FORWARDED);
            return;
        }
            contract.setStatus(Contract.APPROVED);
            contract.setApprovingOfficer(officer);
            LoaningSystem.setLastMessage("Contract #" + contract.getContractId() + " approved by Loan Officer: " + officer.getName());
            return;
    }
}
