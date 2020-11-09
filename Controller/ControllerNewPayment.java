package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServicePayment;
import InvoiceProgram.View.PaymentNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerNewPayment {

    private final PaymentNew view;
    private final ServicePayment service;
    //private ArrayList<CurrencyListeners> listeners = new ArrayList<>();
    private ArrayList<PaymentEditListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerNewPayment(String firm) {
        view = new PaymentNew();
        service = new ServicePayment();
        this.firm = firm;
        initView();
        initControll();
    }

    public interface PaymentEditListeners {

        public void updateData(boolean refreash);
    }

    public void initView() {
        view.getjLab_Firm().setText(firm + " - Új Fizetési Mód");
        view.getjTF_prompt().setToolTipText("Csak egész számokból állhat!");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }

        });
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        service.newPayment(view, name, prompt, mode, isDefault);
                        view.dispose();
                        listeners.removeAll(listeners);
                        listeners.add((PaymentEditListeners) e);
                        listeners.get(0).updateData(true);

                    }
                }

            }

        });

    }

}
