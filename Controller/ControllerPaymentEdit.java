package InvoiceProgram.Controller;

import InvoiceProgram.Model.Payment;
import InvoiceProgram.Service.ServicePayment;
import InvoiceProgram.View.PaymentEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerPaymentEdit implements ControllerPayment.PaymentListeners {

    private final PaymentEdit view;
    private final ServicePayment service;
    //private ArrayList<CurrencyListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerPaymentEdit(String firm) {
        view = new PaymentEdit();
        service = new ServicePayment();
        this.firm = firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm + " - Fizetési Mód módosítás");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(view.getjLa_id().getText());
                String name = view.getjTF_name().getText();
                String prmt = view.getjTF_prompt().getText();

                String mode = view.getjTF_mode().getText();
                boolean isDefault = view.getjCB_isDefault().isSelected();

                if (name.isEmpty() && prmt.isEmpty() && mode.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A '*'-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty() && prmt.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A '*'-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty() && mode.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A '*'-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (prmt.isEmpty() && mode.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A '*'-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (prmt.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A fizetési határidő kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (mode.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A pénzmozgás módjának megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!service.isValidPrompt(prmt)) {
                        JOptionPane.showMessageDialog(view, "Érvénytelen fizetési határidő! (Csak egész számokból állhat)", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int prompt = Integer.parseInt(view.getjTF_prompt().getText());
                        service.updatePayment(view, name, prompt, mode, isDefault, id);
                        view.dispose();

                    }
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

    @Override
    public void updateData(ArrayList<Payment> list) {
        view.getjTF_name().setText(list.get(0).getName());
        view.getjTF_prompt().setText(String.format("%d", list.get(0).getPrompt()));
        view.getjTF_mode().setText(list.get(0).getMode());
        view.getjLa_id().setText(String.format("%d", list.get(0).getId()));
        view.getjCB_isDefault().setSelected(list.get(0).isIsDefault());
    }
}
