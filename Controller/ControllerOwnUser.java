
package InvoiceProgram.Controller;

import InvoiceProgram.Model.User;
import InvoiceProgram.Service.ServiceUser;
import InvoiceProgram.View.OwnUser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerOwnUser {
    
    private int id;
    private final OwnUser view;
    private final ServiceUser service;
    private final String firm;

    public ControllerOwnUser(String firm, int id) {
        view = new OwnUser();
        service = new ServiceUser();
        this.firm=firm;
        this.id= id;
        initView();
        initControll();

    }

    public final void initView() {
        view.getjLab_Firm().setText(firm+" - Saját profil");
        User ownUser= service.getOneUser(id);
        view.getjTF_username().setText(ownUser.getUsername());
        view.getjTF_username().setEditable(false);
        view.getjTF_permission().setText(ownUser.getPermission());
        view.getjTF_permission().setEditable(false);
        view.getjCB_isActive().setSelected(ownUser.getIsActive());
        view.getjCB_isActive().setEnabled(false);
        view.getjB_Save().setEnabled(false);
        view.getjPF_newPassword().setToolTipText("(Tartalmazzon: min. 1 számot, 1-1 kis- és nagybetűt, 1 spec.kar: @#$%^&+=, szóköz mentes és legalább 8 karakter.)");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }
     public final void initControll() {
         view.getjB_EXIT().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
         
         view.getjB_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username= view.getjTF_username().getText();
                String permission= view.getjTF_permission().getText();
                Boolean isActive= view.getjCB_isActive().isSelected();
                String password= view.getjPF_newPassword().getText();
                
                try {
                    service.updateUser(view, username, password, permission, isActive, id);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerOwnUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                view.dispose();
            }
        });
         
         view.getjCB_isValiid().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_isValiid().isSelected()) {
                    String passwordOrigin = view.getjPF_originalPassword().getText();
                    String passwordNew = view.getjPF_originalPassword().getText();
                   
                    try {
                        if (!service.checkPassword(id, passwordOrigin)) {
                            JOptionPane.showMessageDialog(view, "Az eredeti jelszó nem egyezik meg! ", "Figyelem", JOptionPane.ERROR_MESSAGE);
                           
                        }
                        else if (!service.isValidPassword(passwordNew)) {
                        JOptionPane.showMessageDialog(view, "A megadott új jelszó helytelen! ", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        
                        
                    }
                        else{
                        view.getjB_Save().setEnabled(true);
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ControllerOwnUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    
                    
                } else {
                   
                    
                }
            }
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     

    
}
