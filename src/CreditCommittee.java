package src;


public class CreditCommittee extends Staff {
    private int requiredVotes;
    private int currentVotes;

    public CreditCommittee(Staff s, String password,
                           double salary, int requiredVotes) {
        super(s.getName(),s.getAge());
        setSalary(salary);
        setPassword(password);
        setPosition("Credit Committee");
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
            default: return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Position: Credit Committee" +
               " | Salary: " + getSalary() +
               " | Votes: " + currentVotes + "/" + requiredVotes;
    }
}