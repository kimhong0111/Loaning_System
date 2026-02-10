package src;


public class Contract {
   //after CO review the application we need to make a contract make contract with applicant information amount bank name etc. contract should store application object after co review
   Co approvingOfficer;  // Credit officer who approved this contract
   Co[] coSigners;       // Array of co-signers (third parties guaranteeing the loan)
   Applicant applicant;
   int duration; 
   double interestRate; 
   double principal; 
   static int indexID =1;
   int contractId;
   private int coSignerCount = 0;


   public Contract(Applicant applicant, double principal, int duration, int max) {
      this.applicant = applicant;
      this.coSigners = new Co[max];
      this.principal = principal;
      this.duration = duration;
      this.contractId = indexID++;
      this.interestRate = 0.05;  // Default 5% interest rate
   }
   public void setApprovingOfficer(Co officer) {
      if (officer == null) {
         System.out.println("Error: Approving officer cannot be null");
         return;
      }
      this.approvingOfficer = officer;
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
      principal= principal * Math.pow(1+ interestRate, duration); // compound calculation
   }

   @Override
   public String toString() {
      return "Contract ID: " + contractId + ", Applicant: " + applicant.getName()+ 
             ", Approved By: " + (approvingOfficer != null ? approvingOfficer.getRole() + " ,Id: " + approvingOfficer.getId() : "Pending") + 
             ", Principal: $" + String.format("%.2f", principal) + 
             ", Duration: " + duration + " years, Interest Rate: " + (interestRate * 100) + "%";
   }
}