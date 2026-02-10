package src;

public class Applicant {
   private String name; 
   private String gender; 
   private static int indexID=1;
   private int applicantId=indexID;
   private int salary; 
   private int age; 

  public Applicant(String name , String gender , int salary, int age){
      this.name=name;
      this.gender=gender;
      this.salary=salary;
      this.age=age;
      this.applicantId=indexID++;
  }

  public String getName() {
      return name;
  }
  public int getSalary() {
      return salary;
    }
    public int getAge() {
      return age;
    }
    
   @Override
   public String toString() {
      return "Name: " + name + ",Id: " + applicantId + ",gender: " + gender + ",salary: " + salary + ",age: " + age;
   }  
   public boolean equals(Applicant applicant2){
       if(this.name.equals(applicant2.name)){
        return true;
       }
       return false;
}

}