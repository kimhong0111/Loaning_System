package src;

public class Applicant {
   private String name; 
   private String gender; 
   private static int indexID=1;
   private int applicantId;
   private int salary; 
   private int age; 

  public Applicant(String name , String gender , int salary, int age){
      setName(name);
      setGender(gender);
      setAge(age);
      setSalary(salary);
      this.applicantId=indexID++;
  }

  public void setName(String name) {
      String regex="^[A-Z][a-z]{2,29}+ [A-Z][a-z]{2,29}$";
      if(name.matches(regex)){
        this.name = name;
        return;
      }
        System.out.println("Invalid name format. Name should only contain letters");
  }

  public void setGender(String gender){
    String regex="^[MF]$";
    if(gender.matches(regex)){
        this.gender=gender;
        return;
  }
     System.out.println("Invalid gender format. Gender should be either 'M' or 'F'");
}

public void setSalary(int salary) {
    if(salary > 0){
        this.salary = salary;
        return;
    } else {
        System.out.println("Invalid salary. Salary should be a positive integer.");
    }
  }

public void setAge(int age) {
    if(age >= 18 && age <= 65){
        this.age = age;
    } else {
        System.out.println("Invalid age. Age should be between 18 and 65.");
    }
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
      if(applicant2 == null){
        return false;
      }

       if(this.name.equals(applicant2.name)){
        return true;
       }
       return false;
}

}