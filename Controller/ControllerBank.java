package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceBank;
import InvoiceProgram.View.Bank_GUI;
import java.awt.Color;
import javax.swing.BorderFactory;

public class ControllerBank {

    private final Bank_GUI view;
    private final ServiceBank service;
    //private ArrayList<BankListeners> listeners = new ArrayList<>();

    public ControllerBank() {
        view = new Bank_GUI();
        service = new ServiceBank();
        initView();
        initControll();
    }

    public void initView() {
        service.getAllBank(view.getjTab_Bank());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
    }
}
