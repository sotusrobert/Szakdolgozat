/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerCountry.CountryListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.View.CountryEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ControllerCountryEdit implements CountryListeners {

    private final CountryEdit view;
    private final ServiceCountry service;
    private final String firm;

    public ControllerCountryEdit(String firm) {
        view = new CountryEdit();
        service = new ServiceCountry();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Ország módosítás");
        view.setLocationRelativeTo(null);
        view.getjTF_ID().setEnabled(false);
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
                boolean isEU = view.getjCB_EUmember().isSelected();
                if (name.isEmpty() && language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden aktív mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (language.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A nyelv kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {

                    service.updateCountry(view, id, name, language, isactive, isEU);
                    view.dispose();
                    service.setRefreshData(true);

                }

            }
        });
        view.getjB_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    @Override
    public void updateData(ArrayList<Country> list) {
        for (Country item : list) {
            view.getjTF_ID().setText(item.getId());
            view.getjTF_name().setText(item.getName());
            view.getjTF_language().setText(item.getLanguages());
            if (item.isIsactive()) {
                view.getjCB_active().setSelected(true);
            } else {
                view.getjCB_active().setSelected(false);
            }
            if (item.isIsEUmembership()) {
                view.getjCB_EUmember().setSelected(true);
            } else {
                view.getjCB_EUmember().setSelected(false);
            }

        }

    }

}
