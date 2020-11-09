package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerPublicPlace.PublicPlaceListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.View.PublicPlaceNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControllerPublicPlaceNew implements PublicPlaceListeners {

    private final PublicPlaceNew view;
    private final ServicePublicPlace service;
    private final String firm;

    public ControllerPublicPlaceNew(String firm) {
        view = new PublicPlaceNew();
        service = new ServicePublicPlace();
        this.firm=firm;
        initView();
        initControll();
    }

    private void initView() {
        view.getjLab_Firm().setText(firm+" - Új Közterület jelleg");
        ServiceCountry serviceCountry = new ServiceCountry();
        loadCountriesToJComboBox(serviceCountry.getAllActiveCountry());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    private void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryId = view.getjCB_Countries().getSelectedItem().toString();
                String name = view.getjTF_name().getText();
                if (name.isEmpty()) {
                     JOptionPane.showMessageDialog(view, "A megnevezés megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }else{
                        service.newPublicPlace(view, countryId, name);
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

    @Override
    public void updateData(ArrayList<PublicPlace> list) {

    }

    public void loadCountriesToJComboBox(ArrayList<Country> list) {
        String[] labels = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            labels[i] = list.get(i).getId();
        }

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_Countries().getModel();
        model.removeAllElements();
        view.revalidate();
        for (String label : labels) {
            model.addElement(label);
        }

        model.setSelectedItem("HU");
    }

    @Override
    public void sendCountry(String id) {
        
    }
}
