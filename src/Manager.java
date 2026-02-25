package src;

public class Manager implements IStaff {
    
    // ===== Fields =====
    private String name;
    private String role;
    private LoaningSystem bank;
    private static int indexID = 1;
    private int coId = indexID;

    // ===== Constructor =====
    public Manager(String name, LoaningSystem bank, String role, int age) {
        setName(name);
        this.role = role;
        setBank(bank);
        this.coId = indexID++;
    }

    // ===== Getters =====
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public int getStaffId() {
        return coId;
    }

    public LoaningSystem getBank() {
        return bank;
    }

    // ===== Setters with validation =====
    public void setName(String name) {
        String regex = "^[A-Z][a-z]{2,29}+ [A-Z][a-z]{2,29}$";
        if (name.matches(regex)) {
            this.name = name;
            return;
        }
        System.out.println("Invalid name format. Name should only contain letters");
    }

    public void setBank(LoaningSystem bank) {
        if (bank != null) {
            this.bank = bank;
        } else {
            System.out.println("Invalid bank. Bank cannot be null.");
        }
    }
    @Override
    public boolean can(String action) {
        return (action.equals(LoaningSystem.ADD_STAFF) || action.equals(LoaningSystem.REMOVE_STAFF) || action.equals(LoaningSystem.VIEW_STAFF));
    }
    @Override
    public void job(Applicant applicant, String action) {
        // TODO Auto-generated method stub
        
    }
}
