package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerNewPayment.PaymentEditListeners;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Service.ServicePayment;
import InvoiceProgram.View.Payment_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ControllerPayment implements PaymentEditListeners {
    
    private final Payment_GUI view;
    private final ServicePayment service;
    private ArrayList<PaymentListeners> listeners = new ArrayList<>();
    private final String firm;

    public ControllerPayment(String firm) {
        view = new Payment_GUI();
        service = new ServicePayment();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Fizetési Módok");
        service.getAllPayment(view.getjTab_Payment());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    @Override
    public void updateData(boolean refreash) {
        if (refreash) {
            service.getAllPayment(view.getjTab_Payment());
        }
    }

    public interface PaymentListeners {

        public void updateData(ArrayList<Payment> list);
    }

    public void initControll() {
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerNewPayment controll = new ControllerNewPayment(firm);
            }
        });
        view.getjBut_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjTab_Payment().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Payment().getSelectedRow();
                    String value = view.getjTab_Payment().getValueAt(row, 0).toString();

                    ControllerPaymentEdit controll = new ControllerPaymentEdit(firm);
                    listeners.removeAll(listeners);
                    listeners.add((PaymentListeners) controll);
                    listeners.get(0).updateData(service.getOnePayment(value));

                }
            }
        });
        view.getjBut_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Payment().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Payment().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_Payment().getValueAt(row, 0).toString());
                        service.deletePayment(view, value);
                    }

                    service.getAllPayment(view.getjTab_Payment());
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
}
