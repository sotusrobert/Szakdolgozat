package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.View.CountryNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerCountryNew {

    private final CountryNew view;
    private final ServiceCountry service;
    private final String firm;

    public ControllerCountryNew(String firm) {
        view = new CountryNew();
        service = new ServiceCountry();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új Ország");
        view.getjTF_ID().setToolTipText("Az azonosító két karakterből állhat (nem tartalmazhat számot, illetve speciális karaktert)!");
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
                if (id.isEmpty() && name.isEmpty() && language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (id.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (id.isEmpty() && language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty() && language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Az azonosító kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A nyelv kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!service.isValidId(id)) {
                        JOptionPane.showMessageDialog(view, "Nem megfelelő az azonosító!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else if (service.isAvailableCountry(id)) {

                        JOptionPane.showMessageDialog(view, "A megadott azonosítóval már létezik ország!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        service.newCountry(view, id, name, language, isactive, EUmember);
                        view.dispose();
                    }
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
