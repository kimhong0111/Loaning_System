package src;
import java.util.ArrayList;

public class Contract {

    // ===== Fields =====
    private IStaff approvingOfficer;         // Credit officer who approved this contract
    private IStaff draftingOfficer;          // Legal officer who drafted this contract
    private ArrayList<IStaff> coSigners;      // Array of co-signers (third parties guaranteeing the loan)
    private Applicant applicant;
    private int duration;
    private double interestRate;
    private double amount;
    private static int indexID = 1;
    private int contractId = indexID;
    private int staffSignerCount = 0;

    // ===== Constructor =====
    public Contract(Applicant applicant, double amount, int duration) {
        this.coSigners = new ArrayList<IStaff>();
        setApplicant(applicant);
        setAmount(amount);
        setDuration(duration);
        this.contractId = indexID++;
        this.interestRate = LoaningSystem.getCurrentInterestsRate(); // get current interest rate from the bank
    }

    // ===== Getters =====
    public IStaff getApprovingOfficer() {
        return approvingOfficer;
    }

    public IStaff getDraftingOfficer() {
        return draftingOfficer;
    }

    public int getContractId() {
        return contractId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public double getAmount() {
        return amount;
    }

    public int getDuration() {
        return duration;
    }

    // ===== Setters with validation =====
    public void setApprovingOfficer(IStaff officer) {
        if (officer == null || officer.getBank().searchStaffById(officer.getStaffId()) == null) {
            System.out.println("Error: Approving officer cannot be null or must belong to a bank");
            return;
        }
        this.approvingOfficer = officer;
    }

    public void setDraftingOfficer(IStaff officer) {
        if (officer == null || officer.getBank().searchStaffById(officer.getStaffId()) == null) {
            System.out.println("Error: Drafting officer cannot be null or must belong to a bank");
            return;
        }
        this.draftingOfficer = officer;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive");
            return;
        }
        this.amount += amount;
        calculateTotal();
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            System.out.println("Error: Duration must be positive");
            return;
        } else if (duration > 30) {
            System.out.println("Error: Duration cannot exceed 30 years");
            return;
        }
        this.duration = duration;
    }

    public void setApplicant(Applicant applicant) {
        if (applicant == null) {
            System.out.println("Error: Applicant cannot be null");
            return;
        }
        this.applicant = applicant;
    }

    // ===== Co-Signer and Calculation Methods =====
    public boolean addStaffSigner(IStaff staffSigner) {
        if (staffSigner == null) {
            System.out.println("Error: Staff signer cannot be null");
            return false;
        }
        if (staffSignerCount >= coSigners.size()) {
            System.out.println("Error: Maximum co-signers reached (" + coSigners.size() + ")");
            return false;
        }
        coSigners.add(staffSigner);
        staffSignerCount++;
        return true;
    }

    public void calculateTotal() {
        amount = amount * Math.pow(1 + interestRate, duration); // compound calculation
    }

    // ===== Comparison and toString =====
    @Override
    public String toString() {
        return "Contract ID: " + contractId + ", Applicant: " + applicant.getName() +
                ", Approved By: " + (approvingOfficer != null ? approvingOfficer.getRole() + " ,Id: " + approvingOfficer.getStaffId() : "Pending") +
                ", Amount: $" + String.format("%.2f", amount) +
                ", Duration: " + duration + " years, Interest Rate: " + (interestRate * 100) + "%";
    }

    public boolean equals(Contract contract2) {
        if (contract2 == null) {
            return false;
        }
        if (this.applicant.getName().equals(contract2.applicant.getName()) && this.amount == contract2.amount && this.duration == contract2.duration) {
            return true;
        }
        return false;
    }
}
