package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerInvoiceFile.InvoiceFileListeners;
import InvoiceProgram.Model.BankAccount;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.TransactionType;
import InvoiceProgram.Service.ServiceBankAccount;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServiceFirm;
import InvoiceProgram.Service.ServiceInvoiceFile;
import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.View.InvoiceFileNew;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ControllerInvoiceFileNew implements InvoiceFileListeners {

    private final InvoiceFileNew view;
    private final ServiceInvoiceFile service;
    private String firm;

    public ControllerInvoiceFileNew(String firm) {
        view = new InvoiceFileNew();
        service = new ServiceInvoiceFile();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Új Számlatömb");
        setUpComboBoxToJtable(view.getjTab_InvoiceCurrency(), view.getjTab_InvoiceCurrency().getColumnModel().getColumn(1), view.getjTab_InvoiceCurrency().getColumnModel().getColumn(2));
        setUpComboBoxToJtable(view.getjTab_BankAccounts(), view.getjTab_BankAccounts().getColumnModel().getColumn(1));
        loadAllDivisionComboBox(view.getjCB_Firm());
        loadAllTransactionTypeComboBox(view.getjCB_TransactionType());
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

            }
        });
        view.getjBut_AddRowCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               DefaultTableModel tableModel = (DefaultTableModel)view.getjTab_InvoiceCurrency().getModel();
               tableModel.addRow(new Object[]{"", "", false, false, false, false});
            }
        });
        view.getjBut_AddRowRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_DeleteCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });
        view.getjBut_DeleteRowCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_EditCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_EditRip().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_Maintain().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_MaintainCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                service.newInvoiceFile(view, name, taxid, divisionId, transactionid, isBankAccountPrintToInv, initialCounter, VoucherSign, isActive);

            }
        });
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_SaveCur().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

    public void setUpComboBoxToJtable(JTable table, TableColumn col,TableColumn col2) {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllActiveCurrency();
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
                    setText(bankaccount.getNumber());
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

    }

    @Override
    public void sendSelectedTaxYearId(int id, String name) {
        view.getjTF_TaxYear().setText(name);
        view.getjLab_taxId().setText(String.format("%d", id));

    }

    @Override
    public void updateInvoiceFileId(int id) {
        
    }
}
