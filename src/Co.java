package src;


public class Co{
   private String name;
   private String role;
   private LoaningSystem bank;
   private static int indexID=1;
   private int coId=indexID;
   

     public Co(String name, LoaningSystem bank, String role){
        this.name = name;
        this.role=role;
        this.bank=bank;
        this.coId=indexID++;
        
    }
    public void addApplication(Applicant applicant, LoaningSystem bank, double loanAmount , int duration){
        // null safety 
         if(applicant==null ){
            System.out.println("Cannot add: application is null");
            return;
         }
         if(bank==null ){
            System.out.println("Cannot add: bank is null");
            return;
         }
         bank.addCo(this);
         if(ValidateRequest(applicant,loanAmount)){
             Contract approvedContract = new Contract(applicant, loanAmount, duration, 2);
             approvedContract.setApprovingOfficer(this);    
             approvedContract.calculateTotal();
           //  addApprovedContract(approvedContract); // need to change this 
             bank.addApplicant(applicant);
             bank.addContract(approvedContract);
             System.out.println("Loan approved for " + applicant.getName());
         } else {
            System.out.println(applicant.getName() + "'s request was not accepted");
         }
        
    }

   /* 
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

    /* cannot use this 
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
        */
   
    public boolean ValidateRequest(Applicant applicant, double loanAmount){
        int salary=applicant.getSalary();
        int age=applicant.getAge();
            if(age >= 18 &&  salary/2 > loanAmount){
              return true;
            }
            return false;
    }
    public String getRole(){
          return role;

    }
    public int getId(){
        return coId;
    }

    @Override
    public String toString() {
        return "Co ID: " + coId + ",Name: " + name + ",Bank ID: " + bank.bankId + ",Bank Name: " + bank.bankName;
    }
    
    public boolean equals(Co co2){
        if(this.name.equals(co2.name) && this.bank.bankId == co2.bank.bankId){
            return true;
        }
        return false;
     }
    
}
    