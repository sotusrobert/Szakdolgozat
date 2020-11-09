
package InvoiceProgram.Controller;

import InvoiceProgram.Model.InvoiceAddress;
import InvoiceProgram.Service.ServiceInvoice;
import InvoiceProgram.View.FinalizedInvoice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerFinalizedInvoice {
    private final FinalizedInvoice view;
    private final ServiceInvoice service;
    private ArrayList<InvoiceAddressListeners> listeners = new ArrayList<>();
    private String firm;
    
    public ControllerFinalizedInvoice(String firm) {
        view= new FinalizedInvoice();
        service = new ServiceInvoice();
        this.firm=firm;
        initView();
        initControll();
    }
    
    public void initView() {
        view.getjLab_Firm().setText(firm+" - Véglegesített számlák");
        service.getAllInvoice(view.getjTab_FinalizedInvoices());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }
    
    public interface InvoiceAddressListeners {

        public void updateData(ArrayList<InvoiceAddress> list);
    }
    
    public void initControll() {
        view.getjBut_New().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInvoiceFileSelect controller= new ControllerInvoiceFileSelect(firm);
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_FinalizedInvoices().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_FinalizedInvoices().getSelectedRow();
                    int value = Integer.parseInt(view.getjTab_FinalizedInvoices().getValueAt(row, 0).toString());
                    ControllerInvoiceEdit controll = new ControllerInvoiceEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((InvoiceAddressListeners) controll);
                    ArrayList<InvoiceAddress> list= new ArrayList<>();
                    list.add(service.getOneInvoiceAddress(view, value));
                    listeners.get(0).updateData(list);
                }
                
            }
        });
        view.getjBut_Invalidate().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_FinalizedInvoices().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban érvényteleníteni akarja a számlát?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTab_FinalizedInvoices().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_FinalizedInvoices().getValueAt(row, 0).toString());
                        //service.deleteBank(view, value);
                    }

                    //service.getAllBank(view.getjTab_FinalizedInvoices());
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
