package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerPartner.PartnerListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.Partner;
import InvoiceProgram.Model.PartnerAddress;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.TransactionType;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServiceCurrency;
import InvoiceProgram.Service.ServicePartner;
import InvoiceProgram.Service.ServicePayment;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.PartnerEdit;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ControllerPartnerEdit implements PartnerListeners {

    private final PartnerEdit view;
    private final ServicePartner service;
    private String firm;

    public ControllerPartnerEdit(String firm) {
        view = new PartnerEdit();
        service = new ServicePartner();
         this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Partner módosítás");
        loadAllCountriesToComboBox(view.getjCB_county());
        loadAllCountriesToComboBox(view.getjCB_postCountry());
        loadAllCountriesToComboBox(view.getjCB_departmentCountry());
        loadAllCountriesToComboBox(view.getjCB_departmentPostCountry());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_publicPlaceKind());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_departmentPublicPlaceKind());
        loadAllTransactionTypeToComboBox(view.getjCB_transactiontype());
        loadAllPaymentToComboBox(view.getjCB_payment());
        loadAllCurrencyToComboBox(view.getjCB_currency());
        setSelectedCountriesToComboBoxDep(view.getjCB_departmentCountry(),"HU");
        setSelectedCountriesToComboBoxDep(view.getjCB_departmentPostCountry(),"HU");
        setSelectedPublicPlaceToComboBox(view.getjCB_departmentPublicPlaceKind(),"utca");
        view.getjTF_departmentAddress().setEditable(false);
        view.getjTF_address().setEditable(false);
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjTF_publicPlaceName().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = view.getjTF_publicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_publicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();

                view.getjTF_address().setText(name + " " + place + " " + number);
            }
        });
        view.getjTF_publicPlaceNumber().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = view.getjTF_publicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_publicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();

                view.getjTF_address().setText(name + " " + place + " " + number);
            }
        });

        view.getjCB_publicPlaceKind().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name = view.getjTF_publicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_publicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();

                view.getjTF_address().setText(name + " " + place + " " + number);
            }
        });

        view.getjTF_departmentPublicPlaceName().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = view.getjTF_departmentPublicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_departmentPubicPlaceNumber().getText();

                view.getjTF_departmentAddress().setText(name + " " + place + " " + number);
            }
        });
        view.getjTF_departmentPubicPlaceNumber().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = view.getjTF_departmentPublicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_departmentPubicPlaceNumber().getText();

                view.getjTF_departmentAddress().setText(name + " " + place + " " + number);
            }
        });

        view.getjCB_departmentPublicPlaceKind().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name = view.getjTF_departmentPublicPlaceName().getText();
                PublicPlace ppk = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                String place = ppk.getName();
                String number = view.getjTF_departmentPubicPlaceNumber().getText();

                view.getjTF_departmentAddress().setText(name + " " + place + " " + number);
            }
        });

        view.getjTF_zipcode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_zipcode().getText();
                Country country = (Country) view.getjCB_county().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_city().setText(name);
                }
            }
        });
        view.getjTF_postZipcode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_postZipcode().getText();
                Country country = (Country) view.getjCB_postCountry().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_postCity().setText(name);
                }
            }
        });
        view.getjF_departmentZipcode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjF_departmentZipcode().getText();
                Country country = (Country) view.getjCB_departmentCountry().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_departmentCity().setText(name);
                }
            }
        });
        view.getjTF_departmentPostZipcode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_departmentPostZipcode().getText();
                Country country = (Country) view.getjCB_departmentPostCountry().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_departmentPostCity().setText(name);
                }
            }
        });

        view.getjBu_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjBu_DeleteContact().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_DeleteDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int partnerId = Integer.parseInt(view.getjLab_id().getText());
                if (view.getjTab_Department().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_Department().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_Department().getValueAt(row, 0).toString());

                        service.deleteDepartment(view, value);
                    }

                    service.getAllDepartbentByPartner(view.getjTab_Department(), partnerId);
                }
            }
        });
        view.getjBut_EditContact().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_EditDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Department().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a fenti listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!view.getjTF_departmentNameOnVoucer().getText().isEmpty()) {
                        int partnerid = Integer.parseInt(view.getjLab_id().getText());
                        int zipid = 0;
                        int postzipid = 0;
                        int row = view.getjTab_Department().getSelectedRow();
                        String value = view.getjTab_Department().getValueAt(row, 0).toString();
                        int depId = Integer.parseInt(value);

                        String name = view.getjTF_departmentNameOnVoucer().getText();
                        Country country = (Country) view.getjCB_departmentCountry().getSelectedItem();
                        String countryId = country.getId();
                        PublicPlace publicPlace = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                        int publicplaceId = publicPlace.getId();
                        String region = view.getjTF_departmentRegion().getText();
                        int zipCode = Integer.parseInt(view.getjF_departmentZipcode().getText());
                        String city = view.getjTF_departmentCity().getText();
                        String district = view.getjTF_departmentDistrict().getText();
                        String publicPlaceName = view.getjTF_departmentPublicPlaceName().getText();
                        int publicPlaceNumber = Integer.parseInt(view.getjTF_departmentPubicPlaceNumber().getText());
                        String building = view.getjTF_departmentBuilding().getText();
                        String storey = view.getjTF_departmentStorey().getText();
                        String stairWayDiv = view.getjTF_departmentStairWay().getText();
                        int doornumber = Integer.parseInt(view.getjTF_departmentDoornumber().getText());
                        Country postcountry = (Country) view.getjCB_departmentPostCountry().getSelectedItem();
                        String postcountryId = postcountry.getId();
                        int postzipCode = Integer.parseInt(view.getjTF_departmentPostZipcode().getText());
                        String postcity = view.getjTF_departmentPostCity().getText();
                        String postaddress = view.getjTF_departmentPostAddress().getText();
                        String postregion = view.getjTF_departmentPostRegion().getText();
                        ServiceZips serviceZips = new ServiceZips();

                        if (!serviceZips.getOneZipByCode(zipCode)) {
                            serviceZips.newZip(view, countryId, Integer.parseInt(view.getjF_departmentZipcode().getText()), city);
                            zipid = serviceZips.getLatestZipId();
                        } else {
                            zipid = serviceZips.getOneZipIdByCode(zipCode);
                        }
                        if (!serviceZips.getOneZipByCode(postzipCode)) {
                            serviceZips.newZip(view, countryId, Integer.parseInt(view.getjF_departmentZipcode().getText()), postcity);
                            postzipid = serviceZips.getLatestZipId();
                        } else {
                            postzipid = serviceZips.getOneZipIdByCode(postzipCode);
                        }
                        service.updateDepartment(view, name, depId);
                        service.updateDepartmentAddess(view, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWayDiv, storey, doornumber, district, postcountryId, postzipid, postaddress, postregion, depId);
                        service.getAllDepartbentByPartner(view.getjTab_Department(), partnerid);

                        view.getjTF_departmentNameOnVoucer().setText("");
                        view.getjCB_departmentCountry().setSelectedIndex(0);
                        view.getjCB_departmentPublicPlaceKind().setSelectedIndex(0);
                        view.getjTF_departmentRegion().setText("");
                        view.getjF_departmentZipcode().setText("");
                        view.getjTF_departmentCity().setText("");
                        view.getjTF_departmentDistrict().setText("");
                        view.getjTF_departmentPublicPlaceName().setText("");
                        view.getjTF_departmentPubicPlaceNumber().setText("");
                        view.getjTF_departmentBuilding().setText("");
                        view.getjTF_departmentStorey().setText("");
                        view.getjTF_departmentStairWay().setText("");
                        view.getjTF_departmentDoornumber().setText("");
                        view.getjTF_departmentAddress().setText("");
                        view.getjCB_departmentPostCountry().setSelectedIndex(0);
                        view.getjTF_departmentPostAddress().setText("");
                        view.getjTF_departmentPostCity().setText("");
                        view.getjTF_departmentPostRegion().setText("");
                        view.getjTF_departmentPostZipcode().setText("");
                    } else {
                        JOptionPane.showMessageDialog(view, "A telephely módosításához adja meg a telephely nevét!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }
        });
        view.getjBut_NewDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!view.getjTF_departmentNameOnVoucer().getText().isEmpty()) {
                    int partnerid = Integer.parseInt(view.getjLab_id().getText());
                    int zipid = 0;
                    int postzipid = 0;

                    String name = view.getjTF_departmentNameOnVoucer().getText();
                    Country country = (Country) view.getjCB_departmentCountry().getSelectedItem();
                    String countryId = country.getId();
                    PublicPlace publicPlace = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                    int publicplaceId = publicPlace.getId();
                    String region = view.getjTF_departmentRegion().getText();
                    int zipCode = Integer.parseInt(view.getjF_departmentZipcode().getText());
                    String city = view.getjTF_departmentCity().getText();
                    String district = view.getjTF_departmentDistrict().getText();
                    String publicPlaceName = view.getjTF_departmentPublicPlaceName().getText();
                    int publicPlaceNumber = Integer.parseInt(view.getjTF_departmentPubicPlaceNumber().getText());
                    String building = view.getjTF_departmentBuilding().getText();
                    String storey = view.getjTF_departmentStorey().getText();
                    String stairWayDiv = view.getjTF_departmentStairWay().getText();
                    int doornumber = Integer.parseInt(view.getjTF_departmentDoornumber().getText());
                    Country postcountry = (Country) view.getjCB_departmentPostCountry().getSelectedItem();
                    String postcountryId = postcountry.getId();
                    int postzipCode = Integer.parseInt(view.getjTF_departmentPostZipcode().getText());
                    String postcity = view.getjTF_departmentPostCity().getText();
                    String postaddress = view.getjTF_departmentPostAddress().getText();
                    String postregion = view.getjTF_departmentPostRegion().getText();
                    ServiceZips serviceZips = new ServiceZips();

                    if (!serviceZips.getOneZipByCode(zipCode)) {
                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjF_departmentZipcode().getText()), city);
                        zipid = serviceZips.getLatestZipId();
                    } else {
                        zipid = serviceZips.getOneZipIdByCode(zipCode);
                    }
                    if (!serviceZips.getOneZipByCode(postzipCode)) {
                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjF_departmentZipcode().getText()), postcity);
                        postzipid = serviceZips.getLatestZipId();
                    } else {
                        postzipid = serviceZips.getOneZipIdByCode(postzipCode);
                    }
                    service.newDepartmentByPartnerId(view, name, partnerid);
                    service.newDepartmentAddess(view, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWayDiv, storey, doornumber, district, postcountryId, postzipid, postaddress, postregion);
                    service.getAllDepartbentByPartner(view.getjTab_Department(), partnerid);

                    view.getjTF_departmentNameOnVoucer().setText("");
                    view.getjCB_departmentCountry().setSelectedIndex(0);
                    view.getjCB_departmentPublicPlaceKind().setSelectedIndex(0);
                    view.getjTF_departmentRegion().setText("");
                    view.getjF_departmentZipcode().setText("");
                    view.getjTF_departmentCity().setText("");
                    view.getjTF_departmentDistrict().setText("");
                    view.getjTF_departmentPublicPlaceName().setText("");
                    view.getjTF_departmentPubicPlaceNumber().setText("");
                    view.getjTF_departmentBuilding().setText("");
                    view.getjTF_departmentStorey().setText("");
                    view.getjTF_departmentStairWay().setText("");
                    view.getjTF_departmentDoornumber().setText("");
                    view.getjTF_departmentAddress().setText("");
                    view.getjCB_departmentPostCountry().setSelectedIndex(0);
                    view.getjTF_departmentPostAddress().setText("");
                    view.getjTF_departmentPostCity().setText("");
                    view.getjTF_departmentPostRegion().setText("");
                    view.getjTF_departmentPostZipcode().setText("");

                } else {
                    JOptionPane.showMessageDialog(view, "A telephely létrehozáshoz adja meg a telephely nevét!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        view.getjBut_OpenDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_Department().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a fenti listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_Department().getSelectedRow();
                    String value = view.getjTab_Department().getValueAt(row, 0).toString();
                    int depId = Integer.parseInt(value);
                    String nameV = view.getjTab_Department().getValueAt(row, 1).toString();
                    view.getjTF_departmentNameOnVoucer().setText(nameV);
                    ArrayList<PartnerAddress> list = service.gePartnerAddressByDepartment(depId);

                    setSelectedCountriesToComboBox(view.getjCB_departmentCountry(), list);
                    setSelectedCountriesToComboBoxDep(view.getjCB_departmentPostCountry(), list);
                    setSelectedPublicPlaceToComboBox(view.getjCB_departmentPublicPlaceKind(), list);

                    view.getjTF_departmentRegion().setText(list.get(0).getAddress().getRegio());
                    view.getjF_departmentZipcode().setText(list.get(0).getAddress().getZip().getCode());
                    view.getjTF_departmentCity().setText(list.get(0).getAddress().getZip().getName());
                    view.getjTF_departmentDistrict().setText(list.get(0).getAddress().getDistrict());
                    view.getjTF_departmentPublicPlaceName().setText(list.get(0).getAddress().getPublicPlaceName());
                    view.getjTF_departmentPubicPlaceNumber().setText(String.format("%d", list.get(0).getAddress().getPublicPlaceNumber()));
                    view.getjTF_departmentBuilding().setText(list.get(0).getAddress().getBuilding());
                    view.getjTF_departmentStorey().setText(list.get(0).getAddress().getStorey());
                    view.getjTF_departmentStairWay().setText(list.get(0).getAddress().getStairWay());
                    view.getjTF_departmentDoornumber().setText(String.format("%d", list.get(0).getAddress().getDoornumber()));
                    view.getjTF_departmentAddress().setText(String.format("%s %s, %d", list.get(0).getAddress().getPublicPlaceName(), list.get(0).getAddress().getPublicPlaceKind().getName(), list.get(0).getAddress().getPublicPlaceNumber()));
                    view.getjTF_departmentPostRegion().setText(list.get(0).getAddressPost().getRegion());
                    if (Integer.parseInt(list.get(0).getAddressPost().getZip().getCode()) == 0) {
                        view.getjTF_departmentPostZipcode().setText("");
                    } else {
                        view.getjTF_departmentPostZipcode().setText(list.get(0).getAddressPost().getZip().getCode());
                    }
                    view.getjTF_departmentPostCity().setText(list.get(0).getAddressPost().getZip().getName());
                    view.getjTF_departmentPostAddress().setText(list.get(0).getAddressPost().getAddress());

                }
            }
        });
        view.getjBut_SaveContact().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getjBu_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Igen",
                    "Nem"};
                int n = JOptionPane.showOptionDialog(view,
                        "Menteni akarja a partner módosításokat?",
                        "Figyelem",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    //partner data
                    int id = Integer.parseInt(view.getjLab_id().getText());
                    String shorName = view.getjTF_shortName().getText();
                    String fullName = view.getjTF_fullName().getText();
                    String voucherName = view.getjTF_nameOnVoucher().getText();
                    String VATNumber = view.getjTF_VATNumber().getText();
                    String VATNumberEU = view.getjTF_VATNumberEU().getText();
                    String VATNumberGroup = view.getjTF_VATNumberGroup().getText();
                    boolean isActive = view.getjCB_isActive().isSelected();
                    TransactionType transactionType = (TransactionType) view.getjCB_transactiontype().getSelectedItem();
                    int transactionTypeid = transactionType.getId();
                    Payment defaultPayment = (Payment) view.getjCB_payment().getSelectedItem();
                    int paymentid = defaultPayment.getId();
                    Currency defaultCurrency = (Currency) view.getjCB_currency().getSelectedItem();
                    String currencyid = defaultCurrency.getId();
                    boolean isVATRegistrated = view.getjCB_isVATRegistrated().isSelected();
                    boolean isCashAccounting = view.getjCB_sCashAccounting().isSelected();
                    boolean isKATA = view.getjCB_isKATA().isSelected();
                    boolean isNaturalPerson = view.getjCB_isNaturalPerson().isSelected();

                    //partner address
                    int zipid = 0;
                    int postzipid = 0;
                    Country country = (Country) view.getjCB_county().getSelectedItem();
                    String countryId = country.getId();
                    String regio = view.getjTF_region().getText();
                    int zipcode = Integer.parseInt(view.getjTF_zipcode().getText());
                    String publicPlaceName = view.getjTF_publicPlaceName().getText();
                    PublicPlace publicPlace = (PublicPlace) view.getjCB_publicPlaceKind().getSelectedItem();
                    int publicplaceId = publicPlace.getId();
                    int publicPlaceNumber = Integer.parseInt(view.getjTF_publicPlaceNumber().getText());
                    String building = view.getjTF_building().getText();
                    String stairWay = view.getjTF_stairWay().getText();
                    String storey = view.getjTF_storey().getText();
                    int doornumber = Integer.parseInt(view.getjTF_doornumber().getText());
                    String district = view.getjTF_dictrict().getText();
                    boolean isPostInherited = view.getjCB_isEqualRealAddress().isSelected();
                    Country countrypost = (Country) view.getjCB_postCountry().getSelectedItem();
                    String postcountryId = countrypost.getId();
                    int postzipcode = Integer.parseInt(view.getjTF_postZipcode().getText());
                    String postaddress = view.getjTF_postAddress().getText();
                    String postregio = view.getjTF_postRegion().getText();

                    ServiceZips serviceZips = new ServiceZips();

                    if (!serviceZips.getOneZipByCode(zipcode)) {
                        serviceZips.newZip(view, countryId,Integer.parseInt(view.getjTF_zipcode().getText()) , view.getjTF_city().getText());
                        zipid = serviceZips.getLatestZipId();
                    } else {
                        zipid = serviceZips.getOneZipIdByCode(zipcode);
                    }

                    if (!serviceZips.getOneZipByCode(postzipcode)) {
                        serviceZips.newZip(view, countryId,Integer.parseInt(view.getjTF_postZipcode().getText()) , view.getjTF_postCity().getText());
                        postzipid = serviceZips.getLatestZipId();
                    } else {
                        postzipid = serviceZips.getOneZipIdByCode(postzipcode);
                    }

                    service.updatePartner(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup, isActive, transactionTypeid, paymentid, currencyid, isVATRegistrated, isCashAccounting, isKATA, isNaturalPerson, id);
                    service.updatePartnerAddress(view, countryId, regio, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWay, storey, doornumber, district, isPostInherited, postcountryId, postzipid, postaddress, postregio, id);

                    view.dispose();
                }
            }
        });

        view.getjCB_isEqualRealAddress().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_isEqualRealAddress().isSelected()) {
                    view.getjCB_postCountry().setEnabled(false);
                    view.getjCB_postCountry().setSelectedIndex(view.getjCB_county().getSelectedIndex());
                    view.getjTF_postCity().setText(view.getjTF_city().getText());
                    view.getjTF_postCity().setEnabled(false);
                    view.getjTF_postAddress().setText(view.getjTF_address().getText());
                    view.getjTF_postAddress().setEnabled(false);
                    view.getjTF_postRegion().setText(view.getjTF_region().getText());
                    view.getjTF_postRegion().setEnabled(false);
                    view.getjTF_postZipcode().setText(view.getjTF_zipcode().getText());
                    view.getjTF_postZipcode().setEnabled(false);
                } else {
                    view.getjCB_postCountry().setEnabled(true);
                    view.getjTF_postCity().setEnabled(true);
                    view.getjTF_postAddress().setEnabled(true);
                    view.getjTF_postAddress().setText("");
                    view.getjTF_postCity().setText("");
                    view.getjTF_postRegion().setEnabled(true);
                    view.getjTF_postRegion().setText("");
                    view.getjTF_postZipcode().setEnabled(true);
                    view.getjTF_postZipcode().setText("");

                }
            }
        });
        view.getjCB_departmentIsEqualRealAddress().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_departmentIsEqualRealAddress().isSelected()) {
                    view.getjCB_departmentCountry().setSelectedIndex(view.getjCB_county().getSelectedIndex());
                    view.getjCB_departmentPublicPlaceKind().setSelectedIndex(view.getjCB_publicPlaceKind().getSelectedIndex());
                    view.getjTF_departmentRegion().setText(view.getjTF_region().getText());
                    view.getjF_departmentZipcode().setText(view.getjTF_zipcode().getText());
                    view.getjTF_departmentCity().setText(view.getjTF_city().getText());
                    view.getjTF_departmentDistrict().setText(view.getjTF_dictrict().getText());
                    view.getjTF_departmentPublicPlaceName().setText(view.getjTF_publicPlaceName().getText());
                    view.getjTF_departmentPubicPlaceNumber().setText(view.getjTF_publicPlaceNumber().getText());
                    view.getjTF_departmentBuilding().setText(view.getjTF_building().getText());
                    view.getjTF_departmentStorey().setText(view.getjTF_storey().getText());
                    view.getjTF_departmentStairWay().setText(view.getjTF_stairWay().getText());
                    view.getjTF_departmentDoornumber().setText(view.getjTF_doornumber().getText());
                    view.getjTF_departmentAddress().setText(view.getjTF_address().getText());
                } else {
                    view.getjTF_departmentRegion().setText("");
                    view.getjF_departmentZipcode().setText("");
                    view.getjTF_departmentCity().setText("");
                    view.getjTF_departmentDistrict().setText("");
                    view.getjTF_departmentPublicPlaceName().setText("");
                    view.getjTF_departmentPubicPlaceNumber().setText("");
                    view.getjTF_departmentBuilding().setText("");
                    view.getjTF_departmentStorey().setText("");
                    view.getjTF_departmentStairWay().setText("");
                    view.getjTF_departmentDoornumber().setText("");
                    view.getjTF_departmentAddress().setText("");
                }
            }
        });

    }

    public void loadAllCountriesToComboBox(JComboBox box) {
        ServiceCountry sc = new ServiceCountry();
        ArrayList<Country> list = new ArrayList<>();
        list = sc.getAllCountry();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
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

    public void loadAllTransactionTypeToComboBox(JComboBox box) {
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
                    TransactionType transactions = (TransactionType) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(transactions.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllPaymentToComboBox(JComboBox box) {
        ServicePayment sc = new ServicePayment();
        ArrayList<Payment> list = new ArrayList<>();
        list = sc.getAllPayment();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Payment) {
                    Payment payments = (Payment) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(payments.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllCurrencyToComboBox(JComboBox box) {
        ServiceCurrency sc = new ServiceCurrency();
        ArrayList<Currency> list = new ArrayList<>();
        list = sc.getAllCurrency();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
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

    public void loadAllPublicPlaceKindsToComboBox(JComboBox box) {
        ServicePublicPlace pp = new ServicePublicPlace();
        ArrayList<PublicPlace> list = new ArrayList<>();
        list = pp.getAllPublicPlace();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
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

    public void setSelectedCountriesToComboBox(JComboBox box, ArrayList<PartnerAddress> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Country> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Country) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(list.get(0).getAddress().getCountry().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedCountriesToComboBoxPost(JComboBox box, ArrayList<PartnerAddress> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Country> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Country) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(list.get(0).getAddressPost().getCountry().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedPublicPlaceToComboBox(JComboBox box, ArrayList<PartnerAddress> list) {
        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<PublicPlace> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((PublicPlace) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getAddress().getPublicPlaceKind().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedCountriesToComboBoxDep(JComboBox box, ArrayList<PartnerAddress> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Country> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Country) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(list.get(0).getAddressPost().getCountry().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }
    
    public void setSelectedCountriesToComboBoxDep(JComboBox box, String countryId) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Country> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Country) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(countryId)) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedPublicPlaceToComboBoxDiv(JComboBox box, ArrayList<Division> list) {
        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<PublicPlace> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((PublicPlace) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getAddress().getAddress().getPublicPlaceKind().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }
    
    public void setSelectedPublicPlaceToComboBox(JComboBox box, String street) {
        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<PublicPlace> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((PublicPlace) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getName().equals(street)) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedTransactionToComboBox(JComboBox box, int id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<TransactionType> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((TransactionType) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == id) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedPaymentToComboBox(JComboBox box, int id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Payment> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Payment) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == id) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedCurrencyToComboBox(JComboBox box, String id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Currency> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Currency) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(id)) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    @Override
    public void updateData(ArrayList<Partner> list) {
        setSelectedTransactionToComboBox(view.getjCB_transactiontype(), list.get(0).getTransactionType().getId());
        setSelectedPaymentToComboBox(view.getjCB_payment(), list.get(0).getDefaultPayment().getId());
        setSelectedCurrencyToComboBox(view.getjCB_currency(), list.get(0).getDefaultCurrency().getId());
        view.getjLab_id().setText(String.format("%d", list.get(0).getId()));
        view.getjTF_shortName().setText(list.get(0).getShortName());
        view.getjTF_fullName().setText(list.get(0).getFullName());
        view.getjTF_nameOnVoucher().setText(list.get(0).getShortName());
        view.getjTF_VATNumber().setText(list.get(0).getVATNumber());
        view.getjTF_VATNumberEU().setText(list.get(0).getVATNumberEU());
        view.getjTF_VATNumberGroup().setText(list.get(0).getVATNumberGroup());
        view.getjCB_isActive().setSelected(list.get(0).isIsActive());
        view.getjCB_isKATA().setSelected(list.get(0).isIsKATA());
        view.getjCB_isNaturalPerson().setSelected(list.get(0).isIsNaturalPerson());
        view.getjCB_isVATRegistrated().setSelected(list.get(0).isIsVATRegistrated());
        view.getjCB_sCashAccounting().setSelected(list.get(0).isIsCashAccounting());
        ArrayList<PartnerAddress> address = service.gePartnerAddress(list.get(0).getId());

        setSelectedCountriesToComboBox(view.getjCB_county(), address);
        setSelectedPublicPlaceToComboBox(view.getjCB_publicPlaceKind(), address);
        //setSelectedCountriesToComboBoxPost(view.getjCB_postCountry(), address);

        view.getjTF_region().setText(address.get(0).getAddress().getRegio());
        view.getjTF_zipcode().setText(address.get(0).getAddress().getZip().getCode());
        view.getjTF_city().setText(address.get(0).getAddress().getZip().getName());
        view.getjTF_dictrict().setText(address.get(0).getAddress().getDistrict());
        view.getjTF_publicPlaceName().setText(address.get(0).getAddress().getPublicPlaceName());
        view.getjTF_publicPlaceNumber().setText(String.format("%d", address.get(0).getAddress().getPublicPlaceNumber()));
        view.getjTF_building().setText(address.get(0).getAddress().getBuilding());
        view.getjTF_storey().setText(address.get(0).getAddress().getStorey());
        view.getjTF_stairWay().setText(address.get(0).getAddress().getStairWay());
        view.getjTF_doornumber().setText(String.format("%d", address.get(0).getAddress().getDoornumber()));
        view.getjCB_isEqualRealAddress().setSelected(address.get(0).isIsPostInherited());
        view.getjTF_address().setText(String.format("%s %s, %d", address.get(0).getAddress().getPublicPlaceName(), address.get(0).getAddress().getPublicPlaceKind().getName(), address.get(0).getAddress().getPublicPlaceNumber()));
        if (address.get(0).isIsPostInherited()) {
            setSelectedCountriesToComboBox(view.getjCB_postCountry(), address);

            view.getjTF_postZipcode().setText(address.get(0).getAddress().getZip().getCode());

            view.getjTF_postCity().setText(address.get(0).getAddress().getZip().getName());

            view.getjTF_postAddress().setText(String.format("%s %s, %d", address.get(0).getAddress().getPublicPlaceName(), address.get(0).getAddress().getPublicPlaceKind().getName(), address.get(0).getAddress().getPublicPlaceNumber()));

            view.getjTF_postRegion().setText(address.get(0).getAddress().getRegio());
            view.getjTF_postZipcode().setEnabled(false);
            view.getjTF_postRegion().setEnabled(false);
            view.getjTF_postAddress().setEnabled(false);
            view.getjTF_postCity().setEnabled(false);
            view.getjCB_postCountry().setEnabled(false);
        } else {
            view.getjTF_postZipcode().setText(address.get(0).getAddressPost().getZip().getCode());

            view.getjTF_postCity().setText(address.get(0).getAddressPost().getZip().getName());

            view.getjTF_postAddress().setText(address.get(0).getAddressPost().getAddress());

            view.getjTF_postRegion().setText(address.get(0).getAddressPost().getRegion());

        }

        service.getAllDepartbentByPartner(view.getjTab_Department(), list.get(0).getId());

    }

}
