package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerZips.ZipsListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Zipcode;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.ZipcodeEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControllerZipEdit implements ZipsListeners {

    private final ZipcodeEdit view;
    private final ServiceZips service;
    private final String firm;

    public ControllerZipEdit(String firm) {
        view = new ZipcodeEdit();
        service = new ServiceZips();
        this.firm = firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm + " - Irányítószám módosítás");
        ServiceCountry serviceCountry = new ServiceCountry();
        loadCountriesToJComboBox(serviceCountry.getAllActiveCountry());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryid = view.getjLab_id().getText();
                String code = view.getjTF_code().getText();
                String name = view.getjTF_name().getText();
                String countryId = view.getjCB_countryid().getSelectedItem().toString();

                if (countryid.isEmpty() && code.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (countryId.isEmpty() && code.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (countryId.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (code.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (countryId.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (code.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!service.isValidZip(code)) {
                        JOptionPane.showMessageDialog(view, "Nem megfelelő irányítószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        service.updateZip(view, countryId, Integer.parseInt(code), name, countryid);
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
        
        view.getjTF_code().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_code().getText();
                String countryId = view.getjCB_countryid().getSelectedItem().toString();
                
                if (service.isValidZip(zipCode)) {
                    String name = service.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_name().setText(name);
                }
            }
        });
    }

    public void loadCountriesToJComboBox(ArrayList<Country> list) {
        String[] labels = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            labels[i] = list.get(i).getId();
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_countryid().getModel();
        model.removeAllElements();
        view.revalidate();
        for (String label : labels) {
            model.addElement(label);
        }
        model.setSelectedItem("HU");
    }

    @Override
    public void updateData(ArrayList<Zipcode> list) {
        view.getjCB_countryid().setSelectedItem(list.get(0).getCountryId());
        view.getjTF_code().setText(list.get(0).getCode());
        view.getjTF_name().setText(list.get(0).getName());
        view.getjLab_id().setText(String.format("%d", list.get(0).getId()));
    }
}
