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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class ControllerZipEdit implements ZipsListeners {

    private final ZipcodeEdit view;
    private final ServiceZips service;

    public ControllerZipEdit() {
        view = new ZipcodeEdit();
        service = new ServiceZips();
        initView();
        initControll();
    }

    public void initView() {
        ServiceCountry serviceCountry = new ServiceCountry();
        loadCountriesToJComboBox(serviceCountry.getAllCountry());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String id=view.getjLab_id().getText();
            String code=view.getjTF_code().getText();
            String name=view.getjTF_name().getText();
            String countryId=view.getjCB_countryid().getSelectedItem().toString();
            service.updateZip(view,countryId, code, name, id);
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
