package InvoiceProgram.Controller;

import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Service.ServiceBank;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.BankNew;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;

public final class ControllerBankNew {

    private final BankNew view;
    private final ServiceBank service;
    private String firm;

    public ControllerBankNew(String firm) {
        view = new BankNew();
        service = new ServiceBank();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm + " - Új Bank");
        loadAllCountriesToComboBox();
        view.getjTF_Swift().setToolTipText("Felépítése: 1-6 karakter: csak nagy betű , 7-8 nagy betű vagy egész szám, 9-11: csak nagy betű");
        view.getjTF_ZiipCode().setToolTipText("Csak egész számból állhat!");
        view.getjTF_PublicPlaceNumber().setToolTipText("Csak egész számból állhat!");
        view.getjCB_Country().setSelectedIndex(0);
        loadAllPublicPlaceKindsToComboBox();
        view.getjCB_PublicPlaceKind().setSelectedIndex(0);
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {

        view.getjTF_ZiipCode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_ZiipCode().getText();
                Country country = (Country) view.getjCB_Country().getSelectedItem();
                String countryId = country.getId();
                System.out.println(countryId);
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_City().setText(name);
                }
            }
        });

        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int zipid = 0;
                String name = view.getjTF_name().getText();
                String swift = view.getjTF_Swift().getText();
                Country country = (Country) view.getjCB_Country().getSelectedItem();
                String countryId = country.getId();
                String region = view.getjTF_Region().getText();
                String zipCode = view.getjTF_ZiipCode().getText();
                String city = view.getjTF_City().getText();
                String publicPlaceName = view.getjTF_PublicPlaceName().getText();
                PublicPlace publicPlace = (PublicPlace) view.getjCB_PublicPlaceKind().getSelectedItem();
                int publicplaceId = publicPlace.getId();
                int publicPlaceNumber = 0;
                ServiceZips serviceZips = new ServiceZips();
                if (name.isEmpty() && swift.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés és a SWIFT kód megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A megnevezés megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                } else if (swift.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "A SWIFT kód megadása kötelező!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                } else if (!service.isValidSwift(swift)) {

                    JOptionPane.showMessageDialog(view, "Érvénytelen SWIFT kód!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                } else {

                    if (!zipCode.isEmpty() && !view.getjTF_PublicPlaceNumber().getText().isEmpty()) {

                        if (!serviceZips.isValidZip(zipCode) && !serviceZips.isValidZip(view.getjTF_PublicPlaceNumber().getText())) {
                            JOptionPane.showMessageDialog(view, "Érvénytelen irányítószám és házszám", "Figyelem", JOptionPane.ERROR_MESSAGE);

                        } else if (!serviceZips.isValidZip(zipCode)) {
                            JOptionPane.showMessageDialog(view, "Érvénytelen irányítószám", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        } else if (!serviceZips.isValidZip(view.getjTF_PublicPlaceNumber().getText())) {
                            JOptionPane.showMessageDialog(view, "Érvénytelen házszám", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        } else {
                            publicPlaceNumber = Integer.parseInt(view.getjTF_PublicPlaceNumber().getText());
                            if (!serviceZips.isAvailableZip(countryId, Integer.parseInt(zipCode))) {

                                serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_ZiipCode().getText()), city);
                                zipid = serviceZips.getLatestZipId();
                                System.out.println(zipid);
                                service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
                                view.dispose();
                            } else {
                                zipid = serviceZips.getOneZipIdByCode(countryId, Integer.parseInt(zipCode));
                                System.out.println(zipid);
                                service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
                                view.dispose();

                            }
                        }

                    } else if (!zipCode.isEmpty()) {
                        if (!serviceZips.isValidZip(zipCode)) {
                            JOptionPane.showMessageDialog(view, "Érvénytelen irányítószám", "Figyelem", JOptionPane.ERROR_MESSAGE);

                        } else if (!serviceZips.isAvailableZip(countryId, Integer.parseInt(zipCode))) {

                            serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_ZiipCode().getText()), city);
                            zipid = serviceZips.getLatestZipId();
                            System.out.println(zipid);
                            service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
                            view.dispose();

                        } else {
                            zipid = serviceZips.getOneZipIdByCode(countryId, Integer.parseInt(zipCode));
                            System.out.println(zipid);
                            service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
                            view.dispose();

                        }

                    } else if (!view.getjTF_PublicPlaceNumber().getText().isEmpty()) {

                        if (!serviceZips.isValidZip(view.getjTF_PublicPlaceNumber().getText())) {
                            JOptionPane.showMessageDialog(view, "Érvénytelen házszám", "Figyelem", JOptionPane.ERROR_MESSAGE);
                        } else {
                            publicPlaceNumber = Integer.parseInt(view.getjTF_PublicPlaceNumber().getText());
                            service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
                            view.dispose();
                        }
                    } else {

                        zipid = serviceZips.getOneZipIdByCode(countryId, Integer.parseInt(zipCode));
                        service.newBank(view, name, swift, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber);
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

    public void loadAllCountriesToComboBox() {
        ServiceCountry sc = new ServiceCountry();
        ArrayList<Country> list = new ArrayList<>();
        list = sc.getAllCountry();

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_Country().getModel();
        view.getjCB_Country().removeAllItems();
        view.getjCB_Country().revalidate();
        model.addAll(list);

        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_Country().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Country) {
                    Country countries = (Country) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(countries.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllPublicPlaceKindsToComboBox() {
        ServicePublicPlace pp = new ServicePublicPlace();
        ArrayList<PublicPlace> list = new ArrayList<>();
        list = pp.getAllPublicPlace();

        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjCB_PublicPlaceKind().getModel();
        view.getjCB_PublicPlaceKind().removeAllItems();
        view.getjCB_PublicPlaceKind().revalidate();
        model.addAll(list);

        /*for (int i = 0; i < list.size(); i++) {
            view.getjCB_currency().addItem(list.get(i).getId());
        }*/
        view.getjCB_PublicPlaceKind().setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PublicPlace) {
                    PublicPlace publicPlaces = (PublicPlace) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(publicPlaces.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

}
