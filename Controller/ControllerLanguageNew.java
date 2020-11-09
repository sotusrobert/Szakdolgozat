package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceLanguage;
import InvoiceProgram.View.LanguageNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerLanguageNew {

    private final LanguageNew view;
    private final ServiceLanguage service;
    private final String firm;

    public ControllerLanguageNew(String firm) {
        view = new LanguageNew();
        service = new ServiceLanguage();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új Nyelv");
        view.getjTF_Id().setToolTipText("Az azonosító 2 nagybetűs karakter lehet!");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
        view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getjTF_Id().getText();
                String name = view.getjTF_Name().getText();
                boolean active = view.getjCB_IsActive().isSelected();

                if (id.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Az azonosítót és a megnevezést kötelező megadni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Az azonosítót kötelező megadni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezést kötelező megadni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!service.isValidId(id)) {
                        JOptionPane.showMessageDialog(view, "Érvénytlelen azonosító!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else if (service.isAvailableLanguage(id)) {
                        JOptionPane.showMessageDialog(view, "Már létező azonosító!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        service.newLanguage(view, id, name, active);
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

    }
}
