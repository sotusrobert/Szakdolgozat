package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerPublicPlace.PublicPlaceListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.View.PublicPlaceEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class ControllerPublicPlaceEdit implements PublicPlaceListeners {

    private final PublicPlaceEdit view;
    private final ServicePublicPlace service;

    public ControllerPublicPlaceEdit() {
        view = new PublicPlaceEdit();
        service = new ServicePublicPlace();
        initView();
        initControll();
    }

    public final void initView() {

        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
        ServiceCountry serviceCountry = new ServiceCountry();
        loadCountriesToJComboBox(serviceCountry.getAllCountry());
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getjLab_Id().getText();
                String name = view.getjTF_name().getText();
                String languageId = view.getjCB_Countries().getSelectedItem() + "";
                service.updatePublicPlace(view, id, name, languageId);
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
    public void updateData(ArrayList<PublicPlace> list) {
        view.getjTF_name().setText(list.get(0).getName());
        view.getjLab_Id().setText(String.format("%d", list.get(0).getId()));
        view.getjCB_Countries().setSelectedItem(list.get(0).getCountryId());
    }

    @Override
    public void sendCountry(String id) {
        ServiceCountry sc = new ServiceCountry();
        ArrayList<Country> list = new ArrayList<>();
        list.addAll(sc.getAllCountry());
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

        model.setSelectedItem(id);
    }
}
