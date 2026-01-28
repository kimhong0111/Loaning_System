package src;

public class Main {
    public static void main(String[] args) {
        Applicant app1=new Applicant("Kimhong","M",2000,18);
        Applicant app2=new Applicant("Vichea","M",1000,18);

        Co myCo=new Co(10);
        myCo.addApplication(app1,600.00);
        myCo.addApplication(app2, 300.0);
        myCo.printRequest();    
        myCo.printContract();

    }
}
