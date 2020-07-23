package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerLanguage.LanguageListeners;
import InvoiceProgram.Model.Language;
import InvoiceProgram.Service.ServiceLanguage;
import InvoiceProgram.View.LanguageEdit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class ControllerLanguageEdit implements LanguageListeners {

    private final LanguageEdit view;
    private final ServiceLanguage service;

    public ControllerLanguageEdit() {
        view = new LanguageEdit();
        service = new ServiceLanguage();
        initView();
        initControll();
    }

    public final void initView() {

        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
           view.getjB_Update().addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                 String id= view.getjTF_Id().getText();
                 String name=view.getjTF_Name().getText();
                 boolean active=view.getjCB_IsActive().isSelected();
                 service.updateLanguage(view, id, name, active);
                 view.dispose();
               }
           });
            view.getjB_EXIT().addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                   view.dispose();
               }
           });
    }

    @Override
    public void updateData(ArrayList<Language> list) {
        for (Language item : list) {
            view.getjTF_Id().setText(item.getId());
            view.getjTF_Name().setText(item.getNyelv());
            if (item.isActive()) {
                view.getjCB_IsActive().setSelected(true);
            } else {
                view.getjCB_IsActive().setSelected(false);
            }

        }

    }

}
