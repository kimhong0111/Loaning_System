public interface IStaff {
    String getName();
    String getStaffId();
    boolean isActive();
    boolean can();
    boolean checkPassword(String input);

}
