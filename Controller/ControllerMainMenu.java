package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerUser.UserListeners;
import InvoiceProgram.Model.User;
import InvoiceProgram.Service.ServiceUser;
import InvoiceProgram.View.MainMenu2;
import InvoiceProgram.View.PanelMain;
import InvoiceProgram.View.PanelMasterData;
import InvoiceProgram.View.PanelSettings;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ControllerMainMenu implements UserListeners {

    private final MainMenu2 view;
    private PanelMain panelMain;
    private PanelMasterData panelMasterData;
    private PanelSettings panelSettings;
    private String username;
    private int id;
    private String firm;

    public ControllerMainMenu(String firm) {
        view = new MainMenu2();
        this.firm=firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjTF_user().setText(username);
        view.getjLab_Firm().setText(firm);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.getjPan_Main().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panelMain = new PanelMain();
        panelMasterData = new PanelMasterData();
        panelSettings = new PanelSettings();

        view.getjPan_Main().add(panelMain, c);
        view.getjPan_Main().add(panelMasterData, c);
        view.getjPan_Main().add(panelSettings, c);
        panelMain.setVisible(true);
        panelMasterData.setVisible(false);
        panelSettings.setVisible(false);
    }

    public final void initControll() {
        panelMain.getjBut_Partner().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPartner controller=new ControllerPartner(firm);
            }
        });
        
        panelMain.getjBut_Invoice().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInvoiceMenu controller=new ControllerInvoiceMenu(firm);
            }
        });

        view.getjBt_MainMenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                panelMain.setVisible(true);
                panelMasterData.setVisible(false);
                panelSettings.setVisible(false);
                
                
            }
        });
        view.getjBt_MasterData().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                panelMain.setVisible(false);
                panelMasterData.setVisible(true);
                panelSettings.setVisible(false);
                initControllMasterData();
            }
        });
        view.getjBt_Settings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                panelMain.setVisible(false);
                panelMasterData.setVisible(false);
                panelSettings.setVisible(true);
                initControllMasterSettings();
            }
        });
        view.getjBt_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

    }
    
    public void initControllMasterData(){
        panelMasterData.getjBt_Firm().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerFirm controller= new ControllerFirm(firm);
            }
        });
        panelMasterData.getjBt_TaxYears().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxYear controller= new ControllerTaxYear(firm);
            }
        });
        panelMasterData.getjBt_TaxCodes().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxCode controller= new ControllerTaxCode(firm);
            }
        });
        panelMasterData.getjB_Banks().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerBank controller= new ControllerBank(firm);
            }
        });
        panelMasterData.getjB_BankAccount().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerBankAccount controller= new ControllerBankAccount(firm);
            }
        });
        panelMasterData.getjB_Countires().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCountry controller= new ControllerCountry(firm);
            }
        });
        panelMasterData.getjB_Currencies().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCurrency controller= new ControllerCurrency(firm);
            }
        });
        panelMasterData.getjB_Languages().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLanguage controller= new ControllerLanguage(firm);
                
            }
        });
        panelMasterData.getjB_PublicPlaces().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPublicPlace controller= new ControllerPublicPlace(firm);
            }
        });
        panelMasterData.getjB_Zips().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerZips controller= new ControllerZips(firm);
            }
        });
        panelMasterData.getjB_Payment().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPayment controller= new ControllerPayment(firm);
            }
        });
        panelMasterData.getjB_TransactionType().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTransactionType controller= new ControllerTransactionType(firm);
            }
        });
        
        panelMain.getjBut_Invoice().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        panelMain.getjBut_Query().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    
    public void initControllMasterSettings(){
        ServiceUser su = new ServiceUser();
        
        User user= su.getOneUser(id);
        if (!user.getPermission().equals("Admin")) {
            panelSettings.getjBt_User().setEnabled(false);
            panelSettings.getjBt_User().setToolTipText("Csak rendszergazdai jogosultásggal tekinthető meg a menüpont!");
        }
        panelSettings.getjBt_User().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerUsers controller= new ControllerUsers(firm);
            }
        });
        
        panelSettings.getjBt_OwnData().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerOwnUser controller= new ControllerOwnUser(firm, id);
                
                
            }
        });
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void updateData(int id, String user) {
        view.getjTF_user().setText(user);
        this.id=id;
    }

}
