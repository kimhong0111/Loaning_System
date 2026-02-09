package src;


public class Co{
    LoaningSystem Bank;
    static int indexID=1;
    int coId=indexID;

    public Co(LoaningSystem Bank,int maxRequest){
        this.Bank=Bank;
        this.coId=indexID++;
    }
    public void addApplication(Applicant applicant,double loanAmount,int duration, LoaningSystem bank){
        // null safety 
         if(applicant==null ){
            System.out.println("Cannot add: application is null");
            return;
         }
         if(bank==null ){
            System.out.println("Cannot add: bank is null");
            return;
         }
         if(ValidateRequest(applicant,loanAmount)){
             Contract validContract = new Contract(applicant, loanAmount,duration,bank);
             validContract.calculateTotal();
             bank.addContract(validContract);
             System.out.println("Complete");
         } else {
            System.out.println(applicant.name + "'s request was not accepted");
         }
        
    }

    public boolean ValidateRequest(Applicant applicant, double loanAmount){
        int salary=applicant.salary;
        int age=applicant.age;
            if(age >= 18 &&  salary/2 > loanAmount){
              return true;
            }
            return false;
    }

    @Override
    public String toString() {
        return "Co ID: " + coId + ",Bank ID: " + Bank.bankId + ",Bank Name: " + Bank.bankName;
    }
}
    