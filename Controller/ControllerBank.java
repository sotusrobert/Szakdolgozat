package InvoiceProgram.Controller;

import InvoiceProgram.Model.Bank;
import InvoiceProgram.Service.ServiceBank;
import InvoiceProgram.View.Bank_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerBank {

    private final Bank_GUI view;
    private final ServiceBank service;
    private final ArrayList<BankListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerBank(String firm) {
        view = new Bank_GUI();
        service = new ServiceBank();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Bankok");
        service.getAllBank(view.getjTab_Bank());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }
    public interface BankListeners {

        public void updateData(ArrayList<Bank> list);
    }
    public void initControll() {
        view.getjBut_New().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerBankNew controller = new ControllerBankNew(firm);
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Bank().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Bank().getSelectedRow();
                    int value = Integer.parseInt(view.getjTab_Bank().getValueAt(row, 0).toString());
                    ControllerBankEdit controll = new ControllerBankEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((BankListeners) controll);
                    listeners.get(0).updateData(service.getOneBank(view, value));
                }
                
            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Bank().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Bank().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_Bank().getValueAt(row, 0).toString());
                        service.deleteBank(view, value);
                    }

                    service.getAllBank(view.getjTab_Bank());
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
