package src.model;

import src.controller.LoaningSystem;
import src.interfaces.IStaff;
    
public abstract class Staff implements IStaff{

    private String name;
    private int age;
    private String password;
    private  static  int staffIndexID = 1;
    private int staffId;
    private boolean active;
    private double salary;
    private String position;

    public Staff(String name, int age , String password) {
        setName(name);
        setPassword(password);
        setAge(age);
        setNextStaffId();
        this.active=true;
        this.salary=0;
        this.position="Staff";
        System.out.println("Staff Constructor run Successfully");


    }


    
    public String getName(){
        return name;
    }

    public int getStaffId(){
        return staffId;
    }
    public int getAge(){
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public boolean isActive(){
        return  active;
    }

    public boolean checkPassword(String password){
       if(password==null){
        return false;
       }
       return this.password.equals(password);
    }

    // ===== Setters with validation =====


    public void setNextStaffId(){
        this.staffId=staffIndexID++;
    }
    public void setName(String name) {
        this.name=name;
    }


    public void setAge(int age){
         if(age < 18 || age > 65){
         throw new IllegalArgumentException("Invalid age. Age should be over 18 and under 65");
          }else {
            this.age=age;
         }
    }

    public void setPassword(String password) {
    if (password == null || password.isBlank()) {
         throw new IllegalArgumentException("Error: Password cannot be empty.");
    }
    if (password.length() < 4) {
        throw new IllegalArgumentException("Error: Password must be at least 4 characters long.");
    }
    this.password = password;
}

public void setSalary(double salary){
    if(salary <= 0){
       throw new IllegalArgumentException("Error: Salary must be greater than 0."); 
    }
    this.salary = salary;
}

public void setPosition(String position){
    if(position == null || position.isBlank()){
        throw new IllegalArgumentException("Error: Position cannot be empty.");
    }
    this.position = position;
}


public void setActive(boolean c){
    this.active=c;
}
   

    @Override
    public String toString() {
        return "Staff ID: " + staffId + ",Name: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        Staff s1 = (Staff) obj;
        if(s1.name.equals(this.name) && s1.staffId==this.staffId){
            return true;
        }

        return false;

    }

    public void setNewName(String name){
         setName(name);
    }

    public boolean can(String action){
           switch (action) {
            case LoaningSystem.SET_NEW_NAME : return true;
            default : return false;

        }
    }
    public abstract void canContractApprove(Staff staff , Contract contract);


}