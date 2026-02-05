package src;

public class Applicant {
    String name; 
    String gender; 
    static int count=1;
    int  applicantId=count;
    int salary; 
    int age; 
  public Applicant(String name , String gender , int salary, int age){
      this.name=name;
      this.gender=gender;
      this.salary=salary;
      this.age=age;
      this.applicantId=count++;
  }
   @Override
   public String toString() {
      return "Name: " + name + ",Id: " + applicantId + ",gender: " + gender + ",salary: " + salary + ",age: " + age;
   }  
}