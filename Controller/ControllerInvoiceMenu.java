package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceMenuService;
import InvoiceProgram.View.InvoiceMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public class ControllerInvoiceMenu {

    private final InvoiceMenu view;
    private final ServiceMenuService service;
    //private ArrayList<BankListeners> listeners = new ArrayList<>();
    private String firm;

    public ControllerInvoiceMenu(String firm) {
        view = new InvoiceMenu();
        service = new ServiceMenuService();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Számlázás");
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBt_NewInvoice().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInvoiceFileSelect controller = new ControllerInvoiceFileSelect(firm);
            }
        });
        view.getjBt_ClosedInvoice().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerFinalizedInvoice controller = new ControllerFinalizedInvoice(firm);
            }
        });
        view.getjBt_InvoiceFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInvoiceFile controller = new ControllerInvoiceFile(firm);
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
