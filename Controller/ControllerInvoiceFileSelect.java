package InvoiceProgram.Controller;

import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Service.ServiceInvoiceFile;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.View.InvoiceFileSelect;
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

public class ControllerInvoiceFileSelect {

    private final InvoiceFileSelect view;
    private final ServiceInvoiceFile service;
    private ArrayList<InvoiceFileSelectListeners> listeners = new ArrayList<>();
    private String firm;

    public ControllerInvoiceFileSelect(String firm) {
        view = new InvoiceFileSelect();
        service = new ServiceInvoiceFile();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Kiválasztás: Számlatömb");
        loadAllTaxYearComboBox(view.getjCB_TaxYear());
        int size = view.getjCB_TaxYear().getItemCount();
        view.getjCB_TaxYear().setSelectedIndex(size - 1);
        service.getAllInvoiceFileSelector(view.getjTab_InvoiceFile());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public interface InvoiceFileSelectListeners {

        public void updateData(int id);
    }

    public void initControll() {
        view.getjBut_Ok().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_InvoiceFile().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_InvoiceFile().getSelectedRow();

                    String value = view.getjTab_InvoiceFile().getValueAt(row, 0).toString();
                    int invFileId = Integer.parseInt(value);
                    if (service.isTaxYearClosed(invFileId)) {
                        JOptionPane.showMessageDialog(view, "Kiválasztott adóév le van zárva, így erre az időszakra nem állítható ki új számla!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {
                        InvoiceFile invFile = service.getOneInvoiceFileById(invFileId);
                        if (invFile.isIsActive()) {
                            JOptionPane.showMessageDialog(view, "Kiválasztott számlatömb le van zárva, nem állítható ki új számla!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        } else {
                            ControllerInvoice controll = new ControllerInvoice(firm);
                            listeners.removeAll(listeners);
                            listeners.add((InvoiceFileSelectListeners) controll);
                            listeners.get(0).updateData(invFileId);
                        }
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
        view.getjCB_TaxYear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaxYear taxyear = (TaxYear) view.getjCB_TaxYear().getSelectedItem();
                int id = taxyear.getId();
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

}
