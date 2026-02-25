package src;

public interface IStaff {
    public String getName();
    public int getStaffId();
    public String getRole();
    public LoaningSystem getBank(); 
    public int password = 1234; // default 
    public boolean can(String action);
    public void job(Applicant applicant, String action);
    public boolean isActive();
    public boolean checkPassword(String password);
}
