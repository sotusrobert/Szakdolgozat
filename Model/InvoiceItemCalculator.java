
package InvoiceProgram.Model;

public class InvoiceItemCalculator {
       private int row;
       private int qty;
       private int price;
       private int netprice;

    public InvoiceItemCalculator(int row, int qty, int price) {
        this.row = row;
        this.qty = qty;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNetprice() {
        return netprice;
    }

    public void setNetprice(int netprice) {
        this.netprice = netprice;
    }
     
    
       
       
       
}
