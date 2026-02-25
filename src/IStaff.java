package src;

public interface IStaff {
    public String getName();
    public int getStaffId();
    public String getRole();
    public LoaningSystem getBank(); 
    public boolean can(String action);
    public void job(Applicant applicant, String action);
}
