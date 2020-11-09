package InvoiceProgram.Controller;

import InvoiceProgram.Model.Zipcode;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.Zipcode_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerZips {

    private final Zipcode_GUI view;
    private final ServiceZips service;
    private ArrayList<ZipsListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerZips(String firm) {
        view = new Zipcode_GUI();
        service = new ServiceZips();
        this.firm=firm;
        initView();
        initControll();
    }

    public interface ZipsListeners {

        public void updateData(ArrayList<Zipcode> list);
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Irányítószámok");
        service.getAllZip(view.getjTab_Zip());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjB_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerZipNew controller = new ControllerZipNew(firm);
            }
        });
        view.getjB_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Zip().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {

                    int row = view.getjTab_Zip().getSelectedRow();
                    String value = view.getjTab_Zip().getValueAt(row, 0).toString();
                    ControllerZipEdit controll = new ControllerZipEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((ZipsListeners) controll);
                    listeners.get(0).updateData(service.getOneZipcode(value));

                }
            }
        });
        view.getjB_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Zip().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Zip().getSelectedRow();
                        String value = view.getjTab_Zip().getValueAt(row, 0).toString();
                        service.deleteZip(view, value);
                        service.getAllZip(view.getjTab_Zip());
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
