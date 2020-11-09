package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerFinalizedInvoice.InvoiceAddressListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Firm;
import InvoiceProgram.Model.Invoice;
import InvoiceProgram.Model.InvoiceAddress;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.InvoiceFileBankAccount;
import InvoiceProgram.Model.InvoiceFileCurrency;
import InvoiceProgram.Model.InvoiceItem;
import InvoiceProgram.Model.InvoiceNumberCounter;
import InvoiceProgram.Model.InvoiceObject;
import InvoiceProgram.Model.Language;
import InvoiceProgram.Model.Partner;
import InvoiceProgram.Model.PartnerAddress;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.TaxYear;
import InvoiceProgram.Model.TransactionType;
import InvoiceProgram.Service.ServiceCountry;
import InvoiceProgram.Service.ServiceFirm;
import InvoiceProgram.Service.ServiceInvoice;
import InvoiceProgram.Service.ServiceInvoiceFile;
import InvoiceProgram.Service.ServiceInvoiceFileBankAccount;
import InvoiceProgram.Service.ServiceInvoiceFileCurrency;
import InvoiceProgram.Service.ServiceInvoiceObjectToXML;
import InvoiceProgram.Service.ServiceLanguage;
import InvoiceProgram.Service.ServicePartner;
import InvoiceProgram.Service.ServicePayment;
import InvoiceProgram.Service.ServicePublicPlace;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.Service.ServiceXmlToPdf;
import InvoiceProgram.View.InvoiceEdit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ControllerInvoiceEdit implements InvoiceAddressListeners {

    private final InvoiceEdit view;
    private final ServiceInvoice service;
    // private ArrayList<BankListeners> listeners = new ArrayList<>();
    private int invoiceFileId;
    private int invoiceId;
    private String invoiceNumber;
    private Invoice invoice;
    private InvoiceFile invocieFile;
    private Firm firm;
    private TaxYear taxYear;
    private InvoiceNumberCounter invoiceNumberCounter;
    private Partner partner;
    private String firmName;
    public ControllerInvoiceEdit(String firmName) {
        view = new InvoiceEdit();
        service = new ServiceInvoice();
        this.firmName=firmName;
        initView();
        initControll();
    }

    public void initView() {
         view.getjLab_Firm().setText(firm+" - Véglegesített számlák");
        loadAllPartnerComboBox(view.getjCB_CustomerSelector());
        loadAllCountryComboBox(view.getjCB_CustomerCountry());
        loadAllPublicPlaceKindComboBox(view.getjCB_CustomerPublicPlaceKind());
        loadAllLanguageComboBox(view.getjCB_InvoiceLanguageSelector());
        loadAllTransactionTypeComboBox(view.getjCB_TransactionType());
        loadAllPaymentComboBox(view.getjCB_Payment());
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

        view.getjBut_Ok().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getjCB_isInvalidate().isSelected()) {
                    InvoiceObject invoiceObject = new InvoiceObject();
                    ServiceFirm serviceFirm = new ServiceFirm();
                    invoiceObject.setFirm(serviceFirm.getFirmToXML());
                    invoiceObject.setCompanyAddress(serviceFirm.getCompanyAddressToXML());
                    invoiceObject.setPartner(partner);
                    ServicePartner partnerService = new ServicePartner();
                    invoiceObject.setPartnerAddress(partnerService.gePartnerAddressToXML(partner.getId()));
                    ServiceTaxYear tax = new ServiceTaxYear();
                    TaxYear taxYear = tax.getAllTaxYearById(invocieFile.getTaxYear().getId());
                    String voucherNumber = generateVoucherNumber(taxYear, invocieFile);
                    view.getjDC_InvoiceDate().setDate(new java.sql.Date(System.currentTimeMillis()));
                    Date did = new Date(view.getjDC_InvoiceDate().getDate().getTime());
                    LocalDate vF = did.toLocalDate();
                    Date invoiceDate = (java.sql.Date.valueOf(vF));
                    int cancellationState = 2;
                    double amount = invoice.getAmount() * -1;
                    double taxAmount = invoice.getTax() * -1;
                    double total = invoice.getTotal() * -1;
                    service.newInvoice(view, invocieFile.getId(), invoice.getBankAccount().getId(), invoice.getCurrency().getId(), invoice.getLanguage().getId(), invoice.getCurrencyRate(), invoice.getTransactionType().getId(), voucherNumber, invoice.getPayment().getId(), invoiceDate, invoice.getFulfilmentDate(), invoice.getDueDate(), invoice.getRoundingValue(), invoice.getHeadNote(), cancellationState, amount, taxAmount, total);
                    Invoice invoiceNew = service.getLastInvoice();
                    invoiceObject.setInvoice(invoiceNew);
                    String mode = "INVALIDATE";
                    service.newInvoiceLink(view, invoice.getId(), invoiceNew.getId(), mode);
                    int departmentId = 0;
                    service.updateInvoice(view, invoiceId);
                    service.newInvoiceAddress(view, invoiceNew.getId(), partner.getId(), departmentId);
                    ArrayList<InvoiceItem> oldInvoiceItems = new ArrayList<>();
                    ArrayList<InvoiceItem> newInvoiceItems = new ArrayList<>();

                    oldInvoiceItems = service.getAllInvoiceItemByoriginalInvoiceId(invoiceId);

                    for (int i = 0; i < oldInvoiceItems.size(); i++) {
                        int qty = oldInvoiceItems.get(i).getQuantity() * -1;
                        double unitPrice = oldInvoiceItems.get(i).getUnitPrice();
                        double itemamount = oldInvoiceItems.get(i).getAmount() * -1;
                        double itemtax = oldInvoiceItems.get(i).getTax() * -1;
                        double itemtotal = oldInvoiceItems.get(i).getTotal() * -1;
                        double currencyUnitPrice = oldInvoiceItems.get(i).getCurrencyUnitPrice();
                        double currencyAmount = oldInvoiceItems.get(i).getCurrencyAmount() * -1;
                        double currencyTax = oldInvoiceItems.get(i).getCurrencyTax() * -1;
                        double currencyTotal = oldInvoiceItems.get(i).getCurrencyTotal() * -1;

                        service.newInvoiceItem(invoiceNew.getId(), oldInvoiceItems.get(i).getItemNumber(), oldInvoiceItems.get(i).getTaxType().getId(), oldInvoiceItems.get(i).getDescriptionDefault(), oldInvoiceItems.get(i).getMeasureDefault(), qty, unitPrice, itemamount, itemtax, itemtotal, currencyUnitPrice, currencyAmount, currencyTax, currencyTotal);
                        InvoiceItem invoiceItem = new InvoiceItem();
                        invoiceItem.setItemNumber(oldInvoiceItems.get(i).getItemNumber());
                        invoiceItem.setTaxType(oldInvoiceItems.get(i).getTaxType());
                        invoiceItem.setDescriptionDefault(oldInvoiceItems.get(i).getDescriptionDefault());
                        invoiceItem.setMeasureDefault(oldInvoiceItems.get(i).getMeasureDefault());
                        invoiceItem.setQuantity(qty);
                        invoiceItem.setUnitPrice(unitPrice);
                        invoiceItem.setAmount(itemamount);
                        invoiceItem.setTax(itemtax);
                        invoiceItem.setTotal(itemtotal);
                        invoiceItem.setCurrencyUnitPrice(currencyUnitPrice);
                        invoiceItem.setCurrencyAmount(currencyAmount);
                        invoiceItem.setCurrencyTax(currencyTax);
                        invoiceItem.setCurrencyTotal(currencyTotal);
                        newInvoiceItems.add(invoiceItem);

                    }
                    invoiceObject.setInvoiceItems(newInvoiceItems);
                    invoiceObject.setInvoiceFile(invocieFile);
                    invoiceObject.setTaxYear(taxYear);

                    try {
                        String XML = invoiceObject.getInvoice().getVoucherNumber();
                        String XMLname = XML.replace("/", "_");
                        FileWriter file = new FileWriter("src\\main\\java\\XML\\" + XMLname + ".xml", StandardCharsets.UTF_8);
                        //BufferedWriter br= new BufferedWriter(file);

                        JAXBContext contextObj = JAXBContext.newInstance(InvoiceObject.class);
                        Marshaller marshallerObj = contextObj.createMarshaller();
                        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                        marshallerObj.marshal(invoiceObject, file);
                        file.close();
                        File xml = new File("src\\main\\java\\XML\\" + XMLname + ".xml");
                        File xsl = new File("src\\main\\java\\XSD\\standard.xsl");

                        ServiceXmlToPdf sxp = new ServiceXmlToPdf(xml, xsl, XMLname);
                        sxp.XmlToPdf();

                    } catch (JAXBException ex) {
                        Logger.getLogger(ServiceInvoiceObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "A számla sztornózásához jelöle be a érvénytelenítés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        view.getjBut_PrintPreview().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File printpreview = new File("./Invoices/" + invoiceNumber + ".pdf");
                try {
                    Desktop.getDesktop().open(printpreview);
                } catch (IOException ex) {
                    Logger.getLogger(ControllerInvoiceEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void loadAllPartnerComboBox(JComboBox box) {
        ServicePartner sc = new ServicePartner();
        ArrayList<Partner> list = new ArrayList<>();
        list = sc.getAllPartner();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Partner) {
                    Partner taxyear = (Partner) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(taxyear.getNameOnVoucher());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllCountryComboBox(JComboBox box) {
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
                    Country country = (Country) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(country.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllPublicPlaceKindComboBox(JComboBox box) {
        ServicePublicPlace sc = new ServicePublicPlace();
        ArrayList<PublicPlace> list = new ArrayList<>();
        list = sc.getAllPublicPlace();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PublicPlace) {
                    PublicPlace publicPlace = (PublicPlace) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(publicPlace.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllLanguageComboBox(JComboBox box) {
        ServiceLanguage sc = new ServiceLanguage();
        ArrayList<Language> list = new ArrayList<>();
        list = sc.getAllLanguage();

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Language) {
                    Language language = (Language) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(language.getNyelv());
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
                    TransactionType transactionType = (TransactionType) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(transactionType.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllInvoiceFileBankAccountComboBox(JComboBox box, int id) {
        ServiceInvoiceFileBankAccount sc = new ServiceInvoiceFileBankAccount();
        ArrayList<InvoiceFileBankAccount> list = new ArrayList<>();
        list = sc.getAllInvoiceFileBankAccount(id);

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof InvoiceFileBankAccount) {
                    InvoiceFileBankAccount invFileBankAcc = (InvoiceFileBankAccount) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(invFileBankAcc.getBankAccount().toString());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllInvoiceFileCurrencyComboBox(JComboBox box, int id) {
        ServiceInvoiceFileCurrency sc = new ServiceInvoiceFileCurrency();
        ArrayList<InvoiceFileCurrency> list = new ArrayList<>();
        list = sc.getAllInvoiceFileCurrency(id);

        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        box.removeAllItems();
        box.revalidate();
        model.addAll(list);

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof InvoiceFileCurrency) {
                    InvoiceFileCurrency invFileCurrency = (InvoiceFileCurrency) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(invFileCurrency.getCurrency().getId());
                    setText(sb + "");
                }
                return this;
            }

        });
    }

    public void loadAllPaymentComboBox(JComboBox box) {
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
                    Payment payment = (Payment) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(payment.getName());
                    setText(sb + "");
                }
                return this;
            }

        });
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

    public void setSelectedBankAccountToComboBox(JComboBox box, int id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<InvoiceFileBankAccount> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((InvoiceFileBankAccount) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == id) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedLanguageToComboBox(JComboBox box, String id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Language> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Language) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId().equals(id)) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedCurrencyToComboBox(JComboBox box, String id) {

        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<InvoiceFileCurrency> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((InvoiceFileCurrency) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getCurrency().getId().equals(id)) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public void setSelectedPartnerToComboBox(JComboBox box, ArrayList<InvoiceAddress> list) {
        int sizeOfCombo = box.getItemCount();
        int index = 0;
        ArrayList<Partner> cl = new ArrayList<>();
        for (int i = 0; i < sizeOfCombo; i++) {
            Object item = box.getItemAt(i);

            cl.add((Partner) item);
        }
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i).getId() == (list.get(0).getPartner().getId())) {
                index = i;
            }
        }
        box.setSelectedIndex(index);
    }

    public String generateVoucherNumber(TaxYear taxyear, InvoiceFile invoiceFile) {
        String voucherSign = invoiceFile.getVoucherSign();
        int counter = service.getLastInvoiceNumber(invoiceFile.getId());
        int voucherCount = counter + 1;
        int digits = taxyear.getVoucherDigits();
        String firstSep = taxyear.getVoucherSepFirst();
        String lastSep = taxyear.getVoucherSepLast();
        int year = taxyear.getTaxYearNumber();
        int length = String.valueOf(voucherCount).length();
        String number = "";
        for (int i = 1; i < (digits - length) + 1; i++) {
            number += 0;
        }
        number += voucherCount;
        service.updateInvoiceNumber(view, voucherCount, invoiceFile.getId());
        return voucherSign + firstSep + number + lastSep + year;

    }

    @Override
    public void updateData(ArrayList<InvoiceAddress> list) {
        ServicePartner spa = new ServicePartner();
        ArrayList<PartnerAddress> plist = new ArrayList<>();
        plist = spa.gePartnerAddress(list.get(0).getPartner().getId());

        setSelectedPartnerToComboBox(view.getjCB_CustomerSelector(), list);
        view.getjCB_CustomerSelector().setEnabled(false);
        ServicePartner servicePartner = new ServicePartner();
        ArrayList<PartnerAddress> address = servicePartner.gePartnerAddress(list.get(0).getPartner().getId());

        view.getjTF_CustomerRegion().setText(address.get(0).getAddress().getRegio());
        view.getjTF_CustomerRegion().setEnabled(false);
        view.getjTF_CustomerZipcode().setText(address.get(0).getAddress().getZip().getCode());
        view.getjTF_CustomerZipcode().setEnabled(false);
        view.getjTF_CustomerCity().setText(address.get(0).getAddress().getZip().getName());
        view.getjTF_CustomerCity().setEnabled(false);
        view.getjTF_CustomerDistrict().setText((address.get(0).getAddress().getDistrict()));
        view.getjTF_CustomerDistrict().setEnabled(false);
        view.getjTF_Address().setText(address.get(0).getAddressPost().getAddress());
        view.getjTF_Address().setEnabled(false);
        view.getjTF_CustomerPublicPlaceName().setText(address.get(0).getAddress().getPublicPlaceName());
        view.getjTF_CustomerPublicPlaceName().setEnabled(false);
        view.getjTF_CustomerPublicPlaceNumber().setText(String.format("%d", address.get(0).getAddress().getPublicPlaceNumber()));
        view.getjTF_CustomerPublicPlaceNumber().setEnabled(false);
        view.getjTF_CustomerBuilding().setText(address.get(0).getAddress().getBuilding());
        view.getjTF_CustomerBuilding().setEnabled(false);
        view.getjTF_Storey().setText(address.get(0).getAddress().getStorey());
        view.getjTF_Storey().setEnabled(false);
        view.getjTF_CustomerStairWay().setText(address.get(0).getAddress().getStairWay());
        view.getjTF_CustomerStairWay().setEnabled(false);
        view.getjTF_CustomerDoornumber().setText(String.format("%d", address.get(0).getAddress().getDoornumber()));
        view.getjTF_CustomerDoornumber().setEnabled(false);
        view.getjTF_CustomerVatNumber().setText(list.get(0).getPartner().getVATNumber());
        view.getjTF_CustomerVatNumber().setEnabled(false);
        view.getjTF_CustomerEUVATNumber().setText(list.get(0).getPartner().getVATNumberEU());
        view.getjTF_CustomerEUVATNumber().setEnabled(false);
        view.getjTF_CustomerGroupVATNumber().setText(list.get(0).getPartner().getVATNumberGroup());
        view.getjTF_CustomerGroupVATNumber().setEnabled(false);
        view.getjCB_CustomerIsCashAccounting().setSelected(list.get(0).getPartner().isIsCashAccounting());
        view.getjCB_CustomerIsCashAccounting().setEnabled(false);

        setSelectedCountriesToComboBoxDep(view.getjCB_CustomerCountry(), plist);
        view.getjCB_CustomerCountry().setEnabled(false);
        setSelectedPublicPlaceToComboBox(view.getjCB_CustomerPublicPlaceKind(), plist);
        view.getjCB_CustomerPublicPlaceKind().setEnabled(false);
        setSelectedTransactionToComboBox(view.getjCB_TransactionType(), list.get(0).getInvoice().getTransactionType().getId());
        view.getjCB_TransactionType().setEnabled(false);
        setSelectedLanguageToComboBox(view.getjCB_InvoiceLanguageSelector(), list.get(0).getInvoice().getLanguage().getId());
        view.getjCB_InvoiceLanguageSelector().setEnabled(false);
        view.getjLab_Firm().setText(firmName+" - "+list.get(0).getInvoice().getVoucherNumber() + " számla - " + list.get(0).getInvoice().getInvocieFile().getName() + " számlatömb");
        view.getjLab_Firm().setEnabled(false);

        loadAllInvoiceFileBankAccountComboBox(view.getjCB_InvoiceFileBankAccount(), list.get(0).getInvoice().getInvocieFile().getId());
        setSelectedBankAccountToComboBox(view.getjCB_InvoiceFileBankAccount(), list.get(0).getInvoice().getBankAccount().getId());
        view.getjCB_InvoiceFileBankAccount().setEnabled(false);
        loadAllInvoiceFileCurrencyComboBox(view.getjCB_InvoiceFileCurrency(), list.get(0).getInvoice().getInvocieFile().getId());
        setSelectedCurrencyToComboBox(view.getjCB_InvoiceFileCurrency(), list.get(0).getInvoice().getCurrency().getId());
        view.getjCB_InvoiceFileCurrency().setEnabled(false);
        setSelectedPaymentToComboBox(view.getjCB_Payment(), list.get(0).getInvoice().getPayment().getId());
        view.getjCB_Payment().setEnabled(false);
        view.getjTF_ExchangeRate().setText(String.format("%.2f", list.get(0).getInvoice().getCurrencyRate()));
        view.getjTF_ExchangeRate().setEnabled(false);
        view.getjTF_InvoiceTotal().setText(String.format("%.2f", list.get(0).getInvoice().getTotal()));
        view.getjTF_InvoiceTotal().setEnabled(false);
        view.getjTF_InvoiceNetTotal().setText(String.format("%.2f", list.get(0).getInvoice().getAmount()));
        view.getjTF_InvoiceNetTotal().setEnabled(false);
        view.getjTF_InvoiceVATTotal().setText(String.format("%.2f", list.get(0).getInvoice().getTax()));
        view.getjTF_InvoiceVATTotal().setEnabled(false);
        view.getjTF_InvoiceRound().setText(String.format("%d", list.get(0).getInvoice().getRoundingValue()));
        view.getjTF_InvoiceRound().setEnabled(false);
        view.getjDC_DueDate().setDate(list.get(0).getInvoice().getDueDate());
        view.getjDC_DueDate().setEnabled(false);
        view.getjDC_FulfilmentDate().setDate(list.get(0).getInvoice().getFulfilmentDate());
        view.getjDC_FulfilmentDate().setEnabled(false);
        view.getjDC_InvoiceDate().setDate(list.get(0).getInvoice().getInvoiceDate());
        view.getjDC_InvoiceDate().setEnabled(false);
        if (list.get(0).getInvoice().getDueDate() == list.get(0).getInvoice().getFulfilmentDate()) {
            view.getjCB_isCountinuosFulfilment().setSelected(true);
            view.getjCB_isCountinuosFulfilment().setEnabled(false);
        }
        view.getjTF_InvoiceHeadnote().setText(list.get(0).getInvoice().getHeadNote());
        view.getjTF_InvoiceHeadnote().setEnabled(false);
        invoiceFileId = list.get(0).getInvoice().getInvocieFile().getId();
        if (list.get(0).getInvoice().getCancellationState() == 1) {
            view.getjCB_isInvalidate().setSelected(true);
            view.getjCB_isInvalidate().setEnabled(false);
            view.getjBut_Ok().setEnabled(false);
            view.getjBut_Ok().setToolTipText("A számla már érvénytelenítve van!");
        } else if (list.get(0).getInvoice().getCancellationState() == 2) {
            view.getjCB_isInvalidate().setSelected(true);
            view.getjCB_isInvalidate().setEnabled(false);
            view.getjBut_Ok().setEnabled(false);
            view.getjBut_Ok().setToolTipText("A számla egy sztornó számla, nem lehet érvényteleníteni!");
        }

        service.getAllInvoiceItemByInvoiceId(view.getjTab_InvoiceItem(), list.get(0).getInvoice().getId());
        view.getjTab_InvoiceItem().setEnabled(false);

        invoiceId = list.get(0).getInvoice().getId();
        String invNr = list.get(0).getInvoice().getVoucherNumber();
        invoiceNumber = invNr.replace("/", "_");
        invocieFile = list.get(0).getInvoice().getInvocieFile();
        partner = list.get(0).getPartner();
        invoice = list.get(0).getInvoice();
    }
}
