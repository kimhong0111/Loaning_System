package src;

public class LoaningSystem {
    // after making contract make sure you put the contract into the system 
    String bankName; 
    Applicant[] applicants;
    Contract[] contracts;
    Co[] cos;
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
        applicants = new Applicant[max];
        cos = new Co[max];
        contracts = new Contract[max];
        contractCount = 0;
        applicantCount = 0;
        coCount = 0;
    }
    public void addContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        if (contractCount >= contracts.length) {
            System.out.println("Cannot add: contract list is full");
            return;
        }
        contracts[contractCount++] = contract;
    }

    public void addApplicant(Applicant applicant) {
        if (applicant == null) {
            System.out.println("Cannot add: applicant is null");
            return;
        }
        if (applicantCount >= applicants.length) {
            System.out.println("Cannot add: applicant list is full");
            return;
        }
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i] == applicant) {
                return;
            }
        }
        applicants[applicantCount++] = applicant;
    }

    public void addCo(Co co) {
        if (co == null) {
            System.out.println("Cannot add: CO is null");
            return;
        }
        if (coCount >= cos.length) {
            System.out.println("Cannot add: CO list is full");
            return;
        }
        for (int i = 0; i < coCount; i++) {
            if (cos[i] == co) {
                return;
            }
        }
        cos[coCount++] = co;
    }
    public void displayList(){
        if(contractCount==0){
            System.out.println("No contract in the system");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < contractCount; i++) {
            System.out.print(" "+ contracts[i].applicant.name);
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
            if (name.equals(contracts[i].applicant.name)) {
                return contracts[i];
            }
        }
        throw new NullPointerException("Name not found");
    }
    @Override
    public String toString() {
        return "Bank Name: " + bankName + ",Bank ID: " + bankId + ",Current Interest Rate: " + currentInterestsRate;
    }
}