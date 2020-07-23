package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerTaxCode.TaxListeners;
import InvoiceProgram.Model.Tax;
import InvoiceProgram.Service.ServiceTax;
import InvoiceProgram.View.TaxEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class ControllerTaxEdit implements TaxListeners {

    private final TaxEdit view;
    private final ServiceTax service;

    public ControllerTaxEdit() {
        view = new TaxEdit();
        service = new ServiceTax();
        initView();
        initControll();
    }

    public final void initView() {

        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Id = Integer.parseInt(view.getjLab_Id().getText());
                String name = view.getjTF_name().getText();
                String shortName = view.getjTF_shortName().getText();
                int rate = Integer.parseInt(view.getjTF_rate().getText());
                Date dvf = new Date(view.getjDC_validFrom().getDate().getTime());
                LocalDate vF = dvf.toLocalDate();
                java.sql.Date validFrom = (java.sql.Date.valueOf(vF));
                Date dvu = new Date(view.getjDC_validUntil().getDate().getTime());
                LocalDate vU = dvu.toLocalDate();
                java.sql.Date validUntil = (java.sql.Date.valueOf(vU));
                service.updateLanguage(view, Id, name, shortName, rate, validFrom, validUntil);
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

    @Override
    public void updateData(ArrayList<Tax> list) {
        for (Tax item : list) {
            view.getjLab_Id().setText(String.format("%d", list.get(0).getId()));
            view.getjTF_name().setText(list.get(0).getName());
            view.getjTF_shortName().setText(list.get(0).getShortName());
            view.getjTF_rate().setText(String.format("%d", list.get(0).getRate()));
            view.getjDC_validFrom().setDate(list.get(0).getValidFrom());
            view.getjDC_validUntil().setDate(list.get(0).getValidUntil());

        }
    }
}
