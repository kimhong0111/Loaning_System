package src;
import java.util.ArrayList;

public class Contract {
   //after CO review the application we need to make a contract make contract with applicant information amount bank name etc. contract should store application object after co review
   private Co approvingOfficer;  // Credit officer who approved this contract
   private ArrayList<Co> coSigners;       // Array of co-signers (third parties guaranteeing the loan)
   private Applicant applicant;
   private int duration;    
   private double interestRate; 
   private double amount; 
   private static int indexID =1;
   private int contractId=indexID;
   private int coSignerCount = 0;


   public Contract(Applicant applicant, double amount, int duration) {
      this.coSigners = new ArrayList<Co>();
      setApplicant(applicant);
      setAmount(amount);
      setDuration(duration);
      this.contractId = indexID++;
      this.interestRate = 0.05;  
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
      this.amount +=amount;
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
      if (coSignerCount >= coSigners.size()) {
         System.out.println("Error: Maximum co-signers reached (" + coSigners.size() + ")");
         return false;
      }
      coSigners.add(coSigner);
      coSignerCount++;
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
   public boolean equals(Contract contract2){
      if(contract2 == null){
         return false;
      }
      if(this.applicant.getName().equals(contract2.applicant.getName()) && this.amount == contract2.amount && this.duration == contract2.duration){
         return true;
      }
      return false;
   }
}
