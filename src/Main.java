package src;

public class Main {
    public static void main(String[] args) {
        Bank ABA = new Bank("ABA",0.05);
        Applicant app1=new Applicant("Keo Kimhong","M",2000,18);
        Applicant app2=new Applicant("Mao Vichea","M",1000,18);

    /*
        Co myCo=new Co(ABA,10);
        myCo.addApplication(app1,600.00,2, ABA);
        myCo.addApplication(app2, 300.0,1, ABA); 
        ABA.displayList();
        */
        // Test
        System.out.println("\nF1 -- Primative Copy ");
        System.out.println("=======================");
        int amount=10;
        int copied=amount;
        copied=20;
        System.out.println("Amount is "+amount);
        System.out.println("Copied amount is "+ copied);
        /*        System.out.println("\nF2 --Reference copy");
        Contract app3A=ABA.searchContractByName("Vichea");
        if(app3A!=null){
            Contract app3B=app3A;
            System.out.println("Before change app3A.name ="+  app3A.applicant.name);
            app3B.applicant.name="Panha101";
            System.out.println("After change app3A.name ="+ app3A.applicant.name);
        }
        /* 
        System.out.println("\nF3 -- Array stores references");  
        ABA.displayList();
        // toString test
        System.out.println("\n=======================");
        System.out.println(app1.toString());
        System.out.println(ABA.toString());
       // System.out.println(myCo.toString());
        System.out.println(ABA.searchContractByName("Hong101").toString());
    */


        Applicant app4=new Applicant("Keo Kimhong","M",2000,18);
        Applicant app5=new Applicant("Vong Sovanpanha","M",2000,20);
        System.out.println("\nF4 -- equals method");
        System.out.println("=======================");
        System.out.println("app4.equals(app5) = "+ app4.equals(app5));


        System.out.println(app4.toString());
        System.out.println(app5.toString());
        /* 
        Co myCo=new Co("Manager Chea",ABA,"Manager",40);
        ABA.addCo(myCo);
        myCo.addApplication(app4,800.00,2);
        myCo.addApplication(app5, 300.0,1);
        ABA.displayList();
        */
    }
}