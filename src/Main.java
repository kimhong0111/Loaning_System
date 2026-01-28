package src;

public class Main {
    public static void main(String[] args) {
        Applicant app1=new Applicant("Kimhong","M",2000,18);
        Applicant app2=new Applicant("Vichea","M",1000,18);

        Co myCo=new Co();
        myCo.addApplication(app1,600.00);
        myCo.printRequest();

    }
}
