package InvoiceProgram.Controller;

import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.View.TransactionType_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerTransactionType {

    private final TransactionType_GUI view;
    private final ServiceTransactionType service;
    //private ArrayList<PaymentListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerTransactionType(String firm) {
        view = new TransactionType_GUI();
        service = new ServiceTransactionType();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Ügyelettípusok");
        service.getAllTransactionType(view.getjTab_TransactionType());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getjTF_name().getText();
                if (!name.isEmpty()) {
                    boolean isactive = view.getjCheckBox_isActive().isSelected();
                    service.newTransactionType(view, name, isactive);
                    service.getAllTransactionType(view.getjTab_TransactionType());
                    view.getjTF_name().setText("");
                    view.getjCheckBox_isActive().setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(view, "Az ügylettípus nevét kötelező megadni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        view.getjBut_Open().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjTab_TransactionType().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_TransactionType().getSelectedRow();
                    view.getjTF_name().setText(view.getjTab_TransactionType().getValueAt(row, 1).toString());
                    view.getjLab_Id().setText(view.getjTab_TransactionType().getValueAt(row, 0).toString());
                    view.getjCheckBox_isActive().setSelected((boolean) view.getjTab_TransactionType().getValueAt(row, 2));

                }
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!view.getjTF_name().getText().isEmpty() ) {
                    String name = view.getjTF_name().getText();
                    boolean isactive = view.getjCheckBox_isActive().isSelected();
                    int id = Integer.parseInt(view.getjLab_Id().getText());

                    service.updateTransactionType(view, name, isactive, id);
                    service.getAllTransactionType(view.getjTab_TransactionType());
                    view.getjTF_name().setText("");
                    view.getjCheckBox_isActive().setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(view, "Az ügylettípus nevét kötelező megadni!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_TransactionType().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
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
                        int row = view.getjTab_TransactionType().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_TransactionType().getValueAt(row, 0).toString());
                        service.deleteTransactionType(view, value);
                    }

                    service.getAllTransactionType(view.getjTab_TransactionType());

                }

            }
        });
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
}
