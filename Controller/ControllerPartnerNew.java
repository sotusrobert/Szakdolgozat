package InvoiceProgram.Controller;

import InvoiceProgram.Model.CompanyAddress;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Currency;
import InvoiceProgram.Model.Division;
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
import InvoiceProgram.View.PartnerNew;
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

public class ControllerPartnerNew {

    private final PartnerNew view;
    private final ServicePartner service;
    //private ArrayList<BankListeners> listeners = new ArrayList<>();
    private String firm;

    public ControllerPartnerNew(String firm) {
        view = new PartnerNew();
        service = new ServicePartner();
        this.firm=firm;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firm+" - Új partner");
        loadAllCountriesToComboBox(view.getjCB_county());
        loadAllCountriesToComboBox(view.getjCB_postCountry());
        loadAllCountriesToComboBox(view.getjCB_departmentCountry());
        loadAllCountriesToComboBox(view.getjCB_departmentPostCountry());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_publicPlaceKind());
        loadAllTransactionTypeToComboBox(view.getjCB_transactiontype());
        loadAllPaymentToComboBox(view.getjCB_payment());
        loadAllCurrencyToComboBox(view.getjCB_currency());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_departmentPublicPlaceKind());
        setSelectedCountriesToComboBox(view.getjCB_county(),"HU");
        setSelectedCountriesToComboBox(view.getjCB_postCountry(), "HU");
        setSelectedCountriesToComboBox(view.getjCB_departmentCountry(),"HU");
        setSelectedCountriesToComboBox(view.getjCB_departmentPostCountry(),"HU");
        setSelectedPublicPlaceToComboBox(view.getjCB_departmentPublicPlaceKind(),"utca");
        setSelectedPublicPlaceToComboBox(view.getjCB_publicPlaceKind(),"utca");
        view.getjTF_departmentAddress().setEditable(false);
        view.getjTF_address().setEditable(false);
        view.getjBut_NewDepartment().setEnabled(false);
        view.getjBut_EditDepartment().setEnabled(false);
        view.getjBut_OpenDepartment().setEnabled(false);
        view.getjBut_DeleteDepartment().setEnabled(false);
        view.getjBut_NewDepartment().setToolTipText("Nem hozható létre külön a telephely új partner esetén!");
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

            }
        });
        view.getjBut_NewDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view.getjBut_OpenDepartment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                        "Menteni akarja az új partnert?",
                        "Figyelem",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    //partner data
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
                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_zipcode().getText()), view.getjTF_city().getText());
                        zipid = serviceZips.getLatestZipId();
                    } else {
                        zipid = serviceZips.getOneZipIdByCode(zipcode);
                    }

                    if (!serviceZips.getOneZipByCode(postzipcode)) {
                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_zipcode().getText()), view.getjTF_postCity().getText());
                        postzipid = serviceZips.getLatestZipId();
                    } else {
                        postzipid = serviceZips.getOneZipIdByCode(postzipcode);
                    }

                    
                    service.newPartner(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup, isActive, transactionTypeid, paymentid, currencyid, isVATRegistrated, isCashAccounting, isKATA, isNaturalPerson);
                    service.newPartnerAddress(view, countryId, regio, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWay, storey, doornumber, district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                    //partner department, ha a név mező ki van töltve
                    if (!view.getjTF_departmentNameOnVoucer().getText().isEmpty()) {
                        int zipid2 = 0;
                        int postzipid2 = 0;

                        String name2 = view.getjTF_departmentNameOnVoucer().getText();
                        Country country2 = (Country) view.getjCB_departmentCountry().getSelectedItem();
                        String countryId2 = country2.getId();
                        PublicPlace publicPlace2 = (PublicPlace) view.getjCB_departmentPublicPlaceKind().getSelectedItem();
                        int publicplaceId2 = publicPlace2.getId();
                        String region2 = view.getjTF_departmentRegion().getText();
                        int zipCode2 = Integer.parseInt(view.getjF_departmentZipcode().getText());
                        String city2 = view.getjTF_departmentCity().getText();
                        String district2 = view.getjTF_departmentDistrict().getText();
                        String publicPlaceName2 = view.getjTF_departmentPublicPlaceName().getText();
                        int publicPlaceNumber2 = Integer.parseInt(view.getjTF_departmentPubicPlaceNumber().getText());
                        String building2 = view.getjTF_departmentBuilding().getText();
                        String storey2 = view.getjTF_departmentStorey().getText();
                        String stairWayDiv2 = view.getjTF_departmentStairWay().getText();
                        int doornumber2 = Integer.parseInt(view.getjTF_departmentDoornumber().getText());
                        Country postcountry2 = (Country) view.getjCB_departmentPostCountry().getSelectedItem();
                        String postcountryId2 = postcountry2.getId();
                        int postzipCode2 = Integer.parseInt(view.getjTF_departmentPostZipcode().getText());
                        String postcity2 = view.getjTF_departmentPostCity().getText();
                        String postaddress2 = view.getjTF_departmentPostAddress().getText();
                        String postregion2 = view.getjTF_departmentPostRegion().getText();
                        

                        if (!serviceZips.getOneZipByCode(zipCode2)) {
                            serviceZips.newZip(view, countryId,Integer.parseInt(view.getjF_departmentZipcode().getText()) , city2);
                            zipid2 = serviceZips.getLatestZipId();
                        } else {
                            zipid2 = serviceZips.getOneZipIdByCode(zipCode2);
                        }
                        if (!serviceZips.getOneZipByCode(postzipCode2)) {
                            serviceZips.newZip(view, countryId,Integer.parseInt(view.getjF_departmentZipcode().getText()), postcity2);
                            postzipid2 = serviceZips.getLatestZipId();
                        } else {
                            postzipid2 = serviceZips.getOneZipIdByCode(postzipCode2);
                        }
                        service.newDepartment(view, name2);
                        service.newDepartmentAddess(view, countryId2, region2, zipid2, publicPlaceName2, publicplaceId2, publicPlaceNumber2, building2, stairWayDiv2, storey2, doornumber2, district2, postcountryId2, postzipid2, postaddress2, postregion2);
                        //service.getAllDepartment(view.getjTab_Department());
                    }
                    
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

    public void setSelectedCountriesToComboBox(JComboBox box, String countryId) {

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

    public void setSelectedCountriesToComboBoxDiv(JComboBox box, ArrayList<Division> list) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Country> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Country) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(list.get(0).getAddress().getAddress().getCountry().getId())) {
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

}
