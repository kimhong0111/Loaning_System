package src;
public class Contract {
   //after CO review the application we need to make a contract make contract with applicant information amount bank name etc. contract should store application object after co review
   Applicant contractApplicant; // reference
   LoaningSystem bank; // reference
   int duration; // primitive
   double interestRate; // primitive
   double principal; // primitive
   // Snapshot   
   String applicantNameSnapshot; // reference 
   int applicantSalarySnapshot; // primitive
   int applicantAgeSnapshot; // primitive

   public Contract(Applicant applicant, double principal,int duration,LoaningSystem bank ){
      this.contractApplicant = applicant;
      this.principal = principal;
      this.duration = duration;
      this.bank = bank;
      this.interestRate = bank.currentInterestsRate;
      // Snapshot data
      this.applicantNameSnapshot = applicant.name;
      this.applicantSalarySnapshot = applicant.salary;
      this.applicantAgeSnapshot = applicant.age;
   }
   public void calculateTotal() {
      principal= principal * Math.pow(1+ interestRate, duration); // compound calculation
   }
}
