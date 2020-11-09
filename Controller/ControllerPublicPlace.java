package InvoiceProgram.Controller;

import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.View.PublicPlace_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerPublicPlace {

    private final PublicPlace_GUI view;
    private final ServicePublicPlace service;
    private ArrayList<PublicPlaceListeners> listeners = new ArrayList<>();
    private final String firm;
    
    public ControllerPublicPlace(String firm) {
        view = new PublicPlace_GUI();
        service = new ServicePublicPlace();
        this.firm=firm;
        initView();
        initControll();
    }

    public interface PublicPlaceListeners {

        public void updateData(ArrayList<PublicPlace> list);

        public void sendCountry(String id);

    }

    private void initView() {
        view.getjLab_Firm().setText(firm+" - Közterület jellegek");
        service.getAllPublicPlace(view.getjT_PublicPlace());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    private void initControll() {
        view.getjB_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPublicPlaceNew controller = new ControllerPublicPlaceNew(firm);
                ServiceCountry serviceCountry = new ServiceCountry();
                listeners.removeAll(listeners);

            }
        });
        view.getjB_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjT_PublicPlace().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjT_PublicPlace().getSelectedRow();
                    String value = view.getjT_PublicPlace().getValueAt(row, 0).toString();

                    ControllerPublicPlaceEdit controll = new ControllerPublicPlaceEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add(controll);
                    listeners.get(0).updateData(service.getOnePublicPlace(value));
 
                    

                }
                service.getAllPublicPlace(view.getjT_PublicPlace());
            }
        });
        view.getjB_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjT_PublicPlace().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjT_PublicPlace().getSelectedRow();
                        String value = view.getjT_PublicPlace().getValueAt(row, 0).toString();
                        service.deletePublicPlace(view, value);

                    }
                }
            }
        }
        );
        view.getjB_EXIT()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        view.dispose();
                    }
                }
                );
    }
}
