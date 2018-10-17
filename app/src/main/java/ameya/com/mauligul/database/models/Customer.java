package ameya.com.mauligul.database.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 06-Jul-18.
 */

public class Customer implements Parcelable{
    private String customerID;
    private String customerName;
    private String shopName;
    private String address;
    private String contactNumber;
    private int amountPending;
    private int totalSale;

    public Customer(String customerName, String shopName, String address, String contactNumber, int amountPending) {
        this.customerName = customerName;
        this.shopName = shopName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.amountPending = amountPending;
    }

    protected Customer(Parcel in) {
        customerID = in.readString();
        customerName = in.readString();
        shopName = in.readString();
        address = in.readString();
        contactNumber = in.readString();
        amountPending = in.readInt();
        totalSale = in.readInt();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getAmountPending() {
        return amountPending;
    }

    public void setAmountPending(int amountPending) {
        this.amountPending = amountPending;
    }

    public int getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerID);
        dest.writeString(customerName);
        dest.writeString(shopName);
        dest.writeString(address);
        dest.writeString(contactNumber);
        dest.writeInt(amountPending);
        dest.writeInt(totalSale);
    }
}
