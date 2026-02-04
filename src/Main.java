package src;

public class Main {
    public static void main(String[] args) {
        LoaningSystem ABA = new LoaningSystem("ABA",001,0.05,10);
        Applicant app1=new Applicant("Kimhong","M",2000,18);
        Applicant app2=new Applicant("Vichea","M",1000,18);

        Co myCo=new Co(001, 001, "ABA",10);
        myCo.addApplication(app1,600.00,2, ABA);
        myCo.addApplication(app2, 300.0,1, ABA); 
        myCo.printContract();
        ABA.displayList();
        
        // Test
        System.out.println("\nF1 -- Primative Copy ");
        System.out.println("=======================");
        int amount=10;
        int copied=amount;
        copied=20;
        System.out.println("Amount is "+amount);
        System.out.println("Copied amount is "+ copied);
        
        System.out.println("\nF2 --Reference copy");
        Contract app3A=ABA.searchContractByName("Vichea");
        if(app3A!=null){
            Contract app3B=app3A;
            System.out.println("Before change app3A.name ="+  app3A.applicant.name);
            app3B.applicant.name="Panha101";
            System.out.println("After change app3A.name ="+ app3A.applicant.name);
        }
        System.out.println("\nF3 -- Array stores references");  
        ABA.displayList();
        System.out.println("\nF4 -- Snapshot behavior");  
        System.out.println("Before change app1.name ="+  app1.name);
        app1.name="Hong101";
        System.out.println("After change app1.name ="+  app1.name);
        ABA.displayList();
        // toString test
        System.out.println("\n=======================");
        System.out.println(app1.toString());
        System.out.println(ABA.toString());
        System.out.println(myCo.toString());
        System.out.println(ABA.searchContractByName("Hong101").toString());
    }
}