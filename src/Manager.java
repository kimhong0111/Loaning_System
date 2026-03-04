package src;
// Manager level = Staff level meaning that manager class can be either inherited from loan officer or credit commitee 
public class Manager extends Staff {
     private float salary;
    // ===== Constructor =====
    public Manager(Staff s , float salary) {
        super(s.getName(), s.getAge(), s.getPassword());
        this.setSalary(salary);
        
    }


    //setter 
    public void setSalary(float salary){
        if(salary < 1000){
            System.out.println("Incorrect Amount");
        }else {
            this.salary=salary;
        }
    }


    

    
    @Override
    public boolean can(String action) {
        return true;
    }


}


