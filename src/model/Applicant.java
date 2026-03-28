package src.model;

import src.interfaces.ILoginable;
import src.controller.LoaningSystem;


public class Applicant  implements ILoginable {

    // ===== Fields =====
    private String name;
    private String password;
    private String gender;
    private boolean active;
    private static int indexID = 1;
    public int applicantId;
    private int salary;
    private  int age;

    // ===== Constructor =====
    public Applicant(String name,String password, String gender, int salary, int age) {
        setName(name);
        setPassword(password);
        setGender(gender);
        setAge(age);
        setSalary(salary);
        this.applicantId = indexID++;
        this.active=true;
    }
  @Override
    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getApplicantId() {
        return applicantId;
    }
    @Override
    public void setName(String name) {
        this.name=name;

    }
    @Override
     public void setPassword(String password) {
        this.password = password;
}

    public void setGender(String gender) {
        String regex = "^[MF]$";
        if (gender.matches(regex)) {
            this.gender = gender;
            return;
        }
      throw new IllegalArgumentException("Invalid gender. Gender should be 'M' or 'F'.");
    }

    public void setSalary(int salary) {
        if (salary > 0) {
            this.salary = salary;
            return;
        }
        throw new IllegalArgumentException("Invalid salary. Salary should be a positive integer.");
    }

    public void setAge(int age) {
        if (age >= 18 && age <= 65) {
            this.age= age;
            return;
        } 
      throw new IllegalArgumentException("Invalid age. Age should be between 18 and 65.");  
    }

@Override
    public boolean checkPassword(String password){
       if(password==null){
        return false;
       }
       return this.password.equals(password);
    }
@Override
     public boolean isActive(){
        return  active;
    }

 @Override
    public boolean can(String action){
           switch (action) {
            case LoaningSystem.SET_NEW_NAME : return true;
            case LoaningSystem.SET_NEW_PASSWORD: return true;
            case LoaningSystem.CREATE_CONTRACT : return true;
            default : return false;
        }
    }
    



    // ===== Comparison and toString =====
    @Override
    public String toString() {
        return "Name: " + name + ", Id: " + applicantId + ", gender: " + gender + ", salary: " + salary + ", age: " + age;
    }

    public boolean equals(Applicant applicant2) {
        if (applicant2 == null) {
            return false;
        }
        if (this.name.equals(applicant2.name) && this.applicantId == applicant2.applicantId) {
            return true;
        }
        return false;
    }
}