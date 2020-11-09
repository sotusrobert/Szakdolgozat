
package InvoiceProgram.Controller;

import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Service.ServiceBank;
import InvoiceProgram.Service.ServiceBankAccount;
import InvoiceProgram.View.BankAccount_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerBankAccount {
    private final BankAccount_GUI view;
    private final ServiceBankAccount service;
    private ArrayList<BankAccountListeners> listeners = new ArrayList<>();
    private final String firm;
    private ServiceBank sb= new ServiceBank();

    public ControllerBankAccount(String firm) {
        view = new BankAccount_GUI();
        service = new ServiceBankAccount();
        this.firm=firm;
        initView();
        initControll();
    }
    public interface BankAccountListeners {

        public void updateData(ArrayList<BankAccount> list);
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Bankszámlák");
        if (sb.isBankAvailable()) {
            service.getAllBankAccount(view.getjTab_BankAccount());
        }
        
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }
    public void initControll(){
    view.getjBut_New().addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (sb.isBankAvailable()) {
                ControllerBanAccountNew controller = new ControllerBanAccountNew(firm);
            }else{
            JOptionPane.showMessageDialog(view, "Hozzon létre egy bankot az új számlaszám megadásához!", "Figyelem", JOptionPane.ERROR_MESSAGE);
            }
             
        }
    });
    view.getjBut_Edit().addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
                if (view.getjTab_BankAccount().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_BankAccount().getSelectedRow();
                    int value = Integer.parseInt(view.getjTab_BankAccount().getValueAt(row, 0).toString());
                    ControllerBankAccountEdit controll = new ControllerBankAccountEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((BankAccountListeners) controll);
                    listeners.get(0).updateData(service.getOneBankAccount(view, value));
                }
        }
    });
    view.getjBut_Delete().addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getjTab_BankAccount().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban törölni akarja a rekordot?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTab_BankAccount().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_BankAccount().getValueAt(row, 0).toString());
                        service.deleteBankAccount(view, value);
                    }

                    service.getAllBankAccount(view.getjTab_BankAccount());
                }
        }
    });
    view.getjBut_Exit().addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    });
    
    }
}
