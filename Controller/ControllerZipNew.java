package InvoiceProgram.Controller;

import InvoiceProgram.Model.Country;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.ZipcodeNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControllerZipNew {

    private final ZipcodeNew view;
    private final ServiceZips service;

    public ControllerZipNew() {
        view = new ZipcodeNew();
        service = new ServiceZips();
        initView();
        initControll();
    }

    public void initView() {
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        ServiceCountry serviceCountry = new ServiceCountry();
        loadCountriesToJComboBox(serviceCountry.getAllCountry());

        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryid = view.getjCB_countryId().getSelectedItem().toString();
                String code = view.getjTF_code().getText();
                String name = view.getjTF_name().getText();
                if (countryid.isEmpty() && code.isEmpty() && name.isEmpty()  ) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        } else if (countryid.isEmpty() && code.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        } else if (countryid.isEmpty()&& name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        }  else if (code.isEmpty() && name.isEmpty() ) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        }  else if (countryid.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        }  else if (code.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        }  else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
        }  else {
                service.newZip(view, countryid, code, name);
        }
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

    private void loadCountriesToJComboBox(ArrayList<Country> list) {
        String[] labels = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            labels[i] = list.get(i).getId();
        }

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_countryId().getModel();
        model.removeAllElements();
        view.revalidate();
        for (String label : labels) {
            model.addElement(label);
        }

        model.setSelectedItem("HU");
    }
}
