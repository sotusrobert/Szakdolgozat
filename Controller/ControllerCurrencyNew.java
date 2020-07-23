package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.View.CurrencyNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerCurrencyNew {

    private final CurrencyNew view;
    private final ServiceCurrency service;

    public ControllerCurrencyNew() {
        view = new CurrencyNew();
        service = new ServiceCurrency();
        initView();
        initControll();
    }

    public final void initView() {

        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getjTF_id().getText();
                String name = view.getjTF_name().getText();
                boolean isactive = view.getjCB_Active().isSelected();
                if (id.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Az azonosító és a megnevezés kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Az azonosító kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    service.newCurrency(view, id, name, isactive);
                    view.dispose();
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