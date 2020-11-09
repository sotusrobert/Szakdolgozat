package InvoiceProgram.Controller;

import InvoiceProgram.Model.CompanyAddress;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Division;
import InvoiceProgram.Model.Firm;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServiceFirm;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.Service.ServiceZips;
import InvoiceProgram.View.FirmEdit;
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

public final class ControllerFirmEdit {

    private final FirmEdit view;
    private final ServiceFirm service;
    private String firm;

    public ControllerFirmEdit(String firm) {
        view = new FirmEdit();
        service = new ServiceFirm();
        this.firm = firm;
        initView();
        initControll();
    }

    public final void initView() {
        view.getjLab_Firm().setText(firm);
        view.getjTF_address().setEditable(false);
        view.getjTF_addressDiv().setEditable(false);
        view.getjTF_VatNumber().setToolTipText("Az adószám felépítése: 8 számjegy + kötőjel + 1 számjegy azonosító + 2 számjegy megyekód");
        view.getjTF_VatNumberEU().setToolTipText("A közösségi adószám felépítése: HU + 8 számjegy");
        view.getjTF_VatNumberGroup().setToolTipText("Az adószám felépítése: 8 számjegy + kötőjel + 1 számjegy azonosító + 2 számjegy megyekód");
        view.getjTF_Zipcode().setToolTipText("Csak egész számból állhat!");
        view.getjTF_ZipcodeDiv().setToolTipText("Csak egész számból állhat!");
        view.getjTF_postZipcode().setToolTipText("Csak egész számból állhat!");
        view.getjTF_postZipcodeDiv().setToolTipText("Csak egész számból állhat!");
        view.getjTF_doornumber().setToolTipText("Csak egész számból állhat!");
        view.getjTF_doornumberDiv().setToolTipText("Csak egész számból állhat!");
        view.getjTF_publicPlaceNumber().setToolTipText("Csak egész számból állhat!");
        view.getjTF_publicPlaceNumberDiv().setToolTipText("Csak egész számból állhat!");
        loadFirm();
        loadAllCountriesToComboBox(view.getjB_Country());
        loadAllCountriesToComboBox(view.getjCB_CountryDiv());
        loadAllCountriesToComboBox(view.getjCB_postCountry());
        loadAllCountriesToComboBox(view.getjCB_postCountryDiv());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_publicPlaceKind());
        loadAllPublicPlaceKindsToComboBox(view.getjCB_publicPlaceKindDiv());
        setSelectedCountriesToComboBox(view.getjCB_CountryDiv(),"HU");
        setSelectedCountriesToComboBox(view.getjCB_postCountryDiv(),"HU");
        setSelectedPublicPlaceToComboBoxDiv(view.getjCB_publicPlaceKindDiv(),"utca");
        view.getjCB_publicPlaceKindDiv().setSelectedIndex(157);
        view.getjCB_postCountryDiv().setSelectedIndex(10);
        view.getjCB_postCountryDiv().setSelectedIndex(10);
        loadCompanyAddress();
        service.getAllDivision(view.getjTab_division());
        view.setLocationRelativeTo(null);
        view.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        view.setVisible(true);
    }

    public void initControll() {
        view.getjTF_publicPlaceName().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name= view.getjTF_publicPlaceName().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKind().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();
                
                view.getjTF_address().setText(name+" "+place+" "+number);
            }
        });
        view.getjTF_publicPlaceNumber().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name= view.getjTF_publicPlaceName().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKind().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();
                
                view.getjTF_address().setText(name+" "+place+" "+number);
            }
        });
        
        view.getjCB_publicPlaceKind().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name= view.getjTF_publicPlaceName().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKind().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumber().getText();
                
                view.getjTF_address().setText(name+" "+place+" "+number);
            }
        });
        
        
        
        view.getjTF_publicPlaceNameDiv().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name= view.getjTF_publicPlaceNameDiv().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKindDiv().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumberDiv().getText();
                
                view.getjTF_addressDiv().setText(name+" "+place+" "+number);
            }
        });
        view.getjTF_publicPlaceNumberDiv().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name= view.getjTF_publicPlaceNameDiv().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKindDiv().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumberDiv().getText();
                
                view.getjTF_addressDiv().setText(name+" "+place+" "+number);
            }
        });
        
        view.getjCB_publicPlaceKindDiv().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name= view.getjTF_publicPlaceNameDiv().getText();
                PublicPlace  ppk =(PublicPlace)view.getjCB_publicPlaceKindDiv().getSelectedItem();
                String place= ppk.getName();
                String number = view.getjTF_publicPlaceNumberDiv().getText();
                
                view.getjTF_addressDiv().setText(name+" "+place+" "+number);
            }
        });
        
        
        
        
        view.getjBut_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Igen",
                    "Nem"};
                int n = JOptionPane.showOptionDialog(view,
                        "Valóban módosítani akarj az adatokat?",
                        "Figyelem",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    //company data
                    String shorName = view.getjTF_shortName().getText();
                    String fullName = view.getjTF_fullName().getText();
                    String voucherName = view.getjTF_nameOnVoucher().getText();
                    String VATNumber = view.getjTF_VatNumber().getText();
                    String VATNumberEU = view.getjTF_VatNumberEU().getText();
                    String VATNumberGroup = view.getjTF_VatNumberGroup().getText();

                    //company address
                    int zipid = 0;
                    int postzipid = 0;
                    Country country = (Country) view.getjB_Country().getSelectedItem();
                    String countryId = country.getId();
                    String regio = view.getjTF_region().getText();
                    String zipcode = view.getjTF_Zipcode().getText();
                    String city = view.getjTF_City().getText();
                    String publicPlaceName = view.getjTF_publicPlaceName().getText();
                    PublicPlace publicPlace = (PublicPlace) view.getjCB_publicPlaceKind().getSelectedItem();
                    int publicplaceId = publicPlace.getId();
                    String publicPlaceNumber = view.getjTF_publicPlaceNumber().getText();
                    String building = view.getjTF_building().getText();
                    String stairWay = view.getjTF_stairWay().getText();
                    String storey = view.getjTF_storey().getText();
                    String doornumber = view.getjTF_doornumber().getText();
                    String district = view.getjTF_District().getText();
                    boolean isPostInherited = view.getjCB_isEqualRealAddress().isSelected();
                    Country countrypost = (Country) view.getjCB_postCountry().getSelectedItem();
                    String postcountryId = countrypost.getId();
                    String postzipcode = view.getjTF_postZipcode().getText();
                    String postaddress = view.getjTF_postAddress().getText();
                    String postregio = view.getjTF_postRegion().getText();
                    ServiceZips serviceZips = new ServiceZips();

                    if (shorName.isEmpty() && fullName.isEmpty() && voucherName.isEmpty() && VATNumber.isEmpty() && zipcode.isEmpty()
                            && city.isEmpty() && publicPlaceName.isEmpty() && publicPlaceNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "A '*'-gal megjelölt mezők a megadása kötelező! (Székhely és Egyéb  adatok fül)", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else if (!service.isValidVatNumber(VATNumber)) {
                        JOptionPane.showMessageDialog(view, "Érvénytelen adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else if (!serviceZips.isValidZip(zipcode)) {
                        JOptionPane.showMessageDialog(view, "Érvénytelen irányítószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else if (!service.isValidNumber(publicPlaceNumber)) {
                        JOptionPane.showMessageDialog(view, "Érvénytelen házszám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    } else {

                        if (!VATNumberEU.isEmpty() && !VATNumberGroup.isEmpty()) {

                            if (!service.isValidEUVatNumber(VATNumberEU) && !service.isValidVatNumber(VATNumberGroup)) {
                                JOptionPane.showMessageDialog(view, "Érvénytelen a közösségi adószám és a csoportos adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                            } else if (!service.isValidEUVatNumber(VATNumberEU)) {
                                JOptionPane.showMessageDialog(view, "Érvénytelen  a közösségi adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                            } else if (!service.isValidVatNumber(VATNumberGroup)) {
                                JOptionPane.showMessageDialog(view, "Érvénytelen a csoportos adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                            } else {

                                if (!serviceZips.isAvailableZip(countryId, Integer.parseInt(zipcode))) {

                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }

                                } else {
                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }
                                }

                            }

                        } else if (!VATNumberEU.isEmpty()) {
                            if (!service.isValidEUVatNumber(VATNumberEU)) {
                                JOptionPane.showMessageDialog(view, "Érvénytelen a közösségi adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                            } else {

                                if (!serviceZips.isAvailableZip(countryId, Integer.parseInt(zipcode))) {

                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }

                                } else {
                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }
                                }

                            }

                        } else if (!VATNumberGroup.isEmpty()) {
                            if (!service.isValidEUVatNumber(VATNumberGroup)) {
                                JOptionPane.showMessageDialog(view, "Érvénytelen a csoportos adószám!", "Figyelem", JOptionPane.ERROR_MESSAGE);

                            } else {

                                if (!serviceZips.isAvailableZip(countryId, Integer.parseInt(zipcode))) {

                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }

                                } else {
                                    if (!postzipcode.isEmpty() && !serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {

                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getLatestZipId();
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else if (!postzipcode.isEmpty() && serviceZips.isAvailableZip(postcountryId, Integer.parseInt(postzipcode))) {
                                        serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_Zipcode().getText()), city);
                                        zipid = serviceZips.getLatestZipId();
                                        serviceZips.newZip(view, postcountryId, Integer.parseInt(postzipcode), view.getjTF_postCity().getText());
                                        postzipid = serviceZips.getOneZipIdByCode(postcountryId, Integer.parseInt(postzipcode));

                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    } else {
                                        service.updateFirm(view, shorName, fullName, voucherName, VATNumber, VATNumberEU, VATNumberGroup);
                                        service.updateFirmAddess(view, countryId, regio, zipid, publicPlaceName, publicplaceId, Integer.parseInt(publicPlaceNumber), building, stairWay, storey, Integer.parseInt(doornumber), district, isPostInherited, postcountryId, postzipid, postaddress, postregio);
                                        view.dispose();

                                    }
                                }

                            }

                        }

                    }

                }

            }
        }
        );
        view.getjBut_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjBut_NewDiv().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zipid = 0;
                int postzipid = 0;

                String name = view.getjTF_nameOnVoucherDiv().getText();
                Country country = (Country) view.getjCB_CountryDiv().getSelectedItem();
                String countryId = country.getId();
                PublicPlace publicPlace = (PublicPlace) view.getjCB_publicPlaceKindDiv().getSelectedItem();
                int publicplaceId = publicPlace.getId();
                String region = view.getjTF_RegionDiv().getText();
                int zipCode = Integer.parseInt(view.getjTF_ZipcodeDiv().getText());
                String city = view.getjTF_CityDiv().getText();
                String district = view.getjTF_District().getText();
                String publicPlaceName = view.getjTF_publicPlaceNameDiv().getText();
                int publicPlaceNumber = Integer.parseInt(view.getjTF_publicPlaceNumberDiv().getText());
                String building = view.getjTF_buildingDiv().getText();
                String storey = view.getjTF_storeyDiv().getText();
                String stairWayDiv = view.getjTF_stairWayDiv().getText();
                int doornumber = Integer.parseInt(view.getjTF_doornumberDiv().getText());
                Country postcountry = (Country) view.getjCB_postCountryDiv().getSelectedItem();
                String postcountryId = postcountry.getId();
                int postzipCode = Integer.parseInt(view.getjTF_postZipcodeDiv().getText());
                String postcity = view.getjTF_postCityDiv().getText();
                String postaddress = view.getjTF_postAddressDiv().getText();
                String postregion = view.getjTF_postRegionDiv().getText();
                ServiceZips serviceZips = new ServiceZips();

                if (!serviceZips.getOneZipByCode(zipCode)) {
                    serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_ZipcodeDiv().getText()), city);
                    zipid = serviceZips.getLatestZipId();
                } else {
                    zipid = serviceZips.getOneZipIdByCode(zipCode);
                }
                if (!serviceZips.getOneZipByCode(postzipCode)) {
                    serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_postZipcodeDiv().getText()), postcity);
                    postzipid = serviceZips.getLatestZipId();
                } else {
                    postzipid = serviceZips.getOneZipIdByCode(postzipCode);
                }
                service.newDivision(view, name);
                service.newDivisionAddess(view, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWayDiv, storey, doornumber, district, postcountryId, postzipid, postaddress, postregion);
                service.getAllDivision(view.getjTab_division());
            }
        });
        view.getjBut_EditDiv().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zipid = 0;
                int postzipid = 0;
                int id = Integer.parseInt(view.getjLab_id().getText());

                String name = view.getjTF_nameOnVoucherDiv().getText();
                Country country = (Country) view.getjCB_CountryDiv().getSelectedItem();
                String countryId = country.getId();
                PublicPlace publicPlace = (PublicPlace) view.getjCB_publicPlaceKindDiv().getSelectedItem();
                int publicplaceId = publicPlace.getId();
                String region = view.getjTF_RegionDiv().getText();
                int zipCode = Integer.parseInt(view.getjTF_ZipcodeDiv().getText());
                String city = view.getjTF_CityDiv().getText();
                String district = view.getjTF_District().getText();
                String publicPlaceName = view.getjTF_publicPlaceNameDiv().getText();
                int publicPlaceNumber = Integer.parseInt(view.getjTF_publicPlaceNumberDiv().getText());
                String building = view.getjTF_buildingDiv().getText();
                String storey = view.getjTF_storeyDiv().getText();
                String stairWayDiv = view.getjTF_stairWayDiv().getText();
                int doornumber = Integer.parseInt(view.getjTF_doornumberDiv().getText());
                Country postcountry = (Country) view.getjCB_postCountryDiv().getSelectedItem();
                String postcountryId = postcountry.getId();
                int postzipCode = Integer.parseInt(view.getjTF_postZipcodeDiv().getText());
                String postcity = view.getjTF_postCityDiv().getText();
                String postaddress = view.getjTF_postAddressDiv().getText();
                String postregion = view.getjTF_postRegionDiv().getText();
                ServiceZips serviceZips = new ServiceZips();

                if (!serviceZips.getOneZipByCode(zipCode)) {
                    serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_ZipcodeDiv().getText()), city);
                    zipid = serviceZips.getLatestZipId();
                } else {
                    zipid = serviceZips.getOneZipIdByCode(zipCode);
                }
                if (!serviceZips.getOneZipByCode(postzipCode)) {
                    serviceZips.newZip(view, countryId, Integer.parseInt(view.getjTF_postZipcodeDiv().getText()), postcity);
                    postzipid = serviceZips.getLatestZipId();
                } else {
                    postzipid = serviceZips.getOneZipIdByCode(postzipCode);
                }
                service.updateDivision(view, name, id);
                service.updateDivisionAddess(view, id, countryId, region, zipid, publicPlaceName, publicplaceId, publicPlaceNumber, building, stairWayDiv, storey, doornumber, district, postcountryId, postzipid, postaddress, postregion);
                service.getAllDivision(view.getjTab_division());
            }
        });
        view.getjBut_OpenDiv1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_division().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy elemet a fenti listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_division().getSelectedRow();
                    int value = Integer.parseInt(view.getjTab_division().getValueAt(row, 0).toString());

                    System.out.println(value);
                    ArrayList<Division> list = service.getOneDivision(value);

                    setSelectedCountriesToComboBoxDiv(view.getjCB_CountryDiv(), list);
                    setSelectedCountriesToComboBoxDiv(view.getjCB_postCountryDiv(), list);
                    setSelectedPublicPlaceToComboBoxDiv(view.getjCB_publicPlaceKindDiv(), list);

                    view.getjTF_RegionDiv().setText(list.get(0).getAddress().getAddress().getRegio());
                    view.getjTF_ZipcodeDiv().setText(list.get(0).getAddress().getAddress().getZip().getCode());
                    view.getjTF_CityDiv().setText(list.get(0).getAddress().getAddress().getZip().getName());
                    view.getjTF_District().setText(list.get(0).getAddress().getAddress().getDistrict());
                    view.getjTF_publicPlaceNameDiv().setText(list.get(0).getAddress().getAddress().getPublicPlaceName());
                    view.getjTF_publicPlaceNumberDiv().setText(String.format("%d", list.get(0).getAddress().getAddress().getPublicPlaceNumber()));
                    view.getjTF_buildingDiv().setText(list.get(0).getAddress().getAddress().getBuilding());
                    view.getjTF_storeyDiv().setText(list.get(0).getAddress().getAddress().getStorey());
                    view.getjTF_stairWayDiv().setText(list.get(0).getAddress().getAddress().getStairWay());
                    view.getjTF_doornumberDiv().setText(String.format("%d", list.get(0).getAddress().getAddress().getDoornumber()));
                    view.getjTF_addressDiv().setText(String.format("%s %s, %d", list.get(0).getAddress().getAddress().getPublicPlaceName(), list.get(0).getAddress().getAddress().getPublicPlaceKind().getName(), list.get(0).getAddress().getAddress().getPublicPlaceNumber()));
                    view.getjTF_postRegionDiv().setText(list.get(0).getAddress().getAddressPost().getRegion());
                    if (Integer.parseInt(list.get(0).getAddress().getAddressPost().getZip().getCode()) == 0) {
                        view.getjTF_postZipcodeDiv().setText("");
                    } else {
                        view.getjTF_postZipcodeDiv().setText(list.get(0).getAddress().getAddressPost().getZip().getCode());
                    }
                    view.getjTF_postCityDiv().setText(list.get(0).getAddress().getAddressPost().getZip().getName());
                    view.getjTF_postAddressDiv().setText(list.get(0).getAddress().getAddressPost().getAddress());
                    view.getjLab_id().setText(String.format("%d", list.get(0).getId()));

                }
            }
        });
        view.getjBut_DeleteDiv().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjTab_division().getSelectionModel().isSelectionEmpty()) {
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
                        int row = view.getjTab_division().getSelectedRow();
                        int value = Integer.parseInt(view.getjTab_division().getValueAt(row, 0).toString());
                        service.deleteDivision(view, value);
                    }

                    service.getAllDivision(view.getjTab_division());
                }
            }
        });
        view.getjCB_isEqualRealAddress().addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_isEqualRealAddress().isSelected()) {
                    view.getjCB_postCountry().setEnabled(false);
                    view.getjCB_postCountry().setSelectedIndex(view.getjB_Country().getSelectedIndex());
                    view.getjTF_postCity().setText(view.getjTF_City().getText());
                    view.getjTF_postCity().setEnabled(false);
                    view.getjTF_postAddress().setText(view.getjTF_address().getText());
                    view.getjTF_postAddress().setEnabled(false);
                    view.getjTF_postRegion().setText(view.getjTF_region().getText());
                    view.getjTF_postRegion().setEnabled(false);
                    view.getjTF_postZipcode().setText(view.getjTF_Zipcode().getText());
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

        view.getjCB_isEqualRealAddressDiv().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_isEqualRealAddressDiv().isSelected()) {
                    view.getjCB_CountryDiv().setSelectedIndex(view.getjB_Country().getSelectedIndex());
                    view.getjCB_publicPlaceKindDiv().setSelectedIndex(view.getjCB_publicPlaceKind().getSelectedIndex());
                    view.getjTF_RegionDiv().setText(view.getjTF_region().getText());
                    view.getjTF_ZipcodeDiv().setText(view.getjTF_Zipcode().getText());
                    view.getjTF_CityDiv().setText(view.getjTF_City().getText());
                    view.getjTF_District().setText(view.getjTF_District().getText());
                    view.getjTF_publicPlaceNameDiv().setText(view.getjTF_publicPlaceName().getText());
                    view.getjTF_publicPlaceNumberDiv().setText(view.getjTF_publicPlaceNumber().getText());
                    view.getjTF_buildingDiv().setText(view.getjTF_building().getText());
                    view.getjTF_storeyDiv().setText(view.getjTF_storey().getText());
                    view.getjTF_stairWayDiv().setText(view.getjTF_stairWay().getText());
                    view.getjTF_doornumberDiv().setText(view.getjTF_doornumber().getText());
                    view.getjTF_addressDiv().setText(view.getjTF_address().getText());
                } else {
                    view.getjTF_RegionDiv().setText("");
                    view.getjTF_ZipcodeDiv().setText("");
                    view.getjTF_CityDiv().setText("");
                    view.getjTF_District().setText("");
                    view.getjTF_publicPlaceNameDiv().setText("");
                    view.getjTF_publicPlaceNumberDiv().setText("");
                    view.getjTF_buildingDiv().setText("");
                    view.getjTF_storeyDiv().setText("");
                    view.getjTF_stairWayDiv().setText("");
                    view.getjTF_doornumberDiv().setText("");
                    view.getjTF_addressDiv().setText("");
                }
            }
        });

        view.getjTF_Zipcode().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_Zipcode().getText();
                Country country = (Country) view.getjB_Country().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_City().setText(name);
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
        view.getjTF_ZipcodeDiv().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_ZipcodeDiv().getText();
                Country country = (Country) view.getjCB_CountryDiv().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_CityDiv().setText(name);
                }
            }
        });
        view.getjTF_postZipcodeDiv().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String zipCode = view.getjTF_postZipcodeDiv().getText();
                Country country = (Country) view.getjCB_postCountryDiv().getSelectedItem();
                String countryId = country.getId();
                ServiceZips serviceZips = new ServiceZips();
                if (serviceZips.isValidZip(zipCode)) {
                    String name = serviceZips.getOneZipIdByCodes(countryId, Integer.parseInt(zipCode));
                    view.getjTF_postCityDiv().setText(name);
                }
            }
        });

    }

    public void loadFirm() {
        ArrayList<Firm> firm = service.getFirm();
        view.getjTF_shortName().setText(firm.get(0).getShorName());
        view.getjTF_fullName().setText(firm.get(0).getFullName());
        view.getjTF_nameOnVoucher().setText(firm.get(0).getVoucherName());
        view.getjTF_VatNumber().setText(firm.get(0).getVATNumber());
        view.getjTF_VatNumberEU().setText(firm.get(0).getVATNumberEU());
        view.getjTF_VatNumberGroup().setText(firm.get(0).getVATNumberGroup());
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

    public void loadCompanyAddress() {
        ArrayList<CompanyAddress> list = service.getCompanyAddress();
        setSelectedCountriesToComboBox(view.getjB_Country(), list);

        setSelectedPublicPlaceToComboBox(view.getjCB_publicPlaceKind(), list);

        view.getjTF_region().setText(list.get(0).getAddress().getRegio());
        view.getjTF_Zipcode().setText(list.get(0).getAddress().getZip().getCode());
        view.getjTF_City().setText(list.get(0).getAddress().getZip().getName());
        view.getjTF_District().setText(list.get(0).getAddress().getDistrict());
        view.getjTF_publicPlaceName().setText(list.get(0).getAddress().getPublicPlaceName());
        view.getjTF_publicPlaceNumber().setText(String.format("%d", list.get(0).getAddress().getPublicPlaceNumber()));
        view.getjTF_building().setText(list.get(0).getAddress().getBuilding());
        view.getjTF_storey().setText(list.get(0).getAddress().getStorey());
        view.getjTF_stairWay().setText(list.get(0).getAddress().getStairWay());
        view.getjTF_doornumber().setText(String.format("%d", list.get(0).getAddress().getDoornumber()));
        view.getjCB_isEqualRealAddress().setSelected(list.get(0).isIsPostInherited());
        view.getjTF_address().setText(String.format("%s %s, %d", list.get(0).getAddress().getPublicPlaceName(), list.get(0).getAddress().getPublicPlaceKind().getName(), list.get(0).getAddress().getPublicPlaceNumber()));
        if (list.get(0).isIsPostInherited()) {
            setSelectedCountriesToComboBox(view.getjCB_postCountry(), list);

            view.getjTF_postZipcode().setText(list.get(0).getAddress().getZip().getCode());

            view.getjTF_postCity().setText(list.get(0).getAddress().getZip().getName());

            view.getjTF_postAddress().setText(String.format("%s %s, %d", list.get(0).getAddress().getPublicPlaceName(), list.get(0).getAddress().getPublicPlaceKind().getName(), list.get(0).getAddress().getPublicPlaceNumber()));

            view.getjTF_postRegion().setText(list.get(0).getAddress().getRegio());
            view.getjTF_postZipcode().setEnabled(false);
            view.getjTF_postRegion().setEnabled(false);
            view.getjTF_postAddress().setEnabled(false);
            view.getjTF_postCity().setEnabled(false);
            view.getjCB_postCountry().setEnabled(false);
        } else {
            setSelectedCountriesToComboBox(view.getjCB_postCountry(), list);
            if (list.get(0).getAddressPost().getZip() != null) {
                view.getjTF_postZipcode().setText(list.get(0).getAddressPost().getZip().getCode());

                view.getjTF_postCity().setText(list.get(0).getAddressPost().getZip().getName());
            }

            view.getjTF_postAddress().setText(list.get(0).getAddressPost().getAddress());

            view.getjTF_postRegion().setText(list.get(0).getAddressPost().getRegion());

        }

    }

    public void setSelectedCountriesToComboBox(JComboBox box, ArrayList<CompanyAddress> list) {

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

    public void setSelectedPublicPlaceToComboBox(JComboBox box, ArrayList<CompanyAddress> list) {
        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<PublicPlace> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = view.getjCB_publicPlaceKind().getItemAt(i);

            cl.add((PublicPlace) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getAddress().getPublicPlaceKind().getId())) {
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
    
    public void setSelectedPublicPlaceToComboBoxDiv(JComboBox box, String street) {
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

}
