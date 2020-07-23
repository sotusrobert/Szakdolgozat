package Main;

import InvoiceProgram.Controller.ControllerUser;
import InvoiceProgram.Service.ServiceUser;
import InvoiceProgram.View.UserLogin;

public class Main {
    public static void main(String[] args) {
        ControllerUser controller= new ControllerUser(new UserLogin(), new ServiceUser());
    }
   
}
