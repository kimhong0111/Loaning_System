package src;

public class LoaningSystem {
    // after making contract make sure you put the contract into the system 
    String bankName; 
    Contract[] contractList; 
    int bankId;
    double currentInterestsRate; 
    int count; 
   
    public LoaningSystem(String bankName,int bankId, double currentInterestsRate, int maxContract) {
        this.bankName = bankName;
        this.bankId = bankId;
        this.currentInterestsRate = currentInterestsRate;
        contractList = new Contract[maxContract];
        count = 0;
    }
    public void addContract(Contract contract) {
        contractList[count] = contract;
        count++;
    }
    public void displayList(){
        if(count==0){
            System.out.println("No contract in the system");
            return;
        }
        System.out.print("List: ");
        for (int i = 0; i < count; i++) {
            System.out.print(" "+ contractList[i].applicant.name);
        }
        System.out.println();
    }
    
    // null safety 
    public Contract searchContractByName(String name) {
        if (name == null) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            // "String comparison" because if we use ==, we actually compare address if two variabels point to the same object
            if (name.equals(contractList[i].applicant.name)) {
                return contractList[i];
            }
        }
        return null; 
    }
    @Override
    public String toString() {
        return "Bank Name: " + bankName + ",Bank ID: " + bankId + ",Current Interest Rate: " + currentInterestsRate;
    }
}