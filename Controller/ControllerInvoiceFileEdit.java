package InvoiceProgram.Controller;

import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.TransactionType;
import InvoiceProgram.Service.ServiceBankAccount;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServiceFirm;
import InvoiceProgram.Service.ServiceInvoiceFile;
import InvoiceProgram.Service.ServiceInvoiceFileBankAccount;
import InvoiceProgram.Service.ServiceInvoiceFileCurrency;
import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.View.InvoiceFileEdit;
import InvoiceProgram_GUI.InvoiceFileCurrencyModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ControllerInvoiceFileEdit implements ControllerInvoiceFile.InvoiceFileListeners {

    private final InvoiceFileEdit view;
    private final ServiceInvoiceFile service;
    private int currencyId;
    private int invoiceFileId;
    private InvoiceFileCurrencyModel tableModel;
     private String firm;
    public int getInvoiceFileId() {
        return invoiceFileId;
    }

    public void setInvoiceFileId(int invoiceFileId) {
        this.invoiceFileId = invoiceFileId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public ControllerInvoiceFileEdit(String firm) {
        view = new InvoiceFileEdit();
        service = new ServiceInvoiceFile();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Számlatömb módosítás");
        setUpComboBoxToJtable(view.getjTab_BankAccounts(), view.getjTab_BankAccounts().getColumnModel().getColumn(1));
        setUpComboBoxToJtable(view.getjTab_InvoiceCurrency(), view.getjTab_InvoiceCurrency().getColumnModel().getColumn(1), view.getjTab_InvoiceCurrency().getColumnModel().getColumn(2));
        //ServiceCurrency sc = new ServiceCurrency();
        //ArrayList<Currency> list = new ArrayList<>();
        //list = sc.getAllCurrency();

        //ServiceBankAccount sc2 = new ServiceBankAccount();
        //ArrayList<BankAccount> list2 = new ArrayList<>();
        //list2 = sc2.getAllBankAccount();
        //ServiceInvoiceFileCurrency serviceInvoiceFileCurrency= new ServiceInvoiceFileCurrency();
        //ArrayList<InvoiceFileCurrency> invoiceFileCurrency = serviceInvoiceFileCurrency.getAllInvoiceFile();
        //tableModel= new InvoiceFileCurrencyModel(invoiceFileCurrency);
        // view.getjTab_InvoiceCurrency().setModel(tableModel);
        // view.getjTab_InvoiceCurrency().setDefaultRenderer(Currency.class, new CurrencyCellRenderer());
        // view.getjTab_InvoiceCurrency().setDefaultEditor(Currency.class, new CurrencyCellEditor(list));
        // view.getjTab_InvoiceCurrency().setDefaultRenderer(BankAccount.class, new BankAccountCellRenderer());
        // view.getjTab_InvoiceCurrency().setDefaultEditor(BankAccount.class, new BankAccountCellEditor(list2));
        //  view.getjTab_InvoiceCurrency().getColumnModel().getColumn(0).setMaxWidth(0);
        // view.getjTab_InvoiceCurrency().getColumnModel().getColumn(0).setMinWidth(0);
        // view.getjTab_InvoiceCurrency().getColumnModel().getColumn(0).setPreferredWidth(0);
        loadAllTransactionTypeComboBox(view.getjCB_TransactionType());
        loadAllDivisionComboBox(view.getjCB_Firm());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjBut_AddRow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_BankAccounts().getModel();
                tableModel.addRow(new Object[]{"", ""});
            }
        });
        view.getjBut_AddRowCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoiceCurrency().getModel();
                tableModel.addRow(new Object[]{0, "", "", false, false, false, false});

            }
        });
        view.getjBut_AddRowRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoivceFileReport().getModel();
                tableModel.addRow(new Object[]{"", "", "", "", "", ""});

            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_BankAccounts().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    ServiceInvoiceFileBankAccount sc = new ServiceInvoiceFileBankAccount();
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban törölni akarja a rekordot?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTab_BankAccounts().getSelectedRow();
                        String value = view.getjTab_BankAccounts().getValueAt(row, 0).toString();
                        int id = Integer.parseInt(value);
                        sc.deleteInvoiceFileBankAccount(view, id);
                    }

                    sc.getAllInvoiceFileBankAccount(view.getjTab_BankAccounts(), invoiceFileId);
                }
            }
        });
        view.getjBut_DeleteCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_InvoiceCurrency().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    ServiceInvoiceFileCurrency sc = new ServiceInvoiceFileCurrency();
                    Object[] options = {"Igen",
                        "Nem"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Valóban törölni akarja a rekordot?",
                            "Figyelem",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0) {
                        int row = view.getjTab_InvoiceCurrency().getSelectedRow();
                        String value = view.getjTab_InvoiceCurrency().getValueAt(row, 0).toString();
                        int id = Integer.parseInt(value);
                        sc.deleteInvoiceFileCurrency(view, id);
                    }

                    sc.getAllInvoiceFileCurrency(view.getjTab_InvoiceCurrency(), invoiceFileId);
                }
            }
        });
        view.getjBut_DeleteRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_DeleteRow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_BankAccounts().getModel();
                int row = tableModel.getRowCount();
                String lastrow = view.getjTab_BankAccounts().getValueAt(row - 1, 0).toString();

                if (lastrow.isEmpty()) {
                    tableModel.removeRow(row - 1);
                } else {
                    JOptionPane.showMessageDialog(view, "A sor törlése nem lehetséges, mert adatot tartalmaz, válassza a rekord törlés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        view.getjBut_DeleteRowCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoiceCurrency().getModel();
                int row = tableModel.getRowCount();
                String lastrow = view.getjTab_InvoiceCurrency().getValueAt(row - 1, 1).toString();

                if (lastrow.isEmpty()) {
                    tableModel.removeRow(row - 1);
                } else {
                    JOptionPane.showMessageDialog(view, "A sor törlése nem lehetséges, mert adatot tartalmaz, válassza a rekord törlés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        view.getjBut_DeleteRowRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoivceFileReport().getModel();
                int row = tableModel.getRowCount();
                String lastrow = view.getjTab_InvoivceFileReport().getValueAt(row - 1, 0).toString();

                if (lastrow.isEmpty()) {
                    tableModel.removeRow(row - 1);
                } else {
                    JOptionPane.showMessageDialog(view, "A sor törlése nem lehetséges, mert adatot tartalmaz, válassza a rekord törlés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        view.getjBut_Maintain().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerBankAccount controllerBankAccount = new ControllerBankAccount(firm);
            }
        });
        view.getjBut_MaintainCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerCurrency controllerCurrency = new ControllerCurrency(firm);
            }
        });
        view.getjBut_MaintainRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_OK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Igen",
                    "Nem"};
                int n = JOptionPane.showOptionDialog(view,
                        "Menteni akarja a módosítást?",
                        "Figyelem",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    int id = Integer.parseInt(view.getjLab_taxId().getText());
                    String name = view.getjTF_name().getText();
                    int taxid = Integer.parseInt(view.getjLab_taxId().getText());
                    Division division = (Division) view.getjCB_Firm().getSelectedItem();
                    int divisionId = division.getId();
                    TransactionType transaction = (TransactionType) view.getjCB_TransactionType().getSelectedItem();
                    int transactionid = transaction.getId();
                    boolean isBankAccountPrintToInv = view.getjCB_isBankAccountPrintToInv().isSelected();
                    int initialCounter = Integer.parseInt(view.getjTF_InitialCounter().getText());
                    String VoucherSign = view.getjTF_VoucherSign().getText();
                    boolean isActive = view.getjCB_isClosed().isSelected();
                    service.updateInvoiceFile(view, name, taxid, divisionId, transactionid, isBankAccountPrintToInv, initialCounter, VoucherSign, isActive, id);
                }
                view.dispose();
            }
        });
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjTab_BankAccounts().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_BankAccounts().getSelectedRow();

                    ServiceInvoiceFileBankAccount serviceInvoiceFileBankAccount = new ServiceInvoiceFileBankAccount();
                    String value = view.getjTab_BankAccounts().getValueAt(row, 0).toString();
                    int id2 = 0;
                    if (!value.equals("")) {
                        id2 = Integer.parseInt(value);
                    }

                    if (id2 > 0) {
                        Object[] options = {"Igen",
                            "Nem"};
                        int n = JOptionPane.showOptionDialog(view,
                                "Valóban módosítani akarja a rekordot?",
                                "Figyelem",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (n == 0) {
                            int id = (Integer) view.getjTab_BankAccounts().getValueAt(row, 0);
                            String bankAccount = (String) view.getjTab_InvoiceCurrency().getValueAt(row, 2).toString();
                            ServiceBankAccount sc = new ServiceBankAccount();
                            ArrayList<BankAccount> list = sc.getAllBankAccount();
                            int bankAccountId = 0;

                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).toString().equals(bankAccount)) {
                                    bankAccountId = (i + 1);
                                }
                            }

                            serviceInvoiceFileBankAccount.updateInvoiceFileBankAccount(view, invoiceFileId, bankAccountId, id);
                            serviceInvoiceFileBankAccount.getAllInvoiceFileBankAccount(view.getjTab_BankAccounts(), invoiceFileId);
                        }
                    } else {

                        String bankAccount = (String) view.getjTab_InvoiceCurrency().getValueAt(row, 2).toString();
                        ServiceBankAccount sc = new ServiceBankAccount();
                        ArrayList<BankAccount> list = sc.getAllBankAccount();
                        int bankAccountId = 0;

                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).toString().equals(bankAccount)) {
                                bankAccountId = (i + 1);
                            }
                        }

                        serviceInvoiceFileBankAccount.newInvoiceFileBankAccount(view, invoiceFileId, bankAccountId);
                        serviceInvoiceFileBankAccount.getAllInvoiceFileBankAccount(view.getjTab_BankAccounts(), invoiceFileId);
                    }
                }
            }
        });
        view.getjBut_SaveCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjTab_InvoiceCurrency().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_InvoiceCurrency().getSelectedRow();

                    ServiceInvoiceFileCurrency serviceInvoiceFileCurrency = new ServiceInvoiceFileCurrency();
                    if ((Integer) view.getjTab_InvoiceCurrency().getValueAt(row, 0) > 0) {
                        Object[] options = {"Igen",
                            "Nem"};
                        int n = JOptionPane.showOptionDialog(view,
                                "Valóban módosítani akarja a rekordot?",
                                "Figyelem",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (n == 0) {
                            int id = (Integer) view.getjTab_InvoiceCurrency().getValueAt(row, 0);
                            String currency = (String) view.getjTab_InvoiceCurrency().getValueAt(row, 1);
                            String currencyId = currency.toString();
                            String bankAccount = (String) view.getjTab_InvoiceCurrency().getValueAt(row, 2).toString();
                            ServiceBankAccount sc = new ServiceBankAccount();
                            ArrayList<BankAccount> list = sc.getAllBankAccount();
                            int bankAccountId = 0;

                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).toString().equals(bankAccount)) {
                                    bankAccountId = (i + 1);
                                }
                            }

                            boolean isDefault = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 3);
                            boolean roundItem = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 4);
                            boolean roundVAT = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 5);
                            boolean roundTotal = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 6);
                            serviceInvoiceFileCurrency.updateInvoiceFileCurrency(view, invoiceFileId, currencyId, bankAccountId, isDefault, roundItem, roundVAT, roundTotal, id);
                            serviceInvoiceFileCurrency.getAllInvoiceFileCurrency(view.getjTab_InvoiceCurrency(), invoiceFileId);
                        }
                    } else {

                        Currency currency = (Currency) view.getjTab_InvoiceCurrency().getValueAt(row, 1);
                        String currencyId = currency.getId();
                        BankAccount bankAccount = (BankAccount) view.getjTab_InvoiceCurrency().getValueAt(row, 2);
                        
                        int bankAccountId = bankAccount.getId();
                        boolean isDefault = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 3);
                        boolean roundItem = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 4);
                        boolean roundVAT = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 5);
                        boolean roundTotal = (Boolean) view.getjTab_InvoiceCurrency().getValueAt(row, 6);

                        serviceInvoiceFileCurrency.newInvoiceFileCurrency(view, invoiceFileId, currencyId, bankAccountId, isDefault, roundItem, roundVAT, roundTotal);
                        serviceInvoiceFileCurrency.getAllInvoiceFileCurrency(view.getjTab_InvoiceCurrency(), invoiceFileId);
                    }
                }
            }
        }
        );
        view.getjBut_SaveRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void loadAllDivisionComboBox(JComboBox box) {
        ServiceFirm sc = new ServiceFirm();
        ArrayList<Division> list = new ArrayList<>();
        list = sc.getAllDivision();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Division) {
                    Division division = (Division) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(division.getName());
                    setText(sb + "");
                }
                return this;
            }

        });

    }

    public void loadAllTransactionTypeComboBox(JComboBox box) {
        ServiceTransactionType sc = new ServiceTransactionType();
        ArrayList<TransactionType> list = new ArrayList<>();
        list = sc.getAllTransactionType();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof TransactionType) {
                    TransactionType transaction = (TransactionType) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(transaction.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void setSelectedDivisionToComboBox(JComboBox box, ArrayList<InvoiceFile> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Division> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Division) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getDivision().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedTransactionTypeToComboBox(JComboBox box, ArrayList<InvoiceFile> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<TransactionType> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((TransactionType) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getTransactionType().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setUpComboBoxToJtable(JTable table, TableColumn col, TableColumn col2) {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllCurrencyByIsActive();
        JComboBox box = new JComboBox();
        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Currency) {
                    Currency currency = (Currency) value;
                    setText(currency.getName());
                }
                return this;
            }

        });

        col.setCellEditor(new DefaultCellEditor(box));

        ServiceBankAccount sc2 = new ServiceBankAccount();
        ArrayList<BankAccount> list2 = new ArrayList<>();
        list2 = sc2.getAllBankAccount();
        JComboBox box2 = new JComboBox();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) box2.getModel();
        box2.removeAllItems();
        box2.revalidate();
        model2.addAll(list2);

        box2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BankAccount) {
                    BankAccount bankaccount = (BankAccount) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(bankaccount.getName()).append(":").append(bankaccount.getNumber());
                    setText(sb.toString());
                }
                return this;
            }

        });

        col2.setCellEditor(new DefaultCellEditor(box2));

    }

    public void setUpComboBoxToJtable(JTable table, TableColumn col) {
        ServiceBankAccount sc2 = new ServiceBankAccount();
        ArrayList<BankAccount> list2 = new ArrayList<>();
        list2 = sc2.getAllBankAccount();
        JComboBox box2 = new JComboBox();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) box2.getModel();
        box2.removeAllItems();
        box2.revalidate();
        model2.addAll(list2);

        box2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BankAccount) {
                    BankAccount bankaccount = (BankAccount) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(bankaccount.getName()).append(":").append(bankaccount.getNumber());
                    setText(sb.toString());
                }
                return this;
            }

        });

        col.setCellEditor(new DefaultCellEditor(box2));

    }

    @Override
    public void updateData(ArrayList<InvoiceFile> list) {
        view.getjTF_name().setText(list.get(0).getName());
        view.getjLab_taxId().setText(String.format("%d", list.get(0).getId()));
        setSelectedDivisionToComboBox(view.getjCB_Firm(), list);
        setSelectedTransactionTypeToComboBox(view.getjCB_TransactionType(), list);
        view.getjCB_isBankAccountPrintToInv().setSelected(list.get(0).isIsBankAccountPrintToInv());
        view.getjTF_InitialCounter().setText(String.format("%d", list.get(0).getInitialCounter()));
        view.getjTF_VoucherSign().setText(list.get(0).getVoucherSign());
        view.getjCB_isClosed().setSelected(list.get(0).isIsActive());
        view.getjTF_TaxYear().setText(list.get(0).getTaxYear().getName());

    }

    @Override
    public void sendSelectedTaxYearId(int id, String name) {
        view.getjTF_TaxYear().setText(name);
        view.getjLab_taxId().setText(String.format("%d", id));
    }

    @Override
    public void updateInvoiceFileId(int id) {
        this.currencyId = id;
        ServiceInvoiceFileCurrency controll = new ServiceInvoiceFileCurrency();
        controll.getAllInvoiceFileCurrency(view.getjTab_InvoiceCurrency(), id);
        ServiceInvoiceFileBankAccount controll2 = new ServiceInvoiceFileBankAccount();
        controll2.getAllInvoiceFileBankAccount(view.getjTab_BankAccounts(), id);
    }

}
