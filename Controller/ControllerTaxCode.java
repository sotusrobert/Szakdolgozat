package InvoiceProgram.Controller;

import InvoiceProgram.Model.Tax;
import InvoiceProgram.Service.ServiceTax;
import InvoiceProgram.View.Tax_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerTaxCode {

    private final Tax_GUI view;
    private final ServiceTax service;
    private ArrayList<TaxListeners> listeners = new ArrayList<>();
    private String firm;

    public ControllerTaxCode(String firm) {
        view = new Tax_GUI();
        service = new ServiceTax();
        this.firm=firm;
        initView();
        initControll();
    }

    public interface TaxListeners {

        public void updateData(ArrayList<Tax> list);
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm+" - ÁFA kulcsok");
        service.getAllTaxCode(view.getjTabl_TaxCodes());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxCodeNew controller = new ControllerTaxCodeNew(firm);

            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTabl_TaxCodes().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTabl_TaxCodes().getSelectedRow();
                    String value = view.getjTabl_TaxCodes().getValueAt(row, 0).toString();
                    ControllerTaxEdit controll = new ControllerTaxEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((TaxListeners) controll);
                    listeners.get(0).updateData(service.getOneTax(value));
                    service.getAllTaxCode(view.getjTabl_TaxCodes());
                }

            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTabl_TaxCodes().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTabl_TaxCodes().getSelectedRow();
                        String value = view.getjTabl_TaxCodes().getValueAt(row, 0).toString();
                        service.deleteTax(view, value);
                    }

                    service.getAllTaxCode(view.getjTabl_TaxCodes());
                }
            }
        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }
}
