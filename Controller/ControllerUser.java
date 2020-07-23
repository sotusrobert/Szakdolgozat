package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceUser;
import InvoiceProgram.View.UserLogin;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ControllerUser {

    private final UserLogin view;
    private final ServiceUser service;

    public ControllerUser(UserLogin view, ServiceUser service) {
        this.view = view;
        this.service = service;
        initView();
        initControll();
    }

    public final void initView() {
        scaleImage(view.getjLab_Logo());
        view.setVisible(true);
    }

    public final void initControll() {
        view.getjBt_Login().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    userLogin();
            }

        });
        view.getjBt_Cancel()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        Object[] options = {"Igen",
                            "Nem"};
                        int n = JOptionPane.showOptionDialog(view,
                                "Valóban ki akar lépni?",
                                "Figyelem",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (n == 0) {
                            System.exit(EXIT_ON_CLOSE);
                        }

                    }
                }
                );
        view.getjTF_username().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                view.getjLab_message().setText("");
            }
        });
        view.getjTF_username().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                    view.getjLab_message().setText("A Caps Lock be van kapcsolva!");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        view.getjTF_username().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
                    view.getjLab_message().setText("");
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
                    view.getjLab_message().setText("");
                }
            }
        });
        
        view.getjPF_password().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
               
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
        });

    }

    ;
    
    private void scaleImage(JLabel label) {
        ImageIcon icon = new ImageIcon("C:\\Users\\ACER\\Documents\\NetBeansProjects\\InviceProgram\\src\\main\\java\\Images\\Login-icon.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);

    }
    
    private void userLogin(){
        
                if (view.getjTF_username().getText().isEmpty() && view.getjPF_password().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden mező kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (view.getjTF_username().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A felhasználónév kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (view.getjPF_password().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A jelszó kitöltése kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (service.isValidUser(view.getjTF_username().getText(), view.getjPF_password().getText())) {
                        JOptionPane.showMessageDialog(view, "Sikeres bejelentkezés!");
                        view.dispose();
                        ControllerMainMenu controllerMainMenu = new ControllerMainMenu();
                        
                    } else {
                        JOptionPane.showMessageDialog(view, "Sikertelen bejelentkezés!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        view.getjTF_username().setText("");
                        view.getjPF_password().setText("");
                    }

                }
    }
}