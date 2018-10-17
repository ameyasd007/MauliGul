package ameya.com.mauligul.database.models;

import java.sql.Timestamp;
import java.util.Date;
import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by Admin on 06-Jul-18.
 */

public class SalesOrderBill implements Parcelable{
    private String billNumber;
    private Date date;
    private Timestamp dateTime;
    private String customerName;
    private String customerID;
    private String shopName;
    private String item;
    private String itemID;
    private int quantity;
    private int rate;
    private int amount;
    private int total;
    private int discountRate;
    private int discountAmount;
    private int taxRate;
    private int taxAmount;
    private String note;
    private String remark;
    private int paidAmount;
    private int pendingOrgder;

    public SalesOrderBill() {
    }

    protected SalesOrderBill(Parcel in) {
        billNumber = in.readString();
        customerName = in.readString();
        customerID = in.readString();
        shopName = in.readString();
        item = in.readString();
        itemID = in.readString();
        quantity = in.readInt();
        rate = in.readInt();
        amount = in.readInt();
        total = in.readInt();
        discountRate = in.readInt();
        discountAmount = in.readInt();
        taxRate = in.readInt();
        taxAmount = in.readInt();
        note = in.readString();
        remark = in.readString();
    }

    public static final Creator<SalesOrderBill> CREATOR = new Creator<SalesOrderBill>() {
        @Override
        public SalesOrderBill createFromParcel(Parcel in) {
            return new SalesOrderBill(in);
        }

        @Override
        public SalesOrderBill[] newArray(int size) {
            return new SalesOrderBill[size];
        }
    };

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billNumber);
        dest.writeString(customerName);
        dest.writeString(customerID);
        dest.writeString(shopName);
        dest.writeString(item);
        dest.writeString(itemID);
        dest.writeInt(quantity);
        dest.writeInt(rate);
        dest.writeInt(amount);
        dest.writeInt(total);
        dest.writeInt(discountRate);
        dest.writeInt(discountAmount);
        dest.writeInt(taxRate);
        dest.writeInt(taxAmount);
        dest.writeString(note);
        dest.writeString(remark);
    }
}
