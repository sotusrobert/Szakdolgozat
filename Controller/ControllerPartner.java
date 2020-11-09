package InvoiceProgram.Controller;

import InvoiceProgram.Model.Partner;
import InvoiceProgram.Service.ServicePartner;
import InvoiceProgram.View.Partner_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerPartner {

    private final Partner_GUI view;
    private final ServicePartner service;
    private ArrayList<PartnerListeners> listeners = new ArrayList<>();
    private final String firm;
    
    public ControllerPartner(String firm) {
        view = new Partner_GUI();
        service = new ServicePartner();
        this.firm=firm;
        initView();
        initControll();
    }

    public interface PartnerListeners {

        public void updateData(ArrayList<Partner> list);
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Partnerek");
        service.getAllPartner(view.getjTab_Partner());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPartnerNew controll = new ControllerPartnerNew(firm);

            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Partner().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Partner().getSelectedRow();
                    String value = view.getjTab_Partner().getValueAt(row, 0).toString();
                    int id = Integer.parseInt(value);

                    ControllerPartnerEdit controll = new ControllerPartnerEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((PartnerListeners) controll);
                    listeners.get(0).updateData(service.getOnePartner(id));

                }

            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Partner().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Partner().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_Partner().getValueAt(row, 0).toString());

                        service.deletePartner(view, value);
                    }

                    service.getAllPartner(view.getjTab_Partner());
                }
            }
        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

}
