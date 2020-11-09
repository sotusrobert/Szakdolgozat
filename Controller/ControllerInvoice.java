package InvoiceProgram.Controller;

import InvoiceProgram.Controller.ControllerInvoiceFileSelect.InvoiceFileSelectListeners;
import InvoiceProgram.Model.Country;
import InvoiceProgram.Model.Firm;
import InvoiceProgram.Model.Invoice;
import InvoiceProgram.Model.InvoiceFile;
import InvoiceProgram.Model.InvoiceFileBankAccount;
import InvoiceProgram.Model.InvoiceFileCurrency;
import InvoiceProgram.Model.InvoiceItem;
import InvoiceProgram.Model.InvoiceNumberCounter;
import InvoiceProgram.Model.InvoiceObject;
import InvoiceProgram.Model.InvoiceXML;
import InvoiceProgram.Model.Language;
import InvoiceProgram.Model.Partner;
import InvoiceProgram.Model.PartnerAddress;
import InvoiceProgram.Model.Payment;
import InvoiceProgram.Model.PublicPlace;
import InvoiceProgram.Model.Tax;
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
import InvoiceProgram.Service.ServiceTax;
import InvoiceProgram.Service.ServiceTaxYear;
import InvoiceProgram.Service.ServiceTransactionType;
import InvoiceProgram.Service.ServiceXmlToPdf;
import InvoiceProgram.View.Invoice_GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ControllerInvoice implements InvoiceFileSelectListeners {

    private final Invoice_GUI view;
    private final ServiceInvoice service;
    // private ArrayList<BankListeners> listeners = new ArrayList<>();
    private int invoiceFileId;
    private String firmName;
    private InvoiceFile invocieFile;
    private Firm firm;
    private TaxYear taxYear;
    private InvoiceNumberCounter invoiceNumberCounter;

    public int getInvoiceFileId() {
        return invoiceFileId;
    }

    public void setInvoiceFileId(int invoiceFileId) {
        this.invoiceFileId = invoiceFileId;
    }

    public ControllerInvoice(String firmName) {
        view = new Invoice_GUI();
        service = new ServiceInvoice();
        this.firmName=firmName;
        initView();
        initControll();
    }

    public void initView() {
        view.getjLab_Firm().setText(firmName+" - Új számla előkészítés");
        loadAllPartnerComboBox(view.getjCB_CustomerSelector());
        loadAllCountryComboBox(view.getjCB_CustomerCountry());
        loadAllPublicPlaceKindComboBox(view.getjCB_CustomerPublicPlaceKind());
        loadAllLanguageComboBox(view.getjCB_InvoiceLanguageSelector());
        setSelectedLanguageToComboBox(view.getjCB_InvoiceLanguageSelector(), "hu");
        loadAllTransactionTypeComboBox(view.getjCB_TransactionType());
        loadAllPaymentComboBox(view.getjCB_Payment());
        view.getjTF_ExchangeRate().setText("1");
        view.getjCB_Payment().setSelectedIndex(0);
        view.getjTF_InvoiceTotal().setEnabled(false);
        view.getjTF_InvoiceNetTotal().setEnabled(false);
        view.getjTF_InvoiceVATTotal().setEnabled(false);
        view.getjTF_InvoiceRound().setEnabled(false);
        view.getjTF_InvoiceRound().setText("0");
        view.getjDC_InvoiceDate().setDate(new java.sql.Date(System.currentTimeMillis()));
        view.getjDC_FulfilmentDate().setDate(new java.sql.Date(System.currentTimeMillis()));
        view.getjDC_DueDate().setDate(new java.sql.Date(System.currentTimeMillis()));
        setUpComboBoxToJtable(view.getjTab_InvoiceItem(), view.getjTab_InvoiceItem().getColumnModel().getColumn(3));
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
        view.getjBut_AddCustomer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                ControllerPartner controller = new ControllerPartner(firmName);
            }
        });

        view.getjCB_CustomerSelector().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Partner item = (Partner) view.getjCB_CustomerSelector().getSelectedItem();
                ServicePartner servicePartner = new ServicePartner();
                ArrayList<PartnerAddress> address = servicePartner.gePartnerAddress(item.getId());
                setSelectedCountriesToComboBoxDep(view.getjCB_CustomerCountry(), address);
                view.getjTF_CustomerRegion().setText(address.get(0).getAddress().getRegio());
                view.getjTF_CustomerZipcode().setText(address.get(0).getAddress().getZip().getCode());
                view.getjTF_CustomerCity().setText(address.get(0).getAddress().getZip().getName());
                view.getjTF_CustomerDistrict().setText((address.get(0).getAddress().getDistrict()));
                view.getjTF_Address().setText(address.get(0).getAddressPost().getAddress());
                view.getjTF_CustomerPublicPlaceName().setText(address.get(0).getAddress().getPublicPlaceName());
                view.getjTF_CustomerPublicPlaceNumber().setText(String.format("%d", address.get(0).getAddress().getPublicPlaceNumber()));
                setSelectedPublicPlaceToComboBox(view.getjCB_CustomerPublicPlaceKind(), address);
                view.getjTF_CustomerBuilding().setText(address.get(0).getAddress().getBuilding());
                view.getjTF_Storey().setText(address.get(0).getAddress().getStorey());
                view.getjTF_CustomerStairWay().setText(address.get(0).getAddress().getStairWay());
                view.getjTF_CustomerDoornumber().setText(String.format("%d", address.get(0).getAddress().getDoornumber()));
                view.getjTF_CustomerVatNumber().setText(item.getVATNumber());
                view.getjTF_CustomerEUVATNumber().setText(item.getVATNumberEU());
                view.getjTF_CustomerGroupVATNumber().setText(item.getVATNumberGroup());
                setSelectedTransactionToComboBox(view.getjCB_TransactionType(), item.getTransactionType().getId());
                view.getjCB_CustomerIsCashAccounting().setSelected(item.isIsCashAccounting());
            }
        });

        view.getjCB_isCountinuosFulfilment().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (view.getjCB_isCountinuosFulfilment().isSelected()) {
                    view.getjDC_FulfilmentDate().setDate(view.getjDC_DueDate().getDate());
                } else {

                    view.getjDC_FulfilmentDate().setDate(view.getjDC_InvoiceDate().getDate());
                }
            }
        });

        view.getjCB_Payment().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Payment payment = (Payment) view.getjCB_Payment().getSelectedItem();
                int paymentDays = payment.getPrompt();
                Date dvf = new Date(view.getjDC_InvoiceDate().getDate().getTime());
                Date dvfAdd = addDays(dvf, paymentDays);
                view.getjDC_DueDate().setDate(dvfAdd);
            }
        });
        view.getjBut_AddRow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoiceItem().getModel();
                tableModel.addRow(new Object[]{});
            }
        });
        view.getjBut_DeleteRow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) view.getjTab_InvoiceItem().getModel();

                if (view.getjTab_InvoiceItem().getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(view, "Válasszon ki egy sort a listából!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = view.getjTab_InvoiceItem().getSelectedRow();

                    tableModel.removeRow(row);

                }
            }
        });

        view.getjTab_InvoiceItem().getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                view.getjTab_InvoiceItem().getModel().removeTableModelListener(this);
                int row = e.getFirstRow();
                int col = e.getColumn();
                int qty = 0;
                double price = 0;
                Integer one = (Integer) view.getjTab_InvoiceItem().getValueAt(row, 1);
                Double two = (Double) view.getjTab_InvoiceItem().getValueAt(row, 4);

                double sum = 0;
                if (one != null && two != null) {
                    qty = (Integer) view.getjTab_InvoiceItem().getValueAt(row, 1);
                    price = (Double) view.getjTab_InvoiceItem().getValueAt(row, 4);
                    sum = qty * price;
                    if (sum != 0) {
                        view.getjTab_InvoiceItem().setValueAt(sum, row, 5);
                    }
                }
                double total = 0;
                double net = 0;
                double vat = 0;
                double round = 0;

                int rowcount = view.getjTab_InvoiceItem().getRowCount();

                for (int i = 0; i < view.getjTab_InvoiceItem().getRowCount(); i++) {

                    if (view.getjTab_InvoiceItem().getValueAt(i, 5) != null) {
                        Tax vatN = (Tax) view.getjTab_InvoiceItem().getValueAt(i, 3);
                        int vatRate = vatN.getRate();
                        if (vatRate > 0) {
                            double vatCalc = (double) view.getjTab_InvoiceItem().getValueAt(i, 5) * vatRate / 100;
                            vat += vatCalc;
                            net += (double) view.getjTab_InvoiceItem().getValueAt(i, 5);

                        } else {
                            vat += 0;
                            net += (double) view.getjTab_InvoiceItem().getValueAt(i, 5);

                        }

                    }
                }
                total += (vat + net);
                view.getjTF_InvoiceTotal().setText(String.format("%.2f", total));
                view.getjTF_InvoiceNetTotal().setText(String.format("%.2f", net));
                view.getjTF_InvoiceVATTotal().setText(String.format("%.2f", vat));

                view.getjTab_InvoiceItem().getModel().addTableModelListener(this);
            }
        });

        view.getjBut_Ok().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (view.getjCB_isFinalize().isSelected()) {
                    if (view.getjCB_CustomerSelector().getSelectedItem() != null && !view.getjTF_InvoiceNetTotal().getText().isEmpty()) {
                        InvoiceObject  invoiceObject= new InvoiceObject();
                        ServiceFirm serviceFirm= new ServiceFirm();
                        invoiceObject.setFirm(serviceFirm.getFirmToXML());
                        invoiceObject.setCompanyAddress(serviceFirm.getCompanyAddressToXML());
                        
                        ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                        InvoiceFile invFile = invoiceFile.getOneInvoiceFileById(invoiceFileId);
                        invoiceObject.setInvoiceFile(invFile);
                        ServiceTaxYear tax = new ServiceTaxYear();
                        TaxYear taxYear = tax.getAllTaxYearById(invFile.getTaxYear().getId());
                        invoiceObject.setTaxYear(taxYear);
                        InvoiceFileBankAccount bankAccount = (InvoiceFileBankAccount) view.getjCB_InvoiceFileBankAccount().getSelectedItem();
                        int bankAccountId = bankAccount.getId();
                        InvoiceFileCurrency currency = (InvoiceFileCurrency) view.getjCB_InvoiceFileCurrency().getSelectedItem();
                        String currencyId = currency.getCurrency().getId();
                        Language language = (Language) view.getjCB_InvoiceLanguageSelector().getSelectedItem();
                        String languageId = language.getId();
                        Double currencyRate = Double.parseDouble(view.getjTF_ExchangeRate().getText());
                        TransactionType transactionType = (TransactionType) view.getjCB_TransactionType().getSelectedItem();
                        int transactionTypeId = transactionType.getId();
                        String voucherNumber = generateVoucherNumber(taxYear, invFile);
                        Payment payment = (Payment) view.getjCB_Payment().getSelectedItem();
                        int paymentId = payment.getId();
                        Date did = new Date(view.getjDC_InvoiceDate().getDate().getTime());
                        LocalDate vF = did.toLocalDate();
                        Date invoiceDate = (java.sql.Date.valueOf(vF));
                        Date dif = new Date(view.getjDC_FulfilmentDate().getDate().getTime());
                        LocalDate vFF = dif.toLocalDate();
                        Date fulfilmentDate = (java.sql.Date.valueOf(vFF));
                        Date didd = new Date(view.getjDC_DueDate().getDate().getTime());
                        LocalDate vFFD = didd.toLocalDate();
                        Date dueDate = (java.sql.Date.valueOf(vFFD));
                        int roundingValue = Integer.parseInt(view.getjTF_InvoiceRound().getText());
                        String headNote = view.getjTF_InvoiceHeadnote().getText();
                        int cancellationState = 0;
                        NumberFormat nf = NumberFormat.getInstance();
                        double amountInv=0;
                        double taxAmount=0;
                        double total=0;
                        try {
                             amountInv = nf.parse(view.getjTF_InvoiceNetTotal().getText()).doubleValue();
                             taxAmount = nf.parse(view.getjTF_InvoiceVATTotal().getText()).doubleValue();
                             total = nf.parse(view.getjTF_InvoiceTotal().getText()).doubleValue();

                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        service.newInvoice(view, invoiceFileId , bankAccountId ,currencyId ,languageId, currencyRate, transactionTypeId, voucherNumber, paymentId , invoiceDate, fulfilmentDate, dueDate,roundingValue, headNote, cancellationState, amountInv, taxAmount, total );
                        System.out.println("Számlatömb: "+invoiceFileId +"Bankszámla azonosító: "+ bankAccountId +"Deviza: "+currencyId +"Nyelv: "+languageId + "Árfolyam: "+currencyRate + "Ügylet: "+transactionTypeId + "Számlaszám: "+voucherNumber+"Fizetési mód: "+ paymentId +"Számla dátum: "+ invoiceDate +"Számla teljesítés: "+fulfilmentDate + "Számla fizetési határidŐ: "+dueDate +"Kerekítés: "+roundingValue + "Megjegyzés: "+headNote + "Státusz: "+cancellationState + "Számla nettó érték: "+ amountInv + "Számla Áfa: "+taxAmount + "Számla bruttó összeg: "+total );
                        Invoice invoice= service.getLastInvoice();
                        Partner partner=(Partner) view.getjCB_CustomerSelector().getSelectedItem();
                        ServicePartner partnerService= new ServicePartner();
                        invoiceObject.setPartner(partner);
                        invoiceObject.setPartnerAddress(partnerService.gePartnerAddressToXML(partner.getId()));
                        invoiceObject.setInvoice(invoice);
                        
                        int partnerId= partner.getId();
                        int departmentId=0;
                        service.newInvoiceAddress(view, invoice.getId(), partnerId, departmentId);
                        System.out.println("Számla azonosító: "+invoice.getVoucherNumber() + "Partner: "+ partnerId +"Partner telephely: "+ departmentId);
                        //loop through JTable and sava InvoiceItems OneByeOne
                        ArrayList<InvoiceItem>list = new ArrayList<>();
                        for (int i = 0; i < view.getjTab_InvoiceItem().getRowCount(); i++) {
                            if (view.getjTab_InvoiceItem().getValueAt(i, 5) != null) {
                                int itemNumber=i+1;
                                Tax vatN = (Tax) view.getjTab_InvoiceItem().getValueAt(i, 3);
                                int vatId = vatN.getId();
                                int vatRate=vatN.getRate();
                                String descriptionDefault=(String)view.getjTab_InvoiceItem().getValueAt(i, 0);
                                String measureDefault=(String)view.getjTab_InvoiceItem().getValueAt(i, 2);
                                int quantity=(Integer)view.getjTab_InvoiceItem().getValueAt(i, 1);
                                double unitPrice=0;
                                double amount=0;
                                double taxItem=0;
                                double totalItem=0;
                                double currencyUnitPrice=0;
                                double currencyAmount=0;
                                double currencyTax=0;
                                double currencyTotal=0;
                                try {
                                    unitPrice= nf.parse(view.getjTab_InvoiceItem().getValueAt(i, 4).toString()).doubleValue();
                                    currencyUnitPrice=unitPrice*currencyRate;
                                    amount= nf.parse(view.getjTab_InvoiceItem().getValueAt(i, 5).toString()).doubleValue();
                                    currencyAmount=amount*currencyRate;
                                    if (vatRate>0) {
                                        taxItem=amount*vatRate/100;
                                        currencyTax=taxItem*currencyRate;
                                    }
                                    totalItem=amount+taxItem;
                                    currencyTotal=totalItem*currencyRate;
                                } catch (ParseException ex) {
                                    Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               service.newInvoiceItem(invoice.getId(),itemNumber, vatId,descriptionDefault, measureDefault, quantity , unitPrice, amount,taxItem, totalItem, currencyUnitPrice, currencyAmount, currencyTax, currencyTotal );
                               InvoiceItem invoiceItem= new InvoiceItem();
                               invoiceItem.setItemNumber(itemNumber);
                               invoiceItem.setTaxType(vatN);
                               invoiceItem.setDescriptionDefault(descriptionDefault);
                               invoiceItem.setMeasureDefault(measureDefault);
                               invoiceItem.setQuantity(quantity);
                               invoiceItem.setUnitPrice(unitPrice);
                               invoiceItem.setAmount(amount);
                               invoiceItem.setTax(taxItem);
                               invoiceItem.setTotal(totalItem);
                               invoiceItem.setCurrencyUnitPrice(currencyUnitPrice);
                               invoiceItem.setCurrencyAmount(currencyAmount);
                               invoiceItem.setCurrencyTax(currencyTax);
                               invoiceItem.setCurrencyTotal(currencyTotal);
                               list.add(invoiceItem);
                               
                               System.out.println("Számla azonosító: "+invoice.getVoucherNumber() + "Tétel sorszám: "+itemNumber + "Tétel áfakód: "+vatId +"Tétel megnevezés: "+descriptionDefault+ "Mennyiségi egység: "+measureDefault+"Mennyiség: "+ quantity + "Egységár: "+unitPrice+"Nettó összeg: "+ amount + "Áfa: "+taxItem +"Bruttó: "+totalItem+ "Egységár HUF-ban: "+currencyUnitPrice + "Nettó ár HUF-ban: "+currencyAmount + "ÁFA HUF-ban: "+currencyTax +"Bruttó HUF-ban: "+ currencyTotal );
                                
                                
                            }
                        }
                        invoiceObject.setInvoiceItems(list);
                        System.out.println(invoiceObject.toString());
                         try {  
                            String XML= invoiceObject.getInvoice().getVoucherNumber();
                            String XMLname=XML.replace("/", "_");
                            FileWriter file= new FileWriter("src\\main\\java\\XML\\"+XMLname+".xml",StandardCharsets.UTF_8);
                            //BufferedWriter br= new BufferedWriter(file);
                            
                            JAXBContext contextObj =JAXBContext.newInstance(InvoiceObject.class);
                            Marshaller marshallerObj = contextObj.createMarshaller();
                            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
                            
                            marshallerObj.marshal(invoiceObject, file);  
                            file.close();
                            File xml= new File("src\\main\\java\\XML\\"+XMLname+".xml");
                            File xsl= new File("src\\main\\java\\XSD\\standard.xsl");
                            
                            ServiceXmlToPdf sxp= new ServiceXmlToPdf(xml, xsl,XMLname );
                            sxp.XmlToPdf();
                            
                        } catch (JAXBException  ex) {
                            Logger.getLogger(ServiceInvoiceObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    } else {
                        JOptionPane.showMessageDialog(view, "A számla rögzítéséhez válasszon ki egy partnert és adja meg a számlatételeket!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(view, "A számla rögzítéséhez jelöle be a véglegesítés opciót!", "Figyelem", JOptionPane.ERROR_MESSAGE);
                }
                view.dispose();
            }
        });
        
        view.getjBut_PrintPreview().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                        InvoiceObject  invoiceObject= new InvoiceObject();
                        ServiceFirm serviceFirm= new ServiceFirm();
                        invoiceObject.setFirm(serviceFirm.getFirmToXML());
                        invoiceObject.setCompanyAddress(serviceFirm.getCompanyAddressToXML());
                        Invoice invoicePrint= new Invoice();
                        ServiceInvoiceFile invoiceFile = new ServiceInvoiceFile();
                        InvoiceFile invFile = invoiceFile.getOneInvoiceFileById(invoiceFileId);
                        invoiceObject.setInvoiceFile(invFile);
                        ServiceTaxYear tax = new ServiceTaxYear();
                        TaxYear taxYear = tax.getAllTaxYearById(invFile.getTaxYear().getId());
                        invoiceObject.setTaxYear(taxYear);
                        InvoiceFileBankAccount bankAccount = (InvoiceFileBankAccount) view.getjCB_InvoiceFileBankAccount().getSelectedItem();
                        invoicePrint.setBankAccount(bankAccount.getBankAccount());
                        int bankAccountId = bankAccount.getId();
                        InvoiceFileCurrency currency = (InvoiceFileCurrency) view.getjCB_InvoiceFileCurrency().getSelectedItem();
                        invoicePrint.setCurrency(currency.getCurrency());
                        String currencyId = currency.getCurrency().getId();
                        Language language = (Language) view.getjCB_InvoiceLanguageSelector().getSelectedItem();
                        invoicePrint.setLanguage(language);
                        String languageId = language.getId();
                        Double currencyRate = Double.parseDouble(view.getjTF_ExchangeRate().getText());
                        invoicePrint.setCurrencyRate(currencyRate);
                        TransactionType transactionType = (TransactionType) view.getjCB_TransactionType().getSelectedItem();
                        invoicePrint.setTransactionType(transactionType);
                        int transactionTypeId = transactionType.getId();
                        //String voucherNumber = generateVoucherNumber(taxYear, invFile);
                        invoicePrint.setVoucherNumber("PrintPreview");
                        Payment payment = (Payment) view.getjCB_Payment().getSelectedItem();
                        invoicePrint.setPayment(payment);
                        int paymentId = payment.getId();
                        Date did = new Date(view.getjDC_InvoiceDate().getDate().getTime());
                        LocalDate vF = did.toLocalDate();
                        Date invoiceDate = (java.sql.Date.valueOf(vF));
                        invoicePrint.setInvoiceDate(invoiceDate);
                        Date dif = new Date(view.getjDC_FulfilmentDate().getDate().getTime());
                        LocalDate vFF = dif.toLocalDate();
                        Date fulfilmentDate = (java.sql.Date.valueOf(vFF));
                        invoicePrint.setFulfilmentDate(fulfilmentDate);
                        Date didd = new Date(view.getjDC_DueDate().getDate().getTime());
                        LocalDate vFFD = didd.toLocalDate();
                        Date dueDate = (java.sql.Date.valueOf(vFFD));
                        invoicePrint.setDueDate(dueDate);
                        int roundingValue = Integer.parseInt(view.getjTF_InvoiceRound().getText());
                        invoicePrint.setRoundingValue(roundingValue);
                        String headNote = view.getjTF_InvoiceHeadnote().getText();
                        invoicePrint.setHeadNote(headNote);
                        int cancellationState = 0;
                        NumberFormat nf = NumberFormat.getInstance();
                        double amountInv=0;
                        double taxAmount=0;
                        double total=0;
                        try {
                             amountInv = nf.parse(view.getjTF_InvoiceNetTotal().getText()).doubleValue();
                             taxAmount = nf.parse(view.getjTF_InvoiceVATTotal().getText()).doubleValue();
                             total = nf.parse(view.getjTF_InvoiceTotal().getText()).doubleValue();

                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                        }
 
                        //service.newInvoice(view, invoiceFileId , bankAccountId ,currencyId ,languageId, currencyRate, transactionTypeId, voucherNumber, paymentId , invoiceDate, fulfilmentDate, dueDate,roundingValue, headNote, cancellationState, amountInv, taxAmount, total );
                        invoicePrint.setTax(taxAmount);
                        invoicePrint.setTotal(total);
                        invoicePrint.setAmount(amountInv);
                        //System.out.println("Számlatömb: "+invoiceFileId +"Bankszámla azonosító: "+ bankAccountId +"Deviza: "+currencyId +"Nyelv: "+languageId + "Árfolyam: "+currencyRate + "Ügylet: "+transactionTypeId + "Számlaszám: "+voucherNumber+"Fizetési mód: "+ paymentId +"Számla dátum: "+ invoiceDate +"Számla teljesítés: "+fulfilmentDate + "Számla fizetési határidŐ: "+dueDate +"Kerekítés: "+roundingValue + "Megjegyzés: "+headNote + "Státusz: "+cancellationState + "Számla nettó érték: "+ amountInv + "Számla Áfa: "+taxAmount + "Számla bruttó összeg: "+total );
                        //Invoice invoice= service.getLastInvoice();
                        Partner partner=(Partner) view.getjCB_CustomerSelector().getSelectedItem();
                        ServicePartner partnerService= new ServicePartner();
                        invoiceObject.setPartner(partner);
                        invoiceObject.setPartnerAddress(partnerService.gePartnerAddressToXML(partner.getId()));
                        invoiceObject.setInvoice(invoicePrint);
                        
                        int partnerId= partner.getId();
                        int departmentId=0;
                        //service.newInvoiceAddress(view, invoice.getId(), partnerId, departmentId);
                        //System.out.println("Számla azonosító: "+invoice.getVoucherNumber() + "Partner: "+ partnerId +"Partner telephely: "+ departmentId);
                        //loop through JTable and sava InvoiceItems OneByeOne
                        ArrayList<InvoiceItem>list = new ArrayList<>();
                        for (int i = 0; i < view.getjTab_InvoiceItem().getRowCount(); i++) {
                            if (view.getjTab_InvoiceItem().getValueAt(i, 5) != null) {
                                int itemNumber=i+1;
                                Tax vatN = (Tax) view.getjTab_InvoiceItem().getValueAt(i, 3);
                                int vatId = vatN.getId();
                                int vatRate=vatN.getRate();
                                String descriptionDefault=(String)view.getjTab_InvoiceItem().getValueAt(i, 0);
                                String measureDefault=(String)view.getjTab_InvoiceItem().getValueAt(i, 2);
                                int quantity=(Integer)view.getjTab_InvoiceItem().getValueAt(i, 1);
                                double unitPrice=0;
                                double amount=0;
                                double taxItem=0;
                                double totalItem=0;
                                double currencyUnitPrice=0;
                                double currencyAmount=0;
                                double currencyTax=0;
                                double currencyTotal=0;
                                try {
                                    unitPrice= nf.parse(view.getjTab_InvoiceItem().getValueAt(i, 4).toString()).doubleValue();
                                    currencyUnitPrice=unitPrice*currencyRate;
                                    amount= nf.parse(view.getjTab_InvoiceItem().getValueAt(i, 5).toString()).doubleValue();
                                    currencyAmount=amount*currencyRate;
                                    if (vatRate>0) {
                                        taxItem=amount*vatRate/100;
                                        currencyTax=taxItem*currencyRate;
                                    }
                                    totalItem=amount+taxItem;
                                    currencyTotal=totalItem*currencyRate;
                                } catch (ParseException ex) {
                                    Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               //service.newInvoiceItem(invoice.getId(),itemNumber, vatId,descriptionDefault, measureDefault, quantity , unitPrice, amount,taxItem, totalItem, currencyUnitPrice, currencyAmount, currencyTax, currencyTotal );
                               InvoiceItem invoiceItem= new InvoiceItem();
                               invoiceItem.setItemNumber(itemNumber);
                               invoiceItem.setTaxType(vatN);
                               invoiceItem.setDescriptionDefault(descriptionDefault);
                               invoiceItem.setMeasureDefault(measureDefault);
                               invoiceItem.setQuantity(quantity);
                               invoiceItem.setUnitPrice(unitPrice);
                               invoiceItem.setAmount(amount);
                               invoiceItem.setTax(taxItem);
                               invoiceItem.setTotal(totalItem);
                               invoiceItem.setCurrencyUnitPrice(currencyUnitPrice);
                               invoiceItem.setCurrencyAmount(currencyAmount);
                               invoiceItem.setCurrencyTax(currencyTax);
                               invoiceItem.setCurrencyTotal(currencyTotal);
                               list.add(invoiceItem);
                               
                               //System.out.println("Számla azonosító: "+invoice.getVoucherNumber() + "Tétel sorszám: "+itemNumber + "Tétel áfakód: "+vatId +"Tétel megnevezés: "+descriptionDefault+ "Mennyiségi egység: "+measureDefault+"Mennyiség: "+ quantity + "Egységár: "+unitPrice+"Nettó összeg: "+ amount + "Áfa: "+taxItem +"Bruttó: "+totalItem+ "Egységár HUF-ban: "+currencyUnitPrice + "Nettó ár HUF-ban: "+currencyAmount + "ÁFA HUF-ban: "+currencyTax +"Bruttó HUF-ban: "+ currencyTotal );
                                
                                
                            }
                        }
                        invoiceObject.setInvoiceItems(list);
                        System.out.println(invoiceObject.toString());
                         try {  
                            String XML= invoiceObject.getInvoice().getVoucherNumber();
                            String XMLname=XML.replace("/", "_");
                            FileWriter file= new FileWriter("src\\main\\java\\XML\\"+XMLname+".xml",StandardCharsets.UTF_8);
                            //BufferedWriter br= new BufferedWriter(file);
                            
                            JAXBContext contextObj =JAXBContext.newInstance(InvoiceObject.class);
                            Marshaller marshallerObj = contextObj.createMarshaller();
                            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
                            
                            marshallerObj.marshal(invoiceObject, file);  
                            file.close();
                            File xml= new File("src\\main\\java\\XML\\"+XMLname+".xml");
                            File xsl= new File("src\\main\\java\\XSD\\standard.xsl");
                            
                            ServiceXmlToPdf sxp= new ServiceXmlToPdf(xml, xsl,XMLname );
                            sxp.XmlToPdf();
                            
                                //Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler"+"./Invoices/PrintPreview.pdf");
                                File printpreview= new File("./Invoices/PrintPreview.pdf");
                                Desktop.getDesktop().open(printpreview);
                        } catch (JAXBException  ex) {
                            Logger.getLogger(ServiceInvoiceObjectToXML.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ControllerInvoice.class.getName()).log(Level.SEVERE, null, ex);
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

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    @Override
    public void updateData(int id) {
        System.out.println(id);
        this.setInvoiceFileId(id);
        loadAllInvoiceFileBankAccountComboBox(view.getjCB_InvoiceFileBankAccount(), id);
        view.getjCB_InvoiceFileBankAccount().setSelectedIndex(0);
        loadAllInvoiceFileCurrencyComboBox(view.getjCB_InvoiceFileCurrency(), id);
        view.getjCB_InvoiceFileCurrency().setSelectedIndex(0);
    }

    public void invoiceItemModel(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        table.revalidate();
        model.getColumnCount();
        Object[] row = new Object[model.getColumnCount()];
        for (int i = 0; i < model.getColumnCount(); i++) {
            row[0] = i;
            row[1] = "";
            row[2] = "";
            row[3] = "";
            row[4] = "";
            row[5] = "";
            row[6] = "";

            model.addRow(row);

        }

    }

    public void setUpComboBoxToJtable(JTable table, TableColumn col) {
        ServiceTax sc2 = new ServiceTax();
        ArrayList<Tax> list2 = new ArrayList<>();
        list2 = sc2.getAllTaxCode();
        JComboBox box2 = new JComboBox();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) box2.getModel();
        box2.removeAllItems();
        box2.revalidate();
        model2.addAll(list2);

        box2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Tax) {
                    Tax tax = (Tax) value;
                    StringBuilder sb = new StringBuilder();
                    sb.append(tax.getName());
                    setText(sb.toString());
                }
                return this;
            }

        });

        col.setCellEditor(new DefaultCellEditor(box2));

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
     

}
