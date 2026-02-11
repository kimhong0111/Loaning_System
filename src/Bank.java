package src;
import java.util.ArrayList;

public class Bank {
   private String bankName; 
   private ArrayList<Applicant> applicantLists;
   private ArrayList<Contract> contractLists;
   private ArrayList<Co> coLists;
   private static int indexID=1;
   private int bankId=indexID;
   private double currentInterestsRate; 
   private int contractCount; 
   private int applicantCount;
   private int coCount;

    public Bank(String bankName, double currentInterestsRate) {
        setBankName(bankName);
        this.bankId = indexID++;
        this.currentInterestsRate = currentInterestsRate;
        applicantLists = new ArrayList<Applicant>();
        coLists = new ArrayList<Co>();
        contractLists = new ArrayList<Contract>();
        contractCount = 0;
        applicantCount = 0;
        coCount = 0;
    }
    public void setBankName(String bankName){
        String regex = "[A-Za-z]{2,29}$";
        if(bankName.matches(regex)){
            this.bankName = bankName;
        } else {
            System.out.println("Invalid bank name format. Bank name should start with an uppercase letter followed by lowercase letters, and be between 3 and 30 characters long.");
        }
    }

    public String getName() {
        return bankName;
    }

    public int getBankId() {
        return bankId;
    }

    
    public void addContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        if (contractCount >= contractLists.size()) {
            System.out.println("Cannot add: contract list is full");
            return;
        }
        contractLists.add(contract);
    }

    public void addApplicantAndContract(Contract approvedContract, Applicant applicant) {
        if (approvedContract == null) {
            System.out.println("Cannot add: applicant is null");
            return;
        }
        for (int i = 0; i < contractCount; i++) {
            if (approvedContract.getApplicant().applicantId == contractLists.get(i).getApplicant().applicantId) {
                contractLists.get(i).setAmount(approvedContract.getAmount()); // Update existing applicant
                return;
            }
        }
        applicantLists.add(applicant);
        contractLists.add(approvedContract);
    }

    public void addCo(Co co) {
        if (co == null) {
            System.out.println("Cannot add: CO is null");
            return;
        }
        if (coCount >= coLists.size()) {
            System.out.println("Cannot add: CO list is full");
            return;
        }
        for (int i = 0; i < coCount; i++) {
            if (co.equals(coLists.get(i))) {
                return;
            }
        }
        coLists.add(co);
    }
    public void displayList(){
        if(contractCount==0){
            System.out.println("No contract in the system");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < contractCount; i++) {
            System.out.print(" "+ contractLists.get(i).getApplicant().getName());
        }
        System.out.println();
    }
    
    // null safety 
    public Contract searchContractByName(String name) {
        if (name == null) {
            System.out.println("Name not found");
            return null;
        }
        for (int i = 0; i < contractCount; i++) {
            if (name.equals(contractLists.get(i).getApplicant().getName())) {
                return contractLists.get(i);
            }
        }
        throw new NullPointerException("Name not found");
    }
    public Co searchCoById(int id) {
        for (int i = 0; i < coCount; i++) {
            if (coLists.get(i).getId() == id) {
                return coLists.get(i);
            }
        }
        throw new NullPointerException("ID not found");
    }
    @Override
    public String toString() {
        return "Bank Name: " + bankName + ",Bank ID: " + bankId + ",Current Interest Rate: " + currentInterestsRate + ",Number of Contracts: " + contractCount + ",Number of Applicants: " + applicantCount + ",Number of COs: " + coCount + "id: "+ bankId;
    }

}