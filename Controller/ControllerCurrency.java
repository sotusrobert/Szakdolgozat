package InvoiceProgram.Controller;

import InvoiceProgram.Model.Currency;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.View.Currency_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public final class ControllerCurrency {

    private final Currency_GUI view;
    private final ServiceCurrency service;
    private ArrayList<CurrencyListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerCurrency(String firm) {
        view = new Currency_GUI();
        service = new ServiceCurrency();
        this.firm=firm;
        initView();
        initControll();
    }

    public interface CurrencyListeners {

        public void updateData(ArrayList<Currency> list);
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Pénznemek");
        service.getAllCurrency(view.getjTab_Currencies());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjB_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCurrencyNew controller = new ControllerCurrencyNew(firm);

            }
        });
        view.getjB_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Currencies().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Currencies().getSelectedRow();
                    String value = view.getjTab_Currencies().getValueAt(row, 0).toString();

                    ControllerCurrencyEdit controll = new ControllerCurrencyEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((CurrencyListeners) controll);
                    listeners.get(0).updateData(service.getOneCurrency(value));

                }
            }
        });
        view.getjB_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Currencies().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Currencies().getSelectedRow();
                        String value = view.getjTab_Currencies().getValueAt(row, 0).toString();
                        service.deleteCurrency(view, value);
                    }

                    service.getAllCurrency(view.getjTab_Currencies());
                }
            }
        });
        view.getjB_EXIT().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

    }
}
