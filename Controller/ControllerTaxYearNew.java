package InvoiceProgram.Controller;

import InvoiceProgram.Model.Currency;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.TaxYearNew;
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
import javax.swing.JOptionPane;

public class ControllerTaxYearNew {

    private final TaxYearNew view;
    private final ServiceTaxYear service;
    private String firm;

    public ControllerTaxYearNew(String firm) {
        view = new TaxYearNew();
        service = new ServiceTaxYear();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új Adóév");
        view.getjDC_closeDate().setEnabled(false);
        view.getjCheckBox_isClosed().setEnabled(false);
        view.getjTF_taxYearNumber().setToolTipText("Csak az évszámot kell megadni! Pl.:2020");
        view.getjTF_voucherDigits().setToolTipText("Csak egy egész szám adható meg (Min.:3 Max:8)");
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
                int parentTaxYearId = view.getjCB_parentTaxYear().getSelectedIndex();
                String name = view.getjTF_name().getText();

                Currency currency = (Currency) view.getjCB_currency().getSelectedItem();
                String currencyId = currency.getId();
                String taxYearNumber = view.getjTF_taxYearNumber().getText();
                boolean isKATA = view.getjCheckBox_isKATA().isSelected();
                boolean isClosed = view.getjCheckBox_isClosed().isSelected();
                boolean isCashAccounting = view.getjCheckBox_isCashAccounting().isSelected();
                String voucherFormat = view.getjTF_voucherFormat().getText();
                String voucherDigits = view.getjTF_voucherDigits().getText();
                String voucherSepFirst = view.getjTF_voucherSepFirst().getText();
                String voucherSepLast = view.getjTF_voucherSepLast().getText();

                if (name.isEmpty() && view.getjDC_startDate().getDate() == null && view.getjDC_endDate().getDate() == null
                        && taxYearNumber.isEmpty() && voucherDigits.isEmpty() && voucherSepFirst.isEmpty() && voucherSepLast.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Minden '*'-gal jelölt adat megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (!service.isValidTaxYear(taxYearNumber)) {
                    JOptionPane.showMessageDialog(view, "Az adóév nem megfelelő!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (!service.isValidVoucherDigits(voucherDigits)) {
                    JOptionPane.showMessageDialog(view, "Az számjegyek száma nem megfelelő!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                } else {
                    Date dsd = new Date(view.getjDC_startDate().getDate().getTime());
                    LocalDate sD = dsd.toLocalDate();
                    java.sql.Date startDate = (java.sql.Date.valueOf(sD));
                    Date ded = new Date(view.getjDC_endDate().getDate().getTime());
                    LocalDate eD = ded.toLocalDate();
                    java.sql.Date endDate = (java.sql.Date.valueOf(eD));
                    //Date dcd = new Date(view.getjDateChooser3().getDate().getTime());
                    //LocalDate cD = dcd.toLocalDate();
                    java.sql.Date closeDate = endDate;

                    service.newTaxYear(view, parentTaxYearId, name, startDate, endDate, currencyId, Integer.parseInt(taxYearNumber), isKATA, isClosed, isCashAccounting, voucherFormat, Integer.parseInt(voucherDigits), voucherSepFirst, voucherSepLast, closeDate);
                    view.dispose();
                }

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

        view.getjCB_sample().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_sample().isSelected()) {
                    if (!view.getjTF_voucherDigits().getText().isEmpty()) {

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
            }
        });
        view.getjCheckBox_isCashAccounting().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCheckBox_isCashAccounting().isSelected()) {
                    view.getjCheckBox_isKATA().setEnabled(false);
                } else {
                    view.getjCheckBox_isKATA().setEnabled(true);
                }
            }
        });
        view.getjCheckBox_isKATA().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCheckBox_isKATA().isSelected()) {
                    view.getjCheckBox_isCashAccounting().setEnabled(false);
                } else {
                    view.getjCheckBox_isCashAccounting().setEnabled(true);
                }
            }
        });
    }

    public void loadAllCurrencyToComboBox() {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllActiveCurrency();
        // Currency [] currencies= new Currency[list.size()];
        //for (int i = 0; i < list.size(); i++) {
        //    currencies[i]=list.get(i);
        // }

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
                if (value instanceof Currency) {
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
