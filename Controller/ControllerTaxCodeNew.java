package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceTax;
import InvoiceProgram.View.TaxNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;

public class ControllerTaxCodeNew {

    private final TaxNew view;
    private final ServiceTax service;

    public ControllerTaxCodeNew() {
        view = new TaxNew();
        service = new ServiceTax();
        initView();
        initControll();
    }

    public final void initView() {

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
                int rate = Integer.parseInt(view.getjTF_rate().getText());
                //Format formatter = new SimpleDateFormat("yyyy-[m]m-[d]d");
                Date dvf = new Date(view.getjDC_validFrom().getDate().getTime());
                LocalDate vF = dvf.toLocalDate();
                Date validFrom = (java.sql.Date.valueOf(vF));
                Date dvu = new Date(view.getjDC_validUntil().getDate().getTime());
                LocalDate vU = dvu.toLocalDate();
                Date validUntil = (java.sql.Date.valueOf(vU));
                service.newTax(view, name, shortName, rate, validFrom, validUntil);
                view.dispose();
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
