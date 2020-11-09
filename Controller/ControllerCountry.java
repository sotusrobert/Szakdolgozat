package InvoiceProgram.Controller;

import InvoiceProgram.Model.Country;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.View.Country_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerCountry {

    private final Country_GUI view;
    private final ServiceCountry service;
    private ArrayList<CountryListeners> listeners = new ArrayList<>();
    private final String firm;
    
    public ControllerCountry(String firm) {
        view = new Country_GUI();
        service = new ServiceCountry();
        this.firm=firm;
        initView();
        initControll();
        refreshData();

    }

    public final void initView() {
        view.getjLab_Firm().setText(firm+" - Országok");
        service.getAllCountry(view.getjTabl_Country());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public interface CountryListeners {

        public void updateData(ArrayList<Country> list);
    }

    private void initControll() {

        view.getjB_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCountryNew controller = new ControllerCountryNew(firm);
                service.getAllCountry(view.getjTabl_Country());
            }
        });
        view.getjB_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTabl_Country().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTabl_Country().getSelectedRow();
                    String value = view.getjTabl_Country().getValueAt(row, 0).toString();

                    ControllerCountryEdit controll = new ControllerCountryEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((CountryListeners) controll);
                    listeners.get(0).updateData(service.getOneCountry(value));
                    for (CountryListeners item : listeners) {
                        // System.out.println(item.toString());
                    }

                }

                //service.getAllCountry(view.getjTabl_Country());
                refreshData();
            }

        });
        view.getjB_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTabl_Country().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban törölni akarja a rekordot?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTabl_Country().getSelectedRow();
                        String value = view.getjTabl_Country().getValueAt(row, 0).toString();
                        service.deleteCountry(view, value);
                    }

                    service.getAllCountry(view.getjTabl_Country());
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

    private void refreshData() {
        if (service.isRefreshData()) {
            System.out.println(service.isRefreshData());
            service.getAllCountry(view.getjTabl_Country());
            System.out.println(service.getCountries());
            service.setRefreshData(false);
        }
    }
}
