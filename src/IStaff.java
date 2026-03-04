package src;

public interface IStaff {
    public String getName();
    public int getStaffId();
    public String getPassword();
    public boolean can(String action);
    public boolean isActive();
    public boolean checkPassword(String password);
}
