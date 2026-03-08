package src;


public class Staff implements IStaff {

    // ===== Fields =====
    private String name;
    private int age;
    private String password;
    private  int staffIndexID = 1;
    private int staffId;
    private boolean active;

    // ===== Constructor =====
    public Staff(String name, int age, String password) {
        setName(name);
        setAge(age);
        setPassword(password);
        this.staffId = getNextIndexID();
        this.active=true;
        System.out.println("Staff Constructor run Successfully");


    }

    // ===== Getters =====

    public  int getNextIndexID() {
        return staffIndexID++;
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

    public boolean isActive(){
        return  active;
    }

    // check password
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


   

    @Override
    public String toString() {
        return "Staff ID: " + staffId + ",Name: " + name  + "id: " + staffId; 
    }

    @Override
    public boolean equals(Obj obj) {
        Staff s1 = (Staff) obj;
        if(s1.name.equals(this.name) && s1.staffId==this.staffId){
            return true;
        }

        return false;

    }

    public boolean can(String action){
        return false;
    }


}