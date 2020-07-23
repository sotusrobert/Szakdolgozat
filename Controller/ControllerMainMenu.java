package InvoiceProgram.Controller;

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

public class ControllerMainMenu {

    private final MainMenu2 view;
    private PanelMain panelMain;
    private PanelMasterData panelMasterData;
    private PanelSettings panelSettings;

    public ControllerMainMenu() {
        view = new MainMenu2();
        initView();
        initControll();
    }

    public final void initView() {
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
                
            }
        });
        panelMasterData.getjBt_TaxYears().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxYear controller= new ControllerTaxYear();
            }
        });
        panelMasterData.getjBt_TaxCodes().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerTaxCode controller= new ControllerTaxCode();
            }
        });
        panelMasterData.getjB_Banks().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerBank controller= new ControllerBank();
            }
        });
        panelMasterData.getjB_BankAccount().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        panelMasterData.getjB_Countires().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCountry controller= new ControllerCountry();
            }
        });
        panelMasterData.getjB_Currencies().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCurrency controller= new ControllerCurrency();
            }
        });
        panelMasterData.getjB_Languages().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLanguage controller= new ControllerLanguage();
                
            }
        });
        panelMasterData.getjB_PublicPlaces().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerPublicPlace controller= new ControllerPublicPlace();
            }
        });
        panelMasterData.getjB_Zips().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerZips controller= new ControllerZips();
            }
        });

    }

}
