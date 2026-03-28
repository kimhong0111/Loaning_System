package src.interfaces;

import src.model.Contract;
import src.model.Staff;

public interface IStaff {
    
    int getStaffId();
    void setNewName(String name);
    void canContractApprove(Staff staff , Contract contract);


}
