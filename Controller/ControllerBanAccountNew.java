package InvoiceProgram.Controller;

import InvoiceProgram.Model.Bank;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Service.ServiceBank;
import InvoiceProgram.Service.ServiceBankAccount;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.View.BankAccountNew;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ControllerBanAccountNew {

    private final BankAccountNew view;
    private final ServiceBankAccount service;
    private final String firm;

    public ControllerBanAccountNew(String firm) {
        view = new BankAccountNew();
        service = new ServiceBankAccount();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új Bankszámla");
        view.getjTF_BankAccount().setToolTipText("A számlaszám kétszer nyolc vagy a háromszor nyolc számjegyből állhat kötőjellel tagolva! (pl:12345678-00000000)");
        loadAllBankToComboBox();
        view.getjCB_Bank().setSelectedIndex(0);
        loadAllCurrencyToComboBox();
        view.getjCB_Currency().setSelectedIndex(0);
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getjTF_name().getText();
                Bank bank = (Bank) view.getjCB_Bank().getSelectedItem();
                int bankid = bank.getId();
                Currency currency = (Currency) view.getjCB_Currency().getSelectedItem();
                String currencyId = currency.getId();
                boolean isActive = view.getjCB_isActive().isSelected();
                String number = view.getjTF_BankAccount().getText();

                if (name.isEmpty() && number.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés és a számlaszám megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (number.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A számlaszám megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!service.isValidAccountNumber(number)) {
                        JOptionPane.showMessageDialog(view, "Érvénytelen bankszámlaszám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        service.newBankAccount(view, name, currencyId, bankid, number, isActive);
                        view.dispose();
                    }

                }

            }
        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    public void loadAllBankToComboBox() {
        ServiceBank sc = new ServiceBank();
        ArrayList<Bank> list = new ArrayList<>();
        list = sc.getAllBank();

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_Bank().getModel();
        view.getjCB_Bank().removeAllItems();
        view.getjCB_Bank().revalidate();
        model.addAll(list);

        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_Bank().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Bank) {
                    Bank banks = (Bank) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(banks.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllCurrencyToComboBox() {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllActiveCurrency();

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_Currency().getModel();
        view.getjCB_Currency().removeAllItems();
        view.getjCB_Currency().revalidate();
        model.addAll(list);

        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_Currency().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Currency) {
                    Currency currencies = (Currency) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(currencies.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }
}
