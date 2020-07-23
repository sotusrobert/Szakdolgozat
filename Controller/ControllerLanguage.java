package InvoiceProgram.Controller;

import InvoiceProgram.Model.Language;
import InvoiceProgram.Service.ServiceLanguage;
import InvoiceProgram.View.Language_GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class ControllerLanguage {

    private final Language_GUI view;
    private final ServiceLanguage service;
    private ArrayList<LanguageListeners> listeners = new ArrayList<>();

    public ControllerLanguage() {
        view = new Language_GUI();
        service = new ServiceLanguage();
        initView();
        initControll();
    }

    public final void initView() {
        service.getAllLanguage(view.getjTab_Languages());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public interface LanguageListeners {

        public void updateData(ArrayList<Language> list);
    }

    public final void initControll() {
        view.getjB_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLanguageNew controller = new ControllerLanguageNew();
                service.getAllLanguage(view.getjTab_Languages());
            }
        });
        view.getjB_Edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Languages().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Languages().getSelectedRow();
                    String value = view.getjTab_Languages().getValueAt(row, 0).toString();

                    ControllerLanguageEdit controll = new ControllerLanguageEdit();
                    listeners.removeAll(listeners);
                    listeners.add(controll);
                    listeners.get(0).updateData(service.getOneLanguage(value));
                    for (LanguageListeners item : listeners) {
                        //System.out.println(item.toString());
                    }

                }
                service.getAllLanguage(view.getjTab_Languages());
            }
        });
        view.getjB_Delete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Languages().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Languages().getSelectedRow();
                        String value = view.getjTab_Languages().getValueAt(row, 0).toString();
                        service.deleteLanguage(view, value);
                    }

                    service.getAllLanguage(view.getjTab_Languages());
                }
            }

        });
        view.getjB_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

    }

    public void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
}
