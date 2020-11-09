package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerTaxYear.TaxYearListeners;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.TaxYearEdit;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ControllerTaxYearEdit implements TaxYearListeners {

    private final TaxYearEdit view;
    private final ServiceTaxYear service;
    private String firm;

    public ControllerTaxYearEdit(String firm) {
        view = new TaxYearEdit();
        service = new ServiceTaxYear();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Adóév módosítás");
        service.getAllTaxYearToComboBox(view.getjCB_parentTaxYearId());
        loadAllCurrencyToComboBox();
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int parentTaxYearId = view.getjCB_parentTaxYearId().getSelectedIndex();
                String name = view.getjTF_name().getText();
                Date dsd = new Date(view.getjDC_startDate().getDate().getTime());
                LocalDate sD = dsd.toLocalDate();
                java.sql.Date startDate = (java.sql.Date.valueOf(sD));
                Date ded = new Date(view.getjDC_endDate().getDate().getTime());
                LocalDate eD = ded.toLocalDate();
                java.sql.Date endDate = (java.sql.Date.valueOf(eD));
                Currency currency = (Currency) view.getjCB_currencyId().getSelectedItem();
                String currencyId = currency.getId();
                int taxYearNumber = Integer.parseInt(view.getjTF_taxYearNumber().getText());
                boolean isKATA = view.getjCB_isKATA().isSelected();
                boolean isClosed = view.getjCB_isClose().isSelected();
                boolean isCashAccounting = view.getjCB_isCashAccounting().isSelected();
                String voucherFormat = view.getjTF_voucherFormat().getText();
                int voucherDigits = Integer.parseInt(view.getjTF_voucherDigits().getText());
                String voucherSepFirst = view.getjTF_voucherSepFirst().getText();
                String voucherSepLast = view.getjTF_voucherSepLast().getText();
                Date dcd = new Date(view.getjDC_lockDate().getDate().getTime());
                LocalDate cD = dcd.toLocalDate();
                java.sql.Date closeDate = (java.sql.Date.valueOf(cD));
                int id = Integer.parseInt(view.getjLab_Id().getText());
                service.updateTaxYear(view, parentTaxYearId, name, startDate, endDate, currencyId, taxYearNumber, isKATA, isClosed, isCashAccounting, voucherFormat, voucherDigits, voucherSepFirst, voucherSepLast, closeDate, id);
                view.dispose();
            }
        });

        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

    }

    public void loadAllCurrencyToComboBox() {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllActiveCurrency();
        Currency[] currencies = new Currency[list.size()];
        for (int i = 0; i < list.size(); i++) {
            currencies[i] = list.get(i);
        }

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_currencyId().getModel();
        view.getjCB_currencyId().removeAllItems();
        view.getjCB_currencyId().revalidate();
        model.addAll(list);

        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_currencyId().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Currency) {
                    Currency currencies = (Currency) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(currencies.getId()).append(": ").append(currencies.getName());
                    setText(sb.toString());
                }
                return this;
            }

        });

        view.getjCB_sample().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_sample().isSelected()) {
                    int digits = Integer.parseInt(view.getjTF_voucherDigits().getText());
                    String firstSep = view.getjTF_voucherSepFirst().getText();
                    String lastSep = view.getjTF_voucherSepLast().getText();
                    String voucherNum = "";
                    String taxYear = "20XX";

                    for (int i = 1; i < digits + 1; i++) {
                        voucherNum += "X";
                    }

                    String voucherSample = "A" + firstSep + voucherNum + lastSep + taxYear;

                    view.getjTF_sample().setText(voucherSample);
                } else {
                    view.getjTF_sample().setText("Minta");
                }
            }
        });
    }

    @Override
    public void updateData(ArrayList<TaxYear> list) {
        view.getjTF_name().setText(list.get(0).getName());
        view.getjTF_name().setEnabled(false);
        view.getjCB_parentTaxYearId().setSelectedItem(list.get(0).getName());
        view.getjCB_parentTaxYearId().setEnabled(false);
        view.getjDC_startDate().setDate(list.get(0).getStartDate());
        view.getjDC_startDate().setEnabled(false);
        view.getjDC_endDate().setDate(list.get(0).getEndDate());
        view.getjDC_endDate().setEnabled(false);
        int sizeOfCombo = view.getjCB_currencyId().getItemCount();
        int index = 0;
        String currencyOfList = list.get(0).getCurrency();
        ArrayList<Currency> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = view.getjCB_currencyId().getItemAt(i);

            cl.add((Currency) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(list.get(0).getCurrency())) {
                index = i;
            }
        }
        view.getjCB_currencyId().setSelectedIndex(index);
        view.getjCB_currencyId().setEnabled(false);
        view.getjCB_currencyId().setSelectedItem(list.get(0).getCurrency());
        view.getjTF_taxYearNumber().setText(String.format("%d", list.get(0).getTaxYearNumber()));
        view.getjTF_taxYearNumber().setEnabled(false);
        view.getjCB_isKATA().setSelected(list.get(0).isIsKATA());
        view.getjCB_isKATA().setEnabled(false);
        view.getjCB_isClose().setSelected(list.get(0).isIsClosed());
        view.getjDC_lockDate().setDate(list.get(0).getCloseDate());
        if (list.get(0).isIsClosed()) {
            view.getjCB_isClose().setEnabled(false);
            view.getjDC_lockDate().setEnabled(false);
        }
        view.getjCB_isCashAccounting().setSelected(list.get(0).isIsCashAccounting());
        view.getjCB_isCashAccounting().setEnabled(false);
        view.getjTF_voucherFormat().setText(list.get(0).getVoucherFormat());
        view.getjTF_voucherFormat().setEnabled(false);
        view.getjTF_voucherDigits().setText(String.format("%d", list.get(0).getVoucherDigits()));
        view.getjTF_voucherDigits().setEnabled(false);
        view.getjTF_voucherSepFirst().setText(list.get(0).getVoucherSepFirst());
        view.getjTF_voucherSepFirst().setEnabled(false);
        view.getjTF_voucherSepLast().setText(list.get(0).getVoucherSepLast());
        view.getjTF_voucherSepLast().setEnabled(false);
        view.getjLab_Id().setText(String.format("%d", list.get(0).getId()));

    }

}
