package InvoiceProgram.Controller;

import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.TaxYear_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerTaxYear {

    private final TaxYear_GUI view;
    private final ServiceTaxYear service;
    private ArrayList<TaxYearListeners> listeners = new ArrayList<>();

    public ControllerTaxYear() {
        view = new TaxYear_GUI();
        service = new ServiceTaxYear();
        initView();
        initControll();
    }

    public void initView() {
        service.getAllTaxYear(view.getjTab_TaxYear());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }
     public interface TaxYearListeners {

        public void updateData(ArrayList<TaxYear> list);
    }

    public void initControll() {
        view.getjBut_New().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxYearNew controller = new ControllerTaxYearNew();
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if (view.getjTab_TaxYear().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_TaxYear().getSelectedRow();
                    int value = Integer.parseInt(view.getjTab_TaxYear().getValueAt(row, 0).toString());
                    ControllerTaxYearEdit controll = new ControllerTaxYearEdit();
                    listeners.removeAll(listeners);
                    listeners.add((TaxYearListeners) controll);
                    listeners.get(0).updateData(service.getOneTaxYear(view, value));
                }
                
            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_TaxYear().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_TaxYear().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_TaxYear().getValueAt(row, 0).toString());
                        service.deleteTaxYear(view, value);
                    }

                    service.getAllTaxYear(view.getjTab_TaxYear());
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
