package src;

public class Applicant {
    String name; // reference
    String gender; // reference becasue it is String 
    int applicantId; // primitive
    int salary; // primitive
    int age; // primitive
  public Applicant(String name , String gender , int salary, int age){
      this.name=name;
      this.gender=gender;
      this.salary=salary;
      this.age=age;
  }
  
}