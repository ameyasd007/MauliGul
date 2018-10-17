package ameya.com.mauligul.database.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 11-Jul-18.
 */

public class Item implements Parcelable{
    private String itemName;
    private int rate;
    private int stockQuantity;

    public Item(String itemName, int stockQuantity, int rate) {
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.rate = rate;
    }

    public Item(Item item){
        itemName = item.getItemName();
        rate = item.getRate();
        stockQuantity = item.getStockQuantity();
    }

    protected Item(Parcel in) {
        itemName = in.readString();
        rate = in.readInt();
        stockQuantity = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeInt(rate);
        dest.writeInt(stockQuantity);
    }
}
