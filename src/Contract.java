package src;
public class Contract {
   //after CO review the application we need to make a contract make contract with applicant information amount bank name etc. contract should store application object after co review
   Applicant contractApplicant;
   int duration;
   int interestRate;
   double amount;
   LoaningSystem bank;

   public Contract(Applicant applicant, double amount,int duration,LoaningSystem bank ){
      this.contractApplicant = applicant;
      this.amount = amount;
      this.duration = duration;
      this.bank = bank;
   }
   public void calInterest() {
      if (duration < 1) {
         amount = amount * 10.0 * duration; 
      } else
         amount = amount * 12.5 * duration;
      }
}
