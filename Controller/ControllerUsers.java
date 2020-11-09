package InvoiceProgram.Controller;

import InvoiceProgram.Model.User;
import InvoiceProgram.Service.ServiceUser;
import InvoiceProgram.View.Users;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerUsers {

    private final Users view;
    private final ServiceUser service;
    private final String firm;

    public ControllerUsers(String firm) {
        view = new Users();
        service = new ServiceUser();
        this.firm=firm;
        initView();
        initControll();

    }

    public final void initView() {
        view.getjLab_Firm().setText(firm+" - Felhasználók");
        service.getAllUser(view.getjTab_Users());
        view.getjBut_Edit().setEnabled(false);
        view.getjPF_password().setToolTipText("(Tartalmazzon: min. 1 számot, 1-1 kis- és nagybetűt, 1 spec.kar: @#$%^&+=, szóköz mentes és legalább 8 karakter.)");
        view.getjTF_username().setToolTipText("Min. 6 Max. 29 karakter, kis- és nagybetűket tartalmazzon!");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public final void initControll() {
        view.getjCheckBox_isValidate().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCheckBox_isValidate().isSelected()) {
                    String name = view.getjTF_username().getText();
                    String password = view.getjPF_password().getText();
                    if (!service.checkUserName(name)) {
                            JOptionPane.showMessageDialog(view, "A megadott felhasználónév már létezik! ", "Figyelem", JOptionPane.ERROR_MESSAGE);
                           
                        }
                    
                    if (!service.isValidUsername(name)) {
                        JOptionPane.showMessageDialog(view, "A megadott felhasználónév helytelen! ", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        
                        
                    }
                    else if(!service.isValidPassword(password)){
                        JOptionPane.showMessageDialog(view, "A megadott jelszó helytelen! ", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                } else {

                    
                }
            }
        });
        
        
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjCheckBox_isValidate().isSelected()) {
                    String name = view.getjTF_username().getText();
                    String password = view.getjPF_password().getText();
                    String permission = view.getjCB_Permission().getSelectedItem().toString();
                    if (!name.isEmpty() && !password.isEmpty() && !permission.isEmpty()) {
                        boolean isactive = view.getjCheckBox_isActive().isSelected();
                        try {
                            service.newUser(view, name,password, permission,  view.getjCheckBox_isActive().isSelected());
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ControllerUsers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        service.getAllUser(view.getjTab_Users());
                        view.getjTF_username().setText("");
                        view.getjCheckBox_isActive().setSelected(false);
                        view.getjCheckBox_isValidate().setSelected(false);
                    } else {
                        JOptionPane.showMessageDialog(view, "A *-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Az új felhasználó rögzítéséhez jelölje ki az ellenőrzés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        view.getjBut_Open().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjTab_Users().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Users().getSelectedRow();
                    int value= (Integer)view.getjTab_Users().getValueAt(row, 0);
                    User user= service.getOneUser(value);
                    view.getjTF_username().setText(user.getUsername());
                    //view.getjPF_password().setText(user.getPassword());
                    view.getjCB_Permission().setSelectedItem(user.getPermission());
                    view.getjCheckBox_isActive().setSelected(user.getIsActive());
                    view.getjLab_Id().setText(String.format("%d", user.getId()));

                }
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    String name = view.getjTF_username().getText();
                    String password = view.getjPF_password().getText();
                    String permission = view.getjCB_Permission().getSelectedItem().toString();
                    if (!name.isEmpty() && !password.isEmpty() && !permission.isEmpty()) {
                        boolean isactive = view.getjCheckBox_isActive().isSelected();
                        int id= Integer.parseInt(view.getjLab_Id().getText());
                        try {
                            service.updateUser(view, name,password, permission,  view.getjCheckBox_isActive().isSelected(), id);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ControllerUsers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        service.getAllUser(view.getjTab_Users());
                        view.getjTF_username().setText("");
                        view.getjCheckBox_isActive().setSelected(false);
                        view.getjCheckBox_isValidate().setSelected(false);
                    } else {
                        JOptionPane.showMessageDialog(view, "A *-gal jelölt mezők kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }
               
            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Users().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban törölni akarja a felhasználót?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTab_Users().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_Users().getValueAt(row, 0).toString());
                        service.deleteUser(view, value);
                    }

                    service.getAllUser(view.getjTab_Users());

                }

            }
        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        
        view.getjPF_password().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                view.getjLab_isValid().setText("");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                String password= view.getjPF_password().getText();
                if (service.isValidPassword(password)) {
                    view.getjLab_isValid().setText("A jelszó megfelelő!");
                    view.getjBut_Edit().setEnabled(true);
                }else{
                    view.getjLab_isValid().setText("A jelszó helytelen!");
                }
            }
        });
    }
}
