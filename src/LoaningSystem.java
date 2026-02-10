package src;

public class LoaningSystem {
    String bankName; 
    Applicant[] applicantLists;
    Contract[] contractLists;
    Co[] coLists;
    static int indexID=1;
    int bankId;
    double currentInterestsRate; 
    int contractCount; 
    int applicantCount;
    int coCount;
   
    public LoaningSystem(String bankName,int bankId, double currentInterestsRate, int max) {
        this.bankName = bankName;
        this.bankId = indexID++;
        this.currentInterestsRate = currentInterestsRate;
        applicantLists = new Applicant[max];
        coLists = new Co[max];
        contractLists = new Contract[max];
        contractCount = 0;
        applicantCount = 0;
        coCount = 0;
    }
    public void addContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        if (contractCount >= contractLists.length) {
            System.out.println("Cannot add: contract list is full");
            return;
        }
        contractLists[contractCount++] = contract;
    }

    public void addApplicant(Applicant applicant) {
        if (applicant == null) {
            System.out.println("Cannot add: applicant is null");
            return;
        }
        if (applicantCount >= applicantLists.length) {
            System.out.println("Cannot add: applicant list is full");
            return;
        }
        for (int i = 0; i < applicantCount; i++) {
            if (applicant.equals(applicantLists[i])) {
                return;
            }
        }
        applicantLists[applicantCount++] = applicant;
    }

    public void addCo(Co co) {
        if (co == null) {
            System.out.println("Cannot add: CO is null");
            return;
        }
        if (coCount >= coLists.length) {
            System.out.println("Cannot add: CO list is full");
            return;
        }
        for (int i = 0; i < coCount; i++) {
            if (co.equals(coLists[i])) {
                return;
            }
        }
        coLists[coCount++] = co;
    }
    public void displayList(){
        if(contractCount==0){
            System.out.println("No contract in the system");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < contractCount; i++) {
            System.out.print(" "+ contractLists[i].applicant.getName());
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
            // "String comparison" because if we use ==, we actually compare address if two variabels point to the same object
            if (name.equals(contractLists[i].applicant.getName())) {
                return contractLists[i];
            }
        }
        throw new NullPointerException("Name not found");
    }
    @Override
    public String toString() {
        return "Bank Name: " + bankName + ",Bank ID: " + bankId + ",Current Interest Rate: " + currentInterestsRate;
    }
}