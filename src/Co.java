package src;

public class Co{
    Applicant[] Application;
    double[] loanAmoutnList;
    int count;

    public Co(){
        this(1);
    }
    public Co(int maxRequest){
        this.Application=new Applicant[maxRequest];
        this.loanAmoutnList=new double[maxRequest];
        this.count=0;
        ;        
    }
    public void addApplication(Applicant applicant,double loanAmount){
         if(applicant==null){
            System.out.println("Cannot add: application is null");
         }
             if(ValidateRequest(applicant,loanAmount)){
                 Application[count]=applicant;
                 loanAmoutnList[count]=loanAmount;
                 count++;
                 System.out.println("Complete");
             
         } else {
            System.out.println("Request was not accepted");
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

    public void printRequest(){
           for(int i=0;i<count;i++){
             System.out.printf(Application[i].name+" request amount: "+loanAmoutnList[i]+"\n");
           }
    }
}