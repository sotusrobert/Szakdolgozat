package InvoiceProgram.Controller;

import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Service.ServiceInvoiceFile;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.InvoiceFile_GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControllerInvoiceFile {

    private final InvoiceFile_GUI view;
    private final ServiceInvoiceFile service;
    private ArrayList<InvoiceFileListeners> listeners = new ArrayList<>();
    private String firm;
    public ControllerInvoiceFile(String firm) {
        view = new InvoiceFile_GUI();
        service = new ServiceInvoiceFile();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Számlatömbök");
        service.getAllInvoiceFile(view.getjTab_InvoiceFile());
        loadAllTaxYearComboBox(view.getjCB_TaxYear());
        //view.getjBut_DeleteInvoiceFile().setEnabled(false);
        //view.getjBut_DeleteInvoiceFile().setToolTipText("A számlatömb törlése csak indokolt esetben lehetséges...");

        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public interface InvoiceFileListeners {

        public void updateData(ArrayList<InvoiceFile> list);
        public void updateInvoiceFileId(int id);

        public void sendSelectedTaxYearId(int id, String name);
    }

    public void initControll() {
        view.getjBut_NewInvoiceFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjCB_TaxYear().getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy adóévet!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    TaxYear defaultCurrency = (TaxYear) view.getjCB_TaxYear().getSelectedItem();
                    int id = defaultCurrency.getId();
                    String taxname = defaultCurrency.getName();
                    if (service.isTaxYearClosedById(id)) {
                        JOptionPane.showMessageDialog(view, "Kiválasztott adóév le van zárva, így erre az időszakra nem lehet új számlatömböt létrehozni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                    ControllerInvoiceFileNew controll = new ControllerInvoiceFileNew(firm);
                    listeners.removeAll(listeners);
                    listeners.add((InvoiceFileListeners) controll);
                    listeners.get(0).sendSelectedTaxYearId(id, taxname);
                    }
                }

            }
        });
        view.getjBut_EditInvoiceFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_InvoiceFile().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (view.getjCB_TaxYear().getSelectedIndex() < 0) {
                        JOptionPane.showMessageDialog(view, "Válasszon ki egy adóévet!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        TaxYear defaultCurrency = (TaxYear) view.getjCB_TaxYear().getSelectedItem();
                        int taxid = defaultCurrency.getId();

                        int row = view.getjTab_InvoiceFile().getSelectedRow();
                        String value = view.getjTab_InvoiceFile().getValueAt(row, 0).toString();
                        int id = Integer.parseInt(value);
                        String taxname = defaultCurrency.getName();
                        ControllerInvoiceFileEdit controll = new ControllerInvoiceFileEdit(firm);
                        controll.setCurrencyId(id);
                        controll.setInvoiceFileId(id);
                        listeners.removeAll(listeners);
                        listeners.add((InvoiceFileListeners) controll);
                        listeners.get(0).updateData(service.getOneInvoiceFile(id));
                        listeners.get(0).sendSelectedTaxYearId(taxid, taxname);
                        listeners.get(0).updateInvoiceFileId(id);
                    }
                }
            }
        });
        view.getjBut_DeleteInvoiceFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_InvoiceFile().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_InvoiceFile().getSelectedRow();
                    String value = view.getjTab_InvoiceFile().getValueAt(row, 0).toString();
                    int id = Integer.parseInt(value);
                    Integer invoiceFileCounter= service.getInvoiceFileCounter(id);
                    
                    if (invoiceFileCounter> 0) {

                        service.deleteInvoiceFile(view, id);
                    }
                    else{
                    JOptionPane.showMessageDialog(view, "A számlatömb törlése nem lehetséges!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }
                    

                }
            }
        });
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        }
        );

        view.getjTab_InvoiceFile().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                // do some actions here, for example
                // print first column value from selected row
                int row = view.getjTab_InvoiceFile().getSelectedRow();
                String invoice = view.getjTab_InvoiceFile().getValueAt(row, 0).toString();
                int taxid=Integer.parseInt(invoice);
                
                setSelectedTaxToComboBox(view.getjCB_TaxYear(), taxid);
            }
        });
        view.getjCB_TaxYear().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TaxYear taxyear = (TaxYear)view.getjCB_TaxYear().getSelectedItem();
                int id =taxyear.getId();
                service.getAllInvoiceFileByTaxYear(id, view.getjTab_InvoiceFile());
                
            }
        });

    }

    public void loadAllTaxYearComboBox(JComboBox box) {
        ServiceTaxYear sc = new ServiceTaxYear();
        ArrayList<TaxYear> list = new ArrayList<>();
        list = sc.getAllTaxYear();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof TaxYear) {
                    TaxYear taxyear = (TaxYear) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(taxyear.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void setSelectedTaxToComboBox(JComboBox box, int id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<TaxYear> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((TaxYear) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == id) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }
}
