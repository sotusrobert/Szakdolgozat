package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.View.CountryNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public class ControllerCountryNew {

    private final CountryNew view;
    private final ServiceCountry service;

    public ControllerCountryNew() {
        view = new CountryNew();
        service = new ServiceCountry();
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
                String id = view.getjTF_ID().getText();
                String name = view.getjTF_name().getText();
                String language = view.getjTF_language().getText();
                boolean isactive = view.getjCB_active().isSelected();
                boolean EUmember = view.getjCB_EUmember().isSelected();
                service.newCountry(view, id, name, language, isactive, EUmember);
                view.dispose();
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
