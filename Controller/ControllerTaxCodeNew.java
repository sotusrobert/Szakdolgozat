package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceTax;
import InvoiceProgram.View.TaxNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerTaxCodeNew {

    private final TaxNew view;
    private final ServiceTax service;
    private String firm;

    public ControllerTaxCodeNew(String firm) {
        view = new TaxNew();
        service = new ServiceTax();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új ÁFA kulcs");
        view.getjTF_rate().setToolTipText("Az áfakulcs csak kerek egész szám lehet!");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getjTF_name().getText();
                String shortName = view.getjTF_shortName().getText();
                String rate = view.getjTF_rate().getText();

                if (name.isEmpty() && shortName.isEmpty() && rate.isEmpty() && view.getjDC_validFrom().getDate()== null && view.getjDC_validUntil().getDate() == null) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (!service.isValidRate(rate)) {
                    JOptionPane.showMessageDialog(view, "A megadott áfakulcs érvénytelen!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    Date dvf = new Date(view.getjDC_validFrom().getDate().getTime());
                    LocalDate vF = dvf.toLocalDate();
                    Date validFrom = (java.sql.Date.valueOf(vF));
                    Date dvu = new Date(view.getjDC_validUntil().getDate().getTime());
                    LocalDate vU = dvu.toLocalDate();
                    Date validUntil = (java.sql.Date.valueOf(vU));
                    service.newTax(view, name, shortName, Integer.parseInt(rate), validFrom, validUntil);
                    view.dispose();

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
