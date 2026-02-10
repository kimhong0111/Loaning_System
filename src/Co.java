package src;


public class Co{
    Applicant[] applicants;
    Contract[] approvedContracts;
    LoaningSystem bank;
    String name;
    String title;
    static int indexID=1;
    int coId=indexID;
    private int applicantCount = 0;
    private int approvedCount = 0;

    public Co(String name,String title, LoaningSystem bank,int maxRequest){
        this.name = name;
        this.title = title;
        this.bank=bank;
        this.coId=indexID++;
        this.applicants = new Applicant[maxRequest];
        this.approvedContracts = new Contract[maxRequest];
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
         addApplicantToQueue(applicant);
         bank.addCo(this);
         if(ValidateRequest(applicant,loanAmount)){
             Contract approvedContract = new Contract(applicant, loanAmount, duration, 2);
             approvedContract.setApprovingOfficer(this);    
             approvedContract.calculateTotal();
             addApprovedContract(approvedContract); // Store contract in CO's list
             bank.addApplicant(applicant);
             bank.addContract(approvedContract);
             System.out.println("Loan approved for " + applicant.name);
         } else {
            System.out.println(applicant.name + "'s request was not accepted");
         }
        
    }

    private void addApplicantToQueue(Applicant applicant) {
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

    private void addApprovedContract(Contract contract) {
        if (contract == null) {
            System.out.println("Cannot add: contract is null");
            return;
        }
        if (approvedCount >= approvedContracts.length) {
            System.out.println("Cannot add: approved contract list is full");
            return;
        }
        approvedContracts[approvedCount++] = contract;
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
        return "Co ID: " + coId + ",Name: " + name + ",Bank ID: " + bank.bankId + ",Bank Name: " + bank.bankName + ",Title: " + title;
    }
}
    