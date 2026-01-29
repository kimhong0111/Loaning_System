package src;

public class LoaningSystem {
    // after making contract make sure you put the contract into the system 
    int bankId;
    String bankName;
    Contract[] contractList;
    int count;
   
    public LoaningSystem(String bankName,int bankId, int maxContract) {
        this.bankName = bankName;
        this.bankId = bankId;
        contractList = new Contract[maxContract];
        count = 0;
    }
    public void addContract(Contract contract) {
        contractList[count] = contract;
        count++;
    }
    public void displayList(){
        for (int i = 0; i < count; i++) {
            System.out.println(contractList[i].contractApplicant.name);
        }
    }

}