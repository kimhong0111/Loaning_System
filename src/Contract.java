package src;


public class Contract {
   //after CO review the application we need to make a contract make contract with applicant information amount bank name etc. contract should store application object after co review
   private Co approvingOfficer;  // Credit officer who approved this contract
   private Co[] coSigners;       // Array of co-signers (third parties guaranteeing the loan)
   private Applicant applicant;
   private int duration; 
   private double interestRate; 
   private double amount; 
   private static int indexID =1;
   private int contractId;
   private int coSignerCount = 0;


   public Contract(Applicant applicant, double amount, int duration, int max) {
      this.coSigners = new Co[max];
      setApplicant(applicant);
      setAmount(amount);
      setDuration(duration);
      this.contractId = indexID++;
      this.interestRate = 0.05;  // Default 5% interest rate
   }
   public void setApprovingOfficer(Co officer) {
      if (officer == null || officer.getBank().searchCoById(officer.getId()) == null) {
         System.out.println("Error: Approving officer cannot be null or must belong to a bank");
         return;
      }
      this.approvingOfficer = officer;
   }
   public void setAmount(double amount) {
      if (amount <= 0) {
         System.out.println("Error: Amount must be positive");
         return;
      }
      this.amount = amount;
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

   
   public boolean addCoSigner(Co coSigner) {
      if (coSigner == null) {
         System.out.println("Error: Co-signer cannot be null");
         return false;
      }
      if (coSignerCount >= coSigners.length) {
         System.out.println("Error: Maximum co-signers reached (" + coSigners.length + ")");
         return false;
      }
      coSigners[coSignerCount++] = coSigner;
      return true;
   }
   public void calculateTotal() {
      amount = amount * Math.pow(1 + interestRate, duration); // compound calculation
   }

   @Override
   public String toString() {
      return "Contract ID: " + contractId + ", Applicant: " + applicant.getName()+ 
             ", Approved By: " + (approvingOfficer != null ? approvingOfficer.getRole() + " ,Id: " + approvingOfficer.getId() : "Pending") + 
             ", Amount: $" + String.format("%.2f", amount) + 
             ", Duration: " + duration + " years, Interest Rate: " + (interestRate * 100) + "%";   

   }
   public Co getApprovingOfficer() {
      return approvingOfficer;
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
}
