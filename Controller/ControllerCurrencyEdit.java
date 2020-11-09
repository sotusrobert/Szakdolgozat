package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerCurrency.CurrencyListeners;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.View.CurrencyEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerCurrencyEdit implements CurrencyListeners {

    private final CurrencyEdit view;
    private final ServiceCurrency service;
    private final String firm;

    public ControllerCurrencyEdit(String firm) {
        view = new CurrencyEdit();
        service = new ServiceCurrency();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Pénznem módosítás");
        view.getjTF_id().setEditable(false);
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getjTF_id().getText();
                String name = view.getjTF_name().getText();
                boolean isactive = view.getjCB_Active().isSelected();

                if (!name.isEmpty()) {
                    service.updateLanguage(view, id, name, isactive);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "A megnevezés megadása kötelező", "Figyelem", JOptionPane.ERROR_MESSAGE);
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

    @Override
    public void updateData(ArrayList<Currency> list) {
        view.getjTF_id().setText(list.get(0).getId());
        view.getjTF_name().setText(list.get(0).getName());
        if (list.get(0).isIsActive()) {
            view.getjCB_Active().setSelected(true);
        } else {
            view.getjCB_Active().setSelected(false);
        }
    }
}
