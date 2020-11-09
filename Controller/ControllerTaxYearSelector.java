package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceInvoice;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.Invoice_GUI;
import InvoiceProgram.View.TaxYearSelector;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerTaxYearSelector {

    private final TaxYearSelector view;
    private final ServiceTaxYear service;
    // private ArrayList<BankListeners> listeners = new ArrayList<>();

    public ControllerTaxYearSelector() {
        view = new TaxYearSelector();
        service = new ServiceTaxYear();
        initView();
        initControll();
    }

    public void initView() {
        service.getAllTaxYearSelector(view.getjTab_TaxYear());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Ok().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 if (view.getjTab_TaxYear().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_TaxYear().getSelectedRow();
                    String value = view.getjTab_TaxYear().getValueAt(row, 0).toString();

                    view.dispose();
                    //listeners.removeAll(listeners);
                    //listeners.add((CurrencyListeners) controll);
                    //listeners.get(0).updateData(service.getOneCurrency(value));

                }
            }
        });

    }
}
