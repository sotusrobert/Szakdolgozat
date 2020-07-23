package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceLanguage;
import InvoiceProgram.View.LanguageNew;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;



public class ControllerLanguageNew {
    private final LanguageNew view;
    private final ServiceLanguage service;

    public ControllerLanguageNew() {
        view = new LanguageNew();
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
          view.getjB_Save().addActionListener(new ActionListener(){
              @Override
              public void actionPerformed(ActionEvent e) {
                 String id= view.getjTF_Id().getText();
                 String name=view.getjTF_Name().getText();
                 boolean active=view.getjCB_IsActive().isSelected();
                 service.newLanguage(view, id, name, active);
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
}
