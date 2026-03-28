package src.interfaces;

public interface ILoginable {
     String getName();
     void setPassword(String password);
     void setName(String name);
     boolean checkPassword(String password);
     boolean isActive();
     boolean can(String action);


    
}
