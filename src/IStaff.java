package src;
public interface IStaff {
    String getName();
    int getStaffId();
    boolean isActive();
    boolean can(String action);
    boolean checkPassword(String input);

}
