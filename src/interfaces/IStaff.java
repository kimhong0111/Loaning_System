package src.interfaces;
public interface IStaff {
    String getName();
    int getStaffId();
    boolean isActive();
    void setActive(boolean c);
    boolean can(String action);
    boolean checkPassword(String input);

}
