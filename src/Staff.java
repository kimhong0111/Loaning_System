package src;


public abstract class Staff implements IStaff {

    private String name;
    private int age;
    private String password;
    private  int staffIndexID = 1;
    private int staffId;
    private boolean active;
    private double salary;
    private String position;

    public Staff(String name, int age , String password) {
        setName(name);
        setPassword(password);
        setAge(age);
        this.staffId = staffIndexID++;
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
    public void setName(String name) {
        String regex = "^[A-Z][a-z]{2,29}";
        if (name.matches(regex)) {
            this.name = name;
            return;
        }
        System.out.println("Invalid name format. Name should only contain letters");
    }


    public void setAge(int age){
         if(age < 0 || age > 99){
            System.out.println("Invaild Age : write a correct age");
         }else {
            this.age=age;
         }
    }

    public void setPassword(String password) {
    if (password == null || password.isBlank()) {
        System.out.println("Error: Password cannot be empty.");
        return;
    }
    if (password.length() < 4) {
        System.out.println("Error: Password must be at least 4 characters.");
        return;
    }
    this.password = password;
}

public void setSalary(double salary){
    if(salary <= 0){
        System.out.println("Error: Salary cannot be negative.");
        return;
    }
    this.salary = salary;
}

public void setPosition(String position){
    if(position == null || position.isBlank()){
        System.out.println("Error: Position cannot be empty.");
        return;
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

    public abstract boolean can(String action);


}