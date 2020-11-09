package InvoiceProgram.Controller;

import InvoiceProgram.Model.Firm;
import InvoiceProgram.Service.ServiceFirm;
import InvoiceProgram.View.Firm_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;

public class ControllerFirm {

    private final Firm_GUI view;
    private final ServiceFirm service;
    //private ArrayList<BankListeners> listeners = new ArrayList<>();
    private String firm;
    public ControllerFirm(String firm) {
        view = new Firm_GUI();
        service = new ServiceFirm();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm);
        loadFirm();
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerFirmEdit controller = new ControllerFirmEdit(firm);
            }

        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }

        });
    }

    public void loadFirm() {
        ArrayList<Firm> firm = service.getFirm();
        view.getjTF_shortName().setText(firm.get(0).getShorName());
        view.getjTF_fullName().setText(firm.get(0).getFullName());
        view.getjTF_nameOnVoucher().setText(firm.get(0).getVoucherName());
        view.getjTF_VatNumber().setText(firm.get(0).getVATNumber());
        view.getjTF_VatNumberEU().setText(firm.get(0).getVATNumberEU());
        view.getjTF_VatNumberGroup().setText(firm.get(0).getVATNumberGroup());
    }
}
