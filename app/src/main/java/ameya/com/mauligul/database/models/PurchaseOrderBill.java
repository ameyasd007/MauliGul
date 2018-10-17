package ameya.com.mauligul.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.Date;

public class PurchaseOrderBill implements Parcelable {

    private String billNumber;
    private Date date;
    private Timestamp dateTime;
    private String supplierName;
    private String supplierID;
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

    protected PurchaseOrderBill(Parcel in) {
        billNumber = in.readString();
        supplierName = in.readString();
        supplierID = in.readString();
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
        paidAmount = in.readInt();
        pendingOrgder = in.readInt();
    }

    public static final Creator<PurchaseOrderBill> CREATOR = new Creator<PurchaseOrderBill>() {
        @Override
        public PurchaseOrderBill createFromParcel(Parcel in) {
            return new PurchaseOrderBill(in);
        }

        @Override
        public PurchaseOrderBill[] newArray(int size) {
            return new PurchaseOrderBill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billNumber);
        dest.writeString(supplierName);
        dest.writeString(supplierID);
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
        dest.writeInt(paidAmount);
        dest.writeInt(pendingOrgder);
    }
}
