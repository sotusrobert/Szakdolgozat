package InvoiceProgram.Controller;

import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.TaxYearNew;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ControllerTaxYearNew {

    private final TaxYearNew view;
    private final ServiceTaxYear service;

    public ControllerTaxYearNew() {
        view = new TaxYearNew();
        service = new ServiceTaxYear();
        initView();
        initControll();
    }

    public final void initView() {
        service.getAllTaxYearToComboBox(view.getjCB_parentTaxYear());
        loadAllCurrencyToComboBox();
        view.getjCB_currency().setSelectedIndex(0);
        view.getjTF_sample().setEnabled(false);
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int parentTaxYearId=view.getjCB_parentTaxYear().getSelectedIndex();
                String name= view.getjTF_name().getText();
                Date dsd = new Date(view.getjDC_startDate().getDate().getTime());
                LocalDate sD = dsd.toLocalDate();
                java.sql.Date startDate = (java.sql.Date.valueOf(sD));
                Date ded = new Date(view.getjDC_endDate().getDate().getTime());
                LocalDate eD = ded.toLocalDate();
                java.sql.Date endDate = (java.sql.Date.valueOf(eD));
                Currency currency =(Currency)view.getjCB_currency().getSelectedItem();
                String currencyId = currency.getId();
                int taxYearNumber= Integer.parseInt(view.getjTF_taxYearNumber().getText());
                boolean isKATA= view.getjCheckBox_isKATA().isSelected();
                boolean isClosed = view.getjCheckBox_isClosed().isSelected();
                boolean isCashAccounting = view.getjCheckBox_isCashAccounting().isSelected();
                String voucherFormat = view.getjTF_voucherFormat().getText();
                int voucherDigits =Integer.parseInt(view.getjTF_voucherDigits().getText());
                String voucherSepFirst= view.getjTF_voucherSepFirst().getText();
                String voucherSepLast= view.getjTF_voucherSepLast().getText();
                Date dcd = new Date(view.getjDateChooser3().getDate().getTime());
                LocalDate cD = dcd.toLocalDate();
                java.sql.Date closeDate = (java.sql.Date.valueOf(cD));
                
                service.newTaxYear(view, parentTaxYearId, name, startDate, endDate, currencyId, taxYearNumber, isKATA, isClosed, isCashAccounting, voucherFormat, voucherDigits, voucherSepFirst, voucherSepLast, closeDate);
                view.dispose();
            }
        }
        );
        view.getjBut_Exit()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        view.dispose();
                    }
                }
                );
    }

    public void loadAllCurrencyToComboBox() {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllCurrency();
        Currency [] currencies= new Currency[list.size()];
        for (int i = 0; i < list.size(); i++) {
            currencies[i]=list.get(i);
        }
        
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_currency().getModel();
        view.getjCB_currency().removeAllItems();
        view.getjCB_currency().revalidate();
        model.addAll(list);
        
        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_currency().setRenderer(new DefaultListCellRenderer() {
        @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Currency){
                    Currency currencies = (Currency) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(currencies.getId()).append(": ").append(currencies.getName());
                    setText(sb.toString());
                }
                return this;
            }
        
        
        });
    }

}
