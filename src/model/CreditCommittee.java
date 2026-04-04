package src.model;

import src.controller.LoaningSystem;

public class CreditCommittee extends Staff {
    private int requiredVotes;
    private int currentVotes;

    public CreditCommittee(String name , String userName , String phoneNumber , int age ,String password,
                           double salary, int requiredVotes) {
        super(name,userName,phoneNumber ,age, password);
        setSalary(salary);
        setPosition(LoaningSystem.CREDIT_COMMITTEE);
        setRequiredVotes(requiredVotes);
        this.currentVotes = 0;
    }

    public void castVote() {
        currentVotes++;
    }

    public boolean hasEnoughVotes() {
        return currentVotes >= requiredVotes;
    }

    public void resetVotes() {
        currentVotes = 0;
    }

    public int getRequiredVotes() { return requiredVotes; }
    public int getCurrentVotes()  { return currentVotes; }

    public void setRequiredVotes(int requiredVotes) {
        if (requiredVotes <= 0) {
            System.out.println("Error: Required votes must be at least 1.");
            return;
        }
        this.requiredVotes = requiredVotes;
    }

    @Override
    public boolean can(String action) {
        switch (action) {
            case LoaningSystem.APPROVE_LOAN: return true;
            case LoaningSystem.ADD_COSIGNER: return true;
            default: return super.can(action);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Position: Credit Committee" +
               " | Salary: " + getSalary() +
               " | Votes: " + currentVotes + "/" + requiredVotes;
    }

    @Override 
    public boolean canContractApprove(Staff staff , Contract contract){
         CreditCommittee committee = (CreditCommittee) staff;
            committee.castVote();
            if (!committee.hasEnoughVotes()) {
                LoaningSystem.setLastMessage("Vote cast. Current votes: " + committee.getCurrentVotes()
                        + "/" + committee.getRequiredVotes());
                return false;
            }
            committee.resetVotes();
            contract.setStatus("APPROVED");
            contract.setApprovingOfficer(committee);
            return true;
            

    }
}

